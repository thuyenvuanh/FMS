package com.fptuni.fms.service;

import com.fptuni.fms.model.Category;

import java.util.List;

public interface ICategoryService {
    int insertCategory(Category category);

    void updateCategory(Category category);

    List<Category> getCategories();

    Category getCategory(int id);

    List<Category> getCategoryByName(String name);

    int count();
}
