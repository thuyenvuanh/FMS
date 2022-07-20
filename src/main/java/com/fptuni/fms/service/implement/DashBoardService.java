package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.OrderDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.service.IDashBoardService;
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

public class DashBoardService implements IDashBoardService {

    private StoreDAO storeDAO = new StoreDAO();
    private OrderDAO orderDAO = new OrderDAO();
    @Override
    public List<Date> getDateRange(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public List<Date> getTimeRange(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String getDashboardData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> listDateBetween = null;
        List<Date> listTime = null;
        int range = 15;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date2 = calendar.getTime();
            String tmpDate2 = sdf.format(date2);
            date2 = sdf.parse(tmpDate2);
            calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DATE, +1);
            Date date1 = calendar.getTime();
            String tmpDate1 = sdf.format(date1);
            date1 = sdf.parse(tmpDate1);
            listDateBetween = DateUtils.getDaysBetweenDates(date1, date2);
            if(startDate != null && endDate != null){
                date1 = sdf.parse(startDate);
                date2 = sdf.parse(endDate);
                //Get data today or yesterday
                listTime = DateUtils.addHoursToJavaUtilDate(date1, range);
                listDateBetween = DateUtils.getDaysBetweenDates(date1, date2);
                if(listDateBetween.size() == 1){
                    setDataByTime(request, listTime, date1, date2, startDate, endDate);
                    return "/view/admin/dashboard.jsp";
                }
            }
            //Get data not today or yesterday
            setData(request, listDateBetween, date1, date2, startDate, endDate);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "/view/admin/dashboard.jsp";
    }

    private void setData(HttpServletRequest request, List<Date> listDateBetween, Date date1, Date date2, String startDate, String endDate){
        try{
            //Get Top Store
            List<Store> listStore = storeDAO.getTopStore(5, date1, date2);
            Map<Store, Pair<BigDecimal, Integer>> listTopStores =  new HashMap<>();
            for (Store item : listStore) {
                listTopStores.put(item, new Pair(orderDAO.GetTotalValueOfStore(item.getId(), date1, date2), orderDAO.GetOrderQuantity(item.getId(), date1, date2)));
                System.out.println(item.getName());
            }

            //Get Total value of all store
            BigDecimal totalValueAllStore = orderDAO.GetTotalValueOfAllStore(date1, date2);
            //Get Total order of all store
            int totalOrderAllStore = orderDAO.GetTotalOrderOfAllStore(date1, date2);


            //Get order and value data chart by date
            HashMap<Date, Integer> listOrderByTime = new HashMap<>();
            HashMap<Date, BigDecimal> listValueByTime = new HashMap<>();
            List<String> listKeyDateBetween = new ArrayList<>();

            for (Date item : listDateBetween)  {
                listOrderByTime.put(item, orderDAO.GetTotalOrderByDate(item));
                listValueByTime.put(item, orderDAO.GetTotalValueByDate(item));
                listKeyDateBetween.add(new SimpleDateFormat("dd-MM").format(item));
//                System.out.println("Date:" + item + " - " + orderDAO.GetTotalOrderByTime(item));

            }
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
            request.setAttribute("KEY_DATE", listDateBetween);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private void setDataByTime(HttpServletRequest request, List<Date> listDateBetween, Date date1, Date date2, String startDate, String endDate){
        //Get Top Store
        try {
            List<Store> listStore = storeDAO.getTopStoreToday(5, date1);
            Map<Store, Pair<DecimalFormat, Integer>> listTopStores =  new HashMap<>();
            for (Store item : listStore) {
                listTopStores.put(item, new Pair(orderDAO.GetTotalValueOfStore(item.getId(), date1, date2), orderDAO.GetOrderQuantity(item.getId(), date1, date2)));
                System.out.println(item.getName());
            }

            //Get Total value of all store
            BigDecimal totalValueAllStore = orderDAO.GetTotalValueOfAllStore(date1, date2);
            //Get Total order of all store
            int totalOrderAllStore = orderDAO.GetTotalOrderOfAllStore(date1, date2);

            //Get order and value data chart by date
            HashMap<Date, Integer> listOrderByTime = new HashMap<>();
            HashMap<Date, BigDecimal> listValueByTime = new HashMap<>();
            List<String> listKeyDateBetween = new ArrayList<>();

            for(int i = 0; i < listDateBetween.size() - 1; i++){
                listOrderByTime.put(listDateBetween.get(i), orderDAO.GetTotalOrderByTime(listDateBetween.get(i), listDateBetween.get(i + 1)));
                listValueByTime.put(listDateBetween.get(i), orderDAO.GetTotalValueByTime(listDateBetween.get(i), listDateBetween.get(i + 1)));
                listKeyDateBetween.add(new SimpleDateFormat("HH:mm").format(listDateBetween.get(i)) + "-" + new SimpleDateFormat("HH:mm").format(listDateBetween.get(i + 1)));
            }


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
            request.setAttribute("KEY_DATE", listDateBetween);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
