package com.fptuni.fms.controller;

import com.fptuni.fms.dao.implement.CustomerDAO;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.service.ICustomerService;
import com.fptuni.fms.service.implement.CustomerService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@MultipartConfig
@WebServlet(name = "CustomerController", urlPatterns = "/customer/*")
public class CustomerController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
        if (path.equals("/addnewcustomer")) {
            ICustomerService customerService = new CustomerService();
            if (customerService.addnewCustomer(request, response) == 1) {
                request.setAttribute("createStatus", "success");
                response.sendRedirect(request.getContextPath() + "/product/list");
            } else {
                request.setAttribute("createStatus", "fail");
                request.getRequestDispatcher("/view/store/productCreate.jsp").forward(request, response);
            }
            
        }else if(path.equals("/search")){
            CustomerDAO customerDAO = new CustomerDAO();
            String index = "";
            if(request.getParameter("searchItem") != null ||
                    !request.getParameter("searchItem").equals("")){
                index = request.getParameter("searchItem");
            }
            Customer cus = customerDAO.getByPhoneNum(index);
            request.setAttribute("list", cus);
            RequestDispatcher rd = request.getRequestDispatcher("/");
            rd.forward(request,response);
            
        } else if (path.equals("/list")) {
            int pageSize = 3;
            ICustomerService customerService = new CustomerService();

            List<Customer> customers = customerService.getList(request,response);
            int totalPages = customerService.CountCustomer() / pageSize;
            if (customerService.CountCustomer() % pageSize != 0){
                totalPages++;
            }
            /*Change to wallet or transaction
            List<Category> categories = categoryService.getCategories();
            request.setAttribute("categories", categories);*/
            request.setAttribute("customerList", customers);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("/view/customer/Customer_List.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
