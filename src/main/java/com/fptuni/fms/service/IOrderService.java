package com.fptuni.fms.service;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IOrderService {

    void index(HttpServletRequest request, HttpServletResponse response);

    void addNewProduct(HttpServletRequest request, HttpServletResponse response);

    void removeProduct(HttpServletRequest request, HttpServletResponse response);

    void voidAll(HttpServletRequest request, HttpServletResponse response);

    List<Orders> getOrders(HttpServletRequest request, HttpServletResponse response);

    Orders getOrder(HttpServletRequest request, HttpServletResponse response);

    List<Orders> getOrdersByDate(HttpServletRequest request, Date date);

    List<Orders> getOrdersByTimeRange(HttpServletRequest request, Date startTime, Date endTime);

    Orders getOrdersByPaymentID(HttpServletRequest request, int paymentID);
}
