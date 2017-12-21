package com.yr.util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.yr.dao.SimpleFilter;

public class Conn extends SimpleFilter{
	private static Connection conn; 
	public static void main(String[] args) {
//		PropertiesUtils
		conn();
	}
	/**
	 * Á´½ÓÊý¾Ý¿â
	 * @return
	 */
	public static Connection conn(){
		try {
			if(null == conn){
				Class.forName(PropertiesUtils.getString("driver"));
				conn = DriverManager.getConnection("jdbc:mysql://" + PropertiesUtils.getString("ip") + "/" + PropertiesUtils.getString("data") + "?useUnicode=true&characterEncoding=UTF-8", PropertiesUtils.getString("username"), PropertiesUtils.getString("password"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
