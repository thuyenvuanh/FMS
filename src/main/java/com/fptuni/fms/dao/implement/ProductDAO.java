package com.fptuni.fms.dao.implement;

/*
 *
 * Author: Binh
 *
 * */

import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> implements IProductDAO {

    @Override
    public Product getProduct(String id) {// Get single product
        String sql = "SELECT ID, Name, ImagePath, Price, QtyAvailable, CateID, StoreID, IsDeleted\n" +
                "FROM Product\n" +
                "WHERE ID = ? AND IsDeleted = 0";
        List<Product> products = query(sql, new ProductMapper(), id);
        return products == null ? null : products.get(0);
    }

    @Override
    public List<Product> getProducts(Pageable pageable) {
        // Sort theo field xong moi paging
        // Neu chon sortField khac thi cac Product moi trang se thay doi
        // Vi du: sortField = ID ==> list ID ASC ==> paging
        String sql = "SELECT * FROM \n" +
                "(SELECT ID, Name, ImagePath, Price, QtyAvailable, CateID, StoreID \n" +
                "FROM Product WHERE IsDeleted = 0\n";
        String orderBy;
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY " + pageable.getSorter().getSortField() + "  " + orderBy;
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n" +
                    " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY ) AS A \n";
        }
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += "ORDER BY A." + pageable.getSorter().getSortField() + " " + orderBy;
        }

        List<Product> products = query(sql, new ProductMapper());
        return products;
    }

    @Override
    public Integer insertProduct(Product product) {
        String sql = "INSERT INTO Product\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImagePath(),
                product.getQtyAvailable(),
                product.getCateID().getId(),
                product.getStoreID().getId());
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE Product\n" +
                " SET ID = ?,\n" +
                " Name = ?,\n" +
                " Price = ?,\n" +
                " ImagePath = ?,\n" +
                " QtyAvailable = ?,\n" +
                " CateID = ?,\n" +
                " StoreID = ?\n" +
                " WHERE ID = ?";
        return update(sql, product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImagePath(),
                product.getQtyAvailable(),
                product.getCateID().getId(),
                product.getStoreID().getId(),
                product.getId());
    }

    @Override
    public boolean deleteProduct(String id) {
        String sql = "UPDATE Product\n" +
                "SET IsDeleted = 1 \n" +
                "WHERE ID = ?";
        return update(sql, id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(ID) FROM Product";
        return count(sql);
    }

    @Override
    public ArrayList<Product> getProductsByStoreAndCategory(Store store, Category category) {
        String sql = "SELECT ID, Name, Price, ImagePath, QtyAvailable, StoreID, CateID WHERE StoreID = ? AND CateID = ? AND IsDeleted = 0";
        return (ArrayList<Product>) query(sql, new ProductMapper(), store.getId(), category.getId());
    }
}
