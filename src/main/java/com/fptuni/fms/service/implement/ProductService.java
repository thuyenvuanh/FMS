package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.dao.implement.CategoryDAO;
import com.fptuni.fms.dao.implement.ProductDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.ICategoryService;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.sort.Sorter;
import com.fptuni.fms.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    @Override
    public List<Product> getProducts(HttpServletRequest request, HttpServletResponse response) {
        IProductDAO productDAO = new ProductDAO();
        int pageIndex = 1;
        int pageSize = 3;
        String sortField = "ID";
        boolean isAsc = true;
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Sorter sorter = new Sorter(sortField, isAsc);
        Pageable pageable = new PageRequest(pageIndex, pageSize, sorter);
        List<Product> products = productDAO.getProducts(pageable);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAsc", !isAsc);

        return products;
    }

    @Override
    public Product getProductById(int productId) {
        return null;
    }

    @Override
    public Integer insertProduct(HttpServletRequest request, HttpServletResponse response) {

        ICategoryService categoryService = new CategoryService();
        IProductDAO productDAO = new ProductDAO();
        String id = "";
        String name = "";
        BigDecimal price = BigDecimal.valueOf(0.0);
//        String imgPath = "";
        int cateID = 1;
        short quantity = 1;
        // get this store id
        int storeID = 1;
        Category category = new Category();
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
        }
        if (request.getParameter("price") != null) {
            price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
        }
//        if (request.getParameter("imagePath") != null) {
        String imgPath = request.getParameter("imagePath");
//        }
        if (request.getParameter("categoryID") != null) {
            cateID = Integer.parseInt(request.getParameter("categoryID"));
            // get category info by id
            category = categoryService.getCategory(cateID);
            List<Category> categories = categoryService.getCategories();
            int subID = 1;
            // count the number of exist foods which have the same category
            for (Category c : categories) {
                if (c.getShortName().contains(category.getShortName())) {
                    subID++;
                    break;
                }
            }
            // concat short name and the next Id
            id = category.getShortName() + String.valueOf(subID + 1);
        }
        if (request.getParameter("quantity") != null) {
            quantity = Short.parseShort(request.getParameter("quantity"));
        }

        Product product = new Product(id, name, imgPath, price, quantity, category, new Store(storeID));
        // Set input request attribute to forward to create page if not success
        request.setAttribute("product", product);
        // force all of these param not null except Image
        // Key = param name | Value = param value
        Map<String, String> paramMap = RequestUtils.getParameters(request.getQueryString());
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (entry.getKey().equals("imagePath")) continue;
            else if (entry.getValue().isEmpty()) return 0;
        }
        productDAO.insertProduct(product);
        return 1;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }

    @Override
    public int countProduct() {
        IProductDAO productDAO = new ProductDAO();
        return productDAO.count();
    }
}
