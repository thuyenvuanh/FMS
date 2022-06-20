package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.ICategoryDAO;
import com.fptuni.fms.dao.IProductDAO;
import com.fptuni.fms.dao.implement.CategoryDAO;
import com.fptuni.fms.dao.implement.ProductDAO;
import com.fptuni.fms.model.*;
import com.fptuni.fms.service.IOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class OrderService implements IOrderService {


    @Override
    public void index(HttpServletRequest request, HttpServletResponse response, Map<Category, List<Product>> productMap) {
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
        loadProducts(request, response, productMap);
    }

    private void loadProducts(HttpServletRequest request, HttpServletResponse response, Map<Category, List<Product>> productMap) {
        List<Category> categories = (List<Category>) request.getSession().getAttribute("categories");
        if (categories == null) {
            categories = new ArrayList<Category>(productMap.keySet());
            request.getSession().setAttribute("categories", categories);
        }
        Category currentCate = (Category) request.getSession().getAttribute("currentCate");
        if (currentCate == null) {
            currentCate = categories.get(0);
            request.getSession().setAttribute("currentCate", currentCate);
        }
        List<Product> products = productMap.get(currentCate);
        request.getSession().setAttribute("products", products);

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
}
