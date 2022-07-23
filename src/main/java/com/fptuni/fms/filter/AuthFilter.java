package com.fptuni.fms.filter;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.utils.JsonUtils;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AuthFilter implements Filter {
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("============================");
        System.out.println("Filtering on " + ((HttpServletRequest) request).getRequestURI());
        System.out.println("Servlet Path: " + req.getServletPath());
        System.out.println("Action Path: " + req.getPathInfo());
        //get account from [session], [cookie]
        Account account = (Account) req.getSession().getAttribute("account");
        account = (account == null) ? getFromCookie(request) : account;

//        try {
//            if(JsonUtils.getInstance().havePermission(req.getServletPath(),
//                    req.getPathInfo(), account == null ? "all" : account.getRoleID().getName())){
//                System.out.println("Pass");
//                chain.doFilter(request, response);
//            } else {
//                req.getSession().setAttribute("message", "Sign in to continue");
//                System.out.println("required and have to sign in");
//                ((HttpServletResponse) response).sendRedirect(req.getContextPath());
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            switch (e.getMessage()){
//                case "controller not found":
//                    chain.doFilter(request, response);
//                    return;
//                case "action not found":
//                case "Resource file not found":
//                    ((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/view/404.jsp");
//                    return;
//            }
//        }

        boolean isRequired = false;
        try {
            isRequired = JsonUtils.getInstance().isRequired(req.getServletPath(), req.getPathInfo());
            System.out.println("Is Required: " + isRequired);
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("controller not found")){
                req.getSession().setAttribute("errorMessage", "Invalid URL");
                ((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/view/404.jsp");
                return;
            }
        }
        if (!isRequired) {
            if (JsonUtils.getInstance().isWelcomeFile(req.getServletPath())) {
                if (account == null) {
                    System.out.println("Not required and is mainPage");
                    chain.doFilter(request, response);
                } else {
                    String indexPage = JsonUtils.getInstance().getIndexByRole(account.getRoleID().getName());
                    System.out.println("not required and redirect to homepage");
                    ((HttpServletResponse) response).sendRedirect(req.getContextPath() + indexPage);
                }
            } else {
                System.out.println("not required and not mainpage");
                chain.doFilter(request, response);
            }
        } else {
            if (account == null) {
                req.getSession().setAttribute("message", "Sign in to continue");
                System.out.println("required and have to sign in");
                ((HttpServletResponse) response).sendRedirect(req.getContextPath());
            } else {
                try {
                    boolean isAccessible = JsonUtils.getInstance().havePermission(req.getServletPath(), req.getPathInfo(), account.getRoleID().getName());
                    if (isAccessible) {
                        System.out.println("Account is not null and have permission to continue");
                        chain.doFilter(request, response);
                    } else {
                        System.out.println("Account is not null and don't have permission");
                        ((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/view/error.jsp");
                    }
                } catch (Exception e) {
                    System.out.println("Error on filtering: " + e.getMessage());
                    ((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/view/404.jsp");
                }
            }
        }
    }

    private Account getFromCookie(ServletRequest request) {
        return null;
    }
}
