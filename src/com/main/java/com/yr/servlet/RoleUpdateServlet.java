package com.yr.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.yr.dao.LinkMysql;
import com.yr.pojo.Role;
import com.yr.pojo.RoleTzh;
import com.yr.util.JsonUtils;

/**
 * 修改
 * 2017
 * @author zxy
 * Administrator
 * 2017年12月20日 下午5:51:09
 */
public class RoleUpdateServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection conn = (Connection) LinkMysql.getCon();
            String sql = "select id,roleName from role;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
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
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String id = req.getParameter("id");
            String roleName = req.getParameter("roleName");
            Connection conn = (Connection) LinkMysql.getCon();
            String str = "update role set roleName = ? where id = ?";
            
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
            ps.setString(1,roleName);
            ps.setString(2,id);
            ps.executeUpdate();
            String strJson = JsonUtils.beanToJson(ps);
            resp.getWriter().write(strJson);
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("roleQuery");
    }
}
