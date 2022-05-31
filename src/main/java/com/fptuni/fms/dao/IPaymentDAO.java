package com.fptuni.fms.dao;

import com.fptuni.fms.model.Payment;
import java.util.List;

/**
 *
 * @author LucasBV
 */
public interface IPaymentDAO extends GenericDAO<Payment> {

    List<Payment> getPayments();

    List<Payment> getPaymentsByOrderId(int orderID);
}
