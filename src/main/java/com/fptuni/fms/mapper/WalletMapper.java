/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.Wallet;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public class WalletMapper implements RowMapper<Wallet> {

    @Override
    public Wallet mapRow(ResultSet rs) {
        Wallet result = null;
        try {
            result = new Wallet(rs.getInt("ID"));
            result.setCustomerID(new Customer(rs.getInt("CustomerID")));
        } catch (SQLException ex) {
            System.out.println("Mapping Error: " + ex.getMessage());
        }
        return result;
    }
    
}
