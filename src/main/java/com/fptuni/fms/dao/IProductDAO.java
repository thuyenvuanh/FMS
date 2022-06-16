package com.fptuni.fms.dao;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
*
* Author: Anh Quoc
*
* */
public interface IProductDAO extends GenericDAO<Product> {

    Product getProduct(int id);

    List<Product> getProducts(Pageable pageable);

    Integer insertProduct(Product product);

    void updateProduct(String id, String name, String unit, BigDecimal price, short qtyAvailable, Category cateID, Store storeID);

    int count();

    ArrayList<Product> getProductsByStoreAndCategory(Store store, Category category);
}
