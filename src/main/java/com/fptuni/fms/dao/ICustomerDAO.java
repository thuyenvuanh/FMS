package com.fptuni.fms.dao;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.MoneyTransaction;
import com.fptuni.fms.paging.Pageable;

import java.util.List;

public interface ICustomerDAO extends GenericDAO<Customer> {
    List<Customer> getAllCustomer(Pageable pageable);

    Integer insertCustomer(Customer customer);

    Customer getByPhoneNum(String phoneNum);

    Customer getByName(String name);

    Integer count();
    Integer countCustomerNotDeleted();

    boolean deleteCus(String phoneNum);

    Customer getDetail(String phoneNum);

    boolean updateCustomerInfo(Customer customer);

    Customer getCustomerByOrderID(int id);

    Customer getCustomer(int customerID);
    Customer getCustomerByWalletID(int walletID);
}
