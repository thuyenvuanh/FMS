/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fptuni.fms.mapper;

import java.sql.ResultSet;

/**
 *
 * @author Casul
 * @param <T>
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs);
}
