package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.dao.implement.ProductDAO;
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
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderService implements IOrderService {


    @Override
    public void index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Orders order = (Orders) session.getAttribute("order");
        if (order == null) {
            order = new Orders();
            order.setCreatedDate(new Date());
            order.setOrderDetailList(new ArrayList<>());
            order.setStoreID((Store) session.getAttribute("store"));
            order.setPaymentList(new ArrayList<>());
            order.calcTotal();
        }
        session.setAttribute("order", order);

        if (request.getSession().getAttribute("categoryListMap") == null) {
            request.getSession().setAttribute("productsMap", loadData(request));
        }

        new CategoryService().loadProducts(request, response);
    }

    @Override
    public void addNewProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = (List<Product>) request.getSession().getAttribute("products");
        Product product = products.get(products.indexOf(new Product(request.getParameter("id"))));
        Orders orders = (Orders) request.getSession().getAttribute("order");
        List<OrderDetail> details = orders.getOrderDetailList();
        boolean isNewItem = true;
        for (OrderDetail orderDetail : details) {
            if ((orderDetail.getProduct()).equals(product)) {
                orderDetail.increaseOne();
                isNewItem  = false;
                break;
            }
        }
        if (details.isEmpty() || isNewItem){
            OrderDetail newDetail = new OrderDetail();
            newDetail.setOrders(orders);
            newDetail.setProduct(product);
            newDetail.setQuantity(1);
            newDetail.setPrice(product.getPrice());
            newDetail.calcAmount();
            details.add(newDetail);
        }
        orders.calcTotal();
        request.getSession().setAttribute("order", orders);
    }

    @Override
    public void removeProduct(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Orders orders = (Orders) request.getSession().getAttribute("order");
        List<OrderDetail> details = orders.getOrderDetailList();
        for (OrderDetail detail : details) {
            if (detail.getProduct().getId().equals(id)) {
                if (detail.getQuantity() >= 0) {
                    detail.decreaseOne();
                    break;
                }
            }
        }
        details.removeIf(orderDetail -> orderDetail.getQuantity() == 0);
        orders.calcTotal();
        request.getSession().setAttribute("order", orders);
    }

    @Override
    public void voidAll(HttpServletRequest request, HttpServletResponse response) {
        Orders orders = (Orders) request.getSession().getAttribute("order");
        orders.setOrderDetailList( new ArrayList<>());
        orders.calcTotal();
        request.getSession().setAttribute("order", orders);
    }

    public HashMap<Category, List<Product>> loadData(HttpServletRequest request) {
        IProductDAO productDAO = new ProductDAO();
        ICategoryDAO categoryDAO = new CategoryDAO();
        Store store = (Store) request.getSession().getAttribute("store");
        List<Product> productList = productDAO.getProductsByStore(store);
        HashMap<Category, List<Product>> productMap = new HashMap<>();
        productList.forEach(product -> {
            if (productMap.containsKey(product.getCateID())) {
                productMap.get(product.getCateID()).add(product);
            } else {
                Category category = categoryDAO.getCategory(product.getCateID().getId());
                List<Product> products = new ArrayList<>();
                products.add(product);
                productMap.put(category, products);
            }
        });
        return productMap;
    }

    @Override
    public List<Orders> getOrders(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        IOrderDAO orderDAO = new OrderDAO();
        IStoreDAO storeDAO = new StoreDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
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
        Account test = new Account(6);
        Store store = storeDAO.getStoreByAccount(test);
        searcher.put("totalAmount", request.getParameter("totalAmount"));
        searcher.put("storeID", String.valueOf(store.getId()));
        try {
            if (request.getParameter("startDate") != null && request.getParameter("endDate") != null) {
                Date start = sdf.parse(request.getParameter("startDate"));
                Date end = sdf.parse(request.getParameter("endDate"));
                if (start.after(end)) {
                    throw new Exception("Start date must be before end date");
                } else {
                    searcher.put("startDate", request.getParameter("startDate"));
                    searcher.put("endDate", request.getParameter("endDate"));
                }
            } else {
                if (request.getParameter("startDate") != null) {
                    searcher.put("startDate", request.getParameter("startDate"));

                } else if (request.getParameter("endDate") != null) {
                    searcher.put("endDate", request.getParameter("endDate"));
                }
            }
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
        } catch (Exception e) {
            request.setAttribute("dateError", e.getMessage());
            return null;
        }

    }
}
