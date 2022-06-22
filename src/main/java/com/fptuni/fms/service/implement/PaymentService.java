package com.fptuni.fms.service.implement;

import com.fptuni.fms.service.IPaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentService implements IPaymentService {
    @Override
    public void makePayment(HttpServletRequest request, HttpServletResponse response) {
        //check if the wallet exist and have enough money
        //if fulfill the criteria write order, orderDetail, payment, and transaction to the database
        //else return to the cashier screen and notify error message

    }
}
