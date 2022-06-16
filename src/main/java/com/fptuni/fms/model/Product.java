/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.fptuni.fms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author LucasBV
 */
@Entity
@Table(name = "Product")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
        @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
        @NamedQuery(name = "Product.findByImagePath", query = "SELECT p FROM Product p WHERE p.imagePath = :imagePath"),
        @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
        @NamedQuery(name = "Product.findByQtyAvailable", query = "SELECT p FROM Product p WHERE p.qtyAvailable = :qtyAvailable") })
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ImagePath")
    private String imagePath;
    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "QtyAvailable")
    private Short qtyAvailable;
    @Column(name = "IsDeleted")
    private boolean isDeleted;
    @JoinColumn(name = "CateID", referencedColumnName = "ID")
    @ManyToOne
    private Category cateID;
    @JoinColumn(name = "StoreID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Store storeID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderDetail> orderDetailList;

    public Product() {
    }

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name, String imagePath, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
    }

    public Product(String id, String name, String imagePath, BigDecimal price, Short qtyAvailable, Category cateID,
            Store storeID) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.qtyAvailable = qtyAvailable;
        this.cateID = cateID;
        this.storeID = storeID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(Short qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }

    public Category getCateID() {
        return cateID;
    }

    public void setCateID(Category cateID) {
        this.cateID = cateID;
    }

    public Store getStoreID() {
        return storeID;
    }

    public void setStoreID(Store storeID) {
        this.storeID = storeID;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.fptuni.fms.model.Product[ id=" + id + " ]";
    }

}
