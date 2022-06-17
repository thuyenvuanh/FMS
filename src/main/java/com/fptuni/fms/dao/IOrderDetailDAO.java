package com.fptuni.fms.dao;

import com.fptuni.fms.model.OrderDetail;

import java.util.List;

/**
 *
 * @author NhatTan
 */
public interface IOrderDetailDAO extends GenericDAO<OrderDetail> {

    List<OrderDetail> getOrderDetail();

    int createOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> getOrderDetailByOrderID(int orderID);
}
