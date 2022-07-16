package com.fptuni.fms.dao;

import com.fptuni.fms.model.MoneyTransaction;

import java.util.List;

public interface IMoneyTransactionDAO {
    List<MoneyTransaction> getAllMoneyTransaction();

    int createMoneyTransaction(MoneyTransaction moneyTransaction);
}
