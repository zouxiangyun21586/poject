package com.yr.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class LinkMysql extends SimpleFilter {

    public static Connection getCon() {
        Connection con = null;
        System.out.println(map.get("ip"));
        try {
            Class.forName(map.get("driver"));
            con = (Connection) DriverManager.getConnection("jdbc:mysql://" + map.get("ip") + "/" + map.get("data")
                    + "?useUnicode=true&characterEncoding=UTF-8", map.get("username"), map.get("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
