package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.TransactionSharedDAO;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.service.ITransactionService;
import com.fptuni.fms.utils.SecurityUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class TransactionService implements ITransactionService {
    @Override
    public TransactionShared getTransactionSharedByWalletID(Integer walletID) {
        TransactionShared transactionShared = null;
        TransactionSharedDAO transactionSharedDAO = new TransactionSharedDAO();
        if(walletID != null){
            transactionShared = transactionSharedDAO.getLatestTransactionOf(walletID);
            if(transactionShared != null){
                String s = transactionShared.toString();
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
    public BigDecimal getCustomerAmount(TransactionShared transactionShared) {
        BigDecimal amount = BigDecimal.ZERO;
        amount = amount.add(transactionShared.getAmount());
        amount = amount.add(transactionShared.getPreviousBalance());
        return amount;
    }
}
