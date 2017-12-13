package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yr.util.Conn;

/**
 * @author ��ҵ��
 * 2017��12��13�� ����11:14:54
 */
public class SuperAdminDao {
	/**
	 * ���
	 * @param role ���õ�Ȩ��
	 * @param name �û���
	 * @param account �˺�
	 * @param pass ����
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
	 * ��ȡ�ո���ӵ��˻�id
	 * @param name �û���
	 * @param account �û����˺�
	 * @return �������id Integer
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
	 * ��ý�ɫ��id
	 * @param role ��ɫ����
	 * @return ��ɫid Integer
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
	 * ����û�ͬʱ���˻���ɫ������Ӧ��ֵ
	 * @param id �˻�id
	 * @param role ��ɫid
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
	 * �޸�
	 */
	public static void update(){
		
	}
	/**
	 * ɾ��
	 */
	public static void delete(){
		
	}
	/**
	 * ��ѯ
	 */
	public static void query(){
		
	}
}
