package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.Payment;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Acer
 */
public class PaymentDAOTest {

    public PaymentDAOTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPayments method, of class PaymentDAO.
     */
    @Test
    public void testGetPayments() {
        System.out.println("getPayments");
        PaymentDAO instance = new PaymentDAO();
        List<Payment> expResult = new ArrayList<>();
        expResult.add(new Payment(1));
        expResult.add(new Payment(2));
        expResult.add(new Payment(3));
        expResult.add(new Payment(4));
        List<Payment> result = instance.getPayments();
        result.forEach(p -> System.out.println(p));
        assertEquals(expResult.size(), result.size());
        // TODO review the generated test code and remove the default call to fail.
//        fail();
    }

    /**
     * Test of getPaymentsByOrderId method, of class PaymentDAO.
     */
    @Test
    public void testGetPaymentsByOrderId() {
        System.out.println("getPaymentsByOrderId");
        int orderID = 1;
        PaymentDAO instance = new PaymentDAO();
        List<Payment> expResult = null;
        List<Payment> result = instance.getPaymentsByOrderId(orderID);
        result.forEach(p -> System.out.println(p));
//        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
