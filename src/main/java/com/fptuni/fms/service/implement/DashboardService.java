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

    private StoreDAO storeDAO = new StoreDAO();
    @Override
    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("NUll: " + startDate);
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
                calendar.add(Calendar.MONTH, -1);
                date1 = calendar.getTime();
                date2 = new Date();
//                startDate = new SimpleDateFormat("yyyy-MM-dd").format(date1);
//                endDate = new SimpleDateFormat("yyyy-MM-dd").format(date2);
//
//                System.out.println("START DATE: " + startDate);
//                System.out.println("END DATE: " + endDate);
            }
            //Get Top Store
            List<Store> listStore = storeDAO.getTopStore(5, date1, date2);
            Map<Store, Pair<DecimalFormat, Integer>> listTopStores =  new HashMap<>();
            for (Store item : listStore) {
                listTopStores.put(item, new Pair(storeDAO.GetTotalValueOfStore(item.getId(), date1, date2), storeDAO.GetOrderQuantity(item.getId(), date1, date2)));
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
}
