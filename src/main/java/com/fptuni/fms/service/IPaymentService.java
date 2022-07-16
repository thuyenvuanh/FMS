package com.fptuni.fms.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IPaymentService {

    void makePayment(HttpServletRequest request);

}
