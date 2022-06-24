/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.mapper;

import com.fptuni.fms.model.MoneyTransaction;
import com.fptuni.fms.model.Payment;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public class TransactionSharedMapper implements RowMapper<TransactionShared> {

    @Override
    public TransactionShared mapRow(ResultSet rs) {
        
        TransactionShared result = null;
        try {
            result = new TransactionShared(rs.getInt("ID"));
            result.setAmount(rs.getBigDecimal("Amount"));
            result.setWalletID(new Wallet(rs.getInt("WalletID")));
            result.setPreviousHash(rs.getString("PreviousHash"));
            result.setHashValue(rs.getString("HashValue"));
            result.setPreviousBalance(rs.getBigDecimal("PreviousBalance"));
            result.setCreatedDate(rs.getDate("CreatedDate"));
            result.setStatus(rs.getBoolean("Status"));
            result.setMoneyTransactionID(new MoneyTransaction(rs.getInt("MoneyTransactionID")));
            result.setPaymentID(new Payment(rs.getInt("PaymentID")));
        } catch (SQLException ex) {
            Logger.getLogger(TransactionSharedMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
