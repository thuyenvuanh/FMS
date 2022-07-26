package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.WalletDAO;
import com.fptuni.fms.model.MoneyTransaction;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.utils.SecurityUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class PaymentServiceTest {

    @Test
    public void insertTest() {
        //transaction write service
        final MoneyTransactionService mService = new MoneyTransactionService();
        final TransactionService trService = new TransactionService();
        List<MoneyTransaction> list = mService.getAll();
        for (MoneyTransaction moneyTransaction : list) {
            try {
                TransactionShared latestTransaction = trService.getLatestTransaction();
                TransactionShared previousTransactionOfWallet = trService.getLatestTransactionSharedByWalletID(moneyTransaction.getWalletID());
                Wallet wallet = new WalletDAO().getWalletWithID(moneyTransaction.getWalletID());
                String prevHash = latestTransaction == null ? "00000000000000000000000000000000" : latestTransaction.getHashValue();
                BigDecimal prevBal = BigDecimal.valueOf(0);
                if (previousTransactionOfWallet != null)
                    prevBal = prevBal.add(previousTransactionOfWallet.getPreviousBalance()).add(previousTransactionOfWallet.getAmount()).stripTrailingZeros();

                TransactionShared transactionShared = new TransactionShared(moneyTransaction.getAmount(),
                        prevHash,
                        null,
                        prevBal,
                        moneyTransaction.getCreatedDate(),
                        true,
                        moneyTransaction,
                        null,
                        wallet);
                System.out.println("String to be hash: " + transactionShared.toString());
                transactionShared.setHashValue(SecurityUtils.createHash(transactionShared.toString(), String.valueOf(transactionShared.getCreatedDate().getTime())));
                int transactionId = trService.insertNewTransaction(transactionShared);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}