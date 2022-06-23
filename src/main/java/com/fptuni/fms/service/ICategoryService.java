package com.fptuni.fms.service;

import com.fptuni.fms.model.Category;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    int insertCategory(Category category);

    void updateCategory(Category category);

    List<Category> getCategories();

    Category getCategory(int id);

    List<Category> getCategoryByName(String name);

    void loadProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    int count();
}
