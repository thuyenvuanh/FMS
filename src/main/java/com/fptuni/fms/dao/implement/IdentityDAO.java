/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IIdentityCardDAO;
import com.fptuni.fms.mapper.IdentityCardMapper;
import com.fptuni.fms.model.IdentityCard;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class IdentityDAO extends AbstractDAO<IdentityCard> implements IIdentityCardDAO{

    @Override
    public List<IdentityCard> getAll() {
        String sql = "SELECT ID, CustomerID FROM IdentityCard";
        List<IdentityCard> identityCards = query(sql, new IdentityCardMapper());
        return identityCards.isEmpty() ? null : identityCards;
    }

    @Override
    public IdentityCard get(Integer id) {
        String sql = "SELECT ID, CustomerID FROM IdentityCard\n"
                + "Where ID = ?";
        List<IdentityCard> identityCards = query(sql, new IdentityCardMapper(), id);
        return identityCards.isEmpty() ? null : identityCards.get(0);
    }
    
}
