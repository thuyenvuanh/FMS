package com.fptuni.fms.dao;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.paging.Pageable;
import java.util.List;

/**
 *
 * @author NhatTan
 */
public interface IAccountDAO extends GenericDAO<Account> {

    List<Account> getListAccount(Pageable pageable);

    List<Account> search(Pageable pageable, int isDelete, String username, String fullName, int roleId);

    Account getAccount(int id);

    Account getAccountUpdate(int id);

    Integer Create(String Username, String Password, String Fullname, int RoleID);

    boolean Delete(String username);

    boolean Update(String Username, String Password, String Fullname, int RoleID);

    Account checkLogin(String username, String password);

    int count();

    List<Account> getListStoreManager();

    List<Account> getListStoreAccount(int storeID);
}
