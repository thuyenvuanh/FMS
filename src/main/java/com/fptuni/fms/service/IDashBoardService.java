package com.fptuni.fms.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IDashBoardService {
    List<Date> getDateRange(HttpServletRequest request, HttpServletResponse response);

    List<Date> getTimeRange(HttpServletRequest request, HttpServletResponse response);
    String getDashboardData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
