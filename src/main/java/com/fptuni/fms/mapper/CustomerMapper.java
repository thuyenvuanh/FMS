package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Customer;

import java.sql.ResultSet;

public class CustomerMapper implements RowMapper<Customer>{

    @Override
    public Customer mapRow(ResultSet rs) {
        Customer cus = null;
        try {
            cus = new Customer();
            cus.setName(rs.getString("Name"));
            cus.setPhone((rs.getString("phone")));

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return cus;
    }
}