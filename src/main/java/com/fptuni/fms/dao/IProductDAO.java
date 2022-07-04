package com.fptuni.fms.dao;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *
 * Author: Anh Quoc
 *
 * */
public interface IProductDAO extends GenericDAO<Product> {

    Product getProduct(String id);

    List<Product> getProducts(Pageable pageable, Map<String,String> searcher);

    Integer insertProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(String id);

    int count();

    ArrayList<Product> getProductsByStore(Store store);
    int countBySearch(Map<String,String> searcher);

    ArrayList<Product> getProductsByStoreAndCategory(Store store, Category category);
    List<Product> getProductByOrderID(int orderID, Store store);
}
