package com.fptuni.fms.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurityUtilsTest {
    @Test
    public void TestOutput() {
        String[] email = {"admin", "stm1", "cashier", "counter", "store"};
        String[] password = {"admin", "123456789", "12345", "123456789", "store"};

        try {
            for (int i = 0; i < 5; i++) {
                String result = SecurityUtils.createHash(password[i], email[i]);
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(true);
    }

    @Test
    public void checkingHash() {
        String email = "store";
        String password = "store";
        String goodHash = "834402b97ecfc2ca3f56421d694f7e15";
        //                "00000000000000000000000000000000"
        try {
            assertTrue(SecurityUtils.validateHash(password, email, goodHash));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
