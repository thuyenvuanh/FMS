/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fptuni.fms.controller;

import com.fptuni.fms.service.IStoreService;
import com.fptuni.fms.service.implement.StoreService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StoreController", urlPatterns = {"/store/*"})
public class StoreController extends HttpServlet {

    private final StoreService storeService = new StoreService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        String url = null;
        switch (action) {
            case "/create":
                url = storeService.create(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/createPage":
                url = storeService.getStoreManager(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/list":
                url = storeService.getListStore(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/update":
                url = storeService.update(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/updatePage":
                url = storeService.getStoreUpdate(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/view":
                url = storeService.getStore(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/delete":
                url = storeService.delete(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            case "/search":
                url = storeService.search(request, response);
                request.getRequestDispatcher(url).forward(request, response);
                break;
            default:
            //chuyen huong den trang error
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
