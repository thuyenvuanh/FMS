/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fptuni.fms.dao;

import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.paging.Pageable;

import java.util.List;
import java.util.Map;

/**
 *
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public interface ITransactionShared extends GenericDAO<TransactionShared>{
    
    List<TransactionShared> getAll();
    
    TransactionShared getLatestTransactionOf(int WalletID);
    
    List<TransactionShared> getHistoryOf(int WalletID, Boolean... isAscending);

    TransactionShared getLatestTransaction();

    int insertTransaction(TransactionShared transactionShared);
    List<TransactionShared> getTransactionSharedByStore(Store store, Map<String, String> searcher, Pageable pageable);
    List<TransactionShared> searchTransactionShare(Store store, Map<String, String> searcher);
}
