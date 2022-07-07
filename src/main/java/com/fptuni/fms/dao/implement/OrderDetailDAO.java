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
    public List<OrderDetail> getOrderDetail() {
        String sql = "SELECT OrderID, ProID, Price, Quantity, Amount FROM dbo.OrderDetail";
        List<OrderDetail> orderdetail = query(sql, new OrderDetailMapper());
        return orderdetail.isEmpty() ? null : orderdetail;
    }

    @Override
    public int createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO dbo.OrderDetail(OrderID, ProID, Price, Quantity) VALUES (?,?,?,?)";
        BigDecimal quantity = new BigDecimal(orderDetail.getQuantity());
        BigDecimal amount = quantity.multiply(orderDetail.getPrice());
        return insert(sql, orderDetail.getOrders().getId(), orderDetail.getProduct().getId(), orderDetail.getPrice(), orderDetail.getQuantity());
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderID(int orderID) {
        String sql = "SELECT OrderID, ProID, Price, Quantity, Amount FROM dbo.OrderDetail WHERE OrderID=?";
        List<OrderDetail> orderdetail = query(sql, new OrderDetailMapper(), orderID);
        return orderdetail.isEmpty() ? null : orderdetail;
    }


}
