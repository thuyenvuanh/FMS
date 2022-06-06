package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.model.Store;
import com.fptuni.mapper.StoreMapper;

import java.util.List;

/**
 *
 * @author Anh Quoc
 */
public class StoreDAO extends AbstractDAO<Store> implements IStoreDAO {

    @Override
    public Store getStore(String Name) {
        String sql = "SELECT Store.ID, Name, Store.AccountID, Account.FullName from Store\n" +
                "Join Account on Store.AccountID = Account.ID\n" +
                "Where upper(Name) = upper('?');";
        List<Store> stores = query(sql, new StoreMapper(), Name);
        return stores.isEmpty() ? null : stores.get(0);
    }
}