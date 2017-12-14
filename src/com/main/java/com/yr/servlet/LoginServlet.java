package com.yr.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.LinkMysql;
import com.yr.pojo.User;

/**
 * 登录
 * 
 * @作者 千毅
 *
 * @时间 2017年12月13日 下午9:36:39
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");// 得到用户名
        String password = req.getParameter("password");// 得到密码
        String ck = req.getParameter("ck");// 得到密码
        String hiddenCode = req.getParameter("hiddenCode");// 第一次进来hiddenCode等于空(标志是否为第一次登陆)
        if (hiddenCode == null || "".equals(hiddenCode)) {
            if (login(req, resp, username, password, ck)) {
                System.out.println(queaccount(req, resp, username));
                session(req, resp, queaccount(req, resp, username));
            } else {
                req.setAttribute("hiddenCode", 1);
                req.getRequestDispatcher("login.jsp").forward(req, resp);// 跳到欢迎页面
            }
        } else {
            String random = req.getParameter("randomCode");// 得到界面输入的验证码
            String randomCode = random.toUpperCase();
            String yzm = (String) req.getSession().getAttribute("rand");// 得到我们生成的正确的验证码
            if (randomCode.equals("")) {
                req.setAttribute("hiddenCode", 1);// 向hiddencode里设置一个值
                req.getRequestDispatcher("login.jsp").forward(req, resp);// 跳到欢迎页面
            } else {
                System.out.println(randomCode + "--" + yzm);
                if (randomCode.equals(yzm)) {
                    if (login(req, resp, username, password, ck)) {
                        session(req, resp, queaccount(req, resp, username));
                    } else {
                        req.setAttribute("hiddenCode", 1);
                        req.getRequestDispatcher("login.jsp").forward(req, resp);// 跳到欢迎页面
                    }
                } else {
                    req.setAttribute("hiddenCode", 1);// 向hiddencode里设置一个值
                    req.getRequestDispatcher("login.jsp").forward(req, resp);// 跳到欢迎页面
                }
            }
        }
    }

    /**
     * 验证账号密码是否正确
     * 
     * @param req
     * @param resp
     * @param username
     * @param password
     * @param ck
     * @return
     */
    public static boolean login(HttpServletRequest req, HttpServletResponse resp, String username, String password,
            String ck) {
        System.out.println("页面的值" + username + "---" + password + "---" + ck);
        try {
            Connection con = LinkMysql.getCon();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            String sql = "select account,password from account;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                if (rs.getString(1).equals(user.getUsername()) && rs.getString(2).equals(user.getPassword())) {
                    System.out.println("数据库的值" + rs.getString(1) + "---" + rs.getString(2));
                    if ("on".equals(ck)) {
                        System.out.println("ck状态:" + ck);
                        Cookie c = new Cookie("users", username + "-" + password);
                        c.setMaxAge(10 * 60);
                        resp.addCookie(c);
                    }
                    return true;
                }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据账号查询用户名
     * 
     * @param req
     * @param resp
     * @param username
     * @return
     */
    public static String queaccount(HttpServletRequest req, HttpServletResponse resp, String username) {
        String name = null;
        try {
            Connection con = LinkMysql.getCon();
            String sql = "select name from account where account=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                name = rs.getString(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 将用户名存于session
     * 
     * @param req
     * @param resp
     * @param username
     */
    public void session(HttpServletRequest req, HttpServletResponse resp, String username) {
        try {
            req.getSession().setMaxInactiveInterval(5 * 60);
            req.getSession().setAttribute("username", username);
            req.getRequestDispatcher("index.jsp").forward(req, resp);// 跳到欢迎页面
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}