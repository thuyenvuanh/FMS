
package com.fptuni.fms.dao.implement;

import com.fptuni.fms.model.Account;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.annotations.DeleteAll;
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
    public void testCreate() {
        AccountDAO accountDAO = new AccountDAO();
        int check = accountDAO.Create("an", "123456", "Nguyen Nguyen An", 2);
    }

//    @Test
//    public void testGetListAccount() {
//        System.out.println("getListAccount");
//        AccountDAO instance = new AccountDAO();
//        List<Account> expResult = new ArrayList<>();
//        expResult.add(new Account(1));
//        List<Account> result = instance.getListAccount();
//        assertEquals(expResult.size(), result.size());
//    }
//
//    @Test
//    public void getAccounts() {
//        List<Account> accounts = new AccountDAO().getListAccount();
////        accounts.forEach(account -> System.out.println(account.getUsername() + ".Password: " + account.getFullName()));
//        assertEquals(9, accounts.size());
//    }
}

