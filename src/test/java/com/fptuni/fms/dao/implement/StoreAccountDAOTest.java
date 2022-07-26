package com.fptuni.fms.dao.implement;

import org.junit.Test;

class StoreAccountDAOTest {

    @Test
    public void getAccountList() {
        StoreAccountDAO storeAccountDAO = new StoreAccountDAO();
        storeAccountDAO.getAccountsByStoreID(1).forEach(System.out::println);
    }

}