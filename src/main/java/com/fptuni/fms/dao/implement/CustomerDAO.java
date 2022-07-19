package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICustomerDAO;
import com.fptuni.fms.mapper.CustomerMapper;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.MoneyTransaction;
import com.fptuni.fms.paging.Pageable;

import javax.naming.Name;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> implements ICustomerDAO {

    @Override
    public List<Customer> getAllCustomer(Pageable pageable) {
//        String sql = "select ID, Name , Phone , IsDeleted, DoB, Address, Gender\n" +
//                "from [dbo].[Customer]\n";
        String sql = "select sum(m.Amount) as Amount, c.ID , c.Phone, c.IsDeleted, c.Gender,c.DoB, c.Name, c.Address\n" +
                "from [dbo].[Customer] c join [dbo].[MoneyTransaction] m\n" +
                "on c.ID = m.CustomerID\n" +
                "group by c.ID, c.Name, c.Phone, c.IsDeleted, c.Gender,c.DoB, c.Address";

        String order;
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            order = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += " ORDER BY " + pageable.getSorter().getSortField() + "  " + order;
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n" +
                    " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY  \n";
        }

        List<Customer> cus = query(sql, new CustomerMapper());
        return cus.isEmpty() ? null : cus;
    }

    @Override
    public Integer insertCustomer(Customer customer) {
        String sql = "insert into [dbo].[Customer] (Name, Phone)\n" +
                "values (?,?)";
        return insert(sql, customer.getName(), customer.getPhone());
    }

    @Override
    public Customer getByPhoneNum(String phoneNum) {
        String sql = "select ID, Name , Phone , IsDeleted, DoB, Address, Gender\n" +
                "from [dbo].[Customer]\n" +
                "where Phone = ?";
        List<Customer> cus = query(sql, new CustomerMapper(), phoneNum);
        return cus.isEmpty() ? null : cus.get(0);
    }

    @Override
    public Customer getByName(String name) {
        return null;
    }

    @Override
    public Integer count() {
        String sql = "select count(c.ID)\n" +
                "from [dbo].[Customer] c";
        return count(sql);
    }

    @Override
    public boolean deleteCus(String phoneNum) {
        String sql = " UPDATE [dbo].[Customer]\n" +
                " SET IsDeleted = 'true'\n" +
                " WHERE Phone = ? ";
        return update(sql, phoneNum);
    }

    @Override
    public Customer getDetail(String phoneNum) {
        String sql = "select DoB, Address, Gender\n" +
                "from [dbo].[Customer]\n" +
                "where Phone = ? ";
        List<Customer> cus = query(sql, new CustomerMapper(), phoneNum);
        return cus.isEmpty() ? null : cus.get(0);
    }

    @Override
    public boolean updateCustomerInfo(Customer customer) {
        String sql = " UPDATE [dbo].[Customer]\n" +
                " SET Address = ?, Gender = ?, DoB = ?\n" +
                " WHERE Phone = ? ";
        return update(sql,
                customer.getAddress(),
                customer.getGender(),
                customer.getDoB(),
                customer.getPhone());
    }

}
