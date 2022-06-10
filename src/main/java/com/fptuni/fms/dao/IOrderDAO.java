package com.fptuni.fms.dao;

import com.fptuni.fms.model.Orders;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LucasBV
 */
public interface IOrderDAO extends GenericDAO<Orders> {

    public List<Orders> getOrders();

    public Orders getOrderById(int id);
    public Integer insertOrder(Orders orders);

    public boolean updateOrder(int id, int storeID, double total, Timestamp createdDate);
}
