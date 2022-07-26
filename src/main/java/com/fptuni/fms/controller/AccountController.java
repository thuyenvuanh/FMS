package com.fptuni.fms.controller;

import com.fptuni.fms.service.implement.AccountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountController", value = "/account/*")
public class AccountController extends HttpServlet {

    private final AccountService accountService = new AccountService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        String url = null;
        switch (action) {
            case "/login":
                url = accountService.login(request, response);
                response.sendRedirect(url);
                break;
            case "/logout":
                url = accountService.logout(request, response);
                response.sendRedirect(url);
                break;
            case "/create":
                url = accountService.create(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/createPage":
                url = accountService.getRole(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/list":
                url = accountService.getListAccount(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/update":
                url = accountService.update(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/updatePage":
                url = accountService.getAccountUpdate(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/view":
                url = accountService.getAccount(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/delete":
                url = accountService.delete(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/search":
                url = accountService.search(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            default:
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
