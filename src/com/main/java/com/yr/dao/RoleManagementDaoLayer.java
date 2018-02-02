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

import com.yr.pojo.Account_Role;
import com.yr.pojo.Paging;
import com.yr.pojo.Role;
import com.yr.pojo.Role;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class RoleManagementDaoLayer {
	
	private static String roleName=null;
	
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
            roleName = new String(roleName.getBytes("ISO-8859-1"),"UTF-8");
            ps.setString(1,roleName);
            ps.setString(2,id);
            ps.executeUpdate();
            ps.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		
	
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
	
	/**
	 * 查询数据并用list封装起来
	 * @param pageNow 当前页
	 * @param sel 判断是否用了查询功能
	 * @return 返回所查询的数据
	 */
	public static List<Role> selectemp(Integer pageNow, String sel) {
 		String sql = "";
		List<Role> list = new ArrayList<>();
		if (pageNow < 1) {
			pageNow = 1;
		}
		try {
			Connection conn = Conn.conn();
			if (null != sel && !"".equals(sel)) {//使用搜索功能进入这个if判断
				pageNow=0;
				sql = "select * from role where roleName like ? limit ?,?";
				PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
				String roleName=decodeSpecialCharsWhenLikeUseSlash(sel);
				prepar.setString(1,"%"+roleName+"%");
				prepar.setInt(2,pageNow);
				prepar.setInt(3,Paging.getPageNumber());
				ResultSet resu = prepar.executeQuery();
				while (resu.next()) {
					Role us = new Role();
					us.setId(resu.getInt(1));
					us.setName(resu.getString(2));
					list.add(us);
				}
				resu.close();
				prepar.close();
				return list;
			} else {
				pageNow = (pageNow - 1) * 10;
				sql = "select * from  role  limit ?,?";
				PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
				prepar.setInt(1, pageNow);
				prepar.setInt(2, Paging.getPageNumber());
				ResultSet resu = prepar.executeQuery();
				while (resu.next()) {
					Role us = new Role();
					us.setId(resu.getInt(1));
					us.setName(resu.getString(2));
					list.add(us);
				}
				resu.close();
				prepar.close();
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
	public static Integer getPageCount(String roleName) {
		int total = 0;// 总共多少条记录
		int pageCount = 0;// 总页数
		try {
			Connection conn = Conn.conn();
			String sql = "select count(*) from role";
			PreparedStatement prepar = conn.prepareStatement(sql);
			prepar.executeQuery();
			ResultSet resu = prepar.getResultSet();
			while (resu.next()) {
				total = resu.getInt(1);
			}
			if(roleName != null && !roleName.equals("")){
				sql = "select count(*) from role where roleName = ?";
				PreparedStatement pre = conn.prepareStatement(sql);
				pre.setString(1, roleName);
				pre.executeQuery();
				ResultSet re = pre.getResultSet();
				while (re.next()) {
					total = re.getInt(1);
				}
			}
			resu.close();
			prepar.close();
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
				System.out.println(name);
			}
			pre.close();
			return name;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String decodeSpecialCharsWhenLikeUseSlash(String content) {
        String afterDecode = content.replaceAll("'", "''");
        afterDecode = afterDecode.replaceAll("\\\\", "\\\\\\\\");
        afterDecode = afterDecode.replaceAll("%", "\\\\%");
        afterDecode = afterDecode.replaceAll("_", "\\\\_");
        return afterDecode;
    }

}
