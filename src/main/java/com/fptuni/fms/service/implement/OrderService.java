package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.CategoryDAO;
import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Product;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.service.IOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

public class OrderService implements IOrderService {


    @Override
    public void index(HttpServletRequest request, HttpServletResponse response) {
        //order list
        HttpSession session = request.getSession();
        Orders order = (Orders) session.getAttribute("order");
        if (order == null) {
            order = new Orders();
            order.setCreatedDate(new Date());
            order.setOrderDetailList(new ArrayList<>());
            order.setStoreID((Store) session.getAttribute("store"));
            order.setPaymentList(new ArrayList<>());
        }
        session.setAttribute("order", order);

        //lay category
        ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("categories");
        if (categories == null) {
            categories = new CategoryDAO().getCategoriesByStore((Store) session.getAttribute("store"));
        }
        session.setAttribute("categories", categories);

        ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");
        if (products == null) {
//            products = new ProductDAO().getProductsByStoreAndCategory((Store) session.getAttribute("store"), categories.get(0));
            products = new ArrayList<Product>();
        }
        session.setAttribute("products", products);
    }

    @Override
    public void addNewProduct(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void removeProduct(HttpServletRequest request, HttpServletResponse response) {

    }
}
