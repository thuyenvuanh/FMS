package com.fptuni.fms.mapper;

import com.fptuni.fms.dao.implement.AbstractDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Product;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author NhatTan
 */
public class OrderDetailMapper extends AbstractDAO<OrderDetail> implements RowMapper {
    @Override
    public OrderDetail mapRow(ResultSet rs) {
        OrderDetail orderdetail = null;
        Product product = null;
        try {
            orderdetail = new OrderDetail();
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    orderdetail.setOrders(new Orders(rs.getInt("ID")));
                if (metaData.getColumnLabel(i).equals("ProID")) {
                    product = new Product(rs.getString("ProID"));
                }
                if (metaData.getColumnLabel(i).equals("Name")) {
                    product.setName(rs.getString("Name"));
                }
                if (metaData.getColumnLabel(i).equals("CateID")) {
                    product.setCateID(new Category(rs.getInt("CateID")));
                }
                if (metaData.getColumnLabel(i).equals("Price"))
                    orderdetail.setPrice(rs.getBigDecimal("Price"));
                if (metaData.getColumnLabel(i).equals("Quantity"))
                    orderdetail.setQuantity(rs.getInt("Quantity"));
                if (metaData.getColumnLabel(i).equals("Amount"))
                    orderdetail.setAmount(rs.getBigDecimal("Amount"));
                if (metaData.getColumnLabel(i).equals("IsDeleted"))
                    orderdetail.setAmount(rs.getBigDecimal("IsDeleted"));
            }
            orderdetail.setProduct(product);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderdetail;
    }
}
