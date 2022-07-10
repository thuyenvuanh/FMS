package com.fptuni.fms.controller;

import com.fptuni.fms.service.implement.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CategoryController", value = "/category/*")
public class CategoryController extends HttpServlet {

    private final CategoryService categoryService = new CategoryService();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/category":
                categoryService.loadProducts(request, response);
                response.sendRedirect(request.getContextPath() + "/cashier");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
