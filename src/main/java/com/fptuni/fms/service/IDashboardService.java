package com.fptuni.fms.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IDashBoardService {

    String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    List<Date> getDateRange(HttpServletRequest request, HttpServletResponse response);
}
