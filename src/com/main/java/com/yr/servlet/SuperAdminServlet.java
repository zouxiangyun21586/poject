package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SuperAdminDao;

/**
 * ��������Աservlet (��������Ա)
 * @author ��ҵ��
 * 2017��12��13�� ����11:14:00
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
			//���
			PrintWriter out = response.getWriter();
			String role = request.getParameter("interest");//��ɫ
			String name = request.getParameter("username");//�û���
			String account = request.getParameter("account");//�˺�
			String pass = request.getParameter("password");//����
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
