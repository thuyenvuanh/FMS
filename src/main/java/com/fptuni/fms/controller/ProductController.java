package com.fptuni.fms.controller;

import com.fptuni.fms.model.Product;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.IProductService;
import com.fptuni.fms.service.implement.ProductService;
import com.fptuni.fms.sort.Sorter;
import com.fptuni.fms.utils.RequestUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductController", urlPatterns = "/product/*")
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
//        System.out.println(path);
        if (path.equals("/list")) {

            int pageIndex = 1;
            int pageSize = 2;
//            int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
//            System.out.println(pageSize);
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
            IProductService productService = new ProductService();
            List<Product> products = productService.getProducts(pageable);
            int totalPages = (int) Math.ceil(productService.countProduct() / pageSize);
            request.setAttribute("productList", products);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", pageIndex);
            request.setAttribute("sortField", sortField);
            // Tu dong dao nguoc khi nhan nhieu lan vao sortField
            request.setAttribute("isAsc", !isAsc);
            request.getRequestDispatcher("/view/store/productList.jsp").forward(request, response);

//            response.sendRedirect(request.getContextPath() + "/view/store/productList.jsp");
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
