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
        String json = JsonUtils.beanListToJson(PowerDao.jilian(Integer.valueOf(id)));
        resp.getWriter().write(json);
    }

    

}
