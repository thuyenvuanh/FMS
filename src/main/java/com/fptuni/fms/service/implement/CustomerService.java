package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.ICustomerDAO;
import com.fptuni.fms.dao.implement.CustomerDAO;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.service.ICustomerService;
import com.fptuni.fms.utils.RequestUtils;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService {
    @Override
    public List<Customer> getList(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

    @Override
    public Customer getCustomerByPhoneNum(String PhoneNum) {

        return null;
    }

    @Override
    public Integer addnewCustomer(HttpServletRequest request, HttpServletResponse response) {
        ICustomerDAO customerDAO = new CustomerDAO();
        String name = "";
        String phone = "";
        if (request.getParameter("name") != null
                || !request.getParameter("name").equals("")) {
            name = request.getParameter("name");
        }
        if (request.getParameter("phone") != null
                || !request.getParameter("phone").equals("")) {
            phone = request.getParameter("phone");
        }
        Customer customer = new Customer(name, phone);
        request.setAttribute("customer", customer);
        Map<String, String> paramMap = RequestUtils.getParameters(request.getQueryString());
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (entry.getValue().isEmpty())
                return 0;
        }
        return customerDAO.insertCustomer(customer);
    }
}
