package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.dao.implement.CategoryDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.service.ICategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void loadProducts(HttpServletRequest request, HttpServletResponse response) {
        Map<Category, List<Product>> categoryListMap = (Map<Category, List<Product>>) request.getSession().getAttribute("productsMap");
        List<Category> categories = (List<Category>) request.getSession().getAttribute("categories");
        if (categories == null) {
            categories = new ArrayList<Category>(categoryListMap.keySet());
            request.getSession().setAttribute("categories", categories);
        }
        String catID = request.getParameter("catID");
        Category currentCate = null;
        if (catID == null){
            currentCate = categories.get(0);
        }
        else {
            for (Category category : categories) {
                if (String.valueOf(category.getId()).equals(catID)){
                    currentCate = category;
                    break;
                }
            }
        }
        request.getSession().setAttribute("currentCate", currentCate);
        List<Product> products = categoryListMap.get(currentCate);
        request.getSession().setAttribute("products", products);

    }

    @Override
    public int count() {
        return 0;
    }
}
