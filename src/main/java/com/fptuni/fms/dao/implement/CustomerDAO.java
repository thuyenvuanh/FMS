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
        String sql = "select ID, Name , Phone , IsDeleted, DoB, Address, Gender\n" +
                "from [dbo].[Customer]\n" +
                "where IsDeleted = 0";
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
                "where Phone = ? and IsDeleted = 0";
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
    public Integer countCustomerNotDeleted() {
        String sql = "select count(c.ID)\n" +
                "from [dbo].[Customer] c\n" +
                "where c.IsDeleted = 0";
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
                " SET Name = ?, Address = ?, Gender = ?, DoB = ?\n" +
                " WHERE Phone = ? ";
        return update(sql,
                customer.getName(),
                customer.getAddress(),
                customer.getGender(),
                customer.getDoB(),
                customer.getPhone());
    }

    public Customer getCustomerByOrderID(int id) {
        String sql = "SELECT c.ID, c.Name, c.DoB, c.Address, c.Gender, c.Phone, w.ID, ts.ID, p.OrderID FROM Customer c\n" +
                "JOIN Wallet w ON w.CustomerID = c.ID AND w.IsDeleted = 0 AND c.IsDeleted = 0\n" +
                "JOIN TransactionShared ts ON ts.WalletID = w.ID AND ts.Status = 1\n" +
                "JOIN Payment p ON p.ID = ts.PaymentID AND p.IsDeleted = 0\n" +
                "AND p.OrderID = ?";
        List<Customer> result = query(sql, new CustomerMapper(), id);
        return result == null ? null : result.get(0);
    }

    @Override
    public Customer getCustomer(int customerID) {
        String sql = "SELECT ID, Name, DoB, Address, Gender, Phone FROM Customer\n" +
                "WHERE ID = ?";
        List<Customer> result = query(sql, new CustomerMapper(), customerID);
        return result == null ? null : result.get(0);
    }

    @Override
    public Customer getCustomerByWalletID(int walletID) {
        String sql = "SELECT c.ID, c.Name, c.Address, c.DoB, c.Phone, c.Gender FROM Customer c\n" +
                "JOIN \n" +
                "(SELECT ID, CustomerID FROM Wallet WHERE IsDeleted = 0 AND ID = ?) AS subWallet ON c.ID = subWallet.CustomerID\n" +
                "AND c.IsDeleted = 0";
        List<Customer> customers = query(sql, new CustomerMapper(), walletID);
        return customers == null ? null : customers.get(0);
    }
}
