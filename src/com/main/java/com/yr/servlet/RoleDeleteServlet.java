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
		try {
			Connection conn = (Connection) LinkMysql.getCon();
			String id = req.getParameter("id");
			String sql = "select r.id from role r,account a, account_role ar where r.id = ar.role_id and a.id = ar.account_id and a.id=? ";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			rs.next();
			int number = rs.getInt(1);
			if (number == 1 || number == 2) {
				resp.getWriter().write("0");
			} else if (number <= 7 && number > 2) {
				resp.getWriter().write("1");
			} else if (number == 8) {
				resp.getWriter().write("2");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			Connection conn = (Connection) LinkMysql.getCon();
			String sql = "delete from accout where id = ?";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
