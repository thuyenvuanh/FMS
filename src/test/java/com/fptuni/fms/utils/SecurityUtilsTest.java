package com.fptuni.fms.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SecurityUtilsTest {
    @Test
    public void TestOutput() {
        String email = "anhthuyn2412@gmail.com";
        String password = "123456789";

        try {
            String result = SecurityUtils.createHash(password, email);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertTrue(true);
    }

    @Test
    public void checkingHash() {
        String email = "anhthuyn2412@gmail.com";
        String password = "123456789";
        String goodHash = "db7ebff983ce5a76a8b3964e4e5d3961";
        try {
            assertTrue(SecurityUtils.validateHash(password, email, goodHash));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
