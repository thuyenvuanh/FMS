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

//    Store getStore(String name);
    Store getStoreByAccount(Account account);

    List<Store> getStore();

    Store getStore(int id);

    Integer insertStore(Store store);

    boolean updateStore(int id, String name);

    List<Store> getListStore(Pageable pageable);

    List<Store> search(Pageable pageable, int isDelete, String name, String storeManager);

    int count();

    boolean Delete(int id);
}
