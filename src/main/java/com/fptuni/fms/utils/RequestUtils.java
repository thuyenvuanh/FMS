package com.fptuni.fms.utils;

import java.util.HashMap;
import java.util.Map;

public class RequestUtils {
    public static Map<String, String> getParameters(String queryString) {
        Map<String, String> mapParam = new HashMap<>();
        int index = queryString.indexOf("?");
        String[] arrParam = queryString.split("[=&]");
        for (int i = 0; i < arrParam.length - 1; i = i + 2) {
            mapParam.put(arrParam[i], arrParam[i + 1]);
        }
        return mapParam;
    }
}
