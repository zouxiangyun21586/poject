package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
        	/*String yname = req.getParameter("yname");
        	yname=new String(yname.getBytes("ISO-8859-1"),"UTF-8");
        	String yid = req.getParameter("yid");
        	yid=new String(yid.getBytes("ISO-8859-1"),"UTF-8");
        	String yurl = req.getParameter("yurl");
        	yurl=new String(yurl.getBytes("ISO-8859-1"),"UTF-8");
        	String ysta = req.getParameter("ysta");
        	ysta=new String(ysta.getBytes("ISO-8859-1"),"UTF-8");*/
        	String yfor = req.getParameter("for");
        	yfor=new String(yfor.getBytes("ISO-8859-1"),"UTF-8");
        	System.out.println(yfor);
        	PrintWriter out = resp.getWriter();
//        	System.out.println(yname+", "+yid+", "+yurl+", "+ysta);
			out.write("a");
			out.flush();
			out.close();
        }
    }

}
