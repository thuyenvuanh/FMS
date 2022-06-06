package com.fptuni.fms.dao;

import com.fptuni.fms.model.Product;

/*
*
* Author: Anh Quoc
*
* */
public interface IProductDAO extends GenericDAO<Product> {

    Product getProduct(String name);
}
