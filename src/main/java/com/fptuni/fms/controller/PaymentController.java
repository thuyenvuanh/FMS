package com.fptuni.fms.controller;

import com.fptuni.fms.service.implement.OrderService;
import com.fptuni.fms.service.implement.PaymentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "/payment/*", value = "/payment/*")
public class PaymentController extends HttpServlet {

    private final PaymentService paymentService = new PaymentService();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String url = request.getContextPath() + "/view/error.jsp";
        switch (action) {
            case "/checkout":
                url = request.getContextPath() + "/pay";
                break;
            case "/create":
                request.getSession().removeAttribute("payMessage");
                boolean success = paymentService.makePayment(request);
                if (success) {
                    request.getSession().removeAttribute("order");
                    request.getSession().removeAttribute("productsMap");
                    new OrderService().index(request, response);
                    url = request.getContextPath() +"/cashier";
                } else {
                    url = request.getContextPath() +"/pay";
                }
                break;
        }
        response.sendRedirect(url);
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
