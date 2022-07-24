package com.fptuni.fms.service;

import com.fptuni.fms.model.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IPaymentService {

    boolean makePayment(HttpServletRequest request);

    boolean addMoney(HttpServletRequest request);

    boolean withDraw(HttpServletRequest request);
    List<Payment> getPaymentsByOrderID(int orderID);
}
