/**
 *@作者：唐子壕
 *@时间：2018年1月20日上午10:47:56
 */
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

import com.yr.dao.RoleManagementDaoLayer;
import com.yr.pojo.Role;
import com.yr.util.PageService;

import net.sf.json.JSONObject;

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
		System.out.println(11111);
		if("1".equals(roleNum)){//查询所有角色和搜索角色
			//页面显示值
			PrintWriter out = resp.getWriter();
			String type= req.getParameter("type");
			String select= req.getParameter("select");//搜索功能中输入框的值(账号)
			select = new String(select.getBytes("ISO-8859-1"),"UTF-8");
			String pageNow = req.getParameter("pageNow");//获得页面传过来的当前页
			if (null != type && type.equals("list")){
				if (null == pageNow || "".equals(pageNow)){
					pageNow = "1";
				}
				List<Role> list = RoleManagementDaoLayer.selectemp(Integer.valueOf(pageNow),select);
				//获得总页数
				int pageCount=RoleManagementDaoLayer.getPageCount(select);
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
		}
	}
}
