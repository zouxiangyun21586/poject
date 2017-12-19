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
	 * @param role 设置的角色id
	 * @param name 用户名
	 * @param account 账号
	 * @param pass 密码
	 */
	public static String add(String role,String name,String account,String pass){
		try{
			Connection conn = Conn.conn();
			String sql = "insert into account(name,account,password,state) values(?,?,?,?);";
			if(role == null || "".equals(role)){
				return "0";
			}
			boolean bol = repeatName(account);
			if(bol){
				return "1";
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, account);
			pre.setString(3, pass);
			pre.setInt(4, 0);
			pre.executeUpdate();
			pre.close();
			conn.close();
			Integer account_id = getAccountId(name,account);
			Integer role_id = Integer.valueOf(role);//强制转换类型
			addAccount_role(account_id,role_id);
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
	/*public static String update(String strId,String acc,String name){
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
	}*/
	
	/**
	 * 修改职位
	 * @param oldName 修改前的职位
	 * @param idStr 修改后的 职位id 
	 * @param id //修改的id
	 * @param acc //修改的账号
	 * @return 1 没有这个角色,2 不能修改一样的
	 */
	public static String update(String oldName,String roleIdStr,String idStr,String acc){
		try{
			String roleName= quRoleName(roleIdStr);
			if(null == roleName || "".equals(roleName)){//判断他是否选择了职位
				return "1";//请选择职位
			}
			if(oldName.equals(roleName)){//判断他是否是选择了一样的
				return "2";//不能修改一样的
			}
			Integer roleId = Integer.valueOf(roleIdStr);//修改后的 职位id 
			Integer id = Integer.valueOf(idStr);//修改的id
			Integer accId = quAccId(acc);//修改的账号id
			Connection conn = Conn.conn();
			String sql = "update account_role set role_id=? where id=? and account_id=?;";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, roleId);
			pre.setInt(2, id);
			pre.setInt(3, accId);
			pre.executeUpdate();
			pre.close();
			conn.close();
//			resp.sendRedirect(req.getContextPath()+"/test?i=1");
			return roleName;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据角色名字查出对应的id
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
	 * 根据角色id查出对应的角色名字
	 * @param strId 角色id
	 * @return 查出来的角色名字
	 */
	public static String quRoleName(String strId){
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
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 停用职位
	 * @param idStr 账户角色表id
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
			conn.close();
			//将java对象List集合转换成json字符串
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 启用职位
	 * @param idStr 账户角色表id
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
	 * 查询数据并用list封装起来
	 * @param acc 账号
	 * @param rolename 角色名
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
		pageNow = (pageNow - 1) * 10;
		try {
			Connection conn = Conn.conn();
			if (null != sel && !"".equals(sel) || !"quan".equals(rolename)) {//使用搜索功能进入这个if判断
				List<Integer> paramIndex = new ArrayList<>();
				List<Object> param = new ArrayList<>();
				sql = "select ar.id,a.account,r.roleName,a.state from account a INNER JOIN role r INNER JOIN account_role ar on a.id=ar.account_id and r.id=ar.role_id ";
				if(acc != null && !"".equals(acc))
				{
					sql = sql + " and a.account=?";
					paramIndex.add(0);
					param.add(acc);
				}
				if(!"quan".equals(rolename))
				{
					sql = sql + " and r.roleName=?";
					paramIndex.add(0);
					rolename=quRoleName(rolename);//根据角色id查出对应的角色名字
					param.add(rolename);
				}
				
				sql = sql + " limit ?,?";
				paramIndex.add(1);
				paramIndex.add(1);
				
				
				param.add(pageNow);
				param.add(Paging.getPageNumber());
				
				PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
				
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
				
				
				
				/*sql = "select ar.id,a.account,r.roleName,a.state from account a INNER JOIN role r INNER JOIN account_role ar on a.id=ar.account_id and r.id=ar.role_id and a.account=? and r.roleName=? limit ?,?";
				PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
				prepar.setString(1, acc);
				prepar.setString(2, rolename);
				prepar.setInt(3, pageNow);
				prepar.setInt(4, Paging.getPageNumber());*/
				prepar.executeQuery();
				ResultSet resu = prepar.getResultSet();
				while (resu.next()) {
					Account_Role us = new Account_Role();
					us.setId(resu.getInt(1));
					us.setRoleName(resu.getString(2));
					us.setUserName(resu.getString(3));
					us.setState(resu.getInt(4));
					if (us.getState() == 0) {
						us.setStateStr("使用中");
					} else {
						us.setStateStr("已停用");
					}
					list.add(us);
				}
				resu.close();
				prepar.close();
				conn.close();
				return list;
			} else {
				sql = "select ar.id,a.account,r.roleName,a.state from account a INNER JOIN role r INNER JOIN account_role ar on a.id=ar.account_id and r.id=ar.role_id ORDER BY ar.id asc limit ?,?";
				PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
				prepar.setInt(1, pageNow);
				prepar.setInt(2, Paging.getPageNumber());
				prepar.executeQuery();
				ResultSet resu = prepar.getResultSet();
				while (resu.next()) {
					Account_Role us = new Account_Role();
					us.setId(resu.getInt(1));
					us.setRoleName(resu.getString(2));
					us.setUserName(resu.getString(3));
					us.setState(resu.getInt(4));
					if (us.getState() == 0) {
						us.setStateStr("使用中");
					} else {
						us.setStateStr("已停用");
					}
					list.add(us);
				}
				resu.close();
				prepar.close();
				conn.close();
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
