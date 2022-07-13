package com.fptuni.fms.service;

import com.fptuni.fms.model.TransactionShared;

import java.math.BigDecimal;

public interface ITransactionService {

    int insertNewTransaction(TransactionShared transaction);

    TransactionShared getLatestTransactionSharedByWalletID(Integer walletID);

    TransactionShared getLatestTransaction();

    BigDecimal getCustomerBalance(TransactionShared transactionShared);

}
