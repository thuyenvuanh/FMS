/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.Category;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LEGION
 */
public class CategoryDAOTest {

    CategoryDAO categoryDAO;

    public CategoryDAOTest() {
    }

    @Before
    public void setUp() {
        categoryDAO = new CategoryDAO();
    }

    @After
    public void tearDown() {
        categoryDAO = null;
    }
    
    @Test
    public void testInsert(){
        Category cate1 = new Category("Drink", "D");
        Category cate2 = new Category("Food", "F");
        int result1 = categoryDAO.insertCategory(cate1);
        int result2 = categoryDAO.insertCategory(cate2);
        assertEquals(1, result1);
        assertEquals(2, result2);
    }

    @Test
    public void testGetAll() {
        List<Category> listC = categoryDAO.getCategories();
        List<Category> expecteds = new ArrayList<>();
        expecteds.add(new Category(1, "Drink", "D"));
        expecteds.add(new Category(2, "Food", "F"));
        assertArrayEquals(expecteds.toArray(), listC.toArray());
    }

    @Test
    public void testGet() {
        Category category = categoryDAO.getCategory(1);
        Category expected = new Category(1, "Food", "FF");
        System.out.println(category);
        assertEquals(expected, category);
    }

    @Test
    public void testGetByName() {
        List<Category> listC = categoryDAO.getCategoryByName("o");
        List<Category> expecteds = new ArrayList<>();
        expecteds.add(new Category(1, "Food", "F"));
        expecteds.add(new Category(3, "Food1", "F1"));
        for (Category expected : listC) {
            System.out.println(expected);
        }
        assertArrayEquals(expecteds.toArray(), listC.toArray());
    }
}
