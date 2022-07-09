package com.fptuni.fms.controller;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.service.ICustomerService;
import com.fptuni.fms.service.ITransactionService;
import com.fptuni.fms.service.IWalletService;
import com.fptuni.fms.service.implement.CustomerService;
import com.fptuni.fms.service.implement.TransactionService;
import com.fptuni.fms.service.implement.WalletService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@MultipartConfig
@WebServlet(name = "CounterController", value = "/counter/*")
public class CounterController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println("Path:" + path);
        if(path.equals("/index")){
            request.getRequestDispatcher("/view/counter/index.jsp").forward(request, response);
        } else if(path.equals("/check")){
            ICustomerService customerService = new CustomerService();
            IWalletService walletService = new WalletService();
            ITransactionService transactionService = new TransactionService();

            String phoneNumber = request.getParameter("phoneNumber").trim().replaceAll("\\s+","");
            System.out.println(phoneNumber);

            Customer customer = customerService.getCustomerByPhoneNum(phoneNumber);

            if(customer != null){
                Wallet wallet = walletService.getWallet(customer.getId());
                System.out.println(wallet.getId());
                request.setAttribute("CUSTOMER", customer);
                request.getRequestDispatcher("/view/counter/counter.jsp").forward(request, response);
//                if(wallet != null){
//                    TransactionShared transactionShared = transactionService.getLatestTransactionSharedByWalletID(wallet.getId());
//                    System.out.println(transactionShared.getId());
//                    if(transactionShared != null){
////                        BigDecimal amount = transactionService.getCustomerBalance(transactionShared);
//
//                    }
//                }
            } else {
                request.setAttribute("phoneNum",phoneNumber);
                request.getRequestDispatcher("/view/customer/Customer_Create.jsp")
                         .forward(request, response);
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
