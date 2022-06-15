/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.dao.IIdentityCardDAO;
import com.fptuni.fms.mapper.IdentityCardMapper;
import com.fptuni.fms.model.IdentityCard;
import com.fptuni.fms.paging.Pageable;

import java.util.List;

/**
 *
 * @author LEGION
 */
public class IdentityCardDAO extends AbstractDAO<IdentityCard> implements IIdentityCardDAO {

    @Override
    public List<IdentityCard> getList(Boolean status, Pageable pageable) {
        String sql = "SELECT ID, CustomerID FROM IdentityCard";
        if(status != null){
            if(status)
                sql += "\nWHERE CustomerID is not null";
            else
                sql += "\nWHERE CustomerID is null";
        }
        if(pageable != null){
            if(pageable.getSorter() != null && !pageable.getSorter().getSortField().isEmpty() && pageable.getSorter().isAscending() != null){
                sql += "\nORDER BY " + pageable.getSorter().getSortField() + " " + (pageable.getSorter().isAscending() == true ? "ASC" : "DESC");
            }
            if(pageable.getOffset() != null && pageable.getLimit() != null){
                sql += "\nOFFSET " + pageable.getOffset() + " ROWS" +
                        "\nFETCH NEXT " + pageable.getLimit() + " ROWS ONLY";
            }
        }

        List<IdentityCard> identityCards = query(sql, new IdentityCardMapper());
        return identityCards.isEmpty() ? null : identityCards;
    }

    @Override
    public IdentityCard get(int id) {
        String sql = "SELECT ID, CustomerID FROM IdentityCard\n"
                + "Where ID = ?";
        List<IdentityCard> identityCards = query(sql, new IdentityCardMapper(), id);
        return identityCards.isEmpty() ? null : identityCards.get(0);
    }

    @Override
    public void update(IdentityCard identityCard) {
        String sql = "UPDATE IdentityCard\n"
                + "SET CustomerID = ?\n"
                + "WHERE ID = ?";
        update(sql, identityCard.getCustomerID());
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM IdentityCard";
        return count(sql);
    }
}
