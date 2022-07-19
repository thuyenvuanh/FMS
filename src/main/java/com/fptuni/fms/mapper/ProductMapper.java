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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs) {
        Product product = new Product();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i <= columns; i++) {
                if (metaData.getColumnLabel(i).equals("ID")) product.setId(rs.getString("ID"));
                if (metaData.getColumnLabel(i).equals("Name")) product.setName(rs.getString("Name"));
                if (metaData.getColumnLabel(i).equals("ImagePath")) product.setImagePath(rs.getString("ImagePath"));
                if (metaData.getColumnLabel(i).equals("Price")) product.setPrice(rs.getBigDecimal("Price"));
                if (metaData.getColumnLabel(i).equals("QtyAvailable"))
                    product.setQtyAvailable(rs.getShort("QtyAvailable"));
                if (metaData.getColumnLabel(i).equals("CateID")) product.setCateID(new Category(rs.getInt("CateID")));
                if (metaData.getColumnLabel(i).equals("StoreID")) product.setStoreID(new Store(rs.getInt("StoreID")));
                if (metaData.getColumnLabel(i).equals("IsDeleted")) product.setDeleted(rs.getBoolean("IsDeleted"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }
}
