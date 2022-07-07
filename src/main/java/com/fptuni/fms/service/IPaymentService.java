package com.fptuni.fms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IPaymentService {

    public boolean makePayment(HttpServletRequest request);

    public boolean addMoney(HttpServletRequest request);
}
