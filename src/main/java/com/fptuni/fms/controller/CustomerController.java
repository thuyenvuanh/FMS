package com.fptuni.fms.controller;

import com.fptuni.fms.dao.ICustomerDAO;
import com.fptuni.fms.dao.implement.CustomerDAO;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.service.ICustomerService;
import com.fptuni.fms.service.IIdentityCardService;
import com.fptuni.fms.service.ITransactionService;
import com.fptuni.fms.service.IWalletService;
import com.fptuni.fms.service.implement.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@MultipartConfig
@WebServlet(name = "CustomerController", urlPatterns = "/customer/*")
public class CustomerController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession(true);
//        System.out.println(path);

        if (path.equals("/addcustomer")) {
            ICustomerService customerService = new CustomerService();
            IWalletService walletService = new WalletService();
            IIdentityCardService identityCardService = new IdentityCardService();
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
                        Customer cus = customerDAO.getByPhoneNum(phone);
                        walletService.insertWallet(cus);
                        identityCardService.createIdentityCard(cus);
                        request.setAttribute("createStatus", "success");
                        request.setAttribute("phoneNumber",phone);
                        session.setAttribute("createStatus","success");
                        request.getRequestDispatcher("/counter/check")
                                .forward(request, response);
                    }
                }
            }else {
                request.setAttribute("msgblank","Can not blank");
                request.getRequestDispatcher("/view/customer/Customer_Create.jsp")
                        .forward(request,response);
            }

        } else if (path.equals("/search")) {
            ICustomerService customerService = new CustomerService();
            CustomerDAO customerDAO = new CustomerDAO();
            List<Customer> customers = customerService.getList(request, response);
            String phoneNum = request.getParameter("searchItem");
            List<Customer> customer = new ArrayList<>();
            Customer customerbyPhone = customerDAO.getByPhoneNum(phoneNum);
            if (phoneNum != null &&
                    !phoneNum.equals("")) {

                if(customerbyPhone != null){
                    customer.add(customerbyPhone);
                    IWalletService walletService = new WalletService();
                    ITransactionService transactionService = new TransactionService();
                    List<Wallet> walletList = new ArrayList<>();
                    TransactionShared transactionShared = new TransactionShared();
                    Wallet wallet = new Wallet();
                    HashMap<Customer, BigDecimal> getAmount = new HashMap<>();

                    if(customers != null){
                        for (Customer cus : customers) {
                            wallet = walletService.getWallet(cus.getId());
                            if(wallet != null){
                                walletList.add(wallet);
                                transactionShared = transactionService.getLatestTransactionSharedByWalletID(wallet.getId());
                                BigDecimal b = (transactionShared == null)
                                        ? BigDecimal.ZERO
                                        : transactionService.getCustomerBalance(transactionShared);

                                getAmount.put(cus , b);
                            }else{
                                System.out.println("No wallet found");
                            }
                        }
                    }else {
                        System.out.println("No customer found");
                    }
                    request.setAttribute("amountlist", getAmount);
                    request.setAttribute("customerList", customer);
                    int totalPages = 0;
                    request.setAttribute("totalPages", totalPages);
                    request.getRequestDispatcher("/view/customer/Customer_List.jsp")
                            .forward(request, response);
                }else {
//                    int totalPages = 0;
//                    int NoF = 0;
//                    request.setAttribute("NoF",NoF);
//                    request.setAttribute("totalPages", totalPages);
//                    request.getRequestDispatcher("/view/customer/Customer_List.jsp")
//                            .forward(request, response);
                    session.setAttribute("NotF",0);
                    response.sendRedirect(request.getContextPath()
                            +"/customer/list");
                }
            } else {
                session.setAttribute("NotF",0);
                response.sendRedirect(request.getContextPath()
                        +"/customer/list");
            }

        } else if (path.equals("/list")) {
            int pageSize = 3;
            ICustomerService customerService = new CustomerService();
            List<Customer> customers = customerService.getList(request, response);

            //Get Amount
            IWalletService walletService = new WalletService();
            ITransactionService transactionService = new TransactionService();
            List<Wallet> walletList = new ArrayList<>();
            TransactionShared transactionShared = new TransactionShared();
            Wallet wallet = new Wallet();
            HashMap<Customer, BigDecimal> getAmount = new HashMap<>();

            if(customers != null){
                for (Customer cus : customers) {
                    wallet = walletService.getWallet(cus.getId());
                    if(wallet != null){
                        walletList.add(wallet);
                            transactionShared = transactionService.getLatestTransactionSharedByWalletID(wallet.getId());
                        BigDecimal b = (transactionShared == null)
                                ? BigDecimal.ZERO
                                : transactionService.getCustomerBalance(transactionShared);

                        getAmount.put(cus , b);
                    }else{
                        System.out.println("No wallet found");
                    }
                }
            }else {
                System.out.println("No customer found");
            }
            request.setAttribute("amountlist", getAmount);

            int totalPages = customerService.CountCustomerNotDeleted() / pageSize;
            if (customerService.CountCustomerNotDeleted() % pageSize != 0) {
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
            customerService.DeleteCustomer(phoneNum);
            session.setAttribute("deletestatus","success");
            response.sendRedirect(request.getContextPath() + "/customer/list");

        } else if (path.equals("/Movetoupdate")) {
            ICustomerService customerService = new CustomerService();
            Customer customer = new Customer();
            String phone = request.getParameter("phonenum");
            customer = customerService.getCustomerByPhoneNum(phone);
            List<Customer> list = new ArrayList<>();
            if (customer != null) {
                list.add(customer);
            }
            request.setAttribute("info", list);
            request.getRequestDispatcher("/view/customer/Customer_Update.jsp")
                    .forward(request, response);

        } else if (path.equals("/update")) {
            ICustomerService customerService = new CustomerService();
            Customer customer = new Customer();
            String phone = request.getParameter("phone");
            customer = customerService.getCustomerByPhoneNum(phone);
            String name = request.getParameter("name");
            String date = request.getParameter("Dob");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            Short Sgender = 0;
            if (gender.toLowerCase().equals("male")) {
                Sgender = 0;
            } else if (gender.toLowerCase().equals("female")) {
                Sgender = 1;
            } else {
                Sgender = 2;
            }
            if(date != null && !date.isEmpty() && address != null && !address.isEmpty() &&
            gender != null && !gender.isEmpty()){
                try {
                    Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    String dateindex = ("2004-01-01");
                    Date index = new SimpleDateFormat("yyyy-MM-dd").parse(dateindex);
                    if(dob.before(index)){
                        customer.setName(name);
                        customer.setDoB(dob);
                        customer.setAddress(address);
                        customer.setGender(Sgender);
                        session.setAttribute("updateStatus","success");
                        customerService.updateCustomerInfo(customer);
                        response.sendRedirect(request.getContextPath() + "/customer/list");
                    }else {
                        request.setAttribute("InvalidDate","Chosen date is invalid");
                        customer = customerService.getCustomerByPhoneNum(phone);
                        List<Customer> list = new ArrayList<>();
                        if (customer != null) {
                            list.add(customer);
                        }
                        request.setAttribute("info", list);
                        request.getRequestDispatcher("/view/customer/Customer_Update.jsp")
                                .forward(request, response);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }else {
//                request.setAttribute("msgEx","Blank is not accepted");
//                System.out.println("Blank error");
                List<Customer> list = new ArrayList<>();
                if (customer != null) {
                    list.add(customer);
                }
                request.setAttribute("info", list);
                request.getRequestDispatcher("/view/customer/Customer_Update.jsp")
                        .forward(request,response);
            }
//            customerService.updateCustomerInfo(customer);
//            response.sendRedirect(request.getContextPath() + "/customer/list");
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
