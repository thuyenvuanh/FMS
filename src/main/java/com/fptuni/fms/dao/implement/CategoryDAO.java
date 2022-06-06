/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.mapper.CategoryMapper;
import com.fptuni.fms.model.Category;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class CategoryDAO extends AbstractDAO<Category> implements ICategoryDAO {

    @Override
    public List<Category> getAll() {
        String sql = "SELECT ID, Name, ShortName FROM Category";
        return query(sql, new CategoryMapper());
    }

    @Override
    public Category get(int id) {
        String sql = "SELECT ID, Name, ShortName FROM Category\n"
                + "WHERE ID = ?";
        List<Category> categories = query(sql, new CategoryMapper(), id);
        return categories.isEmpty() ? null : categories.get(0);
    }

    @Override
    public List<Category> getByName(String name) {
        String sql = "SELECT ID, Name, ShortName FROM Category\n"
                + "WHERE Name like ?";
        List<Category> categories = query(sql, new CategoryMapper(), "%" + name + "%");
        return categories.isEmpty() ? null : categories;
    }

    @Override
    public int insert(Category category) {
        String sql = "INSERT INTO Category(Name, ShortName)\n"
                + "VALUES (?,?);";
        return insert(sql, category.getName(), category.getShortName());
    }

    @Override
    public void update(Category category) {
        String sql = "UPDATE Category\n"
                + "SET Name = ?, ShortName = ?\n"
                + "WHERE ID = ?";
        update(sql, category.getName(), category.getShortName());
    }

}