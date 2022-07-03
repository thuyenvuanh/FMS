package com.fptuni.fms.controller;

import com.fptuni.fms.dao.ICustomerDAO;
import com.fptuni.fms.dao.implement.CustomerDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.service.ICategoryService;
import com.fptuni.fms.service.ICustomerService;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.service.implement.CategoryService;
import com.fptuni.fms.service.implement.CustomerService;
import com.fptuni.fms.service.implement.ProductService;
import com.sun.xml.internal.ws.addressing.EPRSDDocumentFilter;
import org.eclipse.persistence.sessions.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@MultipartConfig
@WebServlet(name = "CustomerController", urlPatterns = "/customer/*")
public class CustomerController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);

        if (path.equals("/addcustomer")) {
            ICustomerService customerService = new CustomerService();
            String name = request.getParameter("Cusname");
            String phone = request.getParameter("Cusphone");
            if (name != null && !name.equals("") && phone != null && !phone.equals("")) {
                ICustomerDAO customerDAO = new CustomerDAO();
                Customer c = customerDAO.getByPhoneNum(phone);
                if (c != null) {
                    request.setAttribute("msg-1", "This phone number is already exist");
                    request.getRequestDispatcher("/view/customer/Customer_Create.jsp")
                            .forward(request, response);
                } else {
                    if (customerService.addnewCustomer(request, response) == 0) {
                        request.setAttribute("createStatus", "fail");
                        request.getRequestDispatcher("/view/customer/Customer_Create.jsp")
                                .forward(request, response);
                    } else {
                        request.setAttribute("createStatus", "success");
                        response.sendRedirect(request.getContextPath() + "/customer/list");
                    }
                }
            }
        } else if (path.equals("/search")) {
            CustomerDAO customerDAO = new CustomerDAO();
            String phoneNum = request.getParameter("searchItem");
//            System.out.println(phoneNum);
            if (phoneNum != null &&
                    !phoneNum.equals("")) {
                List<Customer> customer = new ArrayList<>();
                Customer cus = customerDAO.getByPhoneNum(phoneNum);
                customer.add(cus);
                request.setAttribute("customerList", customer);
                request.getRequestDispatcher("/view/customer/Customer_List.jsp")
                        .forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/customer/list");
            }

        } else if (path.equals("/list")) {
            int pageSize = 3;
            ICustomerService customerService = new CustomerService();
            List<Customer> customers = customerService.getList(request, response);
            /*Change to wallet or transaction
            List<Category> categories = categoryService.getCategories();
            request.setAttribute("categories", categories);*/
                int totalPages = customerService.CountCustomer() / pageSize;
                if (customerService.CountCustomer() % pageSize != 0) {
                    totalPages++;
                }
                request.setAttribute("customerList", customers);

                request.setAttribute("totalPages", totalPages);
                request.getRequestDispatcher("/view/customer/Customer_List.jsp")
                        .forward(request, response);

        } else if (path.equals("/remove")) {
            String phoneNum = request.getParameter("phonenum");
            ICustomerService customerService = new CustomerService();
            List<Customer> customers = customerService.getList(request, response);
            int index = 0;
            for (Customer customer : customers) {
                if (customer.getPhone().equals(phoneNum)) {
                    index = customerService.DeleteCustomer(phoneNum);
                    break;
                }
            }
            response.sendRedirect(request.getContextPath() + "/customer/list");

        } else if (path.equals("/detail")) {
            ICustomerService customerService = new CustomerService();
            HttpSession session = request.getSession(true);
            String phone = request.getParameter("phoneNum");
            List<Customer> list = new ArrayList<>();
            Customer customer = customerService.getDetail(phone);
            if(customer != null){
                list.add(customer);
            }
            session.setAttribute("detail", list);
            response.sendRedirect(request.getContextPath() + "/customer/list");
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
