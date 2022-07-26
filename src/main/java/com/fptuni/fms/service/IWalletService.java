package com.fptuni.fms.service;

import com.fptuni.fms.model.Customer;
import com.fptuni.fms.model.Wallet;

public interface IWalletService {
    Wallet getWallet(Integer customerID);

    Integer insertWallet (Customer customer);
}
