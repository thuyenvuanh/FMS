/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fptuni.fms.dao;

import com.fptuni.fms.model.IdentityCard;
import com.fptuni.fms.paging.Pageable;

import java.util.List;

/**
 * @author LEGION
 */
public interface IIdentityCardDAO extends GenericDAO<IdentityCard> {

    void update(IdentityCard identityCard);

    List<IdentityCard> getList(Boolean status, Pageable pageable);

    IdentityCard get(int id);

    int getTotalItem();
}
