package com.fptuni.fms.service.implement;

import com.fptuni.fms.model.Product;
import com.fptuni.fms.service.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    @Override
    public List<Product> getProducts() {

        return null;
    }
    @Override
    public Product getProductById(int productId) {
        return null;
    }

    @Override
    public Integer insertProduct(Product product) {
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }
}
