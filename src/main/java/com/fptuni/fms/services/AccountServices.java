package com.fptuni.fms.services;

import com.fptuni.fms.dao.implement.AccountDAO;
import com.fptuni.fms.dao.implement.RoleDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Role;
import com.fptuni.fms.utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServices {

    private static final AccountDAO accountDAO = new AccountDAO();

    public static void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            try {
                password = SecurityUtils.createHash(password, username);
                account = accountDAO.checkLogin(username, password);
                if (account != null) {
                    System.out.println("Log in successful");
                    request.getSession().setAttribute("account", account);
                    Role role = new RoleDAO().getRole(account.getRoleID().getId());
                    if (role.getName().equalsIgnoreCase("admin")) {
                        request.setAttribute("controller", "admin");
                    } else if(role.getName().equalsIgnoreCase("user")){
                        request.setAttribute("controller", "user");
                    }

                } else
                    request.setAttribute("message", "username or password is wrong");
            } catch (Exception e) {
                request.setAttribute("message", "username or password not provided");
            }
            request.setAttribute("action", "index");
            String url = "/views/" + request.getAttribute("controller") + "/" + request.getAttribute("action") + ".jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();
        System.out.println("Sign out successfull");
        request.setAttribute("controller", "auth");
        request.setAttribute("action", "index");
        request.getRequestDispatcher("/views/auth/index.jsp").forward(request,response);
    }
}
