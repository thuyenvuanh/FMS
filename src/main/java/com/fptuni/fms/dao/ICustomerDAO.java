package com.fptuni.fms.dao;

import com.fptuni.fms.model.Customer;

import java.util.List;

public interface ICustomerDAO extends GenericDAO<Customer>{
    List<Customer> getAll();
    List<Customer> getByID(int Id);
    Integer insertCustomer(Customer customer);
    List<Customer> getByPhoneNum(String phoneNum);

}
