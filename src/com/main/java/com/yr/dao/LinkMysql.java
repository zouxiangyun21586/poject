package com.yr.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LinkMysql {
    private static String ip = null;
    private static String driver = null;
    private static String username = null;
    private static String password = null;
    private static String data = null;

    public static Connection getCon() {
        getConnection();
        Connection con = null;
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://" + ip + "/" + data + "?useUnicode=true&characterEncoding=UTF-8", username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void getConnection() {
        try {
            // 加载属性文件，读取数据库连接配置信息
            Properties pro = new Properties();
            try {
                pro.load(LinkMysql.class.getResourceAsStream("/jdbc.properties"));
            } catch (IOException e) {
                System.out.println("未找到配置文件！！！");
            }

            ip = pro.getProperty("ip");
            driver = pro.getProperty("driver");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            data = pro.getProperty("data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
