package com.yr.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.yr.pojo.Jurisdiction;
import com.yr.util.Conn;

public class RoleJurisdictionDaoLayer {
	public static List<Jurisdiction> query(){
		try{
			Connection conn=(Connection) Conn.conn();
			String sql="select * from menu";
			PreparedStatement pr=(PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs=pr.executeQuery();
			List<Jurisdiction> list=new ArrayList<>();
			while(rs.next()){
				Jurisdiction jurisdiction=new Jurisdiction();
				jurisdiction.setId(rs.getInt(1));
				jurisdiction.setMenu_id(rs.getInt(2));
				jurisdiction.setFatherName(rs.getString(3));
				list.add(jurisdiction);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static List<Jurisdiction> test(Integer fatherId,String sql){
		try{
			Connection conn=(Connection) Conn.conn();
			PreparedStatement pr=(PreparedStatement) conn.prepareStatement(sql);
			pr.setInt(1,fatherId);
			ResultSet rs=pr.executeQuery();
			List<Jurisdiction> list=new ArrayList<>();
			while(rs.next()){
				Jurisdiction jurisdiction=new Jurisdiction();
				jurisdiction.setId(rs.getInt(1));
				jurisdiction.setFatherName(rs.getString(2));
				list.add(jurisdiction);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
