package com.fptuni.fms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IOrderService {

    void index(HttpServletRequest request, HttpServletResponse response);

    void addNewProduct(HttpServletRequest request, HttpServletResponse response);

    void removeProduct(HttpServletRequest request, HttpServletResponse response);
}
