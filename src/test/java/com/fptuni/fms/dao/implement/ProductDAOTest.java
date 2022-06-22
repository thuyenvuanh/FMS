package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    @Test
    void getProduct() {
    }

    @Test
    void getProducts() {
//        int pageIndex = Integer.parseInt("1");
//        int maxItem = Integer.parseInt("2");
//        Sorter sorter = new Sorter("price", Boolean.parseBoolean("true"));
//        Pageable pageable = new PageRequest(pageIndex, maxItem, sorter);
//        IProductService productService = new ProductService();
//        List<Product> products = productService.getProducts(pageable);
//        assertEquals("Muffin", products.get(0).getName());
    }

    @Test
    void insertProduct() {
    }

    @Test
    void updateProduct() {
        Category cat1 = new Category(1);
        Category cat2 = new Category(1, "Noodle Soup", "NS");
        assertEquals(cat1, cat2); //TRUE
    }

    @Test
    void getProductByStore() {

    }

    @Test
    void count() {
        IProductDAO dao = new ProductDAO();
        assertEquals(4, dao.count());
    }
}