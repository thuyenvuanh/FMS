package com.fptuni.fms.mapper;

import com.fptuni.fms.dao.implement.AbstractDAO;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Store;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author LucasBV
 */
public class OrderMapper extends AbstractDAO<Orders> implements RowMapper {

    @Override
    public Orders mapRow(ResultSet rs) {
        Orders orders = null;
        ResultSetMetaData metaData = null;
        try {
            metaData = rs.getMetaData();
            orders = new Orders();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    orders.setId(rs.getInt(i));
                else if (metaData.getColumnLabel(i).equals("Total"))
                    orders.setTotal(rs.getBigDecimal(i));
                else if (metaData.getColumnLabel(i).equals("CreatedDate"))
                    orders.setCreatedDate(rs.getDate(i));
                else if (metaData.getColumnLabel(i).equals("StoreID"))
                    orders.setStoreID(rs.getInt(i) == 0 ? null : new Store(rs.getInt(i)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
}
