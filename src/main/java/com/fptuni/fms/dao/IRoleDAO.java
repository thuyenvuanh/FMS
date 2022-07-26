package com.fptuni.fms.dao;

import com.fptuni.fms.model.Role;
import java.util.List;

/**
 *
 * Author: Anh Quoc
 *
 * *
 */
public interface IRoleDAO extends GenericDAO<Role> {

    Role getRole(int id);

    Integer insertRole(Role role);

    void updateRole(int id, String name);

    List<Role> getListRole();
}
