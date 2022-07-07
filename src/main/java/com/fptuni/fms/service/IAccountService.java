package com.fptuni.fms.service;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAccountService {

    String login(HttpServletRequest request, HttpServletResponse respons) throws ServletException, IOException;

    String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String getRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String getListAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String getAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String getAccountUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
