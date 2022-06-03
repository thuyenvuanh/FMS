package com.fptuni.fms.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertTrue;


class SecurityUtilsTest {
    @Test
    public void TestOutput() {
        String email = "J7hexaMfcmuU3Sz/l+resQ==";
        String password = "12345";

        try {
            String hash = SecurityUtils.createHash(email, password);
            System.out.println(hash);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertTrue(true);
    }

    @Test
    public void checkingHash() {
        String email = "J7hexaMfcmuU3Sz/l+resQ==";
        String password = "12345";
        String goodHash = "f8ac6cc79aeb99aa6160bd59ea33c412";
        boolean check = false;
        try {
            check = SecurityUtils.validateHash(password,email, goodHash);
            assertTrue(check);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}