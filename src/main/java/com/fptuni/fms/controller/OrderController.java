package com.fptuni.fms.controller;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.service.IOrderService;
import com.fptuni.fms.service.implement.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "OrderController", value = "/order/*")
public class OrderController extends HttpServlet {

    private static final OrderService orderService = new OrderService();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/index":
                orderService.index(request, response);
                break;
            case "/category":
                orderService.loadProducts(request, response);
                break;
            case "/add":
                orderService.addNewProduct(request, response);
                break;
            case "/voidAll":
                orderService.voidAll(request, response);
                break;
            case"/remove":
                orderService.removeProduct(request, response);
                break;
            case "/list":
                List<Orders> orders = orderService.getOrders(request, response);

                request.setAttribute("orders", orders);
                request.getRequestDispatcher( "/view/store/orderList.jsp").forward(request, response);
        }
//        response.sendRedirect(request.getContextPath()+"/cashier");
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
