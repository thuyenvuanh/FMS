package com.fptuni.fms.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {

    @Test
    void testReadJson() {
        HashMap<String, List<String>> expectedMap = new HashMap<>();
        try {
            System.out.println(
                    JsonUtils.getInstance().readJson("roles_permission.json"));
        } catch (IOException e) {
            fail();
        }
    }
}