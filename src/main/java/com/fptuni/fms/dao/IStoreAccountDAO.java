package com.fptuni.fms.dao;

import com.fptuni.fms.model.Account;

import java.util.List;

public interface IStoreAccountDAO {

    List<Account> getAccountsByStoreID(int storeID);

    int getStoreIDByAccountID(int accountID);

}
