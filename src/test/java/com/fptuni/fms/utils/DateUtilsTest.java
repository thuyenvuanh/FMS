package com.fptuni.fms.utils;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {

    @Test
    void getDaysBetweenDates() {
        Date startDate = new Date(2022, Calendar.JUNE,10);
        Date endDate = new Date(startDate.getTime() + 7 * 24 * 60 * 60 * 1000);
        List<Date> listOfDate = DateUtils.getDaysBetweenDates(startDate, endDate);
        for (Date date : listOfDate) {
            System.out.println(date);
        }
        assertEquals(7, listOfDate.size());
    }
}