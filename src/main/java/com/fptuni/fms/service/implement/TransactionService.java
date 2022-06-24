package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.TransactionSharedDAO;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.service.ITransactionService;
import com.fptuni.fms.utils.SecurityUtils;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;

public class TransactionService implements ITransactionService {
    @Override
    public TransactionShared getTransactionSharedByWalletID(Integer walletID) {
        TransactionShared transactionShared = null;
        TransactionSharedDAO transactionSharedDAO = new TransactionSharedDAO();
        String hashString = "";
        if(walletID != null){
            transactionShared = transactionSharedDAO.getLatestTransactionOf(walletID);
            if(transactionShared != null){
                String s = parseToString(transactionShared);
                try {
                    boolean isValid = SecurityUtils.validateHash(s,
                            String.valueOf(transactionShared.getCreatedDate().getTime()),
                            transactionShared.getHashValue());
                    if(isValid){
                        return transactionShared;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public TransactionShared getLatestTransaction() {
        TransactionShared transactionShared = null;
        TransactionSharedDAO dao = new TransactionSharedDAO();
        transactionShared = dao.getLatestTransaction();
        if (transactionShared != null){
            String target = transactionShared.toString();
            String salt = String.valueOf(transactionShared.getCreatedDate().getTime());
            String compareString = transactionShared.getHashValue();
            try {
                if (SecurityUtils.validateHash(target, salt, compareString)){
                    return transactionShared;
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Cannot hash");
            } catch (InvalidKeySpecException e) {
                System.out.println("Invalid Key Spec");
            }
        }
        return null;
    }

    @Override
    public BigDecimal getCustomerAmount(TransactionShared transactionShared) {
        BigDecimal amount = BigDecimal.ZERO;
        amount = amount.add(transactionShared.getAmount());
        amount = amount.add(transactionShared.getPreviousBalance());
        return amount;
    }

    private String parseToString(TransactionShared transactionShared){
        String s = "";

        s += String.valueOf(transactionShared.getId())
            + String.valueOf(transactionShared.getAmount())
            + transactionShared.getPreviousHash()
            + String.valueOf(transactionShared.getPreviousBalance())
            + String.valueOf(transactionShared.getCreatedDate().getTime())
            + String.valueOf(transactionShared.getStatus())
            + String.valueOf(transactionShared.getMoneyTransactionID() == null ? ""
                : transactionShared.getMoneyTransactionID().getId())
            + String.valueOf(transactionShared.getPaymentID() == null ? ""
                : transactionShared.getPaymentID().getId())
            + String.valueOf(transactionShared.getWalletID() == null ? ""
                : transactionShared.getWalletID().getId());
        return s;
    }
}
