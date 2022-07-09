package com.fptuni.fms.service;

import com.fptuni.fms.model.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getOrderDetailByOrderID(HttpServletRequest request, HttpServletResponse response);

    OrderDetail getOrderDetailByProductID(HttpServletRequest request, HttpServletResponse response, String productID);

    BigDecimal getTotalAmount(HttpServletRequest request, HttpServletResponse response);
}
