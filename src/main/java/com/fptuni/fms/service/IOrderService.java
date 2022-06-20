package com.fptuni.fms.service;

import com.fptuni.fms.model.Category;
import com.fptuni.fms.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IOrderService {

    void index (HttpServletRequest request, HttpServletResponse response, Map<Category, List<Product>> productMap);

    void addNewProduct(HttpServletRequest request, HttpServletResponse response);

    void removeProduct(HttpServletRequest request, HttpServletResponse response);
}
