package com.fptuni.fms.mapper;

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
            Account acc = new Account(rs.getInt("AccountID"));
//            acc.setFullName(rs.getString("FullName")); k nen de full name o day
            store.setAccountID(acc);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return store;
    }
}
