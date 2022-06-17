package com.fptuni.fms.controller;

import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.service.ICategoryService;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.service.implement.CategoryService;
import com.fptuni.fms.service.implement.ProductService;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductController", urlPatterns = "/product/*")
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        System.out.println(request.getQueryString());
        HttpSession session = request.getSession();
        IProductService productService = new ProductService();
        ICategoryService categoryService = new CategoryService();
        IStoreDAO storeDAO = new StoreDAO();
        Account account = (Account) session.getAttribute("account");
        if (path.equals("/list")) {
            int pageSize = 3;
            List<Product> products = productService.getProducts(request, response);
            int totalPages = productService.countProduct() / pageSize;
            if (productService.countProduct() % pageSize != 0) totalPages++;
            List<Category> categories = categoryService.getCategories();
            Store store = storeDAO.getStoreByAccount(account);
            request.setAttribute("store",store);
            request.setAttribute("categories", categories);
            request.setAttribute("productList", products);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("/view/store/productList.jsp").forward(request, response);
        } else if (path.equals("/createPage")) {
            List<Category> categories = categoryService.getCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/view/store/productCreate.jsp").forward(request, response);
        } else if (path.equals("/create")) {
            if (productService.insertProduct(request, response) != 0) {
                session.setAttribute("createStatus", "success");
                response.sendRedirect(request.getContextPath() + "/product/list");
            } else {
                List<Category> categories = categoryService.getCategories();
                request.setAttribute("categories", categories);
                request.setAttribute("createStatus", "fail");
                request.getRequestDispatcher("/view/store/productCreate.jsp").forward(request, response);
            }
        } else if (path.equals("/view")) {
            String productID = request.getParameter("productID");
            Product product = productService.getProductById(productID);
            Category category = categoryService.getCategory(product.getCateID().getId());
            request.setAttribute("product", product);
            request.setAttribute("category", category);
            request.getRequestDispatcher("/view/store/productDetail.jsp").forward(request, response);
        } else if (path.equals("/updatePage")) {
            String productID = request.getParameter("productID");
            List<Category> categories = categoryService.getCategories();
            Product product = productService.getProductById(productID);
            request.setAttribute("product", product);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/view/store/productUpdate.jsp").forward(request, response);
        } else if (path.equals("/update")) {
            if (productService.updateProduct(request, response)) {
                session.setAttribute("updateStatus", "success");
                response.sendRedirect(request.getContextPath() + "/product/list");
            } else {
                List<Category> categories = categoryService.getCategories();
                request.setAttribute("categories", categories);
                session.setAttribute("updateStatus", "fail");
                request.getRequestDispatcher("/view/store/productUpdate.jsp").forward(request, response);
            }
        } else if (path.equals("/delete")) {
            String productID = request.getParameter("productID");
            System.out.println(productID);
            if (productService.deleteProduct(productID)) {
                session.setAttribute("deleteStatus", "success");
            } else {
                session.setAttribute("deleteStatus", "fail");
            }
            response.sendRedirect(request.getContextPath() + "/product/list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
