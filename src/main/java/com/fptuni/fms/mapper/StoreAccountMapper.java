package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.StoreAccount;
import com.fptuni.fms.model.StoreAccountPK;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class StoreAccountMapper implements RowMapper<StoreAccount> {
    @Override
    public StoreAccount mapRow(ResultSet rs) {
        StoreAccount storeAccount = null;
        ResultSetMetaData metaData = null;
        try {
            metaData = rs.getMetaData();
            storeAccount = new StoreAccount();
            storeAccount.setStoreAccountPK(new StoreAccountPK());
            for (int i = 1; i < metaData.getColumnCount(); i++) {
                if (metaData.getColumnLabel(i).equals("AccountID")){
                    storeAccount.getStoreAccountPK().setAccountID(rs.getInt(i));
                } else if (metaData.getColumnLabel(i).equals("StoreID")) {
                    storeAccount.getStoreAccountPK().setStoreID(rs.getInt(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Error on mapping StoreAccount: " + e.getMessage());
        }
        return storeAccount;
    }
}
