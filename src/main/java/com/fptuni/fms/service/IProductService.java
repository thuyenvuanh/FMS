package com.fptuni.fms.service;

import com.fptuni.fms.controller.ProductController;
import com.fptuni.fms.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProductById(int productId);

    Integer insertProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int productId);
}
