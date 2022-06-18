package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICustomerDAO;
import com.fptuni.fms.mapper.CustomerMapper;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.paging.Pageable;

import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> implements ICustomerDAO {

    @Override
    public List<Customer> getAllCustomer(Pageable pageable) {
        String sql = "select c.Name , c.Phone, c.IsDeleted , m.Amount\n" +
                "from [dbo].[Customer] c join [dbo].[MoneyTransaction] m\n" +
                "on c.ID = m.CustomerID";
        String order;
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            order = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY " + pageable.getSorter().getSortField() + "  " + order;
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n" +
                    " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY ) AS A \n";
        }
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            order = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY A." + pageable.getSorter().getSortField() + " " + order;
        }
        List<Customer> cus = query(sql, new CustomerMapper());
        return cus.isEmpty() ? null : cus;
    }

    @Override
    public Integer insertCustomer(Customer customer) {
        String sql = "insert into [dbo].[Customer] (Name, Phone)\n" +
                "values (?,?)";
        return insert(sql, customer.getName() , customer.getPhone());
    }

    @Override
    public Customer getByPhoneNum(String phoneNum) {
        String sql = "select ID, Name, Phone\n" +
                "from [dbo].[Customer]\n" +
                "where Phone = ? ";
        List<Customer> cus = query(sql, new CustomerMapper(), phoneNum);
        return cus == null ? null : cus.get(0);
    }

    @Override
    public Customer getByName(String name) {
        return null;
    }
}
