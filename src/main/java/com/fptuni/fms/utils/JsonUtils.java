package com.fptuni.fms.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonUtils {

    private static JsonUtils INSTANCE;

    public static JsonUtils getInstance() {
        if (INSTANCE == null)
            INSTANCE = new JsonUtils();
        return INSTANCE;
    }

    private InputStream getFile(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(path);
    }

    private String converter(InputStream inputStream) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        StringBuilder stringBuffer = new StringBuilder();
        String tempText = "";
        while ((tempText = bufferedReader.readLine()) != null) {
            stringBuffer.append(tempText);
        }
        return stringBuffer.toString();
    }

    public HashMap<String, List<String>> readJson(String path) throws IOException {
        JSONParser jsonParser = new JSONParser();
        String jsonString = converter(getFile(path));
        try {
            //Read JSON file
            Object obj = jsonParser.parse(jsonString);

            JSONArray mapList = (JSONArray) obj;

            HashMap<String, List<String>> rolesMap = new HashMap<String, List<String>>();

            mapList.forEach(object -> {
                JSONObject roleMap = (JSONObject) object;
                String key = (String) roleMap.get("role");
                List<String> permissions = new ArrayList<>();
                ((JSONArray) roleMap.get("permissions")).forEach(o -> {
                    permissions.add((String) o);
                });
                rolesMap.put(key, permissions);
            });
            return rolesMap;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
