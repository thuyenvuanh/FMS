
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.Account;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author NhatTan
 */
public class AccountDAOTest {

    public AccountDAOTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetListAccount() {
        System.out.println("getListAccount");
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = new ArrayList<>();
        expResult.add(new Account(1));
        List<Account> result = instance.getListAccount();
        assertEquals(expResult.size(), result.size());
        fail("fail");
    }

}

