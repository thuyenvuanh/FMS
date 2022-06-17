package com.fptuni.mapper;


import com.fptuni.fms.mapper.RowMapper;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
*
* Author: Anh Quoc
*
* */
public class StoreMapper implements RowMapper<Store> {

    @Override
    public Store mapRow(ResultSet rs) {
        Store store = null;
        try {
            store = new Store();
            store.setId(rs.getInt("ID"));
            store.setName(rs.getString("Name"));
            store.setAccountID(new Account(rs.getInt("AccountID")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return store;
    }
}
