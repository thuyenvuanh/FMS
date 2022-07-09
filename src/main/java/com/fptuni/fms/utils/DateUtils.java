package com.fptuni.fms.utils;

import java.util.*;

public class DateUtils {
    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        // plus 1 date for end date
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(enddate);
        calendar1.add(Calendar.DATE, 1);
        enddate = calendar1.getTime();

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
}
