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

            Customer customer = customerService.getCustomerByPhoneNum(request, response);

            if(customer != null){
                Wallet wallet = walletService.getWallet(customer.getId());
                if(wallet != null){
                    TransactionShared transactionShared = transactionService.getTransactionSharedByWalletID(wallet.getId());
                    if(transactionShared != null){
                        BigDecimal amount = transactionService.getCustomerAmount(transactionShared);
                        request.setAttribute("AMOUNT", amount);
                        request.setAttribute("CUSTOMER", customer);
                        request.getRequestDispatcher("/view/counter/counter.jsp").forward(request, response);
                    }
                }
            } else {
                 response.sendRedirect("/view/counter/createCustomer.jsp");
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
