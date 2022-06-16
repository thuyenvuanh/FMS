package com.fptuni.fms.dao;

import com.fptuni.fms.model.Role;

/**
 *
 * Author: Anh Quoc
 *
 * **/
public interface IRoleDAO extends GenericDAO<Role> {

    Role getRole(int id);

    Integer insertRole(Role role);

    void updateRole(int id, String name);
}
