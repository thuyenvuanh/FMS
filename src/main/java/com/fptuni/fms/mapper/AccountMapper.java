package com.fptuni.fms.mapper;

import com.fptuni.fms.dao.implement.AbstractDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NhatTan
 */
public class AccountMapper extends AbstractDAO<Account> implements RowMapper {

    @Override
    public Account mapRow(ResultSet rs) {
        Account acc = null;
        try {
            acc = new Account();
            acc.setId(rs.getInt("ID"));
            acc.setUsername(rs.getString("Username"));
            acc.setFullName(rs.getString("FullName"));
            acc.setRole(new Role(rs.getInt("RoleID")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return acc;
    }
}
