package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IStoreAccountDAO;
import com.fptuni.fms.mapper.StoreAccountMapper;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.StoreAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreAccountDAO extends AbstractDAO<StoreAccount> implements IStoreAccountDAO {

    private static StoreAccountMapper storeAccountMapper = new StoreAccountMapper();

    @Override
    public List<Account> getAccountsByStoreID(int storeID) {
        String sql = "SELECT * FROM StoreAccount WHERE StoreID = ? AND IsDeleted = 0";
        List<StoreAccount> result = query(sql, storeAccountMapper, storeID);
        List<Account> accounts = result.
                stream().
                map(storeAccount -> new Account(storeAccount.getStoreAccountPK().getAccountID())).
                collect(Collectors.toList());
        return accounts;
    }

    @Override
    public int getStoreIDByAccountID(int accountID) {
        String sql = "SELECT * FROM StoreAccount WHERE AccountID = ? AND IsDeleted = 0";
        List<StoreAccount> result = query(sql, storeAccountMapper, accountID);
        return result == null ? null : result.get(0).getStoreAccountPK().getStoreID();
    }

    public int insert(Account account, Store store) {
        String sql = "INSERT INTO StoreAccount(AccountID, StoreID) VALUES (?,?)";
        return insert(sql, account.getId(), store.getId());
    }
}
