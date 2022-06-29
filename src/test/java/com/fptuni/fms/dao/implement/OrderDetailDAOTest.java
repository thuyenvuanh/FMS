package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.OrderDetail;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class OrderDetailDAOTest {

    @Test
    void getOrderDetailsByOrderID() {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        List<OrderDetail> orderDetailsActual = orderDetailDAO.getOrderDetailsByOrderID(1, 1);
        for (OrderDetail orderDetail : orderDetailsActual) {
            System.out.println(orderDetail.getProduct().getId());
        }
        assertEquals(4, orderDetailsActual.size());
    }
}