package com.fptuni.fms.dao;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;

import java.math.BigDecimal;
import java.util.List;

/*
*
* Author: Anh Quoc
*
* */
public interface IProductDAO extends GenericDAO<Product> {

    public Product getProduct(String name);

    public List<Product> getProduct();

    public Integer insertProduct(Product product);

    public void updateProduct(String id, String name, String unit, BigDecimal price, short qtyAvailable, Category cateID, Store storeID);
}
