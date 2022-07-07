package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IMoneyTransactionDAO;
import com.fptuni.fms.mapper.MoneyTransactionMapper;
import com.fptuni.fms.model.MoneyTransaction;

import java.util.List;

public class MoneyTransactionDAO extends AbstractDAO<MoneyTransaction> implements IMoneyTransactionDAO {

    private final MoneyTransactionMapper mapper = new MoneyTransactionMapper();

    @Override
    public List<MoneyTransaction> getAllMoneyTransaction() {
        String sql = "select *\n" +
                "from MoneyTransaction;";
        return query(sql, mapper);
    }

    @Override
    public int createMoneyTransaction(MoneyTransaction moneyTransaction) {
        String sql = "INSERT INTO MoneyTransaction(Amount ,CustomerID, WalletID, CounterID, State, CreatedDate)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return insert(sql, moneyTransaction.getAmount(), moneyTransaction.getCustomerID(), moneyTransaction.getWalletID(),
                moneyTransaction.getCounterID(), moneyTransaction.getState(), moneyTransaction.getCreatedDate());
    }

    public MoneyTransaction getByID(int mID) {
        String sql = "SELECT * FROM MoneyTransaction\n" +
                "WHERE ID = ? AND IsDeleted = 0";
        List<MoneyTransaction> list = query(sql, mapper, mID);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<MoneyTransaction> getByWalletID(int wID) {
        String sql = "SELECT * FROM MoneyTransaction\n" +
                "WHERE WalletID = ? AND IsDeleted = 0";
        return query(sql, mapper, wID);
    }
    public List<MoneyTransaction> getByCustomerID(int cID) {
        String sql = "SELECT * FROM MoneyTransaction\n" +
                "WHERE CustomerID = ? AND IsDeleted = 0";
        return query(sql, mapper, cID);
    }
}
