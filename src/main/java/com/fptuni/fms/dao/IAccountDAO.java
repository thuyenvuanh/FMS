package com.fptuni.fms.dao;

import com.fptuni.fms.model.Account;
import java.util.List;

/**
 *
 * @author NhatTan
 */
public interface IAccountDAO extends GenericDAO<Account> {

    public List<Account> getListAccount();

    public int Create(String Username, String Password, String Fullname, int RoleID);

    public boolean Delete(String username);

    public boolean Update(String Username, String Password, String Fullname, int RoleID);

    public Account checkLogin(String username, String password);

}
