package com.fptuni.mapper;

/*
*
* Author: Anh Quoc
*
* */

import com.fptuni.fms.mapper.RowMapper;
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
            product.setImagePath(rs.getString("ImagePath"));
            product.setPrice(rs.getBigDecimal("Price"));
            product.setQtyAvailable(rs.getShort("QtyAvailable"));
            product.setCateID(new Category(rs.getInt("CateID")));
            product.setStoreID(new Store(rs.getInt("StoreID")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
}

