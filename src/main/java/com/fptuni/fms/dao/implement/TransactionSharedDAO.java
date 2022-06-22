/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ITransactionShared;
import com.fptuni.fms.mapper.RowMapper;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.mapper.TransactionSharedMapper;
import java.util.List;

/**
 *
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public class TransactionSharedDAO extends AbstractDAO<TransactionShared> implements ITransactionShared {

    RowMapper<TransactionShared> mapper;

    public TransactionSharedDAO() {
        mapper = new TransactionSharedMapper();
    }

    @Override
    public List<TransactionShared> getAll() {
        String sql = "select * from TransactionShared\n"
                + "order by TransactionShared.CreatedDate\n"
                + "DESC";
        return query(sql, mapper);
    }

    @Override
    public TransactionShared getLatestTransactionOf(int WalletID) {
        String sql =  "select top(1) * from TransactionShared\n"
                    + "where TransactionShared.WalletID = ?\n"
                    + "order by TransacitonShared.CreatedDate\n"
                    + "DESC";
        List<TransactionShared> list = query(sql, mapper, WalletID);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<TransactionShared> getHistoryOf(int WalletID, Boolean... isAscending) {
        String sql =  "select * from TransactionShared\n"
                    + "where TransactionShared.WalletID = ?\n"
                    + "order by TransactionCreate\n";
        sql += ((isAscending[0] != null && isAscending[0]) ? "ASC" : "DESC");
        return query(sql, mapper, WalletID);
    }

    @Override
    public int insertTransaction(TransactionShared transactionShared) {
        return 0;
    }

}
