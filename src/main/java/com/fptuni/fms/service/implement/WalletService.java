package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.IWalletDAO;
import com.fptuni.fms.dao.implement.WalletDAO;
import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.service.IWalletService;

public class WalletService implements IWalletService {
    WalletDAO walletDAO = new WalletDAO();
    @Override
    public Wallet getWallet(Integer customerID) {
        System.out.println("CUSTOMER ID in Service: " + customerID);
        return walletDAO.getWalletWithCustomerID(customerID);
    }

    @Override
    public Integer insertWallet(Customer customer) {
        IWalletDAO walletDAO = new WalletDAO();
        return walletDAO.createWallet(customer);
    }


}
