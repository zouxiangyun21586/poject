package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yr.pojo.Mune;
import com.yr.pojo.Power;
import com.yr.util.Conn;

/**
 * Ȩ�޹���Dao
 * @author ��ҵ��
 * 2017��12��19�� ����9:35:35
 */
public class PowerDao {
	/**
	 * ����
	 * @param url �˵�·��
	 * @return 0�˵�urlΪ��,goodִ�гɹ� ,-1ִ�б���
	 */
	public static String add(String url){
		try{
			Connection conn = Conn.conn();
			String sql = "insert into permission(url,state) values(?,?);";
			if(url == null || "".equals(url)){
				return "0";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, url);
			pre.setInt(2, 0);
			pre.executeUpdate();
			pre.close();
			conn.close();
			return "good";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "-1";
	}
	/**
	 * �޸�url
	 * @param idStr �޸�url��Ȩ�ޱ�id
	 * @param url Ҫ�޸ĳɵ�url
	 * @return 0�˵�Ϊ��,goodִ�гɹ� ,-1ִ�б���
	 */
	public static String update(String idStr,String url){
		try{
			Connection conn = Conn.conn();
			String sql = "update permission set url=? where id=?;";
			Integer id = Integer.valueOf(idStr);
			if(url == null || "".equals(url)){
				return "0";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, url);
			pre.setInt(2, id);
			pre.executeUpdate();
			pre.close();
			conn.close();
			return "good";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "-1";
	}
	/**
	 * ͣ�ò˵�
	 * @param idStr Ȩ�ޱ�id
	 */
	public static void stop(String idStr){
		try{
			Integer id = Integer.valueOf(idStr);
			String sql = "update permission set state=1 where id=?;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			pre.executeUpdate();
			pre.close();
			conn.close();
			//��java����List����ת����json�ַ���
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * ���ò˵�
	 * @param idStr Ȩ�ޱ�id
	 */
	public static void start(String idStr){
		try{
			Integer id = Integer.valueOf(idStr);
			String sql = "update permission set state=0 where id=?;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			pre.executeUpdate();
			pre.close();
			conn.close();
			//��java����List����ת����json�ַ���
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * ɾ���˵�
	 * @param idStr Ҫɾ����Ȩ�ޱ�id
	 */
	public static void delete(String idStr){
		try{
			Integer id = Integer.valueOf(idStr);
			String sql = "delete from permission where id=?;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			pre.executeUpdate();
			pre.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ���ڵ�����
	 */
	public static List<Mune> genName(){
		try{
			String sql = "select fatherName from menu where menu_id=0";
			Connection con = Conn.conn();
			PreparedStatement pre = con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			List<Mune> list = new ArrayList<>();
			while(rs.next()){
				Mune j = new Mune();
				j.setFatherName(rs.getString(1));
				list.add(j);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ȡ���ڵ� ��id
	 */
	public static List<Mune> genId(){
		try{
			String sql = "select id from menu where menu_id=0";
			Connection con = Conn.conn();
			PreparedStatement pre = con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			List<Mune> list = new ArrayList<>();
			while(rs.next()){
				Mune j = new Mune();
				j.setId(rs.getInt(1));
				list.add(j);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * ��ѯ������ֵ
	 * @param id
	 * @return
	 */
	public static List<Mune> jilian(Integer id){
		try{
			String sql = "select * from menu where menu_id=?";
			Connection con = Conn.conn();
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			List<Mune> list = new ArrayList<>();
			while(rs.next()){
				Mune j = new Mune();
				j.setId(rs.getInt(1));
				j.setMuneId(rs.getInt(2));
				j.setFatherName(rs.getString(3));
				list.add(j);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	//�������ƻ�ȡ���ڵ�id
	/**
	 * �������ƻ�ȡ���ڵ�id
	 */
	public static Integer getGenId(String name){
		try{
			String sql = "select id from menu where fatherName=?";
			Connection con = Conn.conn();
			PreparedStatement pre = con.prepareStatement(sql);
			pre.setString(1, name);
			ResultSet rs = pre.executeQuery();
			Integer i = null;
			while(rs.next()){
				i = rs.getInt(1);
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * ��ѯȫ��,û�õ�
	 */
	public static List<Power> query(){
		try {
			String sql = "select p.id,m.fatherName,p.url,p,state from permission p INNER JOIN menu m on p.menu_id=m.menu_id;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			List<Power> list = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			map.put("list", list);
			while (rs.next()) {
				Power us = new Power();
				us.setId(rs.getInt(1));
				us.setName(rs.getString(2));
				us.setUrl(rs.getString(3));
				us.setState(rs.getInt(4));
				if (us.getState() == 0) {
					us.setStaStr("ʹ����");
				} else {
					us.setStaStr("��ͣ��");
				}
				list.add(us);
			}
			rs.close();
			pre.close();
			conn.close();
//			String jsonObjectStr = JSONObject.fromObject(map).toString();
//			jsonObjectStr = new String(jsonObjectStr.getBytes("utf-8"),"utf-8");
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}