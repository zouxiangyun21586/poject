package com.yr.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.yr.dao.LinkMysql;

public class RoleDeleteServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			Connection conn = (Connection) LinkMysql.getCon();
			String sql = "delete from role where id = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
			Integer role_id = Integer.valueOf(id);
			delAccount_role(role_id);
			resp.getWriter().write("0");
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("1");
		}
	}
	
	
	
	/**
	 * 删除用户同时删除给账户角色表对应的值
	 * @param role_id 账户id
	 * @param role 角色id
	 */
	public static void delAccount_role(Integer role_id){
		try{
			Connection conn = (Connection) LinkMysql.getCon();
			String sql = "delete from account_role where account_id=?;";
			PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
			pre.setInt(1,role_id);
			pre.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
