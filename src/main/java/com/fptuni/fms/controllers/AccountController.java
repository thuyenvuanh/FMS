package com.fptuni.fms.controllers;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.fptuni.fms.services.AccountServices.login;

public class AccountController extends HttpServlet {

    private final AccountDAO accountDAO;

    public AccountController() {
        this.accountDAO = new AccountDAO();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.removeAttribute("message");
        String action = (String) request.getAttribute("action");

        switch (action) {
            case "index":
                break;
            case "login":
                login(request, response);
                break;
            case "forgot":
//                forgot(request, response);
                break;
            default:

        }
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
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
