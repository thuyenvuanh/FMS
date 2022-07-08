package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.IOrderDetailDAO;
import com.fptuni.fms.dao.IStoreDAO;
import com.fptuni.fms.dao.implement.OrderDetailDAO;
import com.fptuni.fms.dao.implement.StoreDAO;
import com.fptuni.fms.model.Account;
import com.fptuni.fms.model.OrderDetail;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.service.IOrderDetailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDetailService implements IOrderDetailService {
    IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    @Override
    public List<OrderDetail> getOrderDetailByOrderID(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            IStoreDAO storeDAO = new StoreDAO();
            Store store = storeDAO.getStoreByAccount(account);
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int storeID = store.getId();
            List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrderID(orderID, storeID);
            return orderDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderDetail getOrderDetailByProductID(HttpServletRequest request, HttpServletResponse response, String productID) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        OrderDetail orderDetails = null;
        try {
            Calendar calendar = Calendar.getInstance();
            Date end = calendar.getTime();
            calendar.add(Calendar.MONTH, -1);
            Date start = calendar.getTime();
            if (request.getParameter("startDate") != null && request.getParameter("endDate") != null) {
                start = sdf.parse(request.getParameter("startDate"));
                end = sdf.parse(request.getParameter("endDate"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                request.setAttribute("startDateFmt", simpleDateFormat.format(start));
                request.setAttribute("endDateFmt", simpleDateFormat.format(end));
                if (start.after(end)) {
                    throw new Exception("Start date must be before end date");
                }
            }
            orderDetails = orderDetailDAO.getOrderDetailByProductID(store,productID , start, end);
        } catch (Exception e) {
            request.setAttribute("dateError", e.getMessage());
            e.printStackTrace();
        }
        return orderDetails;
    }
}
