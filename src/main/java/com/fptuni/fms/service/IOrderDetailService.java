package com.fptuni.fms.service;

import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getOrderDetailByOrderID(HttpServletRequest request, HttpServletResponse response);

    OrderDetail getOrderDetailByProductID(HttpServletRequest request, HttpServletResponse response, String productID);

    BigDecimal getTotalAmount(HttpServletRequest request, HttpServletResponse response);

    BigDecimal getTotalAmountByDate(HttpServletRequest request, Date date);
    List<OrderDetail> getOrderDetailInDateRange(HttpServletRequest request, HttpServletResponse response);
    List<OrderDetail> getOrderDetailInTimeRange(HttpServletRequest request, Date start, Date end);
    BigDecimal getTotalAmountOfList(List<OrderDetail> orderDetails);
}
