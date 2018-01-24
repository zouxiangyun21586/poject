/**
 *@作者：唐子壕
 *@时间：2018年1月20日上午10:47:56
 */
package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.LinkMysql;
import com.yr.dao.RoleManagementDaoLayer;
import com.yr.dao.SuperAdminDao;
import com.yr.pojo.RoleTzh;
import com.yr.util.JsonUtils;

/**
 * @author Administrator
 *
 */
public class RoleManagementServletLayer extends HttpServlet{
	
	/* 作者：唐子壕
	 * @时间：2018年1月23日上午10:31:52
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String roleNum=req.getParameter("role");
		if("1".equals(roleNum)){//查询所有角色
			PrintWriter out = resp.getWriter();
			String strJson = RoleManagementDaoLayer.query();
			out.write(strJson);
			out.flush();
			out.close();
		}else if("2".equals(roleNum)){//删除角色
			String roleId=req.getParameter("id");
			PrintWriter out = resp.getWriter();
			RoleManagementDaoLayer.delete(roleId);
			out.flush();
			out.close();
		}else if("3".equals(roleNum)){//增加角色
			String roleName=req.getParameter("value");
			roleName = new String(roleName.getBytes("ISO-8859-1"),"UTF-8");
			PrintWriter out = resp.getWriter();
			String strJson = RoleManagementDaoLayer.add(roleName);
			out.write(strJson);
			out.flush();
			out.close();
		}else if("4".equals(roleNum)){//修改角色
			String id=req.getParameter("id");
			String roleName=req.getParameter("roleName");
			PrintWriter out=resp.getWriter();
			RoleManagementDaoLayer.update(id, roleName);
			out.flush();
			out.close();
		}else if("5".equals(roleNum)){//搜索角色
			String roleName=req.getParameter("roleName");
			roleName = new String(roleName.getBytes("ISO-8859-1"),"UTF-8");
			PrintWriter out = resp.getWriter();
			String strJson = RoleManagementDaoLayer.search(roleName);
			out.write(strJson);
			out.flush();
			out.close();
		}
		
	}
	
	
	
	
	/* 作者：唐子壕
	 * @时间：2018年1月23日上午10:31:58
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String roleNum=req.getParameter("role");
		if("6".equals(roleNum)){
			String roleName=req.getParameter("roleName");
			PrintWriter out = resp.getWriter();
			String strJson = RoleManagementDaoLayer.fuzzySearch(roleName);
			out.write(strJson);
			out.flush();
			out.close();
			
		}
	}	
}
