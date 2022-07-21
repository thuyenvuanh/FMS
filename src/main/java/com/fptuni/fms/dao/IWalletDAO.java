/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fptuni.fms.dao;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.Wallet;
import java.util.List;

/**
 *
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public interface IWalletDAO extends GenericDAO<Wallet> {

    List<Wallet> getAll();

    Wallet getWalletWithID(int ID);

    Wallet getWalletWithCustomerID(int cusID);

    Integer createWallet (Customer customer);

}
