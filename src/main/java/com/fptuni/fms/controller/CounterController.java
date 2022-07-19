package com.fptuni.fms.controller;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.service.*;
import com.fptuni.fms.service.implement.*;

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
        ICustomerService customerService = new CustomerService();
        IWalletService walletService = new WalletService();
        ITransactionService transactionService = new TransactionService();
        IMoneyTransactionService moneyTransactionService = new MoneyTransactionService();
        if(path.equals("/index")){
            request.getRequestDispatcher("/view/counter/index.jsp").forward(request, response);
        } else if(path.equals("/check")){


            String phoneNumber = request.getParameter("phoneNumber").trim().replaceAll("\\s+","");
            System.out.println(phoneNumber);

            Customer customer = customerService.getCustomerByPhoneNum(phoneNumber);

            if(customer != null){
                Wallet wallet = walletService.getWallet(customer.getId());
                System.out.println(wallet.getId());
                BigDecimal balance = BigDecimal.ZERO;
                if(wallet != null){
                    TransactionShared transactionShared = transactionService.getLatestTransactionSharedByWalletID(wallet.getId());
                    if(transactionShared != null){
                        balance = transactionService.getCustomerBalance(transactionShared);
                    }
                }
                request.setAttribute("CUSTOMER", customer);
                request.setAttribute("WALLET", wallet.getId());
                request.setAttribute("BALANCE", balance);
                request.getRequestDispatcher("/view/counter/counter.jsp").forward(request, response);
            } else {

            }
        } else if(path.equals("/addMoney")){
            boolean success = moneyTransactionService.addMoney(request);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/counter/index");
            }

        } else if(path.equals("/withDraw")){
            boolean success = moneyTransactionService.withDraw(request);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/counter/index");
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
