package com.fptuni.fms.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "ProductController", urlPatterns = "/product/*")
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(path);
        if (path.equals("/list")) {
            request.setAttribute("test", "test");
//            request.getRequestDispatcher("productList.jsp").forward(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("name","binh");
            response.sendRedirect(request.getContextPath() + "/productList.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET method");
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST method");
        processRequest(request, response);
    }
}
