/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.mapper;

import com.fptuni.fms.model.MoneyTransaction;
import com.fptuni.fms.model.Payment;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.model.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public class TransactionSharedMapper implements RowMapper<TransactionShared> {

    @Override
    public TransactionShared mapRow(ResultSet rs) {
        ResultSetMetaData metaData = null;
        TransactionShared result = null;
        try {
            metaData = rs.getMetaData();
            result = new TransactionShared();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    result.setId(rs.getInt(i));
                else if (metaData.getColumnLabel(i).equals("Amount"))
                    result.setAmount(rs.getBigDecimal(i));
                else if (metaData.getColumnLabel(i).equals("WalletID"))
                    result.setWalletID(rs.getInt(i) == 0 ? null : new Wallet(rs.getInt(i)));
                else if (metaData.getColumnLabel(i).equals("PreviousHash"))
                    result.setPreviousHash(rs.getString(i));
                else if (metaData.getColumnLabel(i).equals("HashValue"))
                    result.setHashValue(rs.getString(i));
                else if (metaData.getColumnLabel(i).equals("PreviousBalance"))
                    result.setPreviousBalance(rs.getBigDecimal(i));
                else if (metaData.getColumnLabel(i).equals("CreatedDate"))
                    result.setCreatedDate(new Date(rs.getTimestamp(i).getTime()));
                else if (metaData.getColumnLabel(i).equals("Status"))
                    result.setStatus(rs.getBoolean(i));
                else if (metaData.getColumnLabel(i).equals("MoneyTransactionID"))
                    result.setMoneyTransactionID(rs.getInt(i) == 0 ? null : new MoneyTransaction(rs.getInt(i)));
                else if (metaData.getColumnLabel(i).equals("PaymentID"))
                    result.setPaymentID(rs.getInt(i) == 0 ? null : new Payment(rs.getInt(i)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionSharedMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
