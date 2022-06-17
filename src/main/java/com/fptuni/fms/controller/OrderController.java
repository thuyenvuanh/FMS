package com.fptuni.fms.controller;

import com.fptuni.fms.service.implement.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderController", value = "/order/*")
public class OrderController extends HttpServlet {

    private final OrderService orderService = new OrderService();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet Path: " + request.getServletPath());
        System.out.println("Path Info: " + request.getPathInfo());
        //lay order tu session hoac tao moi
        String action = request.getPathInfo();
        switch (action) {
            case "/index":
                orderService.index(request, response);
                break;
            case "/add":
                System.out.println("Controller called: " + request.getParameter("id"));
                orderService.addNewProduct(request, response);
                break;
            case"/remove":
                orderService.removeProduct(request, response);
                break;
        }
        response.sendRedirect(request.getContextPath()+"/cashier");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
