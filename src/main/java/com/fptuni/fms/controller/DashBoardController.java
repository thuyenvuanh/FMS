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
import java.awt.geom.RoundRectangle2D;
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
                List<BigDecimal> totalAmountEachDate = new ArrayList<>();
                List<Integer> numberOfOrderEachDate = new ArrayList<>();

                for (Date date : dateRange) {
                    BigDecimal t = new BigDecimal(0);
                    int n = 0;
                    // get total amount per date
                    if (orderDetailService.getTotalAmountByDate(request, date) != null) {
                        t = orderDetailService.getTotalAmountByDate(request, date);
                    }
                    totalAmountEachDate.add(t);
                    // get number of orders per date
                    List<Orders> ordersPerDate = orderService.getOrdersByDate(request, date);
                    if (ordersPerDate != null) {
                        n = ordersPerDate.size();
                    }
                    numberOfOrderEachDate.add(n);
                }
                List<Category> categories = categoryService.getCategories();
                List<Double> percentageOfProductInCategory = productService.getPercentageOfProductInCategory(request, response);

//                List<OrderDetail> orderDetails = orderDetailService.getOrderDetailInDateRange(request, response);
//                List<Integer> numberOfProductsEachID = new ArrayList<>();
//                String tempProductID = orderDetails.get(0).getProduct().getId();
//                int n = 1;
//                for (OrderDetail od : orderDetails) {
//                    if (od.getProduct().getId().equals(tempProductID)) {
//                        n++;
//                    } else {
//                        n = 1;
//                        tempProductID = od.getProduct().getId();
//                        numberOfProductsEachID.add(n);
//                    }
//                }
                request.setAttribute("numberOfOrders", numberOfOrders);
                request.setAttribute("top5Products", top5Product);
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("dateRange", dateRange);
                request.setAttribute("totalAmountEachDate", totalAmountEachDate);
                request.setAttribute("numberOfOrderEachDate", numberOfOrderEachDate);
                request.setAttribute("categories", categories);
                request.setAttribute("percentageOfProductInCategory", percentageOfProductInCategory);
//                request.setAttribute("numberOfProductsEachID", numberOfProductsEachID);
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
