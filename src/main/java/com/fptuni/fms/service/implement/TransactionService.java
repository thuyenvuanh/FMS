package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.TransactionSharedDAO;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.service.ITransactionService;
import com.fptuni.fms.utils.SecurityUtils;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TransactionService implements ITransactionService {


    private final TransactionSharedDAO dao = new TransactionSharedDAO();

    @Override
    public int insertNewTransaction(TransactionShared transaction) {
        return dao.insertTransaction(transaction);
    }

    @Override
    public TransactionShared getLatestTransactionSharedByWalletID(Integer walletID) {
        TransactionShared transactionShared = null;
        // TransactionSharedDAO transactionSharedDAO = new TransactionSharedDAO();
        String hashString = "";
        if (walletID != null) {
            transactionShared = dao.getLatestTransactionOf(walletID);
            if (transactionShared != null) {
                try {
                    transactionShared.setAmount(transactionShared.getAmount().stripTrailingZeros());
                    transactionShared.setPreviousBalance(transactionShared.getPreviousBalance().stripTrailingZeros());
                    String target = transactionShared.toString();
                    String salt = String.valueOf(transactionShared.getCreatedDate().getTime());
                    String compareString = transactionShared.getHashValue();
                    boolean isValid = SecurityUtils.validateHash(target, salt, compareString);
                    if (isValid) {
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
        transactionShared = dao.getLatestTransaction();
        if (transactionShared != null) {
            transactionShared.setPreviousBalance(transactionShared.getPreviousBalance().stripTrailingZeros());
            transactionShared.setAmount(transactionShared.getAmount().stripTrailingZeros());
            String target = transactionShared.toString();
            String salt = String.valueOf(transactionShared.getCreatedDate().getTime());
            String compareString = transactionShared.getHashValue();
            try {
                if (SecurityUtils.validateHash(target, salt, compareString)) {
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
    public BigDecimal getCustomerBalance(TransactionShared transactionShared) {
        BigDecimal amount = BigDecimal.ZERO.add(transactionShared.getPreviousBalance());
        // if payment == null => is moneyTransaction transaction for top up => add
        // amount
        // otherwise transaction for buy product => subtract amount
        if (transactionShared.getPaymentID() == null) {
            amount = amount.add(transactionShared.getAmount());
        } else {
            amount = amount.subtract(transactionShared.getAmount());
        }
        return amount.stripTrailingZeros();
    }

    public List<TransactionShared> getTransactionSharedByStore(Store store) {
        return dao.getTransactionSharedByStore(store);
    }
}
