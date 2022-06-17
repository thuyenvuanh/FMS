/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import org.junit.After;
import org.junit.Before;

/**
 *
 * @author LEGION
 */
public class CounterDAOTest {
    
    CounterDAO counterDAO;
    
    public CounterDAOTest() {
    }
    
    @Before
    public void setUp() {
        counterDAO = new CounterDAO();
    }
    
    @After
    public void tearDown() {
        counterDAO = null;
    }

//    @Test
//    public void testGetAll() {
//        List<Counter> counters = counterDAO.getAll();
//        List<Counter> expecteds = new ArrayList<>();
//        expecteds.add(new Counter(1));
//        expecteds.add(new Counter(2));
//        expecteds.add(new Counter(3));
//        assertArrayEquals(expecteds.toArray(), counters.toArray());
//    }
//
//    @Test
//    public void testGet() {
//
//    }
    
}
