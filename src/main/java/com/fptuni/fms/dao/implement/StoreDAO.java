package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.mapper.ProductMapper;
import com.fptuni.mapper.StoreMapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anh Quoc
 */
public class StoreDAO extends AbstractDAO<Store> implements IStoreDAO {

    private final StoreMapper mapper = new StoreMapper();

//    @Override
//    public Store getStore(String Name) { //get 1 store
//        String sql = "SELECT Store.ID, Name, Store.AccountID, Account.FullName from Store\n" +
//                "Join Account on Store.AccountID = Account.ID\n" +
//                "Where upper(Name) = upper('?');";
//        List<Store> stores = query(sql, new StoreMapper(), Name);
//        return stores.isEmpty() ? null : stores.get(0);
//    }

    public Store getStoreByAccount(Account account) {
        String sql = "SELECT ID, Name, AccountID FROM Store WHERE AccountID = ? AND IsDeleted = 0";
        List<Store> list = query(sql, mapper, account.getId());
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    @Override
    public List<Store> getStores(){
        String sql = "SELECT Store.ID, Name, Store.AccountID, Account.FullName from Store\n" +
                "Join Account on Store.AccountID = Account.ID\n";
        List<Product> products =query(sql, new ProductMapper());
        List<Store> stores = query(sql, new StoreMapper());
        return stores.isEmpty() ? null : stores;
    }

    @Override
    public Integer insertStore(Store store){
        String sql = "INSERT INTO Store\n" +
                "VALUES(?,?);";
        return insert(store.getName(),store.getAccountID());
    }

    @Override
    public void updateStore(int id, String Name,int AccountID){
        String sql="UPDATE Store\n" +
                "SET\n" +
                "Name=?,\n" +
                "AccountID=?\n" +
                "Where ID = ?;";
        update(Name, AccountID,id);
    }

}