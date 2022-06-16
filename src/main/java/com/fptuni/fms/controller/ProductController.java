package com.fptuni.fms.controller;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.service.ICategoryService;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.service.implement.CategoryService;
import com.fptuni.fms.service.implement.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductController", urlPatterns = "/product/*")
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        HttpSession session = request.getSession();
        session.removeAttribute("createStatus");
        if (path.equals("/list")) {
            int pageSize = 3;
            IProductService productService = new ProductService(); // check valid in product service
            List<Product> products = productService.getProducts(request, response);
            int totalPages = productService.countProduct() / pageSize;
            if (productService.countProduct() % pageSize != 0) totalPages++;
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
//            request.getRequestDispatcher("/product/list").forward(request, response);

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
