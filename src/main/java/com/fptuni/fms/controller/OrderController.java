package com.fptuni.fms.controller;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.service.IOrderDetailService;
import com.fptuni.fms.service.IOrderService;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.service.implement.OrderDetailService;
import com.fptuni.fms.service.implement.OrderService;
import com.fptuni.fms.service.implement.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "OrderController", value = "/order/*")
public class OrderController extends HttpServlet {

    private static final OrderService orderService = new OrderService();
    private static final IOrderDetailService orderDetailServiceService = new OrderDetailService();
    private static final IProductService productService = new ProductService();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        System.out.println(request.getRequestURI());
        switch (action) {
            case "/index":
                orderService.index(request, response);
                response.sendRedirect(request.getContextPath() + "/cashier");
                break;
            case "/add":
                orderService.addNewProduct(request, response);
                response.sendRedirect(request.getContextPath() + "/cashier");
                break;
            case "/voidAll":
                orderService.voidAll(request, response);
                response.sendRedirect(request.getContextPath() + "/cashier");
                break;
            case "/remove":
                orderService.removeProduct(request, response);
                response.sendRedirect(request.getContextPath() + "/cashier");
                break;
            case "/list":
                List<Orders> orders = orderService.getOrders(request, response);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/view/store/orderList.jsp").forward(request, response);
                break;
            case "/view":
                List<OrderDetail> orderDetails = orderDetailServiceService.getOrderDetailByOrderID(request, response);
                List<Product> products = productService.getProductByOrderID(request, response);
//                List<Orders> ordersList = orderService.getOrders(request, response);
//                Orders order = null;
//                int orderID = Integer.parseInt(request.getParameter("orderID"));
//                for (Orders o : ordersList) {
//                    if (orderID == o.getId()) {
//                        request.setAttribute("total", o.getTotal());
//                        request.setAttribute("createDate", o.getCreatedDate());
//                        break;
//                    }
//                }
                Orders order = orderService.getOrder(request, response);
                request.setAttribute("order", order);
                request.setAttribute("orderDetail", orderDetails);
                request.setAttribute("products", products);
                request.getRequestDispatcher("/view/store/orderDetail.jsp").forward(request, response);
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
