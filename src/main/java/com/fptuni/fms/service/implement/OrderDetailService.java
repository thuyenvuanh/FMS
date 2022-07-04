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
}
