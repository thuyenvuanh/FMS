package com.fptuni.fms.filters;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AuthFilter implements Filter {
    private static HashMap<String, List<String>> servletMapper;

    public void init(FilterConfig config) throws ServletException {
        try {
            servletMapper = JsonUtils.getInstance().readJson("roles_permission.json");
        } catch (IOException e) {
            System.out.println("Servlet mapper for role not found");
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("=============================");
        System.out.println("Filtering on " + ((HttpServletRequest) request).getRequestURI());
        HttpServletRequest req = (HttpServletRequest) request;
        Account account = (Account) req.getSession().getAttribute("account");
        String svl = req.getServletPath(), pInfo = req.getPathInfo();
        System.out.println(svl);
        System.out.println(pInfo);
//        chain.doFilter(request, response);
        if (svl.equals("/view/error.jsp")) {
            chain.doFilter(request, response);
        } else if (account == null) {
            System.out.println("not sign in");
            List<String> available = servletMapper.get("none");
            for (String path : available) {
                if (svl.contains(path) || (svl+pInfo).contains(path)) {
                    System.out.println("svl = " + svl);
                    System.out.println("path = " + path);
                    chain.doFilter(request, response);
                    return;
                }
            }
//            req.getSession().setAttribute("message", "Sign in to continue");
            ((HttpServletResponse) response).sendRedirect(req.getContextPath());
        } else {
            List<String> available = servletMapper.get(account.getRole().getName());
            if (svl.equals("/view/authentication/index.jsp")) {
                ((HttpServletResponse) response).sendRedirect(req.getContextPath() + available.get(0));
                return;
            }
            for (String path: available) {
                if (svl.contains(path) || (svl+pInfo).contains(path)){
                    chain.doFilter(request, response);
                    return;
                }
            }
            req.getRequestDispatcher("/view/error.jsp").forward(request, response);
            chain.doFilter(request, response);
        }
    }
}
