package com.fptuni.fms.controller;

import com.fptuni.fms.service.IAdminDashboardService;
import com.fptuni.fms.service.implement.AdminDashboardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminDashboardController", value = "/adminDashboard/*")
public class AdminDashboardController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URL = null;
        IAdminDashboardService dashboardService = new AdminDashboardService();
        String path = request.getPathInfo();
        System.out.println(path);

        if(path.equals("/index")){
            URL = dashboardService.getDashboardData(request, response);
            request.getRequestDispatcher(URL).forward(request, response);
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
