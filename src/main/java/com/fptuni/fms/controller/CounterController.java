package com.fptuni.fms.controller;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.service.ICustomerService;
import com.fptuni.fms.service.implement.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "CounterController", value = "/counter/*")
public class CounterController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println("Path:" + path);
        if(path.equals("/index")){
            response.sendRedirect("/view/counter/index.jsp");
            
        } else if(path.equals("/check")){
            System.out.println(request.getParameter("phoneNumber"));
            ICustomerService customerService = new CustomerService();
            Customer customer = customerService.getCustomerByPhoneNum(request, response);
            if(customer != null){
                request.setAttribute("CUSTOMER", customer);
                request.getRequestDispatcher("/view/counter/counter.jsp").forward(request, response);
            }
             else{
                 response.sendRedirect("error.jsp");
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
