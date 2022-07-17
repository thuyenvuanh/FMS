package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IOrderDetailDAO;
import com.fptuni.fms.mapper.OrderDetailMapper;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Store;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author NhatTan, Binh
 */
public class OrderDetailDAO extends AbstractDAO<OrderDetail> implements IOrderDetailDAO {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<OrderDetail> getOrderDetailsByOrderID(int orderID, int storeID) {
        String sql = "SELECT o.ID, od.ProID, p.Name, p.Price, od.Quantity, od.Amount FROM OrderDetail od\n" +
                " JOIN Orders o ON o.ID = od.OrderID AND o.ID = ? AND StoreID = " + storeID +
                " AND od.IsDeleted = 0 AND o.IsDeleted = 0\n" +
                " JOIN Product p on p.ID = od.ProID AND p.IsDeleted = 0";
        List<OrderDetail> orderDetailList = query(sql, new OrderDetailMapper(), orderID);
        return orderDetailList;
    }

    @Override
    public int createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO dbo.OrderDetail(OrderID, ProID, Price, Quantity, Amount) VALUES (?,?,?,?,?)";
        BigDecimal quantity = new BigDecimal(orderDetail.getQuantity());
        BigDecimal amount = quantity.multiply(orderDetail.getPrice());
        return insert(sql, orderDetail.getOrders(), orderDetail.getProduct(), orderDetail.getPrice(), orderDetail.getQuantity(), amount);
    }

    @Override
    public OrderDetail getOrderDetailByProductID(Store store, String productID, Date start, Date end) {
        String startdate = dateFormat.format(start);
        String enddate = dateFormat.format(end);
        String sql = "SELECT TOP 5  od.ProID, od.Price, od.Quantity, od.Amount FROM Orders o\n" +
                "JOIN OrderDetail od ON o.ID = od.OrderID AND o.StoreID = ?\n" +
                "AND CONVERT(date, o.CreatedDate, 103) between CONVERT(date, ?, 103) AND CONVERT(date, ?, 103) \n" +
                "AND o.IsDeleted = 0\n" +
                "JOIN Product p ON p.ID = od.ProID\n" +
                "WHERE p.ID = ?\n" +
                "ORDER BY od.Amount DESC";
        List<OrderDetail> orderDetails = query(sql, new OrderDetailMapper(), store.getId(), startdate, enddate, productID);
        return orderDetails == null ? null : orderDetails.get(0);
    }

    @Override
    public BigDecimal getTotalAmountByDate(Store store, Date date) {
        String dateString = dateFormat.format(date);
        String sql = "SELECT SUM(od.Amount) AS Amount FROM Orders o \n" +
                "JOIN OrderDetail od ON o.ID = od.OrderID AND o.StoreID = ? AND o.IsDeleted = 0\n" +
                "AND CONVERT(DATE, o.CreatedDate, 103) = CONVERT(DATE, ? , 103)";
        List<OrderDetail> orderDetails = query(sql, new OrderDetailMapper(), store.getId(), dateString);
        return orderDetails == null ? null : orderDetails.get(0).getAmount();
    }

    @Override
    public List<OrderDetail> getOrdersDetailByDateRange(Store store, Date start, Date end) {
        String startDate = dateFormat.format(start);
        String endDate = dateFormat.format(end);
        String sql = "SELECT o.ID, od.ProID, p.CateID, od.Price, od.Quantity, od.Amount FROM OrderDetail od\n" +
                "JOIN Orders o ON o.ID = od.OrderID AND o.StoreID = ? AND o.IsDeleted = 0 \n" +
                "AND CONVERT(date, o.CreatedDate, 103) between CONVERT(date, ?, 103) AND CONVERT(date, ?, 103) \n" +
                "JOIN Product p ON od.ProID = p.ID AND p.IsDeleted = 0";
        return query(sql, new OrderDetailMapper(), store.getId(), startDate, endDate);
    }

    @Override
    public List<OrderDetail> getOrdersDetailByTimeRange(Store store, Date startTime, Date endTime) {
        String startTimeStr = timeFormat.format(startTime);
        String endTimeStr = timeFormat.format(endTime);
        String sql = "SELECT od.ProID, od.Price, od.Quantity, od.Amount FROM OrderDetail od\n" +
                "JOIN Orders o ON o.ID = od.OrderID\n" +
                "AND o.CreatedDate BETWEEN CONVERT(datetime, ? ,120) AND CONVERT(datetime, ? ,120)\n" +
                "AND o.StoreID = ? AND o.IsDeleted = 0";
        return query(sql, new OrderDetailMapper(), startTimeStr, endTimeStr, store.getId());
    }
}
