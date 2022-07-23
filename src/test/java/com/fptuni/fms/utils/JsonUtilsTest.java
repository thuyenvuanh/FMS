package com.fptuni.fms.utils;

import org.junit.jupiter.api.Test;

import javax.json.Json;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {

    @Test
    void testHavePermission() {
        HashMap<String, List<String>> expectedMap = new HashMap<>();
        try {
            boolean isAccessible = JsonUtils.getInstance().havePermission("/account", "/create", "Admin");
            System.out.println(isAccessible ? "Yes" : "No");
            assertTrue(isAccessible);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testRequire(){
        try {
            String servlet  = "/store";
            String pInfo = "/search";
            boolean isRequired = JsonUtils.getInstance().isRequired(servlet, pInfo);
            assertTrue(isRequired);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testWelcomeFile(){
        try {
            boolean isWelcome = JsonUtils.getInstance().isWelcomeFile("view/authentication/index.jsp");
            System.out.println(isWelcome ? "Yes" : "No");
            assertTrue(isWelcome);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getIndexPage(){
        String roleName = "Cashier";
        System.out.println(JsonUtils.getInstance().getIndexByRole(roleName));
    }
}