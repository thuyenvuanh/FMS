/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.fptuni.fms.dao;

import com.fptuni.fms.model.Category;
import java.util.List;

/**
 *
 * @author Casul
 */
public interface ICategoryDAO extends GenericDAO<Category>{
    int insert(Category category);
    void update(Category category);
    List<Category> getAll();
    Category get(int id);
    List<Category> getByName(String name);
}
