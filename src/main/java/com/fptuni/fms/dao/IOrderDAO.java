package com.fptuni.fms.dao;

import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.Pageable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author LucasBV
 */
public interface IOrderDAO extends GenericDAO<Orders> {

    List<Orders> getOrders(Pageable pageable, Map<String, String> searcher);

    Orders getOrderById(int id);

    Integer insertOrder(Orders orders);

    boolean updateOrder(int id, int storeID, double total, Timestamp createdDate);

    BigDecimal GetTotalValueOfStore(Integer storeID, Date startDate, Date endDate);

    Integer GetOrderQuantity(Integer storeID, Date startDate, Date endDate);

    BigDecimal GetTotalValueOfAllStore(Date startDate, Date endDate);

    int GetTotalOrderOfAllStore(Date startDate, Date endDate);

    Integer GetTotalOrderByDate(Date date);

    BigDecimal GetTotalValueByDate(Date date);

    Integer GetTotalOrderByTime(Date startTime, Date endTime);

    BigDecimal GetTotalValueByTime(Date startTime, Date endTime);


    int countNumberOfOrders();

    List<Orders> getOrdersByDate(Store store, Date date);

    List<Orders> getOrdersByTimeRange(Store store, Date startTime, Date endTime);

    Orders getOrdersByPaymentID(int paymentID, Store store);
}
