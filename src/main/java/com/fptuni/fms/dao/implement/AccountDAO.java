package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IAccountDAO;
import com.fptuni.fms.mapper.AccountMapper;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import java.util.List;

/**
 *
 * @author NhatTan
 */
public abstract class AccountDAO extends AbstractDAO<Account> implements IAccountDAO {

    @Override
    public List<Account> getListAccount() {
        String sql = "SELECT ID, Username, Fullname, RoleID FROM dbo.Account";
        List<Account> acc = query(sql, new AccountMapper());
        return acc.isEmpty() ? null : acc;
    }

    @Override
    public int Create(String Username, String Password, String Fullname, int RoleID) {
        String sql = "INSERT INTO dbo.Account(Username, Password, Fullname, RoleID) VALUES (?,?,?,?)";
        String check = "SELECT ID, Username, Fullname, RoleID FROM dbo.Account WHERE Username=?";
        List<Account> acc = query(check, new AccountMapper(), Username);
        return acc.isEmpty() ? insert(sql, Username, Password, Fullname, new Role(RoleID)) : null;
    }

    @Override
    public boolean Delete(String username) {
        String sql = "DELETE FROM news WHERE Username = ?";
        return update(sql, username);
    }

    @Override
    public boolean Update(String Username, String Password, String Fullname, int RoleID) {
        String sql = "UPDATE dbo.Account SET Password=?, Fullname=?, RoleID=? WHERE Username=?";
        return update(sql, Password, Fullname, new Role(RoleID), Username);
    }

    @Override
    public Account checkLogin(String username, String password) {
        String sql = "SELECT ID, Username, Fullname, RoleID FROM dbo.Account WHERE Username=? AND Password=?";
        List<Account> acc = query(sql, new AccountMapper(), username, password);
        return acc.isEmpty() ? null : acc.get(0);
    }

}
