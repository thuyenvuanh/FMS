package com.fptuni.fms.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "/payment/*", value = "/payment/*")
public class PaymentController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        String action = request.getPathInfo();
        String url = "/view/cashier/payment.jsp";
        switch (action) {
            case "/checkout":
                break;
            case "/create":

                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
