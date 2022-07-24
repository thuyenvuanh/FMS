package com.fptuni.fms.controller;

import com.fptuni.fms.dao.IOrderDAO;
import com.fptuni.fms.model.*;
import com.fptuni.fms.service.ICustomerService;
import com.fptuni.fms.service.IOrderService;
import com.fptuni.fms.service.IPaymentService;
import com.fptuni.fms.service.ITransactionService;
import com.fptuni.fms.service.implement.CustomerService;
import com.fptuni.fms.service.implement.OrderService;
import com.fptuni.fms.service.implement.PaymentService;
import com.fptuni.fms.service.implement.TransactionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TransactionSharedController", urlPatterns = "/transactionShared/*")
public class TransactionSharedController extends HttpServlet {
    ITransactionService transactionService = new TransactionService();
    ICustomerService customerService = new CustomerService();
    IPaymentService paymentService = new PaymentService();
    IOrderService orderService = new OrderService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("storeSession");
        if (path.equals("/list")) {
            List<TransactionShared> transactionShares = transactionService.getTransactionSharedByStore(request, store);

            for (TransactionShared transactionShared : transactionShares) {
                Payment payment = transactionShared.getPaymentID();
                Wallet wallet = transactionShared.getWalletID();
                payment.setOrderID(orderService.getOrdersByPaymentID(request, transactionShared.getPaymentID().getId()));
                wallet.setCustomerID(customerService.getCustomerByWalletID(wallet.getId()));
            }

            request.setAttribute("transactionShares", transactionShares);
            request.getRequestDispatcher("/view/store/transactionList.jsp").forward(request, response);
        } else {
            response.sendError(404, "Not Found");
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
