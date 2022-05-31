package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.Orders;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Acer
 */
public class OrderDAOTest {

    public OrderDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOrders method, of class OrderDAO.
     */
    @Test
    public void testGetOrders() {
        System.out.println("getOrders");
        OrderDAO instance = new OrderDAO();
        List<Orders> expResult = new ArrayList<>();
        expResult.add(new Orders(1));
        List<Orders> result = instance.getOrders();
        for (Orders orders : result) {
            System.out.println(orders.getCreatedDate());
        }

        assertEquals(expResult.size(), result.size());
        fail("fail");
    }

    /**
     * Test of getOrderById method, of class OrderDAO.
     */
    @Test
    public void testGetOrderById() {
        System.out.println("getOrderById");
        int id = 1;
        OrderDAO instance = new OrderDAO();
        Orders expResult = new Orders(1);
        Orders result = instance.getOrderById(id);
        assertEquals(expResult.getId(), result.getId());
        System.out.println(result.getId() + " " + result.getCreatedDate());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOrder method, of class OrderDAO.
     */
    @Test
    public void testUpdateOrder() {
        System.out.println("updateOrder");
        int id = 1;
        int storeID = 1;
        double total = 12;
        Timestamp createdDate = new Timestamp(new Date().getTime());
        OrderDAO instance = new OrderDAO();
        boolean expResult = true;
        boolean result = instance.updateOrder(id, storeID, total, createdDate);
        Orders o = instance.getOrderById(id);
        System.out.println(o.getId() + " " + o.getCreatedDate());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
