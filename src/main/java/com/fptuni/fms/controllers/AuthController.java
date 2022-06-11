package com.fptuni.fms.controllers;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthController extends HttpServlet {

    private final AccountDAO accountDAO;

    public AuthController() {
        this.accountDAO = new AccountDAO();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.removeAttribute("message");

        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        if (username != null && password != null) {
            try {
                password = SecurityUtils.createHash(password, username);
                Account account = accountDAO.checkLogin(username, password);
                if (account != null)
                    request.setAttribute("message", "Account found");
                else
                    request.setAttribute("message", "username or password is wrong");
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("action", "index");
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
