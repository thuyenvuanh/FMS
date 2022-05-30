/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.fptuni.fms.dao;

import com.fptuni.fms.model.Counter;
import java.util.List;

/**
 *
 * @author Casul
 */
public interface ICounterDAO extends GenericDAO<Counter> {
    List<Counter> getAll();
    Counter get(Integer id);
}
