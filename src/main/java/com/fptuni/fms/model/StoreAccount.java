/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.fms.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thuyn
 */
@Entity
@Table(name = "StoreAccount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreAccount.findAll", query = "SELECT s FROM StoreAccount s")
    , @NamedQuery(name = "StoreAccount.findByAccountID", query = "SELECT s FROM StoreAccount s WHERE s.storeAccountPK.accountID = :accountID")
    , @NamedQuery(name = "StoreAccount.findByStoreID", query = "SELECT s FROM StoreAccount s WHERE s.storeAccountPK.storeID = :storeID")
    , @NamedQuery(name = "StoreAccount.findByIsDeleted", query = "SELECT s FROM StoreAccount s WHERE s.isDeleted = :isDeleted")})
public class StoreAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreAccountPK storeAccountPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsDeleted")
    private boolean isDeleted;
    @JoinColumn(name = "AccountID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account;
    @JoinColumn(name = "StoreID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store store;

    public StoreAccount() {
    }

    public StoreAccount(StoreAccountPK storeAccountPK) {
        this.storeAccountPK = storeAccountPK;
    }

    public StoreAccount(StoreAccountPK storeAccountPK, boolean isDeleted) {
        this.storeAccountPK = storeAccountPK;
        this.isDeleted = isDeleted;
    }

    public StoreAccount(int accountID, int storeID) {
        this.storeAccountPK = new StoreAccountPK(accountID, storeID);
    }

    public StoreAccountPK getStoreAccountPK() {
        return storeAccountPK;
    }

    public void setStoreAccountPK(StoreAccountPK storeAccountPK) {
        this.storeAccountPK = storeAccountPK;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeAccountPK != null ? storeAccountPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreAccount)) {
            return false;
        }
        StoreAccount other = (StoreAccount) object;
        if ((this.storeAccountPK == null && other.storeAccountPK != null) || (this.storeAccountPK != null && !this.storeAccountPK.equals(other.storeAccountPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fptuni.fms.model.StoreAccount[ storeAccountPK=" + storeAccountPK + " ]";
    }
    
}
