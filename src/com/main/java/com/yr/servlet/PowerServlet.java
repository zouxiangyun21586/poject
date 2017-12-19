package com.yr.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.PowerDao;
import com.yr.pojo.Mune;
import com.yr.pojo.Power;

/**
 * 权限管理servlet
 * @author 周业好
 * 2017年12月19日 下午9:34:21
 */
public class PowerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i = request.getParameter("i");
		if("0".equals(i)){
			//页面加载读取
			List<Mune> genNameList = PowerDao.genName();//获取根节点名字
			List<Mune> genIdList = PowerDao.genId();//获取根节点id
			List<Mune> ziList = new ArrayList<>();
			Map<String,Object> map = new HashMap<>();
			/*for (Mune mune : genIdList) {
				ziList = PowerDao.jilian(mune.getId());//获取字节点
				map.put("x", ziList);
			}*/
			/*for (Mune mune : genIdList) {
				ziList = PowerDao.jilian(mune.getId());//获取字节点
			}*/
			for (int j = 0; j < genIdList.size(); j++) {
				Mune nu = genIdList.get(j);
				ziList = PowerDao.jilian(nu.getId());//获取字节点
				map.put("jian"+i, ziList);
			}
			String name=request.getParameter("name");
			System.out.println("name"+name);
			Integer x = PowerDao.getGenId(name);//根据名称获取根节点id
			List<Mune> testList = PowerDao.jilian(x);//根据根节点id获取字节点
			request.setAttribute("map", map);
			request.setAttribute("testList", testList);
			request.setAttribute("genList", genNameList);
			request.setAttribute("ziList", ziList);
			request.getRequestDispatcher("quanxian.jsp").forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
