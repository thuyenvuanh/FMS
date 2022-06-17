/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LucasBV
 */
public class CategoryMapper implements RowMapper<Category>{

    @Override
    public Category mapRow(ResultSet rs) {
        Category category = null;
        try {
            category = new Category();
            category.setId(rs.getInt("ID"));
            category.setName(rs.getString("Name"));
            category.setShortName(rs.getString("ShortName"));
            category.setIsDeleted(rs.getBoolean("IsDeleted"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }
    
}
