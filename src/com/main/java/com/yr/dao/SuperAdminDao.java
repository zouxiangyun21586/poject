package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yr.util.Conn;

/**
 * @author 周业好
 * 2017年12月13日 上午11:14:54
 */
public class SuperAdminDao {
	/**
	 * 添加
	 * @param role 设置的权限
	 * @param name 用户名
	 * @param account 账号
	 * @param pass 密码
	 */
	public static boolean add(String role,String name,String account,String pass){
		try{
			Connection conn = Conn.conn();
			String sql = "insert into account(name,account,password) values(?,?,?);";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, account);
			pre.setString(3, pass);
			pre.executeUpdate();
			pre.close();
			conn.close();
			Integer account_id = getAccountId(name,account);
			Integer role_id = getRoleId(role);
			addAccount_role(account_id,role_id);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 获取刚刚添加的账户id
	 * @param name 用户名
	 * @param account 用户的账号
	 * @return 查出来的id Integer
	 */
	public static Integer getAccountId(String name,String account){
		try{
			Connection conn = Conn.conn();
			String sql = "select id form account where name=? and account=?;";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, account);
			ResultSet rs = pre.executeQuery();
			Integer i=null;
			while (rs.next()) {
				i = rs.getInt(1);
			}
			pre.close();
			conn.close();
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获得角色的id
	 * @param role 角色名称
	 * @return 角色id Integer
	 */
	public static Integer getRoleId(String role){
		try{
			Connection conn = Conn.conn();
			String sql = "select id form role where name=?;";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, role);
			ResultSet rs = pre.executeQuery();
			Integer i=null;
			while (rs.next()) {
				i = rs.getInt(1);
			}
			pre.close();
			conn.close();
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加用户同时给账户角色表加入对应的值
	 * @param id 账户id
	 * @param role 角色id
	 */
	public static void addAccount_role(Integer id,Integer role){
		try{
			String sql = "insert into account_role(account_id,role_id) values(?,?);";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 修改
	 */
	public static void update(){
		
	}
	/**
	 * 删除
	 */
	public static void delete(){
		
	}
	/**
	 * 查询
	 */
	public static void query(){
		
	}
}
