package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author NhatTan
 */
public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs) {
        Account acc = null;
        ResultSetMetaData metaData = null;
        try {
            metaData = rs.getMetaData();
            acc = new Account();
            int rsColumns = metaData.getColumnCount();
            for (int i = 1; i <= rsColumns; i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    acc.setId(rs.getInt(i));
                if (metaData.getColumnLabel(i).equals("Username"))
                    acc.setUsername(rs.getString(i));
                if (metaData.getColumnLabel(i).equals("FullName"))
                    acc.setFullName(rs.getString(i));
                if (metaData.getColumnLabel(i).equals("RoleID"))
                    acc.setRole(new Role(rs.getInt(i)));
                if (metaData.getColumnLabel(i).equals("Name"))
                    acc.getRole().setName(rs.getString(i));
                if (metaData.getColumnLabel(i).equals("IsDeleted"))
                    acc.setDeleted(rs.getBoolean(i));
            }
        } catch (SQLException e) {
            System.out.println("Mapping error: " + e.getMessage());
        }
        return acc;
    }
}
