package com.fptuni.fms.service;

import com.fptuni.fms.dao.implement.MoneyTransactionDAO;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.MoneyTransaction;
import com.fptuni.fms.model.Wallet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface IMoneyTransactionService {

    List<MoneyTransaction> getAll();
    MoneyTransaction getByID(int mID);


    List<MoneyTransaction> getListByWalletID(int wID);
    MoneyTransaction getLatestByWalletID(int wID);

    List<MoneyTransaction> getListByCustomerID(int cID);
    MoneyTransaction getLatestByCustomerID(int cID);

    public boolean addMoney(HttpServletRequest request, HttpSession session);

    public boolean withDraw(HttpServletRequest request, HttpSession session);
}
