package com.fptuni.fms.service;

import com.fptuni.fms.model.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ICustomerService {
    List<Customer> getList (HttpServletRequest request , HttpServletResponse response);
    Customer getCustomerByPhoneNum (String PhoneNum);
    Integer addnewCustomer (HttpServletRequest request , HttpServletResponse response);
    Integer CountCustomer ();
    Integer DeleteCustomer(String phoneNum);
    Customer getDetail (String phoneNum);
    boolean updateCustomerInfo (Customer customer);
    Customer getCustomerByOrderID(HttpServletRequest request , HttpServletResponse response);
    Customer getCustomer(int customerID);
}
