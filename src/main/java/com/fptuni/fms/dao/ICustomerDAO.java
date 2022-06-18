package com.fptuni.fms.dao;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.paging.Pageable;

import java.util.List;

public interface ICustomerDAO extends GenericDAO<Customer>{
    List<Customer> getAllCustomer(Pageable pageable);
    Integer insertCustomer(Customer customer);
    Customer getByPhoneNum(String phoneNum);
    Customer getByName(String name);

}
