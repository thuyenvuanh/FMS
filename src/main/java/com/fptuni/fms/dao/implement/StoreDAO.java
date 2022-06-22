package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.mapper.StoreMapper;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.mapper.ProductMapper;
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
    @Override
    public Store getStoreByAccount(Account account) {
        String sql = "SELECT ID, Name FROM Store WHERE AccountID = ? AND IsDeleted = 0";
        List<Store> list = query(sql, mapper, account.getId());
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    @Override
    public List<Store> getStore() {
        String sql = "SELECT Store.ID, Name, Store.AccountID, Account.FullName from Store\n"
                + "Join Account on Store.AccountID = Account.ID\n";
        List<Product> products = query(sql, new ProductMapper());
        List<Store> stores = query(sql, new StoreMapper());
        return stores.isEmpty() ? null : stores;
    }

    @Override
    public Store getStore(int id) {
        String sql = "SELECT Store.ID, Name, Store.AccountID, Account.FullName FROM Store Join Account on Store.AccountID = Account.ID WHERE Store.ID = ?";
        List<Store> listStore = query(sql, new StoreMapper(), id);
        return listStore == null ? null : listStore.get(0);
    }

    @Override
    public Integer insertStore(Store store) {
        String sql = "INSERT INTO Store VALUES(?,?,0)";
        return insert(sql, store.getName(), store.getAccountID().getId());
    }

    @Override
    public boolean updateStore(int id, String Name) {
        String sql = "UPDATE Store SET Name=? Where ID = ?";
        return update(sql, Name, id);
    }

    @Override
    public List<Store> getListStore(Pageable pageable) {
        // Sort theo field xong moi paging
        // Neu chon sortField khac thi cac Product moi trang se thay doi
        // Vi du: sortField = ID ==> list ID ASC ==> paging
        String sql = "SELECT * FROM \n"
                + "(SELECT Store.ID, Name, Store.AccountID, Account.FullName \n"
                + "FROM Store Join Account on Store.AccountID = Account.ID \n"
                + "WHERE Store.IsDeleted = 0\n";
        String orderBy;
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY " + pageable.getSorter().getSortField() + "  " + orderBy;
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n"
                    + " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY ) AS A \n";
        }
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY A." + pageable.getSorter().getSortField() + " " + orderBy;
        }

        List<Store> listStore = query(sql, new StoreMapper());
        return listStore;
    }

    @Override
    public List<Store> search(Pageable pageable, int isDelete, String name, String storeManager) {
        // Sort theo field xong moi paging
        // Neu chon sortField khac thi cac Product moi trang se thay doi
        // Vi du: sortField = ID ==> list ID ASC ==> paging
        String sql = "SELECT * FROM \n"
                + "(SELECT Store.ID, Name, Store.AccountID, Account.FullName \n"
                + "FROM Store Join Account on Store.AccountID = Account.ID \n"
                + "WHERE Store.IsDeleted = ? AND Name LIKE ? AND Account.FullName LIKE ?\n";
        String orderBy;
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY " + pageable.getSorter().getSortField() + "  " + orderBy;
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n"
                    + " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY ) AS A \n";
        }
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY A." + pageable.getSorter().getSortField() + " " + orderBy;
        }
        List<Store> listStore = query(sql, new StoreMapper(), isDelete, "%" + name + "%", "%" + storeManager + "%");
        return listStore;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(ID) FROM dbo.Store";
        return count(sql);
    }

    @Override
    public boolean Delete(int id) {
        String sql = "UPDATE dbo.Store SET IsDeleted=1 WHERE ID=? AND IsDeleted = 0";
        return update(sql, id);
    }
}
