/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fptuni.fms.dao;

import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.model.Wallet;
import java.util.List;

/**
 *
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public interface ITransactionShared extends GenericDAO<TransactionShared>{
    
    List<TransactionShared> findAll();
    
    TransactionShared findLatestTransactionOf(int WalletID);
    
    List<TransactionShared> findHistoryOf(int WalletID, Boolean... isAscending);
    
}
