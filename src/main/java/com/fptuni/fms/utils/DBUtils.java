/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.fms.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LucasBV
 */
public class DBUtils {
    private final static String SERVERNAME = "localhost";//"13.215.207.31";
    private final static String DBNAME = "FMS";
    private final static String PORTNUMBER = "1433";
    private final static String INSTANCE = "";// LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final static String USERID = "sa";
    private final static String PASSWORD = "12345";

    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORTNUMBER + "\\" + INSTANCE + ";databaseName=" + DBNAME;
        if (INSTANCE.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + SERVERNAME + ":" + PORTNUMBER + ";databaseName=" + DBNAME;
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, USERID, PASSWORD);
    }
}
