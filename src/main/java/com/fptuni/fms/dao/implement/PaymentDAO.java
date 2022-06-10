package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IPaymentDAO;
import com.fptuni.fms.mapper.PaymentMapper;
import com.fptuni.fms.model.Payment;

import java.util.List;

/**
 * @author LucasBV
 */
public class PaymentDAO extends AbstractDAO<Payment> implements IPaymentDAO {

    @Override
    public List<Payment> getPayments() {
        String sql = "SELECT ID,OrderID,Amount FROM Payment";
        List<Payment> payments = query(sql, new PaymentMapper());
        return payments.isEmpty() ? null : payments;
    }

    @Override
    public List<Payment> getPaymentsByOrderId(int orderID) {
        String sql = "SELECT ID,OrderID,Amount FROM Payment\n"
                + "WHERE OrderID = ?";
        List<Payment> payments = query(sql, new PaymentMapper(), orderID);
        return payments.isEmpty() ? null : payments;
    }

    @Override
    public Integer insertPayment(Payment payment) {
        String sql = "INSERT  INTO Payment\n" +
                "VALUES (?, ?)";
        return insert(sql, payment.getOrderID(), payment.getAmount());
    }

}
