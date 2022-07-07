package com.fptuni.fms.controller;

import com.fptuni.fms.model.Orders;
import com.fptuni.fms.service.IOrderService;
import com.fptuni.fms.service.implement.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashBoardController", urlPatterns = "/dashboard/*")
public class DashBoardController extends HttpServlet {
    private IOrderService orderService = new OrderService();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getPathInfo();
        switch (page) {
            case "/store":
                List<Orders> orders = orderService.getOrders(request, response);
                String s = request.getParameter("startDate");
                int numberOfOrders = orders.size();
                request.setAttribute("numberOfOrders", numberOfOrders);
                request.getRequestDispatcher("/view/store/dashBoard.jsp").forward(request, response);
                break;
        }
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
