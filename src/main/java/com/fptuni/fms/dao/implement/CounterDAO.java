/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ICounterDAO;
import com.fptuni.fms.mapper.CounterMapper;
import com.fptuni.fms.model.Counter;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class CounterDAO extends AbstractDAO<Counter> implements ICounterDAO {

    @Override
    public List<Counter> getAll() {
        String sql = "SELECT ID, IsDeleted FROM Counter\n" +
                "WHERE IsDeleted = 0";
        List<Counter> counters = query(sql, new CounterMapper());
        return counters.isEmpty() ? null : counters;
    }

    @Override
    public Counter get(int id) {
        String sql = "SELECT ID, IsDeleted FROM Counter\n"
                + "Where ID = ? and IsDeleted = 0";
        List<Counter> counters = query(sql, new CounterMapper(), id);
        return counters.isEmpty() ? null : counters.get(0);
    }

}
