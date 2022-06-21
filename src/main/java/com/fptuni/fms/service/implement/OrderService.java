package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.IOrderDAO;
import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.dao.implement.CategoryDAO;
import com.fptuni.fms.dao.implement.OrderDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.*;
import com.fptuni.fms.paging.PageRequest;
import com.fptuni.fms.paging.Pageable;
import com.fptuni.fms.service.IOrderService;
import com.fptuni.fms.sort.Sorter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Override
    public List<Orders> getOrders(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        IOrderDAO orderDAO = new OrderDAO();
        IStoreDAO storeDAO = new StoreDAO();
        int page = 1;
        int pageSize = 5;
        String sortField = "ID";
        boolean isAsc = true;
        if (request.getParameter("currentPage") != null) {
            page = Integer.parseInt(request.getParameter("currentPage"));
        }
        if (request.getParameter("sortField") != null) {
            sortField = request.getParameter("sortField");
        }
        if (request.getParameter("isAscending") != null) {
            isAsc = Boolean.parseBoolean(request.getParameter("isAscending"));
        }
        Sorter sorter = new Sorter(sortField, isAsc);
        Pageable pageable = new PageRequest(page, pageSize, sorter);

        Map<String, String> searcher = new HashMap<>();
        Store store = storeDAO.getStoreByAccount(account);
        searcher.put("totalAmount", request.getParameter("totalAmount"));
        searcher.put("storeID", String.valueOf(store.getId()));

        List<Orders> orders = orderDAO.getOrders(pageable, searcher);

        request.setAttribute("currentPage", page);
        request.setAttribute("sortField", sortField);
        // Tu dong dao nguoc khi nhan nhieu lan vao sortField
        request.setAttribute("isAscending", !isAsc);
        for (Map.Entry<String, String> entry : searcher.entrySet()) {
            // attribute key = entry key |  attribute value = entry value
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        return orders;
    }
}
