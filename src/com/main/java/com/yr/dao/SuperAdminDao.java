package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yr.pojo.Account_Role;
import com.yr.pojo.Paging;
import com.yr.pojo.Role;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

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
	public static String add(String role,String name,String account,String pass){
		try{
			Connection conn = Conn.conn();
			String sql = "insert into account(name,account,password) values(?,?,?);";
			if(role == null || "".equals(role)){
				return "1";
			}
			boolean bol = repeatName(account);
			if(bol){
				return "1";
			}
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
			return "good";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "0";
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
			pre.setInt(1, id);
			pre.setInt(2, role);
			pre.executeUpdate();
			pre.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查询所有角色
	 * @return 返回角色信息json
	 */
	public static String quroleName(){
		try{
			String sql = "select * from role;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			List<Role> list = new ArrayList<>();
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				Role us = new Role();
				us.setId(rs.getInt(1));
				us.setName(rs.getString(2));
				list.add(us);
			}
			rs.close();
			pre.close();
			conn.close();
			//将java对象List集合转换成json字符串
			String jsonStr = JsonUtils.beanListToJson(list);
			return jsonStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * 修改职位
	 * @param strId 修改的id
	 * @param acc 账号
	 * @param name 角色名
	 * @return
	 */
	public static String update(String strId,String acc,String name){
		try{
			Integer roleId= quRoleId(name);
			Integer accId = quAccId(acc);
			Integer id = Integer.valueOf(strId);
			//			name = new String(name.getBytes("ISO-8859-1"),"GB2312");
			if(null == name || "".equals(name)){
				return "4";//请选择职位
			}
			Connection conn = Conn.conn();
			String sql = "update account_role set account_id=?,role_id=? where id=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, accId);
			pre.setInt(2, roleId);
			pre.setInt(3, id);
			pre.executeUpdate();
			pre.close();
			conn.close();
//			resp.sendRedirect(req.getContextPath()+"/test?i=1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "good";
	}
	
	/**
	 * 查出角色的对应id
	 * @param roleName 角色名字
	 * @return 查出来的id
	 */
	public static Integer quRoleId(String roleName){
		try{
			String sql = "select id from role where roleName=?";
			Connection conn = Conn.conn();
			PreparedStatement pre=  conn.prepareStatement(sql);
			pre.setString(1, roleName);
			ResultSet rs=pre.executeQuery();
			Integer i = null;
			while(rs.next()){
				i = rs.getInt(1);
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查出账号的对应id
	 * @param roleName 账号
	 * @return 查出来的id
	 */
	public static Integer quAccId(String accName){
		try{
			String sql = "select id from account where account=?";
			Connection conn = Conn.conn();
			PreparedStatement pre=  conn.prepareStatement(sql);
			pre.setString(1, accName);
			ResultSet rs=pre.executeQuery();
			Integer i = null;
			while(rs.next()){
				i = rs.getInt(1);
			}
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改数据回显
	 */
	public static String update_echo(String idStr){
		try{
			Integer id = Integer.valueOf(idStr);
			String sql = "select * from shanglx where id=?;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			List<Account_Role> list = new ArrayList<>();
			
			while(rs.next()){
				Account_Role us = new Account_Role();
				us.setId(rs.getInt(1));
				us.setRoleName(rs.getString(2));
				us.setUserName(rs.getString(3));
				list.add(us);
			}
			pre.close();
			conn.close();
			//将java对象List集合转换成json字符串
			String jsonStr = JsonUtils.beanListToJson(list);
			return jsonStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断添加的账号是否重复
	 * @param name 要判断的账号
	 * @return true 重复, false 不重复
	 */
	public static boolean repeatName(String name){
		try{
			String sql= "select account from account where account=?";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				String oldname = rs.getString(1);
				if(name.equals(oldname)){
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 删除职位
	 */
	public static void delete(String idStr){
		try{
			Integer id = Integer.valueOf(idStr);
			String sql = "update account_role set state=1 where id=?;";
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
	 * 查询
	 */
	public static List<Account_Role> query(){
		try {
			String sql = "select ar.id,a.account,r.roleName,ar.state from account a INNER JOIN role r INNER JOIN account_role ar on a.id=ar.account_id and r.id=ar.role_id;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			List<Account_Role> list = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			map.put("list", list);
			while (rs.next()) {
				Account_Role us = new Account_Role();
				us.setId(rs.getInt(1));
				us.setRoleName(rs.getString(2));
				us.setUserName(rs.getString(3));
				us.setState(rs.getInt(4));
				if (us.getState() == 0) {
					us.setStateStr("使用中");
				} else {
					us.setStateStr("已停用");
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
	/**
	 * 获得总页数
	 * 
	 * @return 返回总页数
	 */
	public static Integer getPageCount() {
		int total = 0;// 总共多少条记录
		int pageCount = 0;// 总页数
		try {
			Connection conn = Conn.conn();
			String sql = "select count(*) from account_role";
			PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
			prepar.executeQuery();
			ResultSet resu = prepar.getResultSet();
			while (resu.next()) {
				total = resu.getInt(1);
			}
			resu.close();
			prepar.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (total % Paging.getPageNumber() == 0) {
			pageCount = total / Paging.getPageNumber();
		} else {
			pageCount = total / Paging.getPageNumber() + 1;
		}
		return pageCount;
	}
}
