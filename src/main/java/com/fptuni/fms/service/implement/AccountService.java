package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.service.IAccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountService implements IAccountService {

    private static final AccountDAO accountDAO = new AccountDAO();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
