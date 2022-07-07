package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.MoneyTransactionDAO;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.MoneyTransaction;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.service.IMoneyTransactionService;

import java.util.List;

public class MoneyTransactionService implements IMoneyTransactionService {

    private MoneyTransactionDAO dao = new MoneyTransactionDAO();

    @Override
    public List<MoneyTransaction> getAll() {
        List<MoneyTransaction> list = dao.getAllMoneyTransaction();
        list.forEach(moneyTransaction -> moneyTransaction.setAmount(moneyTransaction.getAmount().stripTrailingZeros()));
        return list;
    }

    @Override
    public MoneyTransaction getByID(int mID) {
        MoneyTransaction result = dao.getByID(mID);
        result.setAmount(result.getAmount().stripTrailingZeros());
        return result;
    }

    @Override
    public List<MoneyTransaction> getListByWalletID(int wID) {
        List<MoneyTransaction> result = dao.getByWalletID(wID);
        result.forEach(moneyTransaction -> moneyTransaction.setAmount(moneyTransaction.getAmount().stripTrailingZeros()));
        return dao.getByWalletID(wID);
    }

    @Override
    public MoneyTransaction getLatestByWalletID(int wID) {
        List<MoneyTransaction> list = getListByWalletID(wID);
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    @Override
    public List<MoneyTransaction> getListByCustomerID(int cID) {
        List<MoneyTransaction> list = dao.getByCustomerID(cID);
        for (MoneyTransaction moneyTransaction : list) {
            moneyTransaction.setAmount(moneyTransaction.getAmount().stripTrailingZeros());
        }
        return list;
    }

    @Override
    public MoneyTransaction getLatestByCustomerID(int cID) {
        List<MoneyTransaction> list = getListByCustomerID(cID);
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }
}
