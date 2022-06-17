package com.fptuni.fms.dao;

import com.fptuni.fms.model.Store;

import java.util.List;

/**
 *
 * @author Anh Quoc
 */

public interface IStoreDAO extends GenericDAO<Store> {

//    Store getStore(String name);

    List<Store> getStore();

    Integer insertStore(Store store);

    void updateStore(int id, String name, int AccountID);
}
