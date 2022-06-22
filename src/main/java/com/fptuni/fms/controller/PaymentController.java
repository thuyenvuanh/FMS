package com.fptuni.fms.controller;

import com.fptuni.fms.service.implement.PaymentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "/payment/*", value = "/payment/*")
public class PaymentController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String url = request.getContextPath() + "/view/error.jsp";
        switch (action) {
            case "/checkout":
                url = request.getContextPath() + "/pay";
                break;
            case "/create":
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
