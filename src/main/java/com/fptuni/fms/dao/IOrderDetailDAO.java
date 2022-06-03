package com.fptuni.fms.dao;

import com.fptuni.fms.model.OrderDetail;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author NhatTan
 */
public interface IOrderDetailDAO extends GenericDAO<OrderDetail> {

    public List<OrderDetail> getOrderDetail();

    public boolean createOrderDetail(OrderDetail orderDetail);
}
