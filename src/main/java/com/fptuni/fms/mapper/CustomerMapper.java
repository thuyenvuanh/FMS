package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Customer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs) {
        Customer cus = null;
        try {
            cus = new Customer();
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    cus.setId(rs.getInt("ID"));
                if (metaData.getColumnLabel(i).equals("Name"))
                    cus.setName(rs.getString("Name"));
                if (metaData.getColumnLabel(i).equals("DoB"))
                    cus.setDoB(rs.getDate("DoB"));
                if (metaData.getColumnLabel(i).equals("Address"))
                    cus.setAddress(rs.getString("Address"));
                if (metaData.getColumnLabel(i).equals("Gender"))
                    cus.setGender(rs.getShort("Gender"));
                if (metaData.getColumnLabel(i).equals("Phone"))
                    cus.setPhone((rs.getString("Phone")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cus;
    }
}
