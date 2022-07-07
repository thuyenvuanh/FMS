package com.fptuni.fms.dao;

import com.fptuni.fms.model.OrderDetail;

import java.util.List;

/**
 *
 * @author NhatTan
 */
public interface IOrderDetailDAO extends GenericDAO<OrderDetail> {
    List<OrderDetail> getOrderDetailsByOrderID(int orderID, int storeID);
    int createOrderDetail(OrderDetail orderDetail);
}
