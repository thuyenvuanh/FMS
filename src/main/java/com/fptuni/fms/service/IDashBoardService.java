package com.fptuni.fms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IDashBoardService {
    List<Date> getDateRange(HttpServletRequest request, HttpServletResponse response);

    List<Date> getTimeRange(HttpServletRequest request, HttpServletResponse response);

}
