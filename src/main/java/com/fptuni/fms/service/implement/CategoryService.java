package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.dao.implement.CategoryDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {

    @Override
    public int insertCategory(Category category) {
        return 0;
    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public List<Category> getCategories() {
        ICategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.getCategories();
    }

    @Override
    public Category getCategory(int id) {
        ICategoryDAO categoryDAO = new CategoryDAO();
        return categoryDAO.getCategory(id);
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}
