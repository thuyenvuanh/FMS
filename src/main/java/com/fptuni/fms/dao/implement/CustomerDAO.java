package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICustomerDAO;
import com.fptuni.fms.mapper.CustomerMapper;
import com.fptuni.fms.model.Customer;

import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> implements ICustomerDAO {

    @Override
    public List<Customer> getAll() {
        String sql = "select ID , Name\n" +
                "from [dbo].[Customer]";
        List<Customer> cus = query(sql, new CustomerMapper());
        return cus.isEmpty() ? null : cus;
    }

    @Override
    public List<Customer> getByID(int Id) {
        String sql = "select ID , Name\n" +
                "from [dbo].[Customer]\n" +
                "where ID = ? ";
        List<Customer> cus = query(sql, new CustomerMapper(), Id);
        return cus.isEmpty() ? null : cus;
    }

    @Override
    public Integer insertCustomer(Customer customer) {
        String sql = "insert into [dbo].[Customer] (Name, Phone)\n" +
                "values ('?',?)";
        return insert(sql, customer.getName() , customer.getPhone());
    }
}
