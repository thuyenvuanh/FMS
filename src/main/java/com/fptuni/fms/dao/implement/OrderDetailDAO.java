package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IOrderDetailDAO;
import com.fptuni.fms.mapper.OrderDetailMapper;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author NhatTan
 */
public class OrderDetailDAO extends AbstractDAO<OrderDetail> implements IOrderDetailDAO {
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startdate = simpleDateFormat.format(start);
        String enddate = simpleDateFormat.format(end);
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
}
