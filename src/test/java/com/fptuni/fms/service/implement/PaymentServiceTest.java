package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.IWalletDAO;
import com.fptuni.fms.dao.implement.MoneyTransactionDAO;
import com.fptuni.fms.dao.implement.TransactionSharedDAO;
import com.fptuni.fms.model.MoneyTransaction;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.utils.SecurityUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

class PaymentServiceTest {

    @Test
    public void insertTest() {
        //transaction write service
        final MoneyTransactionDAO dao = new MoneyTransactionDAO();
        final TransactionSharedDAO trDao = new TransactionSharedDAO();
        List<MoneyTransaction> list = dao.getAllMoneyTransaction();
        list.forEach(moneyTransaction ->
        {
            TransactionShared transactionShared = new TransactionShared();
            transactionShared.setAmount(moneyTransaction.getAmount());
            transactionShared.setWalletID(new Wallet(moneyTransaction.getWalletID()));
            TransactionShared temp = trDao.getLatestTransaction();
            transactionShared.setPreviousHash(temp == null ? "00000000000000000000000000000000" : temp.getHashValue());
            TransactionShared prevTran = trDao.getLatestTransactionOf(moneyTransaction.getWalletID());
            BigDecimal prevBal = (prevTran == null) ? new BigDecimal(0) : new BigDecimal(0).add(prevTran.getPreviousBalance()).add(prevTran.getAmount());
            transactionShared.setPreviousBalance(prevBal);
            transactionShared.setCreatedDate(moneyTransaction.getCreatedDate());
            transactionShared.setStatus(moneyTransaction.getState());
            transactionShared.setMoneyTransactionID(moneyTransaction);
            transactionShared.setPaymentID(null);
            try {
                transactionShared.setHashValue(SecurityUtils.createHash(transactionShared.toString(), String.valueOf(transactionShared.getCreatedDate().getTime())));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
            trDao.insertTransaction(transactionShared);
        });
    }

}