package com.fptuni.fms.controller;

import com.fptuni.fms.service.implement.AccountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountController", value = "/account/*")
public class AccountController extends HttpServlet {

    private final AccountService accountService = new AccountService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println(request.getServletPath());
        System.out.println("path info: " + action);
        String redirectUrl = null;
        switch (action) {
            case "/login":
                redirectUrl = accountService.login(request, response);
                break;
            case "/logout":
                redirectUrl = accountService.logout(request, response);
                break;
            default:
                //chuyen huong den trang error
        }
        response.sendRedirect(redirectUrl);
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
