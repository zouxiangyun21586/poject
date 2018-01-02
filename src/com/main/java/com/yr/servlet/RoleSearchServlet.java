/**
 *@作者：唐子壕
 *@时间：2017年12月29日下午2:25:17
 */
package com.yr.servlet;

import java.io.IOException;
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
import com.yr.pojo.RoleTzh;
import com.yr.util.JsonUtils;

/**
 * @author Administrator
 *
 */
public class RoleSearchServlet extends HttpServlet{
	/* 作者：唐子壕
	 * @时间：2017年12月29日下午2:26:19
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			//req.setCharacterEncoding("UTF-8");
			Connection conn=LinkMysql.getCon();
			String roleName=req.getParameter("roleName");
			roleName = new String(roleName.getBytes("ISO-8859-1"),"UTF-8");
			String sql="select count(id) FROM role where roleName like ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,"%"+roleName+"%");
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			rs.next();
			int count=rs.getInt(1);
			if(count == 0){
				System.out.println("没有你要查询的职位");
				resp.getWriter().write("0");
			}else{
				System.out.println("查询如下");
				resp.getWriter().write("1");
			}
		}catch(Exception e){
			
		}
	}
	/* 作者：唐子壕
	 * @时间：2017年12月29日下午2:26:22
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			resp.setCharacterEncoding("UTF-8");
			Connection conn=LinkMysql.getCon();
			String roleName= req.getParameter("roleName");
			if(roleName.equals("")|| roleName.equals(" ")){
				System.out.println("你输入的值为空");
				resp.getWriter().write("0");
			}else{
				String sql="select id,roleName FROM role where roleName like ?";
				PreparedStatement ps=(PreparedStatement)conn.prepareStatement(sql);
				ps.setString(1,"%"+roleName+"%");
				ps.executeQuery();
				ResultSet rs = ps.getResultSet();
				List<RoleTzh> list = new ArrayList<>();
				while (rs.next()){
					RoleTzh role = new RoleTzh();
					role.setId(rs.getInt(1));
					role.setRoleName(rs.getString(2));
					list.add(role);
				}
				String strjson = JsonUtils.beanListToJson(list);
				resp.getWriter().write(strjson);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		
}
