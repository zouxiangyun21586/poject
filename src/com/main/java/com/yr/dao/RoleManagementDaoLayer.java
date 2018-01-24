/**
 *@作者：唐子壕
 *@时间：2018年1月23日上午10:39:03
 */
package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.RoleTzh;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

/**
 * @author Administrator
 *
 */
public class RoleManagementDaoLayer {
	
	/*查询角色*/
	public static String query(){
		try{
			String sql = "select * from role;";
			Connection conn = Conn.conn();
			PreparedStatement ps = conn.prepareStatement(sql);
			List<RoleTzh> list = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RoleTzh us = new RoleTzh();
				us.setId(rs.getInt(1));
				us.setRoleName(rs.getString(2));
				list.add(us);
			}
			rs.close();
			ps.close();
			//将java对象List集合转换成json字符串
			String strjson = JsonUtils.beanListToJson(list);
			return strjson;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*删除角色*/
	public static void delete(String roleId){
		try{
			String sql="delete from role where id = ?";
			Connection conn=Conn.conn();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,Integer.valueOf(roleId));
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/*添加角色*/
	public static String add(String roleName){
		try{
			Connection conn = Conn.conn();
			String sql = "insert into role(roleName) values(?)";
			if(roleName == null || "".equals(roleName)){
				return "0";
			}
			boolean bol = repeatName(roleName);//判断要添加的角色是否存在
			if(bol){
				return "1";
			}
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,roleName);
			ps.executeUpdate();
			return "good";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "-1";
	}
	
	/*修改角色*/
	public static void update(String id,String roleName){
		 try{
            Connection conn = (Connection) LinkMysql.getCon();
            String sql = "update role set roleName = ? where id = ?";
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setString(1,roleName);
            ps.setString(2,id);
            ps.executeUpdate();
            ps.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		
	
	}
	
	/*搜索角色*/
	public static String search(String roleName){
		try{
			String sql="select id,roleName from role where roleName like ?";
			Connection conn = Conn.conn();
			PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+roleName+"%");
            List<RoleTzh> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RoleTzh us = new RoleTzh();
				us.setId(rs.getInt(1));
				us.setRoleName(rs.getString(2));
				list.add(us);
			}
			rs.close();
			ps.close();
			//将java对象List集合转换成json字符串
			String strjson = JsonUtils.beanListToJson(list);
			return strjson;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/*
	 * 判断模糊搜索角色是否存在或输入的值是否为空
	 * */
	public static String fuzzySearch(String roleName){
		try{
			if(roleName == null || "".equals(roleName)){
				return "0";
			}
			boolean bol = judge(roleName);//判断要添加的角色是否存在
			if(bol){//代表角色存在
				return "2";
			}else{
				//代表角色不存在
				return "1";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "-1";
		
	}
	
	
	
	
	
	
	
	/*
	 * 判断模糊搜索角色是否存在
	 *  true 重复, false 不重复
	 * */
	public static boolean judge(String roleName){
		try{
			Connection conn=LinkMysql.getCon();
			String sql="select count(id) from role where roleName like ?";
			PreparedStatement ps=(PreparedStatement)conn.prepareStatement(sql);
			ps.setString(1,"%"+roleName+"%");
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			rs.next();
			int count=rs.getInt(1);
			if(count == 0){
				return false;
			}
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 判断添加的角色是否重复
	 * @param name 要判断的角色
	 * @return true 重复, false 不重复
	 */
	public static boolean repeatName(String roleName){
		try{
			String sql= "select roleName from role where roleName=?";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, roleName);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				String oldRoleName = rs.getString(1);
				if(roleName.equals(oldRoleName)){
					return true;
				}
			}
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	

}
