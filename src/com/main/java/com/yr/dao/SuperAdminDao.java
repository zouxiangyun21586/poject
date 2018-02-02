package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	private static String pageCountSql=null;
	/**
	 * 添加
	 * @param role 设置的角色id
	 * @param name 用户名
	 * @param account 账号
	 * @param pass 密码
	 * @return "0"请选择角色,"1" 账号已存在 , "2"账号或者密码不能为空 
	 */
	public static String add(String role,String name,String account,String pass,String youxiang){
		try{
			Connection conn = Conn.conn();
			String sql = "insert into account(name,account,password,youxiang,state) values(?,?,?,?,?);";
			if(role == null || "".equals(role)){
				return "0";
			}
			if(account == null || "".equals(account) || pass == null || "".equals(pass)){
				return "2";
			}
			boolean bol = repeatName(account);
			if(bol){
				return "1";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, account);
			pre.setString(3, pass);
			pre.setString(4, youxiang);
			pre.setInt(5, 0);
			pre.executeUpdate();
			pre.close();
//			conn.close();
			Integer account_id = getAccountId(name,account);
			String str[] = role.split(",");
			addAccount_role(account_id,str);
			return "good";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "-1";
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
			String sql = "select id from account where name=? and account=?;";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, account);
			ResultSet rs = pre.executeQuery();
			Integer i=null;
			while (rs.next()) {
				i = rs.getInt(1);
			}
			pre.close();
//			conn.close();
			return i;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加用户同时给账户角色表加入对应的值
	 * @param id 账户id
	 * @param str 角色id数组
	 */
	public static void addAccount_role(Integer id,String[] str){
		try{
			String sql = "insert into account_role(account_id,role_id) values(?,?);";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			for (int i = 0; i < str.length; i++) {
				String roleIdStr = str[i];
				Integer roleId = Integer.valueOf(roleIdStr);
				pre.setInt(1, id);
				pre.setInt(2, roleId);
				pre.executeUpdate();
			}
			pre.close();
//			conn.close();
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
//			conn.close();
			//将java对象List集合转换成json字符串
			String jsonStr = JsonUtils.beanListToJson(list);
			return jsonStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 查询角色 除了超级管理员
	 * @return 返回角色信息json
	 */
	public static String quroleNameNo(){
		try{
			String sql = "select * from role where id !=1;";
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
//			conn.close();
			//将java对象List集合转换成json字符串
			String jsonStr = JsonUtils.beanListToJson(list);
			return jsonStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * 修改数据回显
	 * @param name 角色名
	 * @return 角色id
	 */
	public static String updateEcho(String name){
		try{
			String str[] = name.split(",");
			String sql = "select id from role where roleName=?";
			Connection conn = Conn.conn();
			PreparedStatement pre=  conn.prepareStatement(sql);
			String id="";
			for (int i = 0; i < str.length; i++) {
				String nameStr = str[i];
				
				pre.setString(1, nameStr);
				ResultSet rs=pre.executeQuery();
				Integer j = null;
				while(rs.next()){
					///j = rs.getInt(1);
					id = id+rs.getInt(1)+",";
				}
				//id = id+j.toString()+",";
			}
			pre.close();
			return id;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "good";
	}
	
	//根据账号把账号角色表里面账号关联的角色全部删掉
	/**
	 * 修改职位
	 * @param oldNameId 修改前的职位id
	 * @param roleIdStr 修改后的角色id
	 * @param idStr 要修改的用户表账号id
	 * @param acc 要修改的用户表账号
	 * @return 0:没有问题   1:请选择职位   2:不能修改一样的
	 */
	public static String update(String oldNameId,String roleIdStr,String idStr,String acc){
		try{
			if(null==roleIdStr || "".equals(roleIdStr)){
				return "1";
			}else if(oldNameId.equals(roleIdStr)){
				return "2";//不能修改一样的
			}
			String str[] = roleIdStr.split(",");
			Integer id = Integer.valueOf(idStr);//修改的用户表id
			Connection conn = Conn.conn();
			delAccRole(acc);//删除 角色用户表中对应账号的角色
			String sql = "insert into account_role(account_id,role_id) values(?,?);";
			PreparedStatement pre = conn.prepareStatement(sql);
			//循环修改后的角色数组
			for (int i = 0; i < str.length; i++) {
				String ids = str[i];
				Integer roleId = Integer.valueOf(ids);//修改后的 职位id 
				pre.setInt(1, id);
				pre.setInt(2, roleId);
//				pre.setInt(2, id);
				pre.executeUpdate();
			}
			pre.close();
			return "0";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 删除 角色用户表中对应账号的角色
	 * @param acc 账号
	 */
	public static void delAccRole(String acc){
		Integer accId = quAccId(acc);
		try{
			String sql = "delete from account_role where account_id=?;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, accId);
			pre.executeUpdate();
			pre.close();
//			conn.close();
			//将java对象List集合转换成json字符串
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据角色id查出对应的角色名字
	 * @param strId 角色id
	 * @return 查出来的角色名字
	 */
	public static String quRoleNameId(String strId){
		try{
			Integer i = Integer.valueOf(strId);
			String sql = "select roleName from role where id=?";
			Connection conn = Conn.conn();
			PreparedStatement pre=  conn.prepareStatement(sql);
			pre.setInt(1, i);
			ResultSet rs=pre.executeQuery();
			String name = null;
			while(rs.next()){
				name = rs.getString(1);
			}
			pre.close();
//			conn.close();
			return name;
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
			pre.close();
//			conn.close();
			return i;
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
			pre.close();
//			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 停用职位
	 * @param idStr 账户表id
	 */
	public static void delete(String idStr){
		try{
			Integer id = Integer.valueOf(idStr);
			String sql = "update account set state=1 where id=?;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			pre.executeUpdate();
			pre.close();
//			conn.close();
			//将java对象List集合转换成json字符串
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 启用职位
	 * @param idStr 账户表id
	 */
	public static void revive(String idStr){
		try{
			Integer id = Integer.valueOf(idStr);
			String sql = "update account set state=0 where id=?;";
			Connection conn = Conn.conn();
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			pre.executeUpdate();
			pre.close();
//			conn.close();
			//将java对象List集合转换成json字符串
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查询
	 */
	/*public static List<Account_Role> query(){
		try {
			String sql = "select ar.id,a.account,r.roleName,a.state from account a INNER JOIN role r INNER JOIN account_role ar on a.id=ar.account_id and r.id=ar.role_id;";
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
//			conn.close();
//			String jsonObjectStr = JSONObject.fromObject(map).toString();
//			jsonObjectStr = new String(jsonObjectStr.getBytes("utf-8"),"utf-8");
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}*/
	/**
	 * 查询数据并用list封装起来
	 * @param acc 账号
	 * @param rolename 角色名   (同时也是判断他是不是使用了下拉框)
	 * @param pageNow 当前页
	 * @param sel 判断是否用了查询功能 
	 * @return 返回所查询的数据
	 */
	public static List<Account_Role> selectemp(String acc,String rolename,Integer pageNow, String sel) {
		String sql = "";
		List<Account_Role> list = new ArrayList<>();
		if (pageNow < 1) {
			pageNow = 1;
		}
		
		try {
			Connection conn = Conn.conn();
			if (null != sel && !"".equals(sel) && !"".equals(rolename) || null != rolename && !"quan".equals(rolename)) {//使用搜索功能进入这个if判断
				pageNow=0;
				List<Integer> paramIndex = new ArrayList<>();
				List<Object> param = new ArrayList<>();
				pageCountSql = "create VIEW shitu1 as  SELECT DISTINCT a.id FROM account a,account_role ar,role r where a.id=ar.account_id and r.id=ar.role_id  ";
				sql = "SELECT DISTINCT a.id,a.account,a.state,a.youxiang,(select GROUP_CONCAT(r.roleName separator  \",\") as rolename from role r inner join account_role ar on ar.role_id = r.id where ar.account_id = a.id) as rolename FROM account a,account_role ar,role r where a.id=ar.account_id and r.id=ar.role_id  ";
				if(acc != null && !"".equals(acc))
				{
					sql = sql + " and a.account=?";
					paramIndex.add(0);
					param.add(acc);
					pageCountSql = pageCountSql + " and a.account=?";
				}
				if(!"quan".equals(rolename))
				{
					sql = sql + " and r.roleName=?";
					paramIndex.add(0);
					rolename=quRoleNameId(rolename);//根据角色id查出对应的角色名字
					param.add(rolename);
					pageCountSql = pageCountSql + "and r.roleName=?";
				}
				
				sql = sql + " limit ?,?";
				pageCountSql = pageCountSql + " limit ?,?";
				paramIndex.add(1);
				paramIndex.add(1);
				
				
				param.add(pageNow);
				param.add(Paging.getPageNumber());
				
//				pageCountSql = pageCountSql + " limit "+pageNow+","+Paging.getPageNumber();
				PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
				PreparedStatement prepar2 = (PreparedStatement) conn.prepareStatement(pageCountSql);
				for (int i = 0; i < paramIndex.size(); i++) {
					int mark = paramIndex.get(i);
					if(mark == 0 ){
						prepar.setString( (i+1), (String)param.get(i) );
					}
					else if(mark == 1)
					{
						prepar.setInt( (i+1), (Integer)param.get(i) );
					}
				}
				for (int i = 0; i < paramIndex.size(); i++) {
					int mark = paramIndex.get(i);
					if(mark == 0 ){
						prepar2.setString( (i+1), (String)param.get(i) );
					}
					else if(mark == 1)
					{
						prepar2.setInt( (i+1), (Integer)param.get(i) );
					}
				}
				PreparedStatement prepar1 = (PreparedStatement) conn.prepareStatement("drop view if exists shitu1;");
				
				ResultSet resu = prepar.executeQuery();
				while (resu.next()) {
					Account_Role us = new Account_Role();
					us.setId(resu.getInt(1));
					us.setYouxiang(resu.getNString(4));
					us.setRoleName(resu.getString(5));
					us.setState(resu.getInt(3));
					us.setUserName(resu.getString(2));
					if (us.getState() == 0) {
						us.setStateStr("使用中");
					} else {
						us.setStateStr("已停用");
					}
					list.add(us);
				}
				prepar1.executeUpdate();
				prepar2.executeUpdate();
				resu.close();
				prepar1.close();
				prepar2.close();
				prepar.close();
//				conn.close();
				return list;
			} else {
				pageNow = (pageNow - 1) * 10;
				sql = "SELECT a.id,a.account,a.state,a.youxiang,(select GROUP_CONCAT(r.roleName separator  \",\") as rolename from role r inner join account_role ar on ar.role_id = r.id where ar.account_id = a.id) as rolename FROM account a  limit ?,?";
				pageCountSql = "create VIEW shitu1 as select * from account";
				//sql = "select ar.id,a.account,r.roleName,a.state from account a INNER JOIN role r INNER JOIN account_role ar on a.id=ar.account_id and r.id=ar.role_id ORDER BY ar.id asc limit ?,?";
				PreparedStatement prepar1 = (PreparedStatement) conn.prepareStatement("drop view if exists shitu1;");
				prepar1.executeUpdate();
				PreparedStatement prepar2 = (PreparedStatement) conn.prepareStatement(pageCountSql);
				prepar2.executeUpdate();
				PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
				prepar.setInt(1, pageNow);
				prepar.setInt(2, Paging.getPageNumber());
				ResultSet resu = prepar.executeQuery();
				while (resu.next()) {
					Account_Role us = new Account_Role();
					us.setId(resu.getInt(1));
					us.setYouxiang(resu.getNString(4));
					us.setRoleName(resu.getString(5));
					us.setUserName(resu.getString(2));
					us.setState(resu.getInt(3));
					if (us.getState() == 0) {
						us.setStateStr("使用中");
					} else {
						us.setStateStr("已停用");
					}
					list.add(us);
				}
				resu.close();
				prepar.close();
				prepar2.close();
				prepar1.close();
//				conn.close();
				return list;
			}
		} catch (Exception e) {
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
			String sql = "select count(*) from shitu1";
			PreparedStatement prepar = conn.prepareStatement(sql);
			prepar.executeQuery();
			ResultSet resu = prepar.getResultSet();
			while (resu.next()) {
				total = resu.getInt(1);
			}
			resu.close();
			prepar.close();
//			conn.close();
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
