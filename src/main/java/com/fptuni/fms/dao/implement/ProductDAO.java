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
import com.fptuni.fms.paging.Pageable;
import com.fptuni.mapper.ProductMapper;

import java.math.BigDecimal;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> implements IProductDAO {

    @Override
    public Product getProduct(int id) {//Get single product
        String sql = "SELECT Product.ID, Product.Name, Unit, Price, QtyAvailable,CateID,StoreID, Category.Name, Store.Name " +
                "from Product\n" +
                "Join Category on Product.CateID = Category.ID\n" +
                "Join Store on Product.StoreID = Store.ID\n" +
                "Where upper(Product.Name) = upper('?');";
        List<Product> products = query(sql, new ProductMapper(), id);
        return products.isEmpty() ? null : products.get(0);
    }

    @Override
    public List<Product> getProducts(Pageable pageable) {
        // Sort theo field xong moi paging
        // Neu chon sortField khac thi cac Product moi trang se thay doi
        // Vi du: sortField = ID ==> list ID ASC ==> paging
        String sql = "SELECT * FROM \n" +
                "(SELECT ID, Name, Unit, Price, QtyAvailable, CateID, StoreID \n" +
                "FROM Product\n" +
                "ORDER BY " + pageable.getSorter().getSortField() + " ASC ";
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n" +
                    " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY ) AS A \n";
        }
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            String orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY A." + pageable.getSorter().getSortField() + " " + orderBy;
        }


        List<Product> products = query(sql, new ProductMapper());
        return products.isEmpty() ? null : products;
    }

    @Override
    public Integer insertProduct(Product product) {
        String sql = "INSERT INTO Product\n" +
                "VALUES(?,?,?,?,?,?,?);";
        return insert(product.getId(), product.getName(), product.getUnit(), product.getPrice(), product.getQtyAvailable(), product.getCateID(), product.getStoreID());
    }

    @Override
    public void updateProduct(String id, String name, String unit, BigDecimal price, short qtyAvailable, Category cateID, Store storeID) {
        String sql = "UPDATE Product\n" +
                "SET\n" +
                "Name='?',\n" +
                "Unit='?',\n" +
                "Price='?',\n" +
                "QtyAvailable='?',\n" +
                "CateID='?',\n" +
                "StoreID='?'\n" +
                "Where ID = ?;";
        update(name, unit, price, qtyAvailable, cateID, storeID, id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(ID) FROM Product";
        return count(sql);
    }
}
