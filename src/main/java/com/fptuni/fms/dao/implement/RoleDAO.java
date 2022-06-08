package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IRoleDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Role;
import com.fptuni.fms.model.Store;
import com.fptuni.mapper.RoleMapper;

import java.math.BigDecimal;
import java.util.List;

public class RoleDAO extends AbstractDAO<Role> implements IRoleDAO {

    @Override
    public Role getRole(int id) { // select Role
        String sql = "SELECT ID, Name " +
                "From Role " +
                "where ID = ?;";
        List<Role> roles = query(sql, new RoleMapper(), id);
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public Integer insertRole(Role role) {
        String sql = "INSERT into Role\n" +
                "VALUES('?');";
        return insert(role.getName());
    }

    @Override
    public void updateRole(int id, String name){
        String sql="UPDATE Role\n" +
                "SET\n" +
                "Name='?',\n" +
                "Where ID = ?;";
        update(name,String.valueOf(id));
    }


}
