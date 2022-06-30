package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.*;
import com.fptuni.fms.model.*;
import com.fptuni.fms.service.IPaymentService;
import com.fptuni.fms.utils.SecurityUtils;
import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PaymentService implements IPaymentService {

    private final WalletDAO walletDAO = new WalletDAO();
    private final TransactionSharedDAO transactionSharedDAO = new TransactionSharedDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    private final TransactionService transactionService = new TransactionService();
    private final PaymentDAO paymentDAO = new PaymentDAO();

    @Override
    public void makePayment(HttpServletRequest request) {
        //check if the wallet exist and have enough money
        //if fulfill the criteria write order, orderDetail, payment, and transaction to the database
        //else return to the cashier screen and notify error message
        Wallet wallet = walletDAO.getWalletWithID(Integer.parseInt(request.getParameter("walletID")));
        Orders orders = (Orders) request.getSession().getAttribute("order");
        try {
            if (wallet == null) throw new Exception("Wallet not found");
            if (orders == null) throw new Exception("Order not found");
            TransactionShared temp = transactionService.getTransactionSharedByWalletID(wallet.getId());
            if (temp != null) {
                BigDecimal balance = transactionService.getCustomerAmount(temp);
                if (balance.compareTo(orders.getTotal()) >= 0) {
                    //all criteria meet
                    //create order and order detail, payment
                    Payment payment = new Payment(0, orders.getTotal());
                    payment.setOrderID(orders);
                    TransactionShared latest = transactionService.getLatestTransaction();
                    String previousHash = latest == null ? "00000000000000000000000000000000" : latest.getHashValue();
                    TransactionShared newTransaction = new TransactionShared(payment.getAmount(),
                    previousHash, null, temp.getPreviousBalance(), orders.getCreatedDate(),
                            true, null, payment, wallet);
                    //insert order, orderDetail, payment and transaction to database;
                    orderDAO.insertOrder(orders);
                    orders.getOrderDetailList().forEach(orderDetail -> orderDetailDAO.createOrderDetail(orderDetail));
                    paymentDAO.insertPayment(payment);
                    transactionSharedDAO.insertTransaction(newTransaction);
                } else throw new Exception("Balance insufficient");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
