package com.fptuni.fms.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.HashMap;

public class JsonUtils {

    private static JsonUtils INSTANCE;

    private String jsonString;

    public static JsonUtils getInstance() {
        if (INSTANCE == null)
            INSTANCE = new JsonUtils();
        return INSTANCE;
    }

    private JsonUtils() {
        try {
            this.jsonString = converter(getFile("roles_permission.json"));
        } catch (IOException e) {
            this.jsonString = null;
        }
    }

    private InputStream getFile(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(path);
    }

    private String converter(InputStream inputStream) throws IOException {
        if (inputStream == null) throw new IOException("Permissions file not found");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        StringBuilder stringBuffer = new StringBuilder();
        String tempText = "";
        while ((tempText = bufferedReader.readLine()) != null) {
            stringBuffer.append(tempText);
        }
        return stringBuffer.toString();
    }

    public boolean havePermission(String controllerName, String actionName, String roleName) throws Exception, ParseException {

        HashMap<String, HashMap<String, List<String>>> controllers = (HashMap<String, HashMap<String, List<String>>>) toHashMap().get("controllers");
        if (controllers == null) throw new Exception("controllers not found");
        HashMap<String, List<String>> actions = controllers.get(controllerName);
        if (actions == null) throw new Exception("actions not found");
        List<String> accessible = actions.get(actionName == null ? "" : actionName);
        if (accessible == null) throw new Exception("Accessible not found");
        boolean isAccessible = accessible.contains(roleName);

        return isAccessible;
    }

    private HashMap<String, Object> toHashMap() throws ParseException {
        if (jsonString == null) throw new NullPointerException("File not found");
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(jsonString);
        JSONObject object = (JSONObject) obj;
        return (HashMap<String, Object>) object;
    }

    public boolean isWelcomeFile(String servletPath) {
        try {
            List<String> welcomeFile = (List<String>) toHashMap().get("welcome-file");
            return welcomeFile.contains(servletPath);
        } catch (ParseException e) {
            System.out.println("Error on checking: " + e.getMessage());
            return false;
        }
    }

    /**
     * first scenario:  /FMS/
     * second scenario: /FMS/controller
     * third scenario:  /FMS/controller/action
     * fourth scenario: /FMS/css/js/bootstrap.css
     */


    public boolean isRequired(String controllerName, String actionName) throws Exception {
        //first scenario
        if (isWelcomeFile(controllerName)) {
            return false;
        }
        if (controllerName == null || controllerName.isEmpty())
            return false;
        if (isProtectedFolders(controllerName)) {
            return true;
        }

        if (controllerName.startsWith("/view")
                || controllerName.endsWith(".html")
                || controllerName.endsWith(".css")
                || controllerName.endsWith(".map")
                || controllerName.endsWith(".js")
        )
            return false;
        HashMap<String, HashMap<String, List<String>>> controllers = (HashMap<String, HashMap<String, List<String>>>) toHashMap().get("controllers");
        if (controllers == null) {
            throw new Exception("controllers not found");
        }
        HashMap<String, List<String>> controller = controllers.get(controllerName);
        //khong tim thay controller can quan ly
        //=> khong phai controller
        //=> Controller khong co
        if (controller == null) {
            throw new Exception("controller not found");
        } else {
            if (controller.get(actionName) != null && controller.get(actionName).contains("all")) {
                return false;
            } else {
                List<String> action = controller.get(actionName);
                if (action == null){
                    return false;
                }
            }
            return true;
        }
    }

    private boolean isProtectedFolders(String controllerName) throws ParseException {
        List<String> protectedFolders = (List<String>) toHashMap().get("protected-folders");
        for (String path : protectedFolders) {
            if (controllerName.contains(path)) return true;
        }
        return false;
    }

    public String getIndexByRole(String name) {
        try {
            HashMap<String, String> indexPage = (HashMap<String, String>) toHashMap().get("index-page");
            if (indexPage.containsKey(name))
                return indexPage.get(name);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
