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
import com.yr.pojo.Mune;
import com.yr.util.JsonUtils;

public class PowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        System.out.println(id);
        String json = JsonUtils.beanListToJson(jilian(Integer.valueOf(id)));
        System.out.println(id + "============" + json);
        resp.getWriter().write(json);
    }

    public static List<Mune> jilian(Integer id) {
        try {
            String sql = "select * from menu where menu_id=?";
            Connection con = LinkMysql.getCon();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Mune> list = new ArrayList<>();
            while (rs.next()) {
                Mune j = new Mune();
                j.setId(rs.getInt(1));
                j.setMuneId(rs.getInt(2));
                j.setFatherName(rs.getString(3));
                list.add(j);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
