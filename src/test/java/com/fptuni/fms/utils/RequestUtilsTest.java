package com.fptuni.fms.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"name=shirt&price=20"})
    void getParameters(String queryString) {
        Map<String, String> params = new HashMap<>();
        params = RequestUtils.getParameters(queryString);
        assertEquals(params.size(),2);
    }
}