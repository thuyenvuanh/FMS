package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NhatTan
 */
public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs) {
        Account acc = null;
        try {
            acc = new Account();
            acc.setId(rs.getInt("ID"));
            acc.setUsername(rs.getString("Username"));
            acc.setFullName(rs.getString("FullName"));
            acc.setRole(new Role(rs.getInt("RoleID")));
            acc.setDeleted(rs.getBoolean("IsDeleted"));
        } catch (SQLException e) {
            System.out.println("Mapping error: " + e.getMessage());
        }
        return acc;
    }
}
