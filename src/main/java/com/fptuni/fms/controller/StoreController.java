package com.fptuni.fms.controller;

import com.fptuni.fms.service.IStoreService;
import com.fptuni.fms.service.implement.StoreService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StoreController", value = "/store/*")
public class StoreController extends HttpServlet {

    private final IStoreService storeService = new StoreService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        String Url = null;
        switch (action) {
            case "/create":
                Url = storeService.create(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/createPage":
                request.getRequestDispatcher("/view/admin/storeCreate.jsp").forward(request, response);
                break;
            case "/list":
                Url = storeService.getListStore(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/update":
                Url = storeService.update(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/updatePage":
                Url = storeService.getStoreUpdate(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/view":
                Url = storeService.getStore(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            case "/delete":
                Url = storeService.delete(request, response);
                request.getRequestDispatcher(Url).forward(request, response);
                break;
            default:
            //chuyen huong den trang error
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
