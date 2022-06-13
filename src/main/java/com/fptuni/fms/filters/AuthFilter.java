package com.fptuni.fms.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean logedIn = ((HttpServletRequest) request).getSession().getAttribute("account") != null;
        System.out.println("Already loged In = " + logedIn);

        chain.doFilter(request, response);
    }
}
