/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NhatTan
 */
public interface IStoreService {

    String create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

//    String getAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String getListStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String getStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String getStoreUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
