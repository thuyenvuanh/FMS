package com.fptuni.fms.controller;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.service.*;
import com.fptuni.fms.service.implement.*;
import com.fptuni.fms.utils.DateUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@WebServlet(name = "DashBoardController", urlPatterns = "/dashboard/*")
public class DashBoardController extends HttpServlet {
    private IOrderService orderService = new OrderService();
    private IProductService productService = new ProductService();
    private ICategoryService categoryService = new CategoryService();
    private IOrderDetailService orderDetailService = new OrderDetailService();
    private IDashBoardService dashBoardService = new DashBoardService();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getPathInfo();
        switch (page) {
            case "/store":
                List<Product> products = productService.getTop5ProductsOrderByAmount(request, response);
                List<Orders> orders = orderService.getOrders(request, response);
                int numberOfOrders = orders.size();
                // Create a Product with category name as Map object
                Map<Product, Category> productInfo = new LinkedHashMap<>();
                for (Product p : products) {
                    Category cate = categoryService.getCategory(p.getCateID().getId());
                    productInfo.put(p, cate);
                }
                // Create a full report of top 5 products
                // Key is map Product object; Value is OrderDetail
                Map<Map.Entry<Product, Category>, OrderDetail> top5Product = new LinkedHashMap<>();
                for (Map.Entry<Product, Category> map : productInfo.entrySet()) {
                    OrderDetail orderDetail = orderDetailService.getOrderDetailByProductID(request, response, map.getKey().getId());
                    top5Product.put(map, orderDetail);
                }
                BigDecimal totalAmount = orderDetailService.getTotalAmount(request, response) == null ? BigDecimal.valueOf(0) : orderDetailService.getTotalAmount(request, response);

                List<Date> dateRange = dashBoardService.getDateRange(request, response);

                request.setAttribute("numberOfOrders", numberOfOrders);
                request.setAttribute("top5Products", top5Product);
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("dateRange", dateRange);
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
