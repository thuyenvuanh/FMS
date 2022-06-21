package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.ICustomerDAO;
import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.dao.implement.CustomerDAO;
import com.fptuni.fms.dao.implement.ProductDAO;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.ICustomerService;
import com.fptuni.fms.sort.Sorter;
import com.fptuni.fms.utils.RequestUtils;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CustomerService implements ICustomerService {
    @Override
    public List<Customer> getList(HttpServletRequest request, HttpServletResponse response) {
        ICustomerDAO customerDAO = new CustomerDAO();
        int pageIndex = 1;
        int pageSize = 3;
        String sortField = "Name";
        boolean isAsc = true;
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Sorter sorter = new Sorter(sortField, isAsc);
        Pageable pageable = new PageRequest(pageIndex, pageSize, sorter);
        List<Customer> customers = customerDAO.getAllCustomer(pageable);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAsc", !isAsc);

        return customers;
    }

    @Override
    public Customer getCustomerByPhoneNum(HttpServletRequest request , HttpServletResponse response) {
        Customer customer = null;
        String phoneNumber = request.getParameter("phoneNumber").trim();
        CustomerDAO customerDAO = new CustomerDAO();
        if(phoneNumber != null && !phoneNumber.equals("")){
            customer = customerDAO.getByPhoneNum(phoneNumber);
        }

        return customer;
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

    @Override
    public Integer CountCustomer() {
        ICustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.count();
    }
}
