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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDetailService implements IOrderDetailService {
    IOrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


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
        OrderDetail orderDetails = null;
        try {
            Calendar calendar = Calendar.getInstance();
            Date end = calendar.getTime();
            calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DATE, +1);
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
            orderDetails = orderDetailDAO.getOrderDetailByProductID(store, productID, start, end);
        } catch (Exception e) {
            request.setAttribute("dateError", e.getMessage());
            e.printStackTrace();
        }
        return orderDetails;
    }

    public BigDecimal getTotalAmount(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        try {
            Calendar calendar = Calendar.getInstance();
            Date end = calendar.getTime();
            calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DATE, +1);
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
            List<OrderDetail> orderDetails = orderDetailDAO.getOrdersDetailByDateRange(store, start, end);
            for (OrderDetail od : orderDetails) {
                totalAmount = totalAmount.add(od.getAmount());
            }
        } catch (Exception e) {
            request.setAttribute("dateError", e.getMessage());
            e.printStackTrace();
        }
        return totalAmount;
    }

    @Override
    public BigDecimal getTotalAmountByDate(HttpServletRequest request, Date date) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        try {
            totalAmount = orderDetailDAO.getTotalAmountByDate(store, date);
        } catch (Exception e) {
            request.setAttribute("dateError", e.getMessage());
            e.printStackTrace();
        }
        return totalAmount;
    }

    public List<OrderDetail> getOrderDetailInDateRange(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        List<OrderDetail> orderDetails = null;
        try {
            Calendar calendar = Calendar.getInstance();

            Date end = calendar.getTime();
            calendar.add(Calendar.MONTH, -1);
            calendar.add(Calendar.DATE, +1);
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
            orderDetails = orderDetailDAO.getOrdersDetailByDateRange(store, start, end);
        } catch (Exception e) {
            request.setAttribute("dateError", e.getMessage());
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public List<OrderDetail> getOrderDetailInTimeRange(HttpServletRequest request, Date start, Date end) {
        HttpSession session = request.getSession();
        Store store = (Store) session.getAttribute("store");
        List<OrderDetail> orderDetails = null;
        try {
            orderDetails = orderDetailDAO.getOrdersDetailByTimeRange(store, start, end);
        } catch (Exception e) {
            request.setAttribute("dateError", e.getMessage());
            e.printStackTrace();
        }
        return orderDetails;
    }

    @Override
    public BigDecimal getTotalAmountOfList(List<OrderDetail> orderDetails) {
        BigDecimal total = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDetails) {
            total = total.add(orderDetail.getAmount());
        }
        return total;
    }

}
