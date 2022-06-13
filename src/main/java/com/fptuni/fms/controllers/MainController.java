package com.fptuni.fms.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();
        String controller = url.substring(0, url.lastIndexOf("/"));
        String action = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
//        String action = url.substring(url.lastIndexOf("/") + 1);

        System.out.println("ServletPath: " + url);
        System.out.println("Controller: " + controller);
        System.out.println("Action: " + action);

        request.setAttribute("controller", controller);
        request.setAttribute("action", action);

        this.getServletContext().getRequestDispatcher(controller).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
