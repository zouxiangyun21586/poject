package com.yr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.PowerDao;
import com.yr.util.JsonUtils;

public class PowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String sql = "select p.id,m.fatherName,p.url,p.icon,p.state from permission p,menu m where p.id=m.id and m.menu_id=?;";
        String json = JsonUtils.beanListToJson(PowerDao.jilian(Integer.valueOf(id), sql));
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        String i = req.getParameter("i");
        if("1".equals(i)){
	        String id = req.getParameter("id");
	        String sql = "select p.id,m.fatherName,p.url,p.icon,p.state from permission p,menu m where p.id=m.id and m.menu_id=?;";
	        String json = JsonUtils.beanListToJson(PowerDao.jilian(Integer.valueOf(id), sql));
	        resp.getWriter().write(json);
        }else if("2".equals(i)){
        	
        }
    }

}
