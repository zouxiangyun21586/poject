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
 * Ȩ�޹���servlet
 * @author ��ҵ��
 * 2017��12��19�� ����9:34:21
 */
public class PowerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i = request.getParameter("i");
		if("0".equals(i)){
			//ҳ����ض�ȡ
			List<Mune> genNameList = PowerDao.genName();//��ȡ���ڵ�����
			List<Mune> genIdList = PowerDao.genId();//��ȡ���ڵ�id
			List<Mune> ziList = new ArrayList<>();
			Map<String,Object> map = new HashMap<>();
			/*for (Mune mune : genIdList) {
				ziList = PowerDao.jilian(mune.getId());//��ȡ�ֽڵ�
				map.put("x", ziList);
			}*/
			/*for (Mune mune : genIdList) {
				ziList = PowerDao.jilian(mune.getId());//��ȡ�ֽڵ�
			}*/
			for (int j = 0; j < genIdList.size(); j++) {
				Mune nu = genIdList.get(j);
				ziList = PowerDao.jilian(nu.getId());//��ȡ�ֽڵ�
				map.put("jian"+i, ziList);
			}
			String name=request.getParameter("name");
			System.out.println("name"+name);
			Integer x = PowerDao.getGenId(name);//�������ƻ�ȡ���ڵ�id
			List<Mune> testList = PowerDao.jilian(x);//���ݸ��ڵ�id��ȡ�ֽڵ�
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
