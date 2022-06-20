package com.fptuni.fms.dao;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;

import java.util.List;

/**
 *
 * @author Anh Quoc
 */

public interface IStoreDAO extends GenericDAO<Store> {

//    Store getStore(String name);

    List<Store> getStores();

    Integer insertStore(Store store);
    Store getStoreByAccount(Account account);

    void updateStore(int id, String name, int AccountID);
}
