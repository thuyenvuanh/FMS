package com.fptuni.fms.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IDashboardService {
    String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
