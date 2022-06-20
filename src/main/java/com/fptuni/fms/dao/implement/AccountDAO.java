package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IAccountDAO;
import com.fptuni.fms.mapper.AccountMapper;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.utils.SecurityUtils;

import java.util.List;

/**
 * @author NhatTan
 */
public class AccountDAO extends AbstractDAO<Account> implements IAccountDAO {

    private final AccountMapper mapper = new AccountMapper();

    @Override
    public Integer Create(String Username, String Password, String Fullname, int RoleID) {
        try {
            String sql = "INSERT INTO dbo.Account(Username, Password, Fullname, RoleID, IsDeleted) VALUES (?,?,?,?,0)";
            String hashPassword = SecurityUtils.createHash(Password, Username);
            return insert(sql, Username, hashPassword, Fullname, RoleID);
        } catch (Exception e) {
            System.out.println("Database query error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean Delete(String username) {
        String sql = "UPDATE dbo.Account SET IsDeleted=1 WHERE Username=? AND IsDeleted = 0";
        return update(sql, username);
    }

    @Override
    public boolean Update(String Username, String Password, String Fullname, int RoleID) {
        try {
            String sql = "UPDATE dbo.Account SET Password=?, Fullname=?, RoleID=? WHERE Username=? AND IsDeleted = 0";
            String hashPassword = SecurityUtils.createHash(Password, Username);
            return update(sql, Password, Fullname, RoleID, Username);
        } catch (Exception e) {
            System.out.println("Database query error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Account checkLogin(String username, String password) {
        try {
            String sql = "SELECT ID, Username, FullName, RoleID FROM dbo.Account WHERE Username=? AND Password=? AND IsDeleted = 0";
            String hashPassword = SecurityUtils.createHash(password, username);
            List<Account> acc = query(sql, mapper, username, hashPassword);
            return acc == null ? null : (acc.isEmpty() ? null : acc.get(0));
        } catch (Exception e) {
            System.out.println("Database query error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Account> getListAccount(Pageable pageable) {
        // Sort theo field xong moi paging
        // Neu chon sortField khac thi cac Product moi trang se thay doi
        // Vi du: sortField = ID ==> list ID ASC ==> paging
        String sql = "SELECT * FROM \n"
                + "(SELECT ID, Username, Fullname, RoleID \n"
                + "FROM dbo.Account WHERE IsDeleted = 0\n";
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

        List<Account> listAcc = query(sql, new AccountMapper());
        return listAcc;
    }

    @Override
    public Account getAccount(int id) {
        String sql = "SELECT ID, Username, Fullname, RoleID FROM dbo.Account WHERE ID = ?";
        List<Account> listAcc = query(sql, new AccountMapper(), id);
        return listAcc == null ? null : listAcc.get(0);
    }

    @Override
    public Account getAccountUpdate(int id) {
        String sql = "SELECT ID, Username, Password, Fullname, RoleID FROM dbo.Account WHERE ID = ? AND IsDeleted = 0";
        List<Account> listAcc = query(sql, new AccountMapper(), id);
        return listAcc == null ? null : listAcc.get(0);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(ID) FROM dbo.Account";
        return count(sql);
    }
}
