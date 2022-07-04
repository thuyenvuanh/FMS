package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.*;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Payment;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.service.IPaymentService;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class PaymentService implements IPaymentService {

    private final WalletDAO walletDAO = new WalletDAO();
    private final TransactionSharedDAO transactionSharedDAO = new TransactionSharedDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    private final TransactionService transactionService = new TransactionService();
    private final PaymentDAO paymentDAO = new PaymentDAO();

    @Override
    public boolean makePayment(HttpServletRequest request) {
        //check if the wallet exist and have enough money
        //if fulfill the criteria write order, orderDetail, payment, and transaction to the database
        //else return to the cashier screen and notify error message
        Wallet wallet = walletDAO.getWalletWithID(Integer.parseInt(request.getParameter("walletID")));
        Orders orders = (Orders) request.getSession().getAttribute("order");
        try {
            if (wallet == null) throw new Exception("Wallet not found");
            if (orders == null) throw new Exception("Order not found");
            //get the latest transaction of the wallet
            TransactionShared latestTransactionSharedByWalletID = transactionService.getLatestTransactionSharedByWalletID(wallet.getId());
            if (latestTransactionSharedByWalletID != null) {
                BigDecimal balance = transactionService.getCustomerBalance(latestTransactionSharedByWalletID);
                if (balance.compareTo(orders.getTotal()) >= 0) {
                    int orderID = orderDAO.insertOrder(orders);
                    orders.setId(orderID);
                    orders.getOrderDetailList().forEach(
                            orderDetail -> {
                                orderDetail.setOrders(orders);
                                orderDetailDAO.createOrderDetail(orderDetail);
                            }
                    );
                    Payment payment = new Payment(orders.getTotal(), orders);
                    payment.setId(paymentDAO.insertPayment(payment));
                    TransactionShared latestTransaction = transactionService.getLatestTransaction();
                    String previousHash = latestTransaction == null
                            ? "00000000000000000000000000000000"
                            : latestTransaction.getHashValue();
                    TransactionShared newTransaction = new TransactionShared(payment.getAmount(),
                            previousHash, null, balance, orders.getCreatedDate(),
                            true, null, payment, wallet);
                    newTransaction.setHashValue(SecurityUtils.createHash(newTransaction.toString(), String.valueOf(newTransaction.getCreatedDate().getTime())));
                    transactionSharedDAO.insertTransaction(newTransaction);
                    return true;
                } else throw new Exception("Balance insufficient");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
