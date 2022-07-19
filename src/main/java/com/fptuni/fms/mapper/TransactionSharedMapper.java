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

        TransactionShared result = new TransactionShared();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();
            Payment payment = new Payment();
            for (int i = 1; i <= columns; i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    result.setId(rs.getInt("ID"));
                if (metaData.getColumnLabel(i).equals("Amount"))
                    result.setAmount(rs.getBigDecimal("Amount"));
                if (metaData.getColumnLabel(i).equals("WalletID"))
                    result.setWalletID(new Wallet(rs.getInt("WalletID")));
                if (metaData.getColumnLabel(i).equals("PreviousHash"))
                    result.setPreviousHash(rs.getString("PreviousHash"));
                if (metaData.getColumnLabel(i).equals("HashValue"))
                    result.setHashValue(rs.getString("HashValue"));
                if (metaData.getColumnLabel(i).equals("PreviousBalance"))
                    result.setPreviousBalance(rs.getBigDecimal("PreviousBalance"));
                if (metaData.getColumnLabel(i).equals("CreatedDate")) {
                    result.setCreatedDate(rs.getDate("CreatedDate"));
                    result.setCreateDateTime(rs.getTimestamp("CreatedDate"));
                }
                if (metaData.getColumnLabel(i).equals("Status"))
                    result.setStatus(rs.getBoolean("Status"));
                if (metaData.getColumnLabel(i).equals("MoneyTransactionID"))
                    result.setMoneyTransactionID(new MoneyTransaction(rs.getInt("MoneyTransactionID")));
                if (metaData.getColumnLabel(i).equals("PaymentID")) {
                    payment.setId(rs.getInt("PaymentID"));
                }
                if (metaData.getColumnLabel(i).equals("CustomerID")) {
                    Wallet wallet = new Wallet();
                    wallet.setCustomerID(new Customer(rs.getInt("CustomerID")));
                    result.setWalletID(wallet);
                }
                if (metaData.getColumnLabel(i).equals("OrderID")) {
                    payment.setOrderID(new Orders(rs.getInt("OrderID")));
                }
            }
            if (payment.getId() != null) {
                result.setPaymentID(payment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionSharedMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
