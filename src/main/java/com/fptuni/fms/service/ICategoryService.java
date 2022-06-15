package com.fptuni.fms.service;

import com.fptuni.fms.dao.GenericDAO;
import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.paging.Pageable;

import java.util.List;

public interface ICategoryService {
    int insertCategory(Category category);

    void updateCategory(Category category);

    List<Category> getCategories();

    Category getCategory(int id);

    List<Category> getCategoryByName(String name);

    int count();
}
