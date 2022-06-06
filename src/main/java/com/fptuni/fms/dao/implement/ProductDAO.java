package com.fptuni.fms.dao.implement;


/*
*
* Author: Anh Quoc
*
* */

import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.model.Product;
import com.fptuni.mapper.ProductMapper;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> implements IProductDAO {

        @Override
        public Product getProduct(String name) {
                String sql = "SELECT Product.ID, Product.Name, Unit, Price, QtyAvailable,CateID,StoreID, Category.Name, Store.Name " +
                        "from Product\n" +
                        "Join Category on Product.CateID = Category.ID\n" +
                        "Join Store on Product.StoreID = Store.ID\n" +
                        "Where upper(Product.Name) = upper('?');";
                List<Product> products = query(sql, new ProductMapper(), name);
                return products.isEmpty() ? null : products.get(0);
        }
}
