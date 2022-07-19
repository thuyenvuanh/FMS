package com.fptuni.fms.mapper;

import com.fptuni.fms.dao.implement.AbstractDAO;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Store;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LucasBV
 */
public class OrderMapper extends AbstractDAO<Orders> implements RowMapper {

    @Override
    public Orders mapRow(ResultSet rs) {
        Orders orders = null;
        ResultSetMetaData metaData = null;
        Calendar calendar = Calendar.getInstance();

        try {
            metaData = rs.getMetaData();
            orders = new Orders();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    orders.setId(rs.getInt("ID"));
                else if (metaData.getColumnLabel(i).equals("Total"))
                    orders.setTotal(rs.getBigDecimal("Total"));
                else if (metaData.getColumnLabel(i).equals("CreatedDate")) {
                    Timestamp timestamp = new Timestamp(rs.getTimestamp("CreatedDate").getTime());
                    orders.setCreatedDate(rs.getDate("CreatedDate"));
                    orders.setCreatedDateTime(timestamp);
                } else if (metaData.getColumnLabel(i).equals("StoreID"))
                    orders.setStoreID(new Store(rs.getInt("StoreID")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
}
