package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IOrderDetailDAO;
import com.fptuni.fms.mapper.OrderDetailMapper;
import com.fptuni.fms.model.OrderDetail;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author NhatTan
 */
public class OrderDetailDAO extends AbstractDAO<OrderDetail> implements IOrderDetailDAO {
    @Override
    public List<OrderDetail> getOrderDetailsByOrderID(int orderID, int storeID) {
        String sql = "SELECT OrderID, ProID, Price, Quantity, Amount\n" +
                "FROM OrderDetail od\n" +
                "JOIN Orders o \n" +
                "ON od.OrderID = o.ID AND od.OrderID = ? AND o.IsDeleted = 0 AND od.IsDeleted = 0 AND o.StoreID = " + storeID;
        List<OrderDetail> orderDetailList = query(sql, new OrderDetailMapper(), orderID);
        return orderDetailList;
    }
    @Override
    public int createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO dbo.OrderDetail(OrderID, ProID, Price, Quantity) VALUES (?,?,?,?)";
        BigDecimal quantity = new BigDecimal(orderDetail.getQuantity());
        BigDecimal amount = quantity.multiply(orderDetail.getPrice());
        return insert(sql, orderDetail.getOrders().getId(), orderDetail.getProduct().getId(), orderDetail.getPrice(), orderDetail.getQuantity());
    }

}
