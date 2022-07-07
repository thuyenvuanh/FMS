/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.fptuni.fms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LucasBV
 */
@Entity
@Table(name = "MoneyTransaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MoneyTransaction.findAll", query = "SELECT m FROM MoneyTransaction m"),
    @NamedQuery(name = "MoneyTransaction.findById", query = "SELECT m FROM MoneyTransaction m WHERE m.id = :id"),
    @NamedQuery(name = "MoneyTransaction.findByWalletID", query = "SELECT m FROM MoneyTransaction m WHERE m.walletID = :walletID"),
    @NamedQuery(name = "MoneyTransaction.findByMethod", query = "SELECT m FROM MoneyTransaction m WHERE m.method = :method"),
    @NamedQuery(name = "MoneyTransaction.findByState", query = "SELECT m FROM MoneyTransaction m WHERE m.state = :state"),
    @NamedQuery(name = "MoneyTransaction.findByCreatedDate", query = "SELECT m FROM MoneyTransaction m WHERE m.createdDate = :createdDate")})
public class MoneyTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WalletID")
    private int walletID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Method")
    private String method;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Amount")
    private BigDecimal amount;

    @Basic(optional = false)
    @NotNull
    @Column(name = "State")
    private boolean state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @OneToMany(mappedBy = "moneyTransactionID")
    private List<TransactionShared> transactionSharedList;
    @JoinColumn(name = "CounterID", referencedColumnName = "ID")
    @ManyToOne
    private Counter counterID;
    @JoinColumn(name = "CustomerID", referencedColumnName = "ID")
    @ManyToOne
    private Customer customerID;

    public MoneyTransaction() {
    }

    public MoneyTransaction(Integer id) {
        this.id = id;
    }

    public MoneyTransaction(Integer id, int walletID, String method, boolean state, Date createdDate) {
        this.id = id;
        this.walletID = walletID;
        this.method = method;
        this.state = state;
        this.createdDate = createdDate;
    }

    public MoneyTransaction(Integer id, int walletID, String method, BigDecimal amount, boolean state, Date createdDate, List<TransactionShared> transactionSharedList, Counter counterID, Customer customerID) {
        this.id = id;
        this.walletID = walletID;
        this.method = method;
        this.amount = amount;
        this.state = state;
        this.createdDate = createdDate;
        this.transactionSharedList = transactionSharedList;
        this.counterID = counterID;
        this.customerID = customerID;
    }

    public boolean isState() {
        return state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public BigDecimal getAmount() {
        return amount.stripTrailingZeros();
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount.stripTrailingZeros();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public List<TransactionShared> getTransactionSharedList() {
        return transactionSharedList;
    }

    public void setTransactionSharedList(List<TransactionShared> transactionSharedList) {
        this.transactionSharedList = transactionSharedList;
    }

    public Counter getCounterID() {
        return counterID;
    }

    public void setCounterID(Counter counterID) {
        this.counterID = counterID;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MoneyTransaction)) {
            return false;
        }
        MoneyTransaction other = (MoneyTransaction) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.fptuni.fms.model.MoneyTransaction[ id=" + id + " ]";
    }

}
