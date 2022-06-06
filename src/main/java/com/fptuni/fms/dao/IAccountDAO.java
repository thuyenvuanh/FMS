/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fptuni.fms.dao;

import com.fptuni.fms.model.Account;

/**
 *
 * @author Casul
 */
public interface IAccountDAO extends GenericDAO<Account> {

    Account getAccount(String username, String password);
}
