package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SuperAdminDao;
import com.yr.pojo.Account_Role;
import com.yr.util.PageService;

import net.sf.json.JSONObject;

/**
 * 超级管理员servlet (操作管理员)
 * @author 周业好
 * 2017年12月13日 上午11:14:00
 */
public class SuperAdminServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
//		String type= request.getParameter("type");
		String pageNow = request.getParameter("pageNow");//获得页面传过来的当前页
//		String sel = request.getParameter("select");
		if (null == pageNow || "".equals(pageNow)) {
			pageNow = "1";
		}
		List<Account_Role> list = SuperAdminDao.query();
		int pageCount=SuperAdminDao.getPageCount();//获得总页数
		String pageCode = new PageService().getPageCode(Integer.parseInt(pageNow), pageCount);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("pageCount", pageCount + "");
		map.put("pageNow", pageNow);
		map.put("pageCode", pageCode);
		String jsonObjectStr = JSONObject.fromObject(map).toString();
		out.write(jsonObjectStr);
		out.flush();
		out.close();
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
			String bol = SuperAdminDao.add(role,name,account,pass);
			out.write(bol);
			out.flush();
			out.close();
		}else if("3".equals(i)){
			//获取角色
			PrintWriter out = response.getWriter();
			String strJson = SuperAdminDao.quroleName();
			System.out.println("来了");
			out.write(strJson);
			out.flush();
			out.close();
		}
	}
}
