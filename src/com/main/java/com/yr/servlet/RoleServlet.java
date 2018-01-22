/**
 *@作者：唐子壕
 *@时间：2018年1月20日上午10:47:56
 */
package com.yr.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.LinkMysql;
import com.yr.pojo.RoleTzh;
import com.yr.util.JsonUtils;

/**
 * @author Administrator
 *
 */
public class RoleServlet extends HttpServlet{
	/* 作者：唐子壕
	 * @时间：2018年1月20日上午11:07:52
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json");
		resp.setCharacterEncoding("utf-8");
		String roleId=req.getParameter("role");
		if("1".equals(roleId)){//查询角色
			try {
				Connection conn = LinkMysql.getCon();
				String sql = "select id,roleName from role;";
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				List<RoleTzh> list = new ArrayList<>();
				while (rs.next()){
					RoleTzh role = new RoleTzh();
					role.setId(rs.getInt(1));
					role.setRoleName(rs.getString(2));
					list.add(role);
				}
				String strjson = JsonUtils.beanListToJson(list);
				resp.getWriter().write(strjson);
				rs.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("3".equals(roleId)||"4".equals(roleId)){//模糊搜索角色和判断输入框的值是否为空
			try{
				
				Connection conn=LinkMysql.getCon();
				String roleName= req.getParameter("roleName");
				roleName = new String(roleName.getBytes("ISO-8859-1"),"UTF-8");
				if(roleName.equals("")|| roleName.equals(" ")){
					resp.getWriter().write("0");
				}else{
					String sql="select id,roleName FROM role where roleName like ?";
					PreparedStatement ps=(PreparedStatement)conn.prepareStatement(sql);
					ps.setString(1,"%\\"+roleName+"%");
					ps.executeQuery();
					ResultSet rs = ps.getResultSet();
					List<RoleTzh> list = new ArrayList<>();
					while (rs.next()){
						RoleTzh role = new RoleTzh();
						role.setId(rs.getInt(1));
						role.setRoleName(rs.getString(2));
						list.add(role);
					}
					String strjson = JsonUtils.beanListToJson(list);
					resp.getWriter().write(strjson);
					rs.close();
					ps.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if("5".equals(roleId)){//删除角色
			try {
				resp.setContentType("text/json");
				resp.setCharacterEncoding("utf-8");
				String id = req.getParameter("id");
				Connection conn = (Connection) LinkMysql.getCon();
				String sql = "delete from role where id = ?";
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.executeUpdate();
				Integer role_id = Integer.valueOf(id);
				//delAccount_role(role_id);
				resp.getWriter().write("0");
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				resp.getWriter().write("1");
			}
		}else if("6".equals(roleId)){//修改角色
			 try{
				 	resp.setContentType("text/json");
					resp.setCharacterEncoding("utf-8");
		            String id = req.getParameter("id");
		            String roleName = req.getParameter("roleName");
		            Connection conn = (Connection) LinkMysql.getCon();
		            String str = "update role set roleName = ? where id = ?";
		            
		            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
		            ps.setString(1,roleName);
		            ps.setString(2,id);
		            ps.executeUpdate();
		            ps.close();
		        }catch(Exception e){
		            e.printStackTrace();
		        }
		}else if("7".equals(roleId)){//添加角色
			try{
				resp.setContentType("text/json");
				resp.setCharacterEncoding("utf-8");
				Connection conn = (Connection) LinkMysql.getCon();
				String roleName=req.getParameter("value");
				roleName = new String(roleName.getBytes("ISO-8859-1"),"UTF-8");
				String sql="insert into role(roleName) values(?) ";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,roleName);
				ps.executeUpdate();
				resp.getWriter().write("0");
				ps.close();
				}catch(Exception e){
					e.printStackTrace();
					resp.getWriter().write("1");
				}
		}
	}
	
	
	/* 作者：唐子壕
	 * @时间：2018年1月20日上午11:07:58
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json");
		resp.setCharacterEncoding("utf-8");
		String roleId=req.getParameter("role");
		if("2".equals(roleId)){//判断角色是否存在
			try{
				req.setCharacterEncoding("UTF-8");
				Connection conn=LinkMysql.getCon();
				String roleName=req.getParameter("roleName");
				//roleName = new String(roleName.getBytes("ISO-8859-1"),"UTF-8");
				String sql="select count(id) from role where roleName like ?";
				PreparedStatement ps=(PreparedStatement)conn.prepareStatement(sql);
				ps.setString(1,"%"+roleName+"%");
				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				rs.next();
				int count=rs.getInt(1);
				if(count == 0){
					resp.getWriter().write("0");
				}else{
					resp.getWriter().write("1");
				}
				rs.close();
				ps.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if("8".equals(roleId)){
			Connection conn = (Connection) LinkMysql.getCon();
			resp.setContentType("text/json");
			resp.setCharacterEncoding("utf-8");
			resp.setHeader("content-type", "text/html;charset=UTF-8");
			try{
			String roleName=req.getParameter("value");
			String sql="select count(*) from role where roleName= ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,roleName);
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			rs.next();
			int count=rs.getInt(1);
			if(count == 0){
				resp.getWriter().write("0");
			}else{
				resp.getWriter().write("1");
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
