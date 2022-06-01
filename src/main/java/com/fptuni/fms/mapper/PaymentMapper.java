package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Payment;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LucasBV
 */
public class PaymentMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet rs) {
        Payment payment = new Payment();
        try {
            payment.setId(rs.getInt("ID"));
            payment.setOrderID(new Orders(rs.getInt("OrderID")));
            payment.setAmount(rs.getBigDecimal("Amount"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return payment;
    }

}
