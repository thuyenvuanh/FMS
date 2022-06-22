package com.fptuni.fms.dao;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.Pageable;

import java.util.List;

/**
 *
 * @author Anh Quoc
 */
public interface IStoreDAO extends GenericDAO<Store> {

    // Store getStore(String name);

    List<Store> getStores();

    Store getStore(int id);
  
    Integer insertStore(Store store);
  
    Store getStoreByAccount(Account account);


    boolean updateStore(int id, String name, int AccountID);

    List<Store> getListStore(Pageable pageable);

    int count();

    boolean Delete(int id);
}
