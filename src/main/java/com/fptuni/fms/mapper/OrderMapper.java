package com.fptuni.fms.mapper;

import com.fptuni.fms.dao.implement.AbstractDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Store;

import java.sql.ResultSet;
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
        Calendar calendar = Calendar.getInstance();

        try {
            orders = new Orders();
            orders.setId(rs.getInt("ID"));
            orders.setTotal(rs.getBigDecimal("Total"));
            orders.setCreatedDate(rs.getDate("CreatedDate"));
            orders.setStoreID(new Store(rs.getInt("StoreID")));
            Timestamp timestamp = new Timestamp(rs.getTimestamp("CreatedDate").getTime());
            orders.setCreatedDateTime(timestamp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
}
