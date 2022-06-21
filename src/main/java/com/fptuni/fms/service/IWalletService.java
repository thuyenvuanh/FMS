package com.fptuni.fms.service;

import com.fptuni.fms.model.Wallet;

public interface IWalletService {
    Wallet getWallet(Integer customerID);
}
