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
import com.fptuni.fms.paging.Pageable;
import jdk.nashorn.internal.ir.IfNode;

import java.awt.datatransfer.StringSelection;
import java.sql.Timestamp;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public class TransactionSharedDAO extends AbstractDAO<TransactionShared> implements ITransactionShared {

    TransactionSharedMapper mapper = new TransactionSharedMapper();

    public TransactionSharedDAO() {
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
    public List<TransactionShared> getTransactionSharedByStore(Store store, Map<String, String> searcher, Pageable pageable) {
        List<String> params = new ArrayList<>();
        params.add(store.getId().toString());
        String sql = "SELECT ts.ID, ts.Amount, WalletID, PreviousHash, HashValue, PreviousBalance, ts.CreatedDate, Status, MoneyTransactionID, PaymentID\n" +
                "FROM TransactionShared ts\n" +
                "JOIN Wallet w ON w.ID = ts.WalletID AND w.IsDeleted = 0" +
                "JOIN Customer c ON c.ID = w.CustomerID\n" +
                "JOIN Payment p ON p.ID = ts.PaymentID AND p.IsDeleted = 0\n" +
                "JOIN Orders o ON o.ID = p.OrderID  AND o.IsDeleted = 0\n" +
                "JOIN Store s ON s.ID = o.StoreID AND s.ID = ? ";
        if (searcher.get("status") != null && !searcher.get("status").isEmpty()) {
            sql += " AND ts.Status = ? \n";
            params.add(searcher.get("status"));
        }
        if (searcher.get("customerPhone") != null && !searcher.get("customerPhone").isEmpty()) {
            sql += " AND c.Phone = ? \n";
            params.add(searcher.get("customerPhone"));
        }
        if (searcher.get("dateSearch") != null && !searcher.get("dateSearch").isEmpty()) {
            sql += " AND CONVERT(date, ts.CreatedDate, 103)  = CONVERT(date, ? , 103)  \n";
            params.add(searcher.get("dateSearch"));
        }
        if (searcher.get("amount") != null && !searcher.get("amount").isEmpty()) {
            sql += " AND ts.Amount = ? \n";
            params.add(searcher.get("amount"));
        }
        if (pageable != null) {
            if (pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty()) {
                String orderBy = pageable.getSorter().isAscending() ? " ASC " : " DESC ";
                sql += " ORDER BY  " + pageable.getSorter().getSortField() + " " + orderBy;
            }
            if (pageable.getOffset() != null && pageable.getLimit() != null) {
                sql += " OFFSET " + pageable.getOffset() + " ROWS\n " +
                        " FETCH NEXT " + pageable.getLimit() + " ROW ONLY";
            }
        }
        return query(sql, new TransactionSharedMapper(), params.toArray());
    }

    @Override
    public List<TransactionShared> searchTransactionShare(Store store, Map<String, String> searcher) {
        List<String> params = new ArrayList<>();
        params.add(store.getId().toString());
        String sql = "SELECT ts.ID, ts.Amount, WalletID, PreviousHash, HashValue, PreviousBalance, ts.CreatedDate, Status, MoneyTransactionID, PaymentID\n" +
                "FROM TransactionShared ts\n" +
                "JOIN Wallet w ON w.ID = ts.WalletID AND w.IsDeleted = 0" +
                "JOIN Customer c ON c.ID = w.CustomerID\n" +
                "JOIN Payment p ON p.ID = ts.PaymentID AND p.IsDeleted = 0\n" +
                "JOIN Orders o ON o.ID = p.OrderID  AND o.IsDeleted = 0\n" +
                "JOIN Store s ON s.ID = o.StoreID AND s.ID = ? ";
        if (searcher.get("status") != null && !searcher.get("status").isEmpty()) {
            sql += " AND ts.Status = ? \n";
            params.add(searcher.get("status"));
        }
        if (searcher.get("customerPhone") != null && !searcher.get("customerPhone").isEmpty()) {
            sql += " AND c.Phone = ? \n";
            params.add(searcher.get("customerPhone"));
        }
        if (searcher.get("dateSearch") != null && !searcher.get("dateSearch").isEmpty()) {
            sql += " AND CONVERT(date, ts.CreatedDate, 103)  = CONVERT(date, ? , 103)  \n";
            params.add(searcher.get("dateSearch"));
        }
        if (searcher.get("amount") != null && !searcher.get("amount").isEmpty()) {
            sql += " AND ts.Amount = ? \n";
            params.add(searcher.get("amount"));
        }
        return query(sql, new TransactionSharedMapper(), params.toArray());
    }
}
