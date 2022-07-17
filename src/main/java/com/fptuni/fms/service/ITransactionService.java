package com.fptuni.fms.service;

import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.TransactionShared;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    TransactionShared getTransactionSharedByWalletID(Integer walletID);

    BigDecimal getCustomerAmount(TransactionShared transactionShared);
    List<TransactionShared> getTransactionSharedByStore(Store store);
}
