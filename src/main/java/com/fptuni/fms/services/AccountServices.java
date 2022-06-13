package com.fptuni.fms.services;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.dao.implement.RoleDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountServices {

    private static final AccountDAO accountDAO = new AccountDAO();

    public static void login(HttpServletRequest request, HttpServletResponse response) {
        Account account = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            try {
                password = SecurityUtils.createHash(password, username);
                account = accountDAO.checkLogin(username, password);
                if (account != null) {
                    request.getSession().setAttribute("account", account);
                    Role role = new RoleDAO().getRole(account.getRoleID().getId());
                    if (role.getName().equalsIgnoreCase("admin")) {
                        request.setAttribute("controller", "admin");
                    } else if(role.getName().equalsIgnoreCase("user")){
                        request.setAttribute("controller", "user");
                    }
                    request.setAttribute("action", "index");

                } else
                    request.setAttribute("message", "username or password is wrong");
            } catch (Exception e) {
                request.setAttribute("message", "username or password not provided");
            }
        }
    }
}
