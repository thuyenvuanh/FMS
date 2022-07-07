package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Customer;

import java.sql.ResultSet;

public class CustomerMapper implements RowMapper<Customer>{

    @Override
    public Customer mapRow(ResultSet rs) {
        Customer cus = null;
        try {
            cus = new Customer();
            if(rs.getString("Name") != null){
                cus.setName(rs.getString("Name"));
            }
            if(rs.getString("Phone") != null){
                cus.setPhone((rs.getString("phone")));
            }
            if(rs.getString("isDeleted") != null){
                cus.setIsDeleted(rs.getBoolean("isDeleted"));
            }
            if(rs.getString("DoB") != null){
                cus.setDoB(rs.getDate("DoB"));
            }
            if(rs.getString("Address") != null){
                cus.setAddress(rs.getString("Address"));
            }
            if(rs.getString("Gender") != null){
                cus.setGender(rs.getShort("Gender"));
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return cus;
    }
}
