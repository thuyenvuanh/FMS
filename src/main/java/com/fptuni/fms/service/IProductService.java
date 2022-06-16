package com.fptuni.fms.service;

import com.fptuni.fms.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IProductService {
    List<Product> getProducts(HttpServletRequest request, HttpServletResponse response);

    Product getProductById(String productId);

    Integer insertProduct(HttpServletRequest request, HttpServletResponse response);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);

    int countProduct();
}
