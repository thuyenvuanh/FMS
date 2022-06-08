package com.fptuni.fms.dao;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Anh Quoc
 */

public interface IStoreDAO extends GenericDAO<Store> {

    public Store getStore(String name);

    public List<Store> getStore();

    public Integer insertStore(Store store);

    public void updateStore(int id, String name, int AccountID);
}
