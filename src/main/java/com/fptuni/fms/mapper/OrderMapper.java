package com.fptuni.fms.mapper;

import com.fptuni.fms.dao.implement.AbstractDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Store;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LucasBV
 */
public class OrderMapper extends AbstractDAO<Orders> implements RowMapper {

    @Override
    public Orders mapRow(ResultSet rs) {
        Orders orders = null;
        try {
            orders = new Orders();
            Store store = new Store();
            Account account = new Account();
            orders.setId(rs.getInt("ID"));
            orders.setTotal(rs.getBigDecimal("Total"));
            orders.setCreatedDate(rs.getTimestamp("CreatedDate"));
            orders.setStoreID(new Store(rs.getInt("storeID")));
//            store.setId(rs.getInt("storeID"));
//            store.setName(rs.getString("Name"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
}
