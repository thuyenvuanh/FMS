/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.Wallet;
import java.util.List;
import com.fptuni.fms.dao.IWalletDAO;
import com.fptuni.fms.mapper.RowMapper;
import com.fptuni.fms.mapper.WalletMapper;

/**
 *
 * @author anhthuyn2412@gmail.com - Vu Anh Thuyen
 */
public class WalletDAO extends AbstractDAO<Wallet> implements IWalletDAO {

    private final RowMapper<Wallet> mapper;

    public WalletDAO() {
        this.mapper = new WalletMapper();
    }
    
    @Override
    public List<Wallet> getAll() {
        String sql = "Select * from Wallet";
        return query(sql, new WalletMapper());
    }

    @Override
    public Wallet getWalletWithID(int ID) {
        String sql = "Select * from Wallet where ID = ?";
        List<Wallet> wallets = query(sql, mapper, ID);
        return wallets.isEmpty() ? null : wallets.get(0);
    }

    @Override
    public Wallet getWalletWithCustomerID(int cusID) {
        String sql = "SELECT * FROM Wallet where CustomerID = ?";
        List<Wallet> wallets = query(sql, mapper, cusID);
        return wallets.isEmpty() ? null : wallets.get(0);
    }


}
