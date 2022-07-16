/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.fptuni.fms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LucasBV
 */
@Entity
@Table(name = "TransactionShared")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionShared.findAll", query = "SELECT t FROM TransactionShared t"),
    @NamedQuery(name = "TransactionShared.findById", query = "SELECT t FROM TransactionShared t WHERE t.id = :id"),
    @NamedQuery(name = "TransactionShared.findByAmount", query = "SELECT t FROM TransactionShared t WHERE t.amount = :amount"),
    @NamedQuery(name = "TransactionShared.findByPreviousHash", query = "SELECT t FROM TransactionShared t WHERE t.previousHash = :previousHash"),
    @NamedQuery(name = "TransactionShared.findByHashValue", query = "SELECT t FROM TransactionShared t WHERE t.hashValue = :hashValue"),
    @NamedQuery(name = "TransactionShared.findByPreviousBalance", query = "SELECT t FROM TransactionShared t WHERE t.previousBalance = :previousBalance"),
    @NamedQuery(name = "TransactionShared.findByCreatedDate", query = "SELECT t FROM TransactionShared t WHERE t.createdDate = :createdDate"),
    @NamedQuery(name = "TransactionShared.findByStatus", query = "SELECT t FROM TransactionShared t WHERE t.status = :status")})
public class TransactionShared implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PreviousHash")
    private String previousHash;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "HashValue")
    private String hashValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PreviousBalance")
    private BigDecimal previousBalance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "Status")
    private Boolean status;
    @JoinColumn(name = "MoneyTransactionID", referencedColumnName = "ID")
    @ManyToOne
    private MoneyTransaction moneyTransactionID;
    @JoinColumn(name = "PaymentID", referencedColumnName = "ID")
    @ManyToOne
    private Payment paymentID;
    @JoinColumn(name = "WalletID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Wallet walletID;

    public TransactionShared() {
    }

    public TransactionShared(Integer id) {
        this.id = id;
    }

    public TransactionShared(Integer id, BigDecimal amount, String previousHash, String hashValue, BigDecimal previousBalance, Date createdDate) {
        this.id = id;
        this.amount = amount;
        this.previousHash = previousHash;
        this.hashValue = hashValue;
        this.previousBalance = previousBalance;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(BigDecimal previousBalance) {
        this.previousBalance = previousBalance;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public MoneyTransaction getMoneyTransactionID() {
        return moneyTransactionID;
    }

    public void setMoneyTransactionID(MoneyTransaction moneyTransactionID) {
        this.moneyTransactionID = moneyTransactionID;
    }

    public Payment getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Payment paymentID) {
        this.paymentID = paymentID;
    }

    public Wallet getWalletID() {
        return walletID;
    }

    public void setWalletID(Wallet walletID) {
        this.walletID = walletID;
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
        if (!(object instanceof TransactionShared)) {
            return false;
        }
        TransactionShared other = (TransactionShared) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return String.valueOf(id) + String.valueOf(amount) + String.valueOf(walletID) + String.valueOf(previousHash)
                + String.valueOf(previousBalance) + String.valueOf(createdDate.getTime()) + String.valueOf(status)
                + String.valueOf(moneyTransactionID == null ? "" : moneyTransactionID.getId())
                + String.valueOf(paymentID == null ? "": paymentID.getId());
    }

}
