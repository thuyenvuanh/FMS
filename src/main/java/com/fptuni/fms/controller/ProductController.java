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
//            String pageSize = getServletContext().getInitParameter("pageSize");
//            System.out.println(pageSize);
            int pageIndex = Integer.parseInt(request.getParameter("page")) ;
            int maxItem = Integer.parseInt(request.getParameter("maxItem")) ;
            Sorter sorter = new Sorter(request.getParameter("sortField"), Boolean.parseBoolean(request.getParameter("isAscending")) );
            Pageable pageable = new PageRequest(pageIndex,maxItem,sorter);
            IProductService productService = new ProductService();
            List<Product> products = productService.getProducts(pageable);
            int totalPages = (int) Math.ceil(productService.countProduct() / maxItem);
            request.setAttribute("productList", products);
            request.setAttribute("totalPages",totalPages);
            request.getRequestDispatcher("/view/store/productList.jsp").forward(request,response);

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
