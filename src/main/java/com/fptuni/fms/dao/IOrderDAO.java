package com.fptuni.fms.dao;

import com.fptuni.fms.model.Orders;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author LucasBV
 */
public interface IOrderDAO extends GenericDAO<Orders> {

    List<Orders> getOrders();

    Orders getOrderById(int id);
    Integer insertOrder(Orders orders);
    boolean updateOrder(int id, int storeID, double total, Timestamp createdDate);
}
