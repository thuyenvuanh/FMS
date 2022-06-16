package com.fptuni.fms.controller;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.dao.implement.CategoryDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.ICategoryService;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.service.implement.CategoryService;
import com.fptuni.fms.service.implement.ProductService;
import com.fptuni.fms.sort.Sorter;
import com.fptuni.fms.utils.RequestUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "ProductController", urlPatterns = "/product/*")
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        if (path.equals("/list")) {
            int pageSize = 3;
            IProductService productService = new ProductService();
            ICategoryService categoryService = new CategoryService();
            List<Product> products = productService.getProducts(request, response);
            int totalPages = productService.countProduct() / pageSize;
            if (productService.countProduct() % pageSize != 0) totalPages++;
            List<Category> categories = categoryService.getCategories();
            request.setAttribute("categories", categories);
            request.setAttribute("productList", products);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("/view/store/productList.jsp").forward(request, response);
        } else if (path.equals("/createPage")) {
            ICategoryService categoryService = new CategoryService();
            List<Category> categories = categoryService.getCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/view/store/productCreate.jsp").forward(request, response);
        } else if (path.equals("/create")) {
            IProductService productService = new ProductService();
            if (productService.insertProduct(request, response) == 1) {
                session.setAttribute("createStatus", "success");
                response.sendRedirect(request.getContextPath() + "/product/list");
            } else {
                request.setAttribute("createStatus", "fail");
                request.getRequestDispatcher("/view/store/productCreate.jsp").forward(request, response);
            }
        }else if(path.equals("/view")){
            String productID = request.getParameter("productID");
            IProductService productService = new ProductService();
            Product product = productService.getProductById(productID);
            request.setAttribute("product",product);
            request.getRequestDispatcher("/view/store/productDetail.jsp").forward(request,response);
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
