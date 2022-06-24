package com.fptuni.fms.service;

import com.fptuni.fms.model.TransactionShared;

import java.math.BigDecimal;

public interface ITransactionService {
    TransactionShared getTransactionSharedByWalletID(Integer walletID);

    TransactionShared getLatestTransaction();

    BigDecimal getCustomerAmount(TransactionShared transactionShared);
}
