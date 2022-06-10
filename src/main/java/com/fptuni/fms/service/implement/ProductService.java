package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.ProductDAO;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.service.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    @Override
    public List<Product> getProducts(String search, String sortBy, int pageIndex) {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getProduct();
        return products;
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
