package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IOrderDAO;
import com.fptuni.fms.mapper.OrderDetailMapper;
import com.fptuni.fms.mapper.OrderMapper;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.paging.Pageable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author LucasBV
 */
public class OrderDAO extends AbstractDAO<Orders> implements IOrderDAO {

    @Override
    public List<Orders> getOrders(Pageable pageable, Map<String, String> searcher) {
        String sql = "SELECT ID, StoreID, Total, CreatedDate\n" +
                "FROM Orders\n" +
                "WHERE IsDeleted = 0 AND StoreID =  ?";
        if (searcher.get("totalAmount") != null && !searcher.get("totalAmount").isEmpty())
            sql += " AND Total = " + searcher.get("totalAmount");
        if (searcher.get("startDate") != null && !searcher.get("startDate").isEmpty())
            sql += " AND CONVERT(VARCHAR, CreatedDate, 103)  >= CONVERT(VARCHAR, '" + searcher.get("startDate") + "', 103)";
        if (searcher.get("endDate") != null && !searcher.get("endDate").isEmpty())
            sql += " AND CONVERT(VARCHAR, CreatedDate, 103)  <= CONVERT(VARCHAR, '" + searcher.get("endDate") + "', 103)";

        List<Orders> orders = query(sql, new OrderMapper(), searcher.get("storeID"));
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
        String sql = "insert into Orders (StoreID, Total, CreatedDate)\n" +
                "values (?,?,?)";
        return insert(sql, orders.getStoreID().getId(), orders.getTotal(), orders.getCreatedDate());
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
    public BigDecimal GetTotalValueOfStore(Integer storeID, Date startDate, Date endDate) {
        String sql = "select sum(total)\n" +
                "from Orders\n" +
                "where StoreID = ? and CreatedDate between ? and ?\n" +
                "group by StoreID";

        return sum(sql, storeID, startDate, endDate);
    }

    @Override
    public Integer GetOrderQuantity(Integer storeID, Date startDate, Date endDate) {
        String sql = "select count(ID)\n" +
                "from Orders\n" +
                "where StoreID = ? and CreatedDate between ? and ?\n" +
                "group by StoreID";
        return count(sql, storeID, startDate, endDate);
    }

    @Override
    public BigDecimal GetTotalValueOfAllStore(Date startDate, Date endDate) {
        String sql = "select sum(total)\n" +
                "from Orders\n" +
                "where CreatedDate between ? and ?\n";

        return sum(sql, startDate, endDate);
    }

    @Override
    public int GetTotalOrderOfAllStore(Date startDate, Date endDate) {
        String sql = "select count(ID)\n" +
                "from Orders\n" +
                "where CreatedDate between ? and ?";

        return count(sql, startDate, endDate);
    }

    @Override
    public Integer GetTotalOrderByTime(Date date) {
        String sql = "select count(ID)\n" +
                "from Orders\n" +
                "where CONVERT(DATE,CreatedDate) \n" +
                "=     CONVERT(DATE,CAST(? AS DATETIME))";
        return count(sql, date);
    }

    @Override
    public BigDecimal GetTotalValueByTime(Date date) {
        String sql = "select sum(total)\n" +
                "from Orders\n" +
                "where CONVERT(DATE,CreatedDate) \n" +
                "=     CONVERT(DATE,CAST(? AS DATETIME))";

        return sum(sql, date);
    }

    @Override
    public BigDecimal GetTotalValueToday(Date time1, Date time2) {
        String sql = "select sum(total)\n" +
                "from Orders\n" +
                "where CreatedDate between ? and ? ";

        return sum(sql, time1, time2);
    }

    @Override
    public Integer GetTotalOrderToday(Date time1, Date time2) {
        String sql = "select count(ID)\n" +
                "from Orders\n" +
                "where CreatedDate between ? and ? ";

        return count(sql, time1, time2);
    }
}
