package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IAccountDAO;
import com.fptuni.fms.mapper.AccountMapper;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import com.fptuni.fms.utils.SecurityUtils;

import java.util.List;

/**
 * @author NhatTan
 */
public class AccountDAO extends AbstractDAO<Account> implements IAccountDAO {

    @Override
    public List<Account> getListAccount() {
        String sql = "SELECT ID, Username, Fullname, RoleID FROM dbo.Account WHERE IsDeleted = 0";
        List<Account> acc = query(sql, new AccountMapper());
        return acc.isEmpty() ? null : acc;
    }

    @Override
    public int Create(String Username, String Password, String Fullname, int RoleID) {
        String sql = "INSERT INTO dbo.Account(Username, Password, Fullname, RoleID) VALUES (?,?,?,?)";
        return insert(sql, Username, Password, Fullname, RoleID);
    }

    @Override
    public boolean Delete(String username) {
        String sql = "DELETE FROM news WHERE Username = ? AND IsDeleted = 0";
        return update(sql, username);
    }

    @Override
    public boolean Update(String Username, String Password, String Fullname, int RoleID) {
        String sql = "UPDATE dbo.Account SET Password=?, Fullname=?, RoleID=? WHERE Username=? AND IsDeleted = 0";
        return update(sql, Password, Fullname, RoleID, Username);
    }

    @Override
    public Account checkLogin(String username, String password) {
        try {
            String sql = "SELECT ID, Username, FullName, RoleID FROM dbo.Account WHERE Username=? AND Password=? AND IsDeleted = 0";
            String hashPassword = SecurityUtils.createHash(username, password);
            List<Account> acc = query(sql, new AccountMapper(), username, hashPassword);
            return acc.isEmpty() ? null : acc.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
