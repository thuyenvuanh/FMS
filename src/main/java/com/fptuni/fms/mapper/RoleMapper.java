package com.fptuni.mapper;

import com.fptuni.fms.mapper.RowMapper;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Author: Anh Quoc
 *
 *
 * **/
public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs) {
        Role role = null;
        try {
            role = new Role();
            role.setId(rs.getInt("ID"));
            role.setName(rs.getString("Name"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return role;
    }
}
