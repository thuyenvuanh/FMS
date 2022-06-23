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
        List<MoneyTransaction> list = query(sql, mapper);
        return list == null ? null : list;
    }
}
