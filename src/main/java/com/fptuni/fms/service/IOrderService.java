package com.fptuni.fms.service;

import com.fptuni.fms.model.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IOrderService {

    void index(HttpServletRequest request, HttpServletResponse response);

    void addNewProduct(HttpServletRequest request, HttpServletResponse response);

    void removeProduct(HttpServletRequest request, HttpServletResponse response);
    List<Orders> getOrders(HttpServletRequest request, HttpServletResponse response);
}
