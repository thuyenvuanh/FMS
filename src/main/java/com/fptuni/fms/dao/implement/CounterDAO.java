/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICounterDAO;
import com.fptuni.fms.model.Counter;
import com.fptuni.mapper.CounterMapper;
import java.util.List;

/**
 *
 * @author LucasBV
 */
public class CounterDAO extends AbstractDAO<Counter> implements ICounterDAO {

    @Override
    public List<Counter> getAllCounter() {
        String sql = "SELECT ID FROM Counter";
        List<Counter> counters = query(sql, new CounterMapper());
        return counters.isEmpty() ? null : counters;
    }

}
