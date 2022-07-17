package com.fptuni.fms.service.implement;

import com.fptuni.fms.service.IDashBoardService;
import com.fptuni.fms.utils.DateUtils;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashBoardService implements IDashBoardService {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public List<Date> getDateRange(HttpServletRequest request, HttpServletResponse response) {
        List<Date> listOfDate = null;
        try {
            Date end = calendar.getTime();
            calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DATE, +1);
            Date start = calendar.getTime();
            if (request.getParameter("startDate") != null && request.getParameter("endDate") != null) {
                start = simpleDateFormat.parse(request.getParameter("startDate"));
                end = simpleDateFormat.parse(request.getParameter("endDate"));
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                request.setAttribute("startDateFmt", sdf.format(start));
                request.setAttribute("endDateFmt", sdf.format(end));
                if (start.after(end)) {
                    throw new Exception("Start date must be before end date");
                }
            }
//            System.out.println(simpleDateFormat.format(start) + " -- " + simpleDateFormat.format(end));
            listOfDate = DateUtils.getDaysBetweenDates(start, end);
//            for (Date date : listOfDate) {
//                System.out.println(simpleDateFormat.format(date));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfDate;
    }

    @Override
    public List<Date> getTimeRange(HttpServletRequest request, HttpServletResponse response) {
        List<Date> listOfDate = null;
        int range = 15;
        try {
            Date end = calendar.getTime();
            calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DATE, +1);
            Date start = calendar.getTime();
            if (request.getParameter("startDate") != null && request.getParameter("endDate") != null) {
                start = simpleDateFormat.parse(request.getParameter("startDate"));
                end = simpleDateFormat.parse(request.getParameter("endDate"));
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                request.setAttribute("startDateFmt", sdf.format(start));
                request.setAttribute("endDateFmt", sdf.format(end));
                if (start.after(end)) {
                    throw new Exception("Start date must be before end date");
                }
            }
//            System.out.println(simpleDateFormat.format(start) + " -- " + simpleDateFormat.format(end));
            listOfDate = DateUtils.addHoursToJavaUtilDate(start, range);
//            for (Date date : listOfDate) {
//                System.out.println(simpleDateFormat.format(date));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfDate;
    }
}
