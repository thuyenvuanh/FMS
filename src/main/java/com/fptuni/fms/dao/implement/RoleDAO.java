package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IRoleDAO;
import com.fptuni.fms.model.Role;
import com.fptuni.mapper.RoleMapper;
import java.util.List;

public class RoleDAO extends AbstractDAO<Role> implements IRoleDAO {

    @Override
    public Role getRole(int id) {
        String sql = "SELECT ID, Name " +
                "From Role " +
                "where ID = ?;";
        List<Role> roles = query(sql, new RoleMapper(), id);
        return roles.isEmpty() ? null : roles.get(0);
    }
}
