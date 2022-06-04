/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.mapper;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LucasBV
 */
public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs) {
        Account account = null;
        try {
            account = new Account();
            account.setId(rs.getInt("ID"));
            account.setUsername(rs.getString("Username"));
            account.setPassword(rs.getString("Password"));
            account.setFullName(rs.getString("FullName"));
            Role role = new Role();
            role.setId(rs.getInt("RoleID"));
            role.setName(rs.getString("Name"));
            account.setRoleID(role);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

}
