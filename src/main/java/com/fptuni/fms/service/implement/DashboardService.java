package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.service.IDashboardService;
import com.fptuni.fms.utils.DateUtils;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DashboardService implements IDashboardService {
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private StoreDAO storeDAO = new StoreDAO();

    @Override
    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        List<Date> listDateBetween = null;

        try {

            if(startDate != null && endDate != null){
                date1 = sdf.parse(startDate);
                date2 = sdf.parse(endDate);
                if(startDate.equals(sdf.format(new Date())) && endDate.equals(sdf.format(new Date()))){
                    System.out.println("TODAY");
                }
                else {
                    //Create day between list
                    listDateBetween = DateUtils.getDaysBetweenDates(date1, date2);
                }
            }
            else {
                Calendar calendar = Calendar.getInstance();
                date2 = calendar.getTime();
                String tmpDate2 = sdf.format(date2);
                date2 = sdf.parse(tmpDate2);
                calendar.add(Calendar.MONTH, -1);
                calendar.add(Calendar.DATE, +1);
                date1 = calendar.getTime();
                String tmpDate1 = sdf.format(date1);
                date1 = sdf.parse(tmpDate1);
                listDateBetween = DateUtils.getDaysBetweenDates(date1, date2);
            }
            System.out.println("date1: " + date1);
            System.out.println("date2: " + date2);

            //Get Top Store
            List<Store> listStore = storeDAO.getTopStore(5, date1, date2);
            Map<Store, Pair<DecimalFormat, Integer>> listTopStores =  new HashMap<>();
            for (Store item : listStore) {
                listTopStores.put(item, new Pair(storeDAO.GetTotalValueOfStore(item.getId(), date1, date2), storeDAO.GetOrderQuantity(item.getId(), date1, date2)));
                System.out.println(item.getName());
            }

            //Get Total value of all store
            BigDecimal totalValueAllStore = storeDAO.GetTotalValueOfAllStore(date1, date2);
            //Get Total order of all store
            int totalOrderAllStore = storeDAO.GetTotalOrderOfAllStore(date1, date2);

            //Get order and value data chart by date
            HashMap<Date, Integer> listOrderByTime = new HashMap<>();
            HashMap<Date, BigDecimal> listValueByTime = new HashMap<>();
            List<String> listKeyDateBetween = new ArrayList<>();
            for (Date item : listDateBetween)  {
                listOrderByTime.put(item, storeDAO.GetTotalOrderByTime(item));
                listValueByTime.put(item, storeDAO.GetTotalValueByTime(item));
                listKeyDateBetween.add(new SimpleDateFormat("MM-dd").format(item));
            }

            //Get order and value data chart by time in date


            //set request
            request.setAttribute("BEGIN_DATE", startDate);
            request.setAttribute("END_DATE", endDate);
            request.setAttribute("LIST_KEY_TOP_STORES", listStore);
            request.setAttribute("TOP_STORES", listTopStores);
            request.setAttribute("TOTAL_VALUE_ALLSTORES", totalValueAllStore);
            request.setAttribute("TOTAL_ORDER_ALLSTORES", totalOrderAllStore);
            request.setAttribute("LIST_KEY_TOTAL_ORDER_BY_TIME", listKeyDateBetween);
            request.setAttribute("TOTAL_ORDER_BY_TIME", listOrderByTime);
            request.setAttribute("TOTAL_VALUE_BY_TIME", listValueByTime);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "/view/admin/dashboard.jsp";
    }

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
