package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.*;
import com.fptuni.fms.model.*;
import com.fptuni.fms.service.IMoneyTransactionService;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MoneyTransactionService implements IMoneyTransactionService {


    private final WalletDAO walletDAO = new WalletDAO();
    private final TransactionSharedDAO transactionSharedDAO = new TransactionSharedDAO();

    private final TransactionService transactionService = new TransactionService();

    private final CustomerDAO customerDAO = new CustomerDAO();

    private final MoneyTransactionDAO moneyTransactionDAO = new MoneyTransactionDAO();

    @Override
    public List<MoneyTransaction> getAll() {
        List<MoneyTransaction> list = moneyTransactionDAO.getAllMoneyTransaction();
        list.forEach(moneyTransaction -> moneyTransaction.setAmount(moneyTransaction.getAmount().stripTrailingZeros()));
        return list;
    }

    @Override
    public MoneyTransaction getByID(int mID) {
        MoneyTransaction result = moneyTransactionDAO.getByID(mID);
        result.setAmount(result.getAmount().stripTrailingZeros());
        return result;
    }

    @Override
    public List<MoneyTransaction> getListByWalletID(int wID) {
        List<MoneyTransaction> result = moneyTransactionDAO.getByWalletID(wID);
        result.forEach(moneyTransaction -> moneyTransaction.setAmount(moneyTransaction.getAmount().stripTrailingZeros()));
        return moneyTransactionDAO.getByWalletID(wID);
    }

    @Override
    public MoneyTransaction getLatestByWalletID(int wID) {
        List<MoneyTransaction> list = getListByWalletID(wID);
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    @Override
    public List<MoneyTransaction> getListByCustomerID(int cID) {
        List<MoneyTransaction> list = moneyTransactionDAO.getByCustomerID(cID);
        for (MoneyTransaction moneyTransaction : list) {
            moneyTransaction.setAmount(moneyTransaction.getAmount().stripTrailingZeros());
        }
        return list;
    }

    @Override
    public MoneyTransaction getLatestByCustomerID(int cID) {
        List<MoneyTransaction> list = getListByCustomerID(cID);
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    @Override
    public boolean addMoney(HttpServletRequest request, HttpSession session) {
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
            session.setAttribute("phoneNumber", request.getParameter("customerPhone"));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean withDraw(HttpServletRequest request, HttpSession session) {
        BigDecimal amount = BigDecimal.valueOf(Integer.parseInt(request.getParameter("amount").replaceAll("[^\\d.]", ""))).negate();
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
            session.setAttribute("phoneNumber", request.getParameter("customerPhone"));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
