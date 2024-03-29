/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.mapper.CategoryMapper;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Store;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO {

        @Override
        public List<Category> getCategories() {
                String sql = "SELECT ID, Name, ShortName, IsDeleted FROM Category" +
                        " WHERE IsDeleted = 0";
                return query(sql, new CategoryMapper());
        }

        @Override
        public Category getCategory(int id) {
                String sql = "SELECT ID, Name, ShortName, IsDeleted FROM Category\n"
                                + "WHERE ID = ? and IsDeleted = 0";
                List<Category> categories = query(sql, new CategoryMapper(), id);
                return categories.isEmpty() ? null : categories.get(0);
        }

        @Override
        public List<Category> getCategoryByName(String name) {
                String sql = "SELECT ID, Name, ShortName, IsDeleted FROM Category\n"
                                + "WHERE Name like ? and IsDeleted = 0";
                List<Category> categories = query(sql, new CategoryMapper(), "%" + name + "%");
                return categories.isEmpty() ? null : categories;
        }

        @Override
        public int count() {
                String sql = "SELECT COUNT(ID) FROM Category\n" +
                        "Where IsDeleted = 0";
                int count = count(sql, new CategoryMapper());
                return count;
        }
    @Override
    public ArrayList<Category> getCategoriesByStore(Store store) {
        //Under developing
        //TODO: Create field in Category StoreID link to Store
        return new ArrayList<>();
    }

    @Override
    public int insertCategory(Category category) {
            String sql = "INSERT INTO Category(Name, ShortName, IsDeleted)\n"
                    + "VALUES (?,?, 0);";
        return insert(sql, category.getName(), category.getShortName());
    }

        @Override
        public void updateCategory(Category category) {
                String sql = "UPDATE Category\n"
                                + "SET Name = ?, ShortName = ?\n"
                                + "WHERE ID = ?";
                update(sql, category.getName(), category.getShortName());
        }

}
