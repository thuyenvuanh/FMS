package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Orders;
import com.fptuni.fms.model.Payment;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author LucasBV
 */
public class PaymentMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet rs) {
        Payment payment = new Payment();
        ResultSetMetaData metaData = null;
        try {
            metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (metaData.getColumnLabel(i).equals("ID"))
                    payment.setId(rs.getInt(i));
                else if (metaData.getColumnLabel(i).equals("OrderID"))
                    payment.setOrderID(rs.getInt(i) == 0 ? null : new Orders(rs.getInt(i)));
                else if (metaData.getColumnLabel(i).equals("Amount"))
                    payment.setAmount(rs.getBigDecimal(i));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return payment;
    }

}
