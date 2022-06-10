package com.fptuni.fms.dao.implement;


/*
*
* Author: Anh Quoc
*
* */

import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.mapper.ProductMapper;

import java.math.BigDecimal;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> implements IProductDAO {

        @Override
        public Product getProduct(String name) {//Get single product
                String sql = "SELECT Product.ID, Product.Name, Unit, Price, QtyAvailable,CateID,StoreID, Category.Name, Store.Name " +
                        "from Product\n" +
                        "Join Category on Product.CateID = Category.ID\n" +
                        "Join Store on Product.StoreID = Store.ID\n" +
                        "Where upper(Product.Name) = upper('?');";
                List<Product> products = query(sql, new ProductMapper(), name);
                return products.isEmpty() ? null : products.get(0);
        }

        @Override
        public List<Product> getProduct(){
                String sql = "SELECT Product.ID, Product.Name, Unit, Price, QtyAvailable,CateID,StoreID, Category.Name, Store.Name " +
                        "from Product\n" +
                        "Join Category on Product.CateID = Category.ID\n" +
                        "Join Store on Product.StoreID = Store.ID\n";
                List<Product> products =query(sql, new ProductMapper());
                return products.isEmpty() ? null : products;
        }

        @Override
        public Integer insertProduct(Product product){
                String sql = "INSERT INTO Product\n" +
                        "VALUES(?,?,?,?,?,?,?);";
                return insert(product.getId(),product.getName(),product.getUnit(),product.getPrice(),product.getQtyAvailable(),product.getCateID(),product.getStoreID());
        }

        @Override
        public void updateProduct(String id, String name, String unit, BigDecimal price, short qtyAvailable, Category cateID, Store storeID){
                String sql="UPDATE Product\n" +
                        "SET\n" +
                        "Name='?',\n" +
                        "Unit='?',\n" +
                        "Price='?',\n" +
                        "QtyAvailable='?',\n" +
                        "CateID='?',\n" +
                        "StoreID='?'\n" +
                        "Where ID = ?;";
                update(name,unit,price,qtyAvailable,cateID,storeID,id);
        }
}
