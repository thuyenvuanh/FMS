package com.fptuni.fms.mapper;

import com.fptuni.fms.dao.implement.AbstractDAO;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Product;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author NhatTan
 */
public class OrderDetailMapper extends AbstractDAO<OrderDetail> implements RowMapper{
    @Override
    public OrderDetail mapRow(ResultSet rs) {
        OrderDetail orderdetail = null;
        ResultSetMetaData metaData = null;
        try {
            orderdetail = new OrderDetail();
            metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (metaData.getColumnLabel(i).equals("OrderID"))
                    orderdetail.setOrders(rs.getInt(i) == 0 ? null : new Orders(rs.getInt(i)));
                else if (metaData.getColumnLabel(i).equals("ProID"))
                    orderdetail.setProduct(rs.getString(i) == null ? null : new Product(rs.getString(i)));
                else if (metaData.getColumnLabel(i).startsWith("Price"))
                    orderdetail.setPrice(rs.getBigDecimal(i));
                else if (metaData.getColumnLabel(i).equals("Quantity"))
                    orderdetail.setQuantity(rs.getInt(i));
                else if (metaData.getColumnLabel(i).equals("Amount"))
                    orderdetail.setAmount(rs.getBigDecimal(i));
            }
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
