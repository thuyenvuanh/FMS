package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.dao.implement.ProductDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.service.ICategoryService;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.sort.Sorter;
import com.fptuni.fms.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {

    @Override
    public List<Product> getProducts(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        IProductDAO productDAO = new ProductDAO();
        IStoreDAO storeDAO = new StoreDAO();
        Store store = storeDAO.getStoreByAccount(account);
        int pageIndex = 1;
        int pageSize = 5;
        String sortField = "ID";
        boolean isAsc = true;
        if (request.getParameter("currentPage") != null) {
            pageIndex = Integer.parseInt(request.getParameter("currentPage"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Sorter sorter = new Sorter(sortField, isAsc);
        Pageable pageable = new PageRequest(pageIndex, pageSize, sorter);
        Map<String, String> searcher = new HashMap<>(); // key: param name; value: param value
        // Search field
        searcher.put("storeID", String.valueOf(store.getId()));
        searcher.put("categoryID", request.getParameter("categoryID"));
        searcher.put("productName", request.getParameter("productName"));
        searcher.put("minPrice", request.getParameter("minPrice"));
        searcher.put("maxPrice", request.getParameter("maxPrice"));
        searcher.put("quantity", request.getParameter("quantity"));
        // status = enable when quantity > 0
        if (request.getParameter("status") != null) searcher.put("status", request.getParameter("status"));
        List<Product> products = productDAO.getProducts(pageable, searcher);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAscending", !isAsc);
        // forward map input param so as not to reset entered field in searching
        for (Map.Entry<String, String> entries : searcher.entrySet()) {
            if (entries.getValue() != null && !entries.getValue().trim().isEmpty()) {
                // param name= key | param value=map value
                request.setAttribute(entries.getKey(), entries.getValue());
            }
        }

        return products;
    }

    @Override
    public Product getProductById(String productId) {
        if (productId == null || productId.isEmpty())
            return null;
        IProductDAO productDAO = new ProductDAO();
        return productDAO.getProduct(productId);
    }

    @Override
    public Integer insertProduct(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        ICategoryService categoryService = new CategoryService();
        IProductDAO productDAO = new ProductDAO();
        IStoreDAO storeDAO = new StoreDAO();
        Store store = storeDAO.getStoreByAccount(account);
        String id = "";
        String name = "";
        BigDecimal price = BigDecimal.valueOf(0.0);
        // String imgPath = "";
        int cateID = 1;
        short quantity = 1;
        // get this store id
        int storeID = store.getId();
        Category category = new Category();
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
        }
        if (request.getParameter("price") != null) {
            price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
        }
        // if (request.getParameter("imagePath") != null) {
        String imgPath = request.getParameter("imagePath");
        // }
        if (request.getParameter("categoryID") != null) {
            cateID = Integer.parseInt(request.getParameter("categoryID"));
            // get category info by id
            category = categoryService.getCategory(cateID);
            int subID = 1;
            // count the number of exist foods which have the same category
            ArrayList<Product> products = productDAO.getProductsByStoreAndCategory(new Store(storeID), new Category(cateID));
            subID = products.size() + 1;
            // concat short name and the next Id
            id = category.getShortName() + String.valueOf(subID);
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
            if (entry.getKey().equals("imagePath"))
                continue;
            else if (entry.getValue().isEmpty())
                return 0;
        }
        return productDAO.insertProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean updateProduct(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        ICategoryService categoryService = new CategoryService();
        IProductDAO productDAO = new ProductDAO();
        IStoreDAO storeDAO = new StoreDAO();
        Store store = storeDAO.getStoreByAccount(account);
        String id = "";
        String name = "";
        BigDecimal price = BigDecimal.valueOf(0.0);
        // String imgPath = "";
        int cateID = 1;
        short quantity = 1;
        // get this store id
        int storeID = store.getId();
        Category category = new Category();
        if (request.getParameter("id") != null) {
            id = request.getParameter("id");
        }
        if (request.getParameter("name") != null) {
            name = request.getParameter("name");
        }
        if (request.getParameter("price") != null) {
            price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
        }
        // if (request.getParameter("imagePath") != null) {
        String imgPath = request.getParameter("imagePath") == null ? "n/a" : request.getParameter("imagePath");
        // }
        if (request.getParameter("categoryID") != null) {
            cateID = Integer.parseInt(request.getParameter("categoryID"));
            // get category info by id
            category = categoryService.getCategory(cateID);
            List<Category> categories = categoryService.getCategories();
            // if change category: short cate name in id != choosen cate then create new pro
            // id
            // else not change pro id
            if (!id.contains(category.getShortName())) {
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
            if (entry.getKey().equals("imagePath"))
                continue;
            else if (entry.getValue().isEmpty())
                return false;
        }

        return productDAO.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(String productId) {
        if (productId == null)
            return false;
        IProductDAO productDAO = new ProductDAO();
        return productDAO.deleteProduct(productId);
    }

    @Override
    public int countProduct() {
        IProductDAO productDAO = new ProductDAO();
        return productDAO.count();
    }

    @Override
    public int countProductBySearch(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        IProductDAO productDAO = new ProductDAO();
        IStoreDAO storeDAO = new StoreDAO();
        Store store = storeDAO.getStoreByAccount(account);
        int pageIndex = 1;
        int pageSize = 5;
        String sortField = "ID";
        boolean isAsc = true;
        if (request.getParameter("currentPage") != null) {
            pageIndex = Integer.parseInt(request.getParameter("currentPage"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Map<String, String> searcher = new HashMap<>(); // key: param name; value: param value
        // Search field
        searcher.put("storeID", String.valueOf(store.getId()));
        searcher.put("categoryID", request.getParameter("categoryID"));
        searcher.put("productName", request.getParameter("productName"));
        searcher.put("minPrice", request.getParameter("minPrice"));
        searcher.put("maxPrice", request.getParameter("maxPrice"));
        searcher.put("quantity", request.getParameter("quantity"));
        // status = enable when quantity > 0
        if (request.getParameter("status") != null) searcher.put("status", request.getParameter("status"));
        int count = productDAO.countBySearch(searcher);

        return count;
    }
}
