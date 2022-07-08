package com.fptuni.fms.dao;

import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;

import java.util.Date;
import java.util.List;

/**
 *
 * @author NhatTan
 */
public interface IOrderDetailDAO extends GenericDAO<OrderDetail> {
    List<OrderDetail> getOrderDetailsByOrderID(int orderID, int storeID);
    int createOrderDetail(OrderDetail orderDetail);
    OrderDetail getOrderDetailByProductID(Store store, String productID, Date start, Date end);


}
