package com.fptuni.fms.dao;

import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Role;

/**
 *
 * Author: Anh Quoc
 *
 * **/
public interface IRoleDAO extends GenericDAO<Role> {

    public Role getRole(int id);

    public Integer insertRole(Role role);

    public void updateRole(int id, String name);
}
