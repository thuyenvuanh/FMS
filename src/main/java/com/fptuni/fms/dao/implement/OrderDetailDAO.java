package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IOrderDetailDAO;
import com.fptuni.fms.mapper.OrderDetailMapper;
import com.fptuni.fms.model.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author NhatTan
 */
public class OrderDetailDAO extends AbstractDAO<OrderDetail> implements IOrderDetailDAO {
    @Override
    public List<OrderDetail> getOrderDetailsByOrderID(int orderID, int storeID) {
        String sql = "SELECT o.ID, od.ProID, p.Name, p.Price, od.Quantity, od.Amount FROM OrderDetail od\n" +
                " JOIN Orders o ON o.ID = od.OrderID AND o.ID = ? AND StoreID = " + storeID +
                " AND od.IsDeleted = 0 AND o.IsDeleted = 0\n" +
                " JOIN Product p on p.ID = od.ProID AND p.IsDeleted = 0";
        List<OrderDetail> orderDetailList = query(sql, new OrderDetailMapper(), orderID);
        return orderDetailList;
    }

    @Override
    public int createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO dbo.OrderDetail(OrderID, ProID, Price, Quantity, Amount) VALUES (?,?,?,?,?)";
        BigDecimal quantity = new BigDecimal(orderDetail.getQuantity());
        BigDecimal amount = quantity.multiply(orderDetail.getPrice());
        return insert(sql, orderDetail.getOrders(), orderDetail.getProduct(), orderDetail.getPrice(), orderDetail.getQuantity(), amount);
    }

}
