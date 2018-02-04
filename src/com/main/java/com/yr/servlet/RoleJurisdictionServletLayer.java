package com.yr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.RoleJurisdictionDaoLayer;
import com.yr.util.JsonUtils;


public class RoleJurisdictionServletLayer extends HttpServlet{
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        String jsonObjectStr = JsonUtils.beanListToJson(RoleJurisdictionDaoLayer.query());
    	resp.getWriter().write(jsonObjectStr);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        String cascade=req.getParameter("cascade");
        if("1".equals(cascade)){
        	String faterId=req.getParameter("");
        	String sql="select fatherName from menu where menu_id=?";
        	String jsonObjectStr = JsonUtils.beanListToJson(RoleJurisdictionDaoLayer.test(Integer.valueOf(faterId), sql));
        	resp.getWriter().write(jsonObjectStr);
        }
        
	}
}
