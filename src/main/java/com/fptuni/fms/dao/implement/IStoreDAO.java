package com.fptuni.fms.dao;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;

/**
 *
 * @author Anh Quoc
 */

public interface IStoreDAO extends GenericDAO<Store> {

    Store getStore(String name);
}
