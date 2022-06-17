package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IProductDAO;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void count() {
        IProductDAO dao = new ProductDAO();
        assertEquals(4, dao.count());
    }
}