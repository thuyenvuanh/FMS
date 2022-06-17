package com.fptuni.fms.service;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAccountService {

    String login(HttpServletRequest request, HttpServletResponse respons) throws ServletException, IOException;

    String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
