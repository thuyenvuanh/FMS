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

    // Date is the 1 date, hours is range of hours in that date
    public static List<Date> addHoursToJavaUtilDate(Date date, int hours) {
        List<Date> datesTime = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 8); // begin hour is 8:00 AM
        for (int i = 1; i <= hours; i++) {
            datesTime.add(calendar.getTime());
            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }
        return datesTime;
    }
}
