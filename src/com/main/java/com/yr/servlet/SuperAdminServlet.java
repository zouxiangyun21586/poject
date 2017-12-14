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
 * ��������Աservlet (��������Ա)
 * @author ��ҵ��
 * 2017��12��13�� ����11:14:00
 */
public class SuperAdminServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
//		String type= request.getParameter("type");
		String pageNow = request.getParameter("pageNow");//���ҳ�洫�����ĵ�ǰҳ
//		String sel = request.getParameter("select");
		if (null == pageNow || "".equals(pageNow)) {
			pageNow = "1";
		}
		List<Account_Role> list = SuperAdminDao.query();
		int pageCount=SuperAdminDao.getPageCount();//�����ҳ��
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
			//���
			PrintWriter out = response.getWriter();
			String role = request.getParameter("interest");//��ɫ
			String name = request.getParameter("username");//�û���
			String account = request.getParameter("account");//�˺�
			String pass = request.getParameter("password");//����
			String bol = SuperAdminDao.add(role,name,account,pass);
			out.write(bol);
			out.flush();
			out.close();
		}else if("3".equals(i)){
			//��ȡ��ɫ
			PrintWriter out = response.getWriter();
			String strJson = SuperAdminDao.quroleName();
			System.out.println("����");
			out.write(strJson);
			out.flush();
			out.close();
		}
	}
}
