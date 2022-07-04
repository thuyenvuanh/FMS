package com.fptuni.fms.service;

import com.fptuni.fms.model.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getOrderDetailByOrderID(HttpServletRequest request, HttpServletResponse response);
}
