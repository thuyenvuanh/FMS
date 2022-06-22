package com.fptuni.fms.dao;

import com.fptuni.fms.model.Orders;
import com.fptuni.fms.paging.Pageable;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LucasBV
 */
public interface IOrderDAO extends GenericDAO<Orders> {

    List<Orders> getOrders(Pageable pageable, Map<String,String> searcher);

    Orders getOrderById(int id);
    Integer insertOrder(Orders orders);
    boolean updateOrder(int id, int storeID, double total, Timestamp createdDate);
}
