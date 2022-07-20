package com.fptuni.fms.service;

import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.TransactionShared;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {

    int insertNewTransaction(TransactionShared transaction);

    TransactionShared getLatestTransactionSharedByWalletID(Integer walletID);

    TransactionShared getLatestTransaction();

    BigDecimal getCustomerBalance(TransactionShared transactionShared);


    List<TransactionShared> getTransactionSharedByStore(Store store);
}
