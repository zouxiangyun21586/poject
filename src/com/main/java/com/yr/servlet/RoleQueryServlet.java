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

public class RoleQueryServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	/* 作者：唐子壕
	 * @时间：2017年12月15日下午8:29:40
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
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
	}
}
