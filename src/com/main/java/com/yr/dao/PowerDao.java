package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yr.pojo.Account_Role;
import com.yr.pojo.Power;
import com.yr.util.Conn;

/**
 * 权限管理Dao
 * @author 周业好
 * 2017年12月19日 下午9:35:35
 */
public class PowerDao {
	/**
	 * 添加
	 * @param url 菜单路径
	 * @return 0菜单url为空,good执行成功 ,-1执行报错
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
	 * 修改url
	 * @param idStr 修改url的权限表id
	 * @param url 要修改成的url
	 * @return 0菜单为空,good执行成功 ,-1执行报错
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
	 * 停用菜单
	 * @param idStr 权限表id
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
			//将java对象List集合转换成json字符串
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 启用菜单
	 * @param idStr 权限表id
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
			//将java对象List集合转换成json字符串
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 删除菜单
	 * @param idStr 要删除的权限表id
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
	 * 查询
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
					us.setStaStr("使用中");
				} else {
					us.setStaStr("已停用");
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
