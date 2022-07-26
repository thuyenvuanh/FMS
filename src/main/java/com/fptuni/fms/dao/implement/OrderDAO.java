package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IOrderDAO;
import com.fptuni.fms.mapper.OrderDetailMapper;
import com.fptuni.fms.mapper.OrderMapper;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Payment;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.Pageable;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
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
        if (searcher.get("amount") != null && !searcher.get("amount").isEmpty()) {
            sql += " AND Total = ?";
            param.add(searcher.get("amount"));
        }
        if (pageable != null) {
            if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
                String orderBy = pageable.getSorter().isAscending() ? " ASC " : " DESC ";
                sql += " ORDER BY  " + pageable.getSorter().getSortField() + " " + orderBy;
            }
            if (pageable.getOffset() != null && pageable.getLimit() != null) {
                sql += " OFFSET " + pageable.getOffset() + " ROWS\n " +
                        " FETCH NEXT " + pageable.getLimit() + " ROW ONLY";
            }
        }
        Object[] arr = param.toArray();
        List<Orders> orders = query(sql, new OrderMapper(), arr);
        return orders;
    }

    public List<Orders> searchOrdersByStore(Store store, Map<String, String> searcher) {
        List<String> param = new ArrayList<>();
        String sql = "SELECT ID, StoreID, Total, CreatedDate\n" +
                "FROM Orders\n" +
                "WHERE IsDeleted = 0 AND StoreID =  ?";
        param.add(String.valueOf(store.getId()));
        if (searcher.get("startDate") != null && !searcher.get("startDate").isEmpty()) {
            sql += " AND CONVERT(DATE, CreatedDate, 103)  >= CONVERT(DATE, ? , 103)";
            param.add(searcher.get("startDate"));
        }
        if (searcher.get("endDate") != null && !searcher.get("endDate").isEmpty()) {
            sql += " AND CONVERT(DATE, CreatedDate, 103)  <= CONVERT(DATE, ? , 103)";
            param.add(searcher.get("endDate"));
        }
        if (searcher.get("amount") != null && !searcher.get("amount").isEmpty()) {
            sql += " AND Total = ?";
            param.add(searcher.get("amount"));
        }
        return query(sql, new OrderMapper(), param.toArray());
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
                "where StoreID = ? and CONVERT(DATE,CreatedDate)  between ? and ?\n" +
                "group by StoreID";

        return sum(sql, storeID, startDate, endDate);
    }

    @Override
    public Integer GetOrderQuantity(Integer storeID, Date startDate, Date endDate) {
        String sql = "select count(ID)\n" +
                "from Orders\n" +
                "where StoreID = ? and CONVERT(DATE,CreatedDate)  between ? and ?\n" +
                "group by StoreID";
        return count(sql, storeID, startDate, endDate);
    }

    @Override
    public BigDecimal GetTotalValueOfAllStore(Date startDate, Date endDate) {
        String sql = "select sum(total)\n" +
                "from Orders\n" +
                "where CONVERT(DATE,CreatedDate)  between ? and ?\n";

        return sum(sql, startDate, endDate);
    }

    @Override
    public int GetTotalOrderOfAllStore(Date startDate, Date endDate) {
        String sql = "select count(ID)\n" +
                "from Orders\n" +
                "where CONVERT(DATE,CreatedDate)  between ? and ?";

        return count(sql, startDate, endDate);
    }

    @Override
    public Integer GetTotalOrderByDate(Date date) {
        String sql = "select count(ID)\n" +
                "from Orders\n" +
                "where CONVERT(DATE,CreatedDate) \n" +
                "=     CONVERT(DATE,CAST(? AS DATETIME))";
        return count(sql, date);
    }

    @Override
    public BigDecimal GetTotalValueByDate(Date date) {
        String sql = "select sum(total)\n" +
                "from Orders\n" +
                "where CONVERT(DATE,CreatedDate) \n" +
                "=     CONVERT(DATE,CAST(? AS DATETIME))";

        return sum(sql, date);
    }

    @Override
    public Integer GetTotalOrderByTime(Date startTime, Date endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = simpleDateFormat.format(startTime);
        String end = simpleDateFormat.format(endTime);
        String sql = "select count(ID)\n" +
                "from Orders\n" +
                "where CreatedDate between ? and ? ";

        return count(sql, start, end);
    }

    @Override
    public BigDecimal GetTotalValueByTime(Date startTime, Date endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = simpleDateFormat.format(startTime);
        String end = simpleDateFormat.format(endTime);
        String sql = "select sum(total)\n" +
                "from Orders\n" +
                "where CreatedDate between ? and ? ";

        return sum(sql, start, end);
    }


    @Override
    public List<Orders> getOrdersByDate(Store store, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = simpleDateFormat.format(date);
        String sql = "SELECT ID, StoreID, Total, CreatedDate, IsDeleted FROM Orders\n" +
                "WHERE CONVERT(date, CreatedDate, 103) = CONVERT(date, ? , 103)\n" +
                "AND IsDeleted = 0 AND StoreID = ?";
        List<Orders> orders = query(sql, new OrderMapper(), dateStr, store.getId());
        return orders;
    }

    @Override
    public List<Orders> getOrdersByTimeRange(Store store, Date startTime, Date endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = simpleDateFormat.format(startTime);
        String end = simpleDateFormat.format(endTime);
        String sql = "SELECT ID, StoreID, Total, CreatedDate FROM Orders\n" +
                "WHERE CreatedDate between CONVERT(datetime, ? ,120) and CONVERT(datetime, ? ,120)\n" +
                "AND StoreID = ? AND IsDeleted = 0";
        return query(sql, new OrderMapper(), start, end, store.getId());
    }

    @Override
    public Orders getOrdersByPaymentID(int paymentID, Store store) {
        String sql = "SELECT O.ID, O.Total FROM Orders O\n" +
                "JOIN (SELECT ID, OrderID FROM Payment WHERE ID = ?) SUB ON O.ID = SUB.OrderID AND O.StoreID = ?\n";
        List<Orders> orders = query(sql, new OrderMapper(), paymentID, store.getId());
        return orders == null ? null : orders.get(0);
    }
}
