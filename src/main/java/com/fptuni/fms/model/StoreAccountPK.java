/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.fms.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author thuyn
 */
@Embeddable
public class StoreAccountPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "AccountID")
    private int accountID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StoreID")
    private int storeID;

    public StoreAccountPK() {
    }

    public StoreAccountPK(int accountID, int storeID) {
        this.accountID = accountID;
        this.storeID = storeID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) accountID;
        hash += (int) storeID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreAccountPK)) {
            return false;
        }
        StoreAccountPK other = (StoreAccountPK) object;
        if (this.accountID != other.accountID) {
            return false;
        }
        if (this.storeID != other.storeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fptuni.fms.model.StoreAccountPK[ accountID=" + accountID + ", storeID=" + storeID + " ]";
    }
    
}
