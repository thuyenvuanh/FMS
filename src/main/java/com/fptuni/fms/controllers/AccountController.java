package com.fptuni.fms.controllers;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.fptuni.fms.services.AccountServices;

public class AccountController extends HttpServlet {

    public AccountController() {
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("message");
        String action = request.getPathInfo();
        System.out.println("path info: " + action);
        switch (action) {
            case "/index":
                break;
            case "/login":
                AccountServices.login(request, response);
                break;
            case "/forgot":
//                forgot(request, response);
                break;
            case "/logout":
                AccountServices.logout(request, response);
                break;
            default:

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
