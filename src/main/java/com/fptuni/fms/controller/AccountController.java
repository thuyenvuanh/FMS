package com.fptuni.fms.controller;

import com.fptuni.fms.service.implement.AccountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountController", value = "/account/*")
public class AccountController extends HttpServlet {

    private AccountService accountService = new AccountService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("message");
        String action = request.getPathInfo();
        System.out.println("path info: " + action);
        switch (action) {
            case "/index":
                break;
            case "/login":
                accountService.login(request, response);
                break;
            case "/forgot":
//                forgot(request, response);
                break;
            case "/logout":
                accountService.logout(request, response);
                break;
            default:

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
