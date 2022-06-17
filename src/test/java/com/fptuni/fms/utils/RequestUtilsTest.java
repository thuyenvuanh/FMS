package com.fptuni.fms.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"name=shirt&price="})
    void getParameters(String queryString) {
        Map<String, String> paramMap = RequestUtils.getParameters(queryString);
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.println("value: " + entry.getValue());
            System.out.println(entry.getValue().isEmpty() ? "ko co" : "co");
        }
        Map<String, String> params = new HashMap<>();
        params = RequestUtils.getParameters(queryString);
        System.out.println(params);
        params.forEach((k, v) -> System.out.println(k + "" + v));
        System.out.println(params.get("price")==null?"nullp":params.get("price"));
        assertEquals(params.size(), 2);
    }
}