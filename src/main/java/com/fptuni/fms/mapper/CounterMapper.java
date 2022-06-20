/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.mapper;

import com.fptuni.fms.model.Counter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LucasBV
 */
public class CounterMapper implements RowMapper<Counter> {

    @Override
    public Counter mapRow(ResultSet rs) {
        Counter counter = null;
        try {
            counter = new Counter();
            if(rs.getObject("ID") != null)
            counter.setId(rs.getInt("ID"));
            if(rs.getObject("IsDeleted") != null)
            counter.setIsDeleted(rs.getBoolean("IsDeleted"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return counter;
    }

}
