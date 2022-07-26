package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Counter;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.MoneyTransaction;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class MoneyTransactionMapper implements RowMapper<MoneyTransaction> {
    @Override
    public MoneyTransaction mapRow(ResultSet rs) {
        MoneyTransaction moneyTransaction = null;
        ResultSetMetaData metaData = null;
        try {
            moneyTransaction = new MoneyTransaction();
            metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    moneyTransaction.setId(rs.getInt(i));
                if (metaData.getColumnLabel(i).equals("CustomerID"))
                    moneyTransaction.setCustomerID(new Customer(rs.getInt(i)));
                if (metaData.getColumnLabel(i).equals("Amount"))
                    moneyTransaction.setAmount(rs.getBigDecimal(i));
                if (metaData.getColumnLabel(i).equals("WalletID"))
                    moneyTransaction.setWalletID(rs.getInt(i));
                if (metaData.getColumnLabel(i).equals("Method"))
                    moneyTransaction.setMethod(rs.getString(i));
                if (metaData.getColumnLabel(i).equals("CounterID"))
                    moneyTransaction.setCounterID(new Counter(rs.getInt(i)));
                if (metaData.getColumnLabel(i).equals("State"))
                    moneyTransaction.setState(rs.getBoolean(i));
                if (metaData.getColumnLabel(i).equals("CreatedDate"))
                    moneyTransaction.setCreatedDate(rs.getDate(i));
            }
        } catch (Exception e) {
            System.out.println("Mapping Error: " + e.getMessage());
        }
        return moneyTransaction;
    }
}
