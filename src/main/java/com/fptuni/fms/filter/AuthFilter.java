package com.fptuni.fms.filter;

import com.fptuni.fms.model.Account;
import com.fptuni.fms.utils.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class AuthFilter implements Filter {
    private static HashMap<String, List<String>> servletMapper;
    private static List<String> available = new ArrayList<>();

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
        available.clear();
        if (servletMapper == null) init(null);
        System.out.println("=============================");
        System.out.println("Filtering on " + ((HttpServletRequest) request).getRequestURI());
        HttpServletRequest req = (HttpServletRequest) request;
        Account account = (Account) req.getSession().getAttribute("account");
        available.addAll(servletMapper.get(account == null ? "none": account.getRole().getName()));
        String svl = req.getServletPath(), pInfo = req.getPathInfo();
        System.out.println(account == null ? "not sign in" : "signed in with username " + account.getUsername());
        if (svl.equals("/view/error.jsp")) {
            chain.doFilter(request, response);
        }
        if (svl.equals("/view/authentication/index.jsp") && !available.get(0).equals(svl)) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + available.get(0));
            return;
        }
        if (hasPermission(svl, pInfo)){
            chain.doFilter(request, response);
        } else {
            if (account == null) {
                req.getSession().setAttribute("message", "Sign in to continue");
                ((HttpServletResponse) response).sendRedirect(req.getContextPath());
            } else {
                ((HttpServletResponse) response).sendError(403, "This account do not meet requirements to access this site");
            }
        }
//        if (account == null) {
//            if (hasPermission(request, response, chain, svl, pInfo, available)) {
//                chain.doFilter(request, response);
//            } else {
//            }
//        } else {
//            if (hasPermission(request, response, chain, svl, pInfo, available))
//                chain.doFilter(request, response);
//            else
//                ((HttpServletResponse) response).sendError(403, "This account do not meet requirements to access this site");
//        }
    }

    private boolean hasPermission(String svl, String pInfo) throws IOException, ServletException {

        Optional<String> value = available.stream().filter(s -> svl.contains(s) || (svl+pInfo).contains(s)).findFirst();
        return value.isPresent();

//        return available.stream().anyMatch(s -> s.equals(svl) || s.equals(svl + pInfo));
    }
}
