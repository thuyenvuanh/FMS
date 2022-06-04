package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.Account;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    @Test
    void checkLogin() {
        Account account = null;
        try {
            AccountDAO accountDAO = new AccountDAO();
            account = accountDAO.checkLogin("binhvq", "binhvq");
            System.out.println(account.getFullName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(account != null);
    }
}