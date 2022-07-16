package com.fptuni.fms.controller;

import com.fptuni.fms.service.IDashBoardService;
import com.fptuni.fms.service.implement.DashBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminDashboardController", value = "/adminDashboard/*")
public class AdminDashboardController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URL = null;
        IDashBoardService dashboardService = new DashBoardService();
        String path = request.getPathInfo();
        System.out.println(path);

        if(path.equals("/index")){

            URL = dashboardService.index(request, response);
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
