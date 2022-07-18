package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
        ResultSetMetaData metaData = null;
        try {
            metaData = rs.getMetaData();
            store = new Store();
            int rsColumns = metaData.getColumnCount();
            for (int i = 1; i <= rsColumns; i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    store.setId(rs.getInt(i));
                if (metaData.getColumnLabel(i).equals("Name"))
                    store.setName(rs.getString(i));
                if (metaData.getColumnLabel(i).equals("IsDeleted"))
                    store.setDeleted(rs.getBoolean(i));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return store;
    }
}
