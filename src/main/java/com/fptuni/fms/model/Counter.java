/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.fptuni.fms.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author LucasBV
 */
@Entity
@Table(name = "Counter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Counter.findAll", query = "SELECT c FROM Counter c"),
    @NamedQuery(name = "Counter.findById", query = "SELECT c FROM Counter c WHERE c.id = :id")})
public class Counter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @OneToMany(mappedBy = "counterID")
    private List<MoneyTransaction> moneyTransactionList;

    public Counter() {
    }

    public Counter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<MoneyTransaction> getMoneyTransactionList() {
        return moneyTransactionList;
    }

    public void setMoneyTransactionList(List<MoneyTransaction> moneyTransactionList) {
        this.moneyTransactionList = moneyTransactionList;
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
        if (!(object instanceof Counter)) {
            return false;
        }
        Counter other = (Counter) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.fptuni.fms.model.Counter[ id=" + id + " ]";
    }

}
