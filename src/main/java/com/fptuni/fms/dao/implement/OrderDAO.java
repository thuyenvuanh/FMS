package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IOrderDAO;
import com.fptuni.fms.mapper.OrderDetailMapper;
import com.fptuni.fms.mapper.OrderMapper;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.paging.Pageable;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author LucasBV
 */
public class OrderDAO extends AbstractDAO<Orders> implements IOrderDAO {

    @Override
    public List<Orders> getOrders(Pageable pageable, Map<String, String> searcher) {
        List<Object> param = new ArrayList<>();
        String sql = "SELECT ID, StoreID, Total, CreatedDate\n" +
                "FROM Orders\n" +
                "WHERE IsDeleted = 0 AND StoreID =  ?";
        param.add(searcher.get("storeID"));
        if (searcher.get("startDate") != null && !searcher.get("startDate").isEmpty()) {
            sql += " AND CONVERT(DATE, CreatedDate, 103)  >= CONVERT(DATE, ? , 103)";
            param.add(searcher.get("startDate"));
        }
        if (searcher.get("endDate") != null && !searcher.get("endDate").isEmpty()) {
            sql += " AND CONVERT(DATE, CreatedDate, 103)  <= CONVERT(DATE, ? , 103)";
            param.add(searcher.get("endDate"));
        }
        if (searcher.get("totalAmount") != null && !searcher.get("totalAmount").isEmpty()) {
            sql += " AND Total = ?";
            param.add(searcher.get("totalAmount"));
        }
        Object[] arr = param.toArray();
        List<Orders> orders = query(sql, new OrderMapper(), arr);
        return orders;
    }

    @Override
    public Orders getOrderById(int id) {
        String sql = "SELECT o.ID, o.Total, o.CreatedDate, s.ID AS storeID\n"
                + "FROM Orders o\n"
                + "JOIN Store s ON o.StoreID = s.ID\n"
                + "WHERE o.ID = ?";
        List<Orders> orders = query(sql, new OrderMapper(), id);
        return orders.isEmpty() ? null : orders.get(0);
    }

    @Override
    public Integer insertOrder(Orders orders) {
        String sql = "INSERT INTO Orders\n" +
                "VALUES (?,?,?)";
        return insert(sql, orders.getStoreID(), orders.getTotal(), orders.getCreatedDate());
    }

    @Override
    public boolean updateOrder(int id, int storeID, double total, Timestamp createdDate) {
        String sql = "UPDATE Orders\n"
                + "SET \n"
                + "StoreID = ?,\n"
                + "Total = ?,\n"
                + "CreatedDate = CAST(? AS DATETIME)\n"
                + "WHERE ID = ?";

        return update(sql, storeID, total, createdDate, id);
    }

    @Override
    public int countNumberOfOrders() {
        String sql = "SELECT COUNT(ID) AS ID FROM Orders";
        List<Orders> orders = query(sql, new OrderMapper());
        return orders == null ? 0 : orders.get(0).getId();
    }
}
