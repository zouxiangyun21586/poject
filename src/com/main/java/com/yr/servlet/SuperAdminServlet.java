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
		String i = request.getParameter("i");
		if("1".equals(i)){
			//页面显示值
			PrintWriter out = response.getWriter();
			String type= request.getParameter("type");
			String select= request.getParameter("select");//搜索功能中输入框的值(账号)
			String interest= request.getParameter("interest");//搜索功能中下拉框的值(角色)
			String pageNow = request.getParameter("pageNow");//获得页面传过来的当前页
			if (null != type && type.equals("list")) {
				String sel = request.getParameter("select");
				if (null == pageNow || "".equals(pageNow)) {
					pageNow = "1";
				}
//				List<Account_Role> list = SuperAdminDao.query();
				List<Account_Role> list = SuperAdminDao.selectemp(select,interest,Integer.valueOf(pageNow),sel);
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
		}else if("2".equals(i)){
			//停用账号
//			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			SuperAdminDao.delete(id);
//			out.write("good");
//			out.flush();
//			out.close();
			response.sendRedirect("user/user.jsp");
		}else if("3".equals(i)){
			//启动账号
//			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			SuperAdminDao.revive(id);
//			out.write("good");
//			out.flush();
//			out.close();
			response.sendRedirect("user/user.jsp");
		}else if("4".equals(i)){
			//修改数据回显
			String id = request.getParameter("id");//修改的账号表id
			id = new String(id.getBytes("ISO-8859-1"),"UTF-8");
			String acc = request.getParameter("acc");//修改的账号
			acc = new String(acc.getBytes("ISO-8859-1"),"UTF-8");
			String oldrole = request.getParameter("oldRole");//原先的角色
			oldrole = new String(oldrole.getBytes("ISO-8859-1"),"UTF-8");
			String roleId = SuperAdminDao.updateEcho(oldrole);
			request.setAttribute("id",id);
			request.setAttribute("acc",acc);
			request.setAttribute("roleId",roleId);
			request.getRequestDispatcher("user/update.jsp").forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		String i = request.getParameter("i");
		if("2".equals(i)){
			//添加
			PrintWriter out = response.getWriter();
			String role = request.getParameter("interest");//角色的id
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
			out.write(strJson);
			out.flush();
			out.close();
		}else if("4".equals(i)){
			//修改保存
			String oldrole = request.getParameter("oldroleId");//原先的角色id
			String upRoleId = request.getParameter("upRoleId");//后来修改的角色id
			String id = request.getParameter("id");//修改的账号表id
			String acc = request.getParameter("acc");//修改的账号
			PrintWriter out = response.getWriter();
			String strJson = SuperAdminDao.update(oldrole, upRoleId, id, acc);
			out.write(strJson);
			out.flush();
			out.close();
		}
	}
}
