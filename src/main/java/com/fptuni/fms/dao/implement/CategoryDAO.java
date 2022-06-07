/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.mapper.CategoryMapper;
import java.util.List;

/**
 *
 * @author LucasBV
 */
public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO {

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT ID, Name, ShortName FROM Category";
        List<Category> categories = query(sql, new CategoryMapper());
        return categories.isEmpty() ? null : categories;
    }

    @Override
    public Category getCategoryById(int id) {
        String sql = "SELECT ID, Name, ShortName FROM Category\n"
                + "WHERE ID = ?";
        List<Category> categories = query(sql, new CategoryMapper(), id);
        return categories.isEmpty() ? null : categories.get(0);
    }

}
