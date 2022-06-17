/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.fptuni.fms.dao;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Store;

import java.util.List;

/**
 *
 * @author Casul
 */
public interface ICategoryDAO extends GenericDAO<Category>{
    int insertCategory(Category category);
    void updateCategory(Category category);
    List<Category> getCategories();
    Category getCategory(int id);
    List<Category> getCategoryByName(String name);
    int count();

    List<Category> getCategoriesByStore(Store store);
}
