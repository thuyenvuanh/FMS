/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IAccountDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.mapper.AccountMapper;
import java.util.List;

/**
 *
 * @author LucasBV
 */
public class AccountDAO extends AbstractDAO<Account> implements IAccountDAO {

    @Override
    public Account getAccount(String username, String password) {
        String sql = "SELECT A.ID, A.Username, A.Password, A.FullName, A.RoleID, R.Name \n"
                + " FROM Account A\n"
                + " JOIN Role R ON A.RoleID = R.ID"
                + " WHERE A.Username = ? AND Password = ? ";
        List<Account> accounts = query(sql, new AccountMapper(), username, password);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

}
