/**
 *@作者：唐子壕
 *@时间：2017年12月20日上午11:16:08
 */
package com.yr.servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.yr.dao.LinkMysql;

/**
 * @author Administrator
 *
 */
public class RoleAddServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* 作者：唐子壕
	 * @时间：2017年12月20日上午11:16:32
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = (Connection) LinkMysql.getCon();
		req.setCharacterEncoding("UTF-8");
		resp.setHeader("content-type", "text/html;charset=UTF-8");
		try{
		String roleName=req.getParameter("value");
		roleName = new String(roleName.getBytes("ISO-8859-1"), "UTF-8");
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
	
	/* 作者：唐子壕
	 * @时间：2017年12月20日上午11:16:38
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
		Connection conn = (Connection) LinkMysql.getCon();
		String roleName=req.getParameter("value");
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
