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
        Store store = (Store) session.getAttribute("store");
        if (path.equals("/list")) {
            List<TransactionShared> transactionShares = transactionService.getTransactionSharedByStore(store);
            
            Map<Integer, Customer> customerMapByWalletID = new HashMap<>();
            Map<Integer, List<Payment>> paymentMapByOrderID = new HashMap<>();
            for (TransactionShared transactionShared : transactionShares) {
                customerMapByWalletID.put(transactionShared.getId(), customerService.getCustomer(transactionShared.getWalletID().getCustomerID().getId()));
                paymentMapByOrderID.put(transactionShared.getId(), paymentService.getPaymentsByOrderID(transactionShared.getPaymentID().getOrderID().getId()));
            }

            request.setAttribute("transactionShares", transactionShares);
            request.setAttribute("customerMapByWalletID", customerMapByWalletID);
            request.setAttribute("paymentMapByOrderID", paymentMapByOrderID);
            request.getRequestDispatcher("/view/store/transactionList.jsp").forward(request, response);
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
