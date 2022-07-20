/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.ITransactionShared;
import com.fptuni.fms.mapper.RowMapper;
import com.fptuni.fms.model.Store;
import com.fptuni.fms.model.TransactionShared;
import com.fptuni.fms.mapper.TransactionSharedMapper;

import java.sql.Timestamp;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
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
        String sql = "select top(1) * from TransactionShared\n" +
                "where WalletID = ?\n" +
                "order by CreatedDate DESC, ID desc";
        List<TransactionShared> list = query(sql, mapper, WalletID);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<TransactionShared> getHistoryOf(int WalletID, Boolean... isAscending) {
        String sql = "select * from TransactionShared\n"
                + "where TransactionShared.WalletID = ?\n"
                + "order by CreatedDate\n";
        sql += ((isAscending[0] != null && isAscending[0]) ? "ASC" : "DESC");
        return query(sql, mapper, WalletID);
    }

    @Override
    public TransactionShared getLatestTransaction() {
        String sql = "select top(1) * from TransactionShared\n"
                + "order by TransactionShared.CreatedDate\n"
                + "DESC";
        List<TransactionShared> list = query(sql, mapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int insertTransaction(TransactionShared transactionShared) {
        String sql = "INSERT INTO TransactionShared (Amount, WalletID, PreviousHash, HashValue, PreviousBalance, CreatedDate, Status, MoneyTransactionID, PaymentID)\n" +
                "values (?,?,?,?,?,?,?,?,?)";
        return insert(sql,
                transactionShared.getAmount().stripTrailingZeros(),
                transactionShared.getWalletID().getId(),
                transactionShared.getPreviousHash(),
                transactionShared.getHashValue(),
                transactionShared.getPreviousBalance().stripTrailingZeros(),
                new Timestamp(transactionShared.getCreatedDate().getTime()),
                transactionShared.getStatus(),
                transactionShared.getMoneyTransactionID() == null ? null : transactionShared.getMoneyTransactionID().getId(),
                transactionShared.getPaymentID() == null ? null : transactionShared.getPaymentID().getId());
    }

    @Override
    public List<TransactionShared> getTransactionSharedByStore(Store store) {
        String sql = "SELECT ts.ID, ts.Amount, WalletID, PreviousHash, HashValue, PreviousBalance, ts.CreatedDate, Status, MoneyTransactionID, PaymentID, w.CustomerID, s.ID AS StoreID, o.ID AS OrderID \n" +
                "FROM TransactionShared ts\n" +
                "JOIN Wallet w ON w.ID = ts.WalletID AND w.IsDeleted = 0 AND ts.IsDeleted = 0\n" +
                "JOIN Payment p ON p.ID = ts.PaymentID AND p.IsDeleted = 0\n" +
                "JOIN Orders o ON o.ID = p.OrderID AND o.IsDeleted = 0\n" +
                "JOIN Store s ON s.ID = o.StoreID AND s.ID = ? ";
        return query(sql, new TransactionSharedMapper(), store.getId());
    }

}
