package com.fptuni.mapper;

/*
*
* Author: Anh Quoc
*
* */
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs) {
        Product product = null;
        try {
            product = new Product();
            product.setId(rs.getString("ID"));
            product.setName(rs.getString("Name"));
            product.setUnit(rs.getString("Unit"));
            product.setPrice(rs.getBigDecimal("FullName"));
            product.setQtyAvailable(rs.getShort("QtyAvailable"));
            Category category = new Category();
            Store store = new Store();
            category.setId(rs.getInt("ID"));
            store.setId(rs.getInt("ID"));
            product.setCateID(category);
            product.setStoreID(store);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

}

