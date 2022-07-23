package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.*;
import com.fptuni.fms.model.*;
import com.fptuni.fms.service.IPaymentService;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class PaymentService implements IPaymentService {

    private final WalletDAO walletDAO = new WalletDAO();
    private final TransactionSharedDAO transactionSharedDAO = new TransactionSharedDAO();
    private final OrderDAO orderDAO = new OrderDAO();
    private final OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    private final TransactionService transactionService = new TransactionService();
    private final PaymentDAO paymentDAO = new PaymentDAO();

    private final CustomerDAO customerDAO = new CustomerDAO();

    private final MoneyTransactionDAO moneyTransactionDAO = new MoneyTransactionDAO();

    @Override
    public boolean makePayment(HttpServletRequest request) {
        //check if the wallet exist and have enough money
        //if fulfill the criteria write order, orderDetail, payment, and transaction to the database
        //else return to the cashier screen and notify error message
        try {
            Wallet wallet = walletDAO.getWalletWithID(Integer.parseInt(request.getParameter("walletID")));
            Orders orders = (Orders) request.getSession().getAttribute("order");
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
            } else throw new Exception("Balance insufficient");
        } catch (Exception e) {
            request.getSession().setAttribute("message", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addMoney(HttpServletRequest request) {

        BigDecimal amount = BigDecimal.valueOf(Integer.parseInt(request.getParameter("amount").replaceAll("[^\\d.]", "")));
        Wallet wallet = walletDAO.getWalletWithID(Integer.parseInt(request.getParameter("walletID")));
        Customer customer = customerDAO.getByPhoneNum(request.getParameter("customerPhone"));

        try {
            if (wallet == null) throw new Exception("Wallet not found");
            if (amount == null) throw new Exception("Amount not found");
            if (customer == null) throw new Exception("Customer not found");

            TransactionShared latestTransactionSharedByWalletID = transactionService.getLatestTransactionSharedByWalletID(wallet.getId());
            MoneyTransaction moneyTransaction = new MoneyTransaction(wallet.getId(), amount, true, new Date(), new Counter(1), customer);
            if (latestTransactionSharedByWalletID != null) {
                BigDecimal balance = transactionService.getCustomerBalance(latestTransactionSharedByWalletID);

                moneyTransaction.setId(moneyTransactionDAO.createMoneyTransaction(moneyTransaction));
                TransactionShared latestTransaction = transactionService.getLatestTransaction();
                String previousHash = latestTransaction == null
                        ? "00000000000000000000000000000000"
                        : latestTransaction.getHashValue();
                TransactionShared newTransaction = new TransactionShared(amount,
                        previousHash, null, balance, moneyTransaction.getCreatedDate(),
                        true, moneyTransaction, null, wallet);
                newTransaction.setHashValue(SecurityUtils.createHash(newTransaction.toString(), String.valueOf(newTransaction.getCreatedDate().getTime())));
                transactionSharedDAO.insertTransaction(newTransaction);
            } else {
                moneyTransaction.setId(moneyTransactionDAO.createMoneyTransaction(moneyTransaction));
                TransactionShared latestTransaction = transactionService.getLatestTransaction();
                String previousHash = latestTransaction == null
                        ? "00000000000000000000000000000000"
                        : latestTransaction.getHashValue();
                TransactionShared newTransaction = new TransactionShared(amount,
                        previousHash, null, BigDecimal.ZERO, moneyTransaction.getCreatedDate(),
                        true, moneyTransaction, null, wallet);
                newTransaction.setHashValue(SecurityUtils.createHash(newTransaction.toString(), String.valueOf(newTransaction.getCreatedDate().getTime())));
                transactionSharedDAO.insertTransaction(newTransaction);
            }
            request.setAttribute("phoneNumber", request.getParameter("customerPhone"));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean withDraw(HttpServletRequest request) {
        return false;
    }

    @Override
    public List<Payment> getPaymentsByOrderID(int orderID) {
        return paymentDAO.getPaymentsByOrderId(orderID);
    }
}
