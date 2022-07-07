package com.fptuni.fms.dao.implement;

/*
 *
 * Author: Binh
 *
 * */

import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.mapper.OrderDetailMapper;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Product> getProducts(Pageable pageable, Map<String, String> searcher) {
        // Sort theo field xong moi paging
        // Neu chon sortField khac thi cac Product moi trang se thay doi
        // Vi du: sortField = ID ==> list ID ASC ==> paging
        String sql = "SELECT ID, Name, ImagePath, Price, QtyAvailable, CateID, StoreID \n" +
                "FROM Product WHERE IsDeleted = 0 \n";
        if (searcher.getOrDefault("storeID", null) != null) sql += " AND StoreID = " + searcher.get("storeID");
        if (searcher.getOrDefault("categoryID", null) != null && !searcher.get("categoryID").equals("0") && !searcher.get("categoryID").isEmpty())
            sql += " AND CateID = " + searcher.get("categoryID");
        if (searcher.getOrDefault("productName", null) != null && !searcher.get("productName").isEmpty())
            sql += " AND Name LIKE  '%" + searcher.get("productName") + "%'";
        if (searcher.getOrDefault("minPrice", null) != null && !searcher.get("minPrice").isEmpty())
            sql += " AND Price >= " + searcher.get("minPrice");
        if (searcher.getOrDefault("maxPrice", null) != null && !searcher.get("maxPrice").isEmpty())
            sql += " AND Price <= " + searcher.get("maxPrice");
        if (searcher.getOrDefault("quantity", null) != null && !searcher.get("quantity").isEmpty())
            sql += " AND QtyAvailable = " + searcher.get("quantity");
        if (searcher.getOrDefault("status", null) != null) {
            if (searcher.get("status").equals(String.valueOf(0))) {
                sql += " AND QtyAvailable < 0 ";
            } else {
                sql += " AND QtyAvailable > 0 ";
            }
        }
        if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
            String orderBy = pageable.getSorter().isAscending() ? "ASC" : "DESC";
            sql += " ORDER BY " + pageable.getSorter().getSortField() + "  " + orderBy;
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql += " OFFSET " + pageable.getOffset() + " ROWS\n" +
                    " FETCH NEXT " + pageable.getLimit() + " ROWS ONLY  \n";
        }
        List<Product> products = query(sql, new ProductMapper());
        return products;
    }

    @Override
    public Integer insertProduct(Product product) {
        String sql = "INSERT INTO Product (ID, Name, Price, ImagePath, QtyAvailable, CateID, StoreID)\n" +
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
        String sql = "SELECT COUNT(ID) FROM Product WHERE IsDeleted = 0 AND StoreID = 1";
        return count(sql);
    }

    @Override
    public int countBySearch(Map<String, String> searcher) {
        String sql = "SELECT COUNT(ID) \n" +
                "FROM Product WHERE IsDeleted = 0 \n";
        if (searcher.getOrDefault("storeID", null) != null) sql += " AND StoreID = " + searcher.get("storeID");
        if (searcher.getOrDefault("categoryID", null) != null && !searcher.get("categoryID").equals("0") && !searcher.get("categoryID").isEmpty())
            sql += " AND CateID = " + searcher.get("categoryID");
        if (searcher.getOrDefault("productName", null) != null && !searcher.get("productName").isEmpty())
            sql += " AND Name LIKE  '%" + searcher.get("productName") + "%'";
        if (searcher.getOrDefault("minPrice", null) != null && !searcher.get("minPrice").isEmpty())
            sql += " AND Price >= " + searcher.get("minPrice");
        if (searcher.getOrDefault("maxPrice", null) != null && !searcher.get("maxPrice").isEmpty())
            sql += " AND Price <= " + searcher.get("maxPrice");
        if (searcher.getOrDefault("quantity", null) != null && !searcher.get("quantity").isEmpty())
            sql += " AND QtyAvailable = " + searcher.get("quantity");
        if (searcher.getOrDefault("status", null) != null) {
            if (searcher.get("status").equals(String.valueOf(0))) {
                sql += " AND QtyAvailable < 0 ";
            } else {
                sql += " AND QtyAvailable > 0 ";
            }
        }
        return count(sql);
    }

    @Override
    public ArrayList<Product> getProductsByStore(Store store) {
        String sql = "SELECT [ID], [Name], [Price], [ImagePath], [QtyAvailable], [StoreID], [CateID]\n" +
                "FROM Product\n" +
                "WHERE StoreID = ? AND IsDeleted = 0";
        return (ArrayList<Product>) query(sql, new ProductMapper(), store.getId());
    }

    public ArrayList<Product> getProductsByStoreAndCategory(Store store, Category category) {
        String sql = "SELECT ID, Name, ImagePath, Price, QtyAvailable, CateID, StoreID " +
                " FROM  Product" +
                " WHERE StoreID = ? AND CateID = ? AND IsDeleted = 0";
        return (ArrayList<Product>) query(sql, new ProductMapper(), store.getId(), category.getId());
    }

    @Override
    public List<Product> getProductByOrderID(int orderID, Store store) {
        String sql = "SELECT o.ID, od.ProID, p.Name, p.Price, od.Quantity, od.Amount FROM OrderDetail od\n" +
//                " JOIN Orders o ON o.ID = od.OrderID AND o.ID = ? AND StoreID = " + store.getId() +
                " JOIN Orders o ON o.ID = od.OrderID AND o.ID = ? AND StoreID = ? " +
                " AND od.IsDeleted = 0 AND o.IsDeleted = 0\n" +
                " JOIN Product p on p.ID = od.ProID AND p.IsDeleted = 0";
        return query(sql, new OrderDetailMapper(), orderID, store.getId());
    }
}
