package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.service.IAccountService;
import com.fptuni.fms.utils.DBUtils;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

public class AccountService implements IAccountService {

    private static final AccountDAO accountDAO = new AccountDAO();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            password = SecurityUtils.createHash(password, username);
        } catch (Exception e) {
            // fail login
        }
        Account account = accountDAO.checkLogin(username, password);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
