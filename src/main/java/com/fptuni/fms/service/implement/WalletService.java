package com.fptuni.fms.service.implement;

import com.fptuni.fms.dao.implement.WalletDAO;
import com.fptuni.fms.model.Wallet;
import com.fptuni.fms.service.IWalletService;

public class WalletService implements IWalletService {
    @Override
    public Wallet getWallet(Integer customerID) {
        WalletDAO walletDAO = new WalletDAO();
        Wallet wallet = null;

        if(customerID != null){
            wallet = walletDAO.getWalletWithCustomerID(customerID);
        }

        return wallet;
    }
}
