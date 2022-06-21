package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Customer;

import java.sql.ResultSet;

public class CustomerMapper implements RowMapper<Customer>{

    @Override
    public Customer mapRow(ResultSet rs) {
        Customer cus = null;
        try {
            cus = new Customer();
            cus.setId(rs.getInt("ID"));
            cus.setName(rs.getString("Name"));
            cus.setPhone((rs.getString("Phone")));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return cus;
    }
}
