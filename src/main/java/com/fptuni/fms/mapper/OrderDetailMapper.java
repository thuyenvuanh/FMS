package com.fptuni.fms.mapper;

import com.fptuni.fms.dao.implement.AbstractDAO;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author NhatTan
 */
public class OrderDetailMapper extends AbstractDAO<OrderDetail> implements RowMapper{
    @Override
    public OrderDetail mapRow(ResultSet rs) {
        OrderDetail orderdetail = null;
        try {
            orderdetail = new OrderDetail();
            orderdetail.setPrice(rs.getBigDecimal("Price"));
            orderdetail.setQuantity(rs.getInt("Quantity"));
            orderdetail.setAmount(rs.getBigDecimal("Amount"));
            orderdetail.setOrders(new Orders(rs.getInt("OrderID")));
            orderdetail.setProduct(new Product(rs.getString("ProID")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderdetail;
    }
}
