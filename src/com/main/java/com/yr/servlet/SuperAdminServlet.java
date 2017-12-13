package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SuperAdminDao;

/**
 * 超级管理员servlet (操作管理员)
 * @author 周业好
 * 2017年12月13日 上午11:14:00
 */
public class SuperAdminServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		String i = request.getParameter("i");
		if("2".equals(i)){
			//添加
			PrintWriter out = response.getWriter();
			String role = request.getParameter("interest");//角色
			String name = request.getParameter("username");//用户名
			String account = request.getParameter("account");//账号
			String pass = request.getParameter("password");//密码
			boolean bol = SuperAdminDao.add(role,name,account,pass);
			if(bol){
				out.write("good");
			}else {
				out.write("fuck");
			}
			out.flush();
			out.close();
		}
	}
}
