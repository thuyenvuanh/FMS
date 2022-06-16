package com.fptuni.fms.controller;

import com.fptuni.fms.dao.implement.CustomerDAO;
import com.fptuni.fms.model.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", value = "/customer")
public class CustomerController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getContextPath();
        if (path.equals("/signin")) {
            CustomerDAO customerDAO = new CustomerDAO();
            String name = request.getParameter("CusName");
            String phoneNum = request.getParameter("PhoneNum");
            List<Customer> list = customerDAO.getAll();
            request.setAttribute("Name", name);
            request.setAttribute("PhoneNum", phoneNum);

            for (Customer c : list) {
                if (c.getPhone().equals(phoneNum)) {
                    request.setAttribute("Exist_warning","This phone number is already exist");
                    RequestDispatcher rd = request.getRequestDispatcher("");
                    rd.forward(request,response);
                }
            }
            if (!phoneNum.matches("[0-9]+")){
                request.setAttribute("Wrong PhoneNum","Wrong format");
                RequestDispatcher rd = request.getRequestDispatcher("");
                rd.forward(request,response);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
