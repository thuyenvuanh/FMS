package com.fptuni.fms.service;

import com.fptuni.fms.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IProductService {
    List<Product> getProducts(HttpServletRequest request, HttpServletResponse response);

    Product getProductById(String productId);

    List<Product> getProductByOrderID(HttpServletRequest request, HttpServletResponse response);

    Integer insertProduct(HttpServletRequest request, HttpServletResponse response);

    boolean updateProduct(HttpServletRequest request, HttpServletResponse response);

    boolean deleteProduct(String productId);

    int countProduct();

    int countProductBySearch(HttpServletRequest request, HttpServletResponse response);
    List<Product> getTop5ProductsOrderByAmount(HttpServletRequest request, HttpServletResponse response);
    List<Double> getPercentageOfProductInCategory(HttpServletRequest request, HttpServletResponse response);
}