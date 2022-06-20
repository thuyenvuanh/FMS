/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.IdentityCard;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LEGION
 */
public class IdentityCardMapper implements RowMapper<IdentityCard>{

    @Override
    public IdentityCard mapRow(ResultSet rs) {
        IdentityCard identityCard = null;
        try {
            identityCard = new IdentityCard();
            if(rs.getObject("ID") != null)
            identityCard.setId(rs.getInt("ID"));
            if(rs.getObject("CustomerID") != null)
            identityCard.setCustomerID(new Customer(rs.getInt("CustomerID")));
            if(rs.getObject("IsDeleted") != null)
            identityCard.setIsDeleted(rs.getBoolean("IsDeleted"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return identityCard;
    }
    
}
