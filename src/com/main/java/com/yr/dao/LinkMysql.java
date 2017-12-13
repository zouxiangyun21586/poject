package com.yr.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LinkMysql {
	//���ݿ�����
		public Connection getCon(){
			Connection conn=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn=(Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.253:3306/work?useUnicode=true&characterEncoding=UTF-8","root","root");	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		/**
		 * //�ر���
		 * @param conn
		 */
		public static void close(Connection conn,PreparedStatement prepar,ResultSet resu,Statement stat) {

			try {
				if(resu!=null){
					resu.close();
				}
				if(prepar!=null){
					prepar.close();
				}
				if(stat!=null){
					stat.close();
				}
				if(conn!=null){
				 conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
}
