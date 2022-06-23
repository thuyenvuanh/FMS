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
        String Url = null;
        switch (action) {
            case "/login":
                Url = accountService.login(request, response);
                response.sendRedirect(Url);
                break;
            case "/logout":
                Url = accountService.logout(request, response);
                response.sendRedirect(Url);
                break;
            case "/create":
                Url = accountService.create(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/createPage":
                Url = accountService.getRole(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/list":
                Url = accountService.getListAccount(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/update":
                Url = accountService.update(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/updatePage":
                Url = accountService.getAccountUpdate(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/view":
                Url = accountService.getAccount(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/delete":
                Url = accountService.delete(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/search":
                Url = accountService.search(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            default:
                //chuyen huong den trang error
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
