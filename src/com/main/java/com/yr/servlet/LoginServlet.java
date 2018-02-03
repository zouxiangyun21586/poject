package com.yr.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.pojo.User;
import com.yr.util.Conn;
import com.yr.util.Encrypt;

/**
 * 鐧诲綍
 * 
 * @浣滆�� 鍗冩瘏
 *
 * @鏃堕棿 2017骞�12鏈�13鏃� 涓嬪崍9:36:39
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");// 寰楀埌鐢ㄦ埛鍚�
        String password = Encrypt.encodeByMD5(req.getParameter("password"));// 寰楀埌瀵嗙爜
        String ck = req.getParameter("ck");// 寰楀埌瀵嗙爜
        String hiddenCode = req.getParameter("hiddenCode");// 绗竴娆¤繘鏉iddenCode绛変簬绌�(鏍囧織鏄惁涓虹涓�娆＄櫥闄�)
        if (hiddenCode == null || "".equals(hiddenCode)) {
            if (login(req, resp, username, password, ck)) {
                if ("0".equals(questate(req, resp, username))) {
                    session(req, resp, queaccount(req, resp, username), queID(req, resp, username),
                            querole(req, resp, username));
                } else {
                    req.setAttribute("state", 3);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);// 璺冲埌娆㈣繋椤甸潰
                }
            } else {
                req.setAttribute("hiddenCode", 1);
                req.setAttribute("state", 1);
                req.getRequestDispatcher("login.jsp").forward(req, resp);// 璺冲埌娆㈣繋椤甸潰
            }
        } else {
            String random = req.getParameter("randomCode");// 寰楀埌鐣岄潰杈撳叆鐨勯獙璇佺爜
            String randomCode = random.toUpperCase();
            String yzm = (String) req.getSession().getAttribute("rand");// 寰楀埌鎴戜滑鐢熸垚鐨勬纭殑楠岃瘉鐮�
            if (randomCode.equals("")) {
                req.setAttribute("hiddenCode", 1);// 鍚慼iddencode閲岃缃竴涓��
                req.getRequestDispatcher("login.jsp").forward(req, resp);// 璺冲埌娆㈣繋椤甸潰
            } else {
                if (randomCode.equals(yzm)) {
                    if (login(req, resp, username, password, ck)) {
                        if ("0".equals(questate(req, resp, username))) {
                            session(req, resp, queaccount(req, resp, username), queID(req, resp, username),
                                    querole(req, resp, username));
                        } else {
                            req.setAttribute("state", 3);
                            req.getRequestDispatcher("login.jsp").forward(req, resp);// 璺冲埌娆㈣繋椤甸潰
                        }
                    } else {
                        req.setAttribute("hiddenCode", 1);
                        req.setAttribute("state", 1);
                        req.getRequestDispatcher("login.jsp").forward(req, resp);// 璺冲埌娆㈣繋椤甸潰
                    }
                } else {
                    req.setAttribute("state", 2);
                    req.setAttribute("hiddenCode", 1);// 鍚慼iddencode閲岃缃竴涓��
                    req.getRequestDispatcher("login.jsp").forward(req, resp);// 璺冲埌娆㈣繋椤甸潰
                }
            }
        }
    }

    /**
     * 楠岃瘉璐﹀彿瀵嗙爜鏄惁姝ｇ‘
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
        try {
            Connection con = Conn.conn();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            String sql = "select account,password from account;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                if (rs.getString(1).equals(user.getUsername()) && rs.getString(2).equals(user.getPassword())) {
                    if ("true".equals(ck)) {
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
     * 鏍规嵁璐﹀彿鏌ヨ鐢ㄦ埛鍚�
     * 
     * @param req
     * @param resp
     * @param username
     * @return
     */
    public static String queaccount(HttpServletRequest req, HttpServletResponse resp, String username) {
        String name = null;
        try {
            Connection con = Conn.conn();
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
     * 鏍规嵁璐﹀彿鏌ヨ鐢ㄦ埛ID
     * 
     * @param req
     * @param resp
     * @param username
     * @return
     */
    public static int queID(HttpServletRequest req, HttpServletResponse resp, String username) {
        int id = 0;
        try {
            Connection con = Conn.conn();
            String sql = "select id from account where account=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 鏌ヨ鐢ㄦ埛鐘舵��
     * 
     * @param req
     * @param resp
     * @param username
     * @return
     */
    public static String questate(HttpServletRequest req, HttpServletResponse resp, String username) {
        String name = null;
        try {
            Connection con = Conn.conn();
            String sql = "select state from account where account=?;";
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
     * 鏍规嵁璐﹀彿鏌ヨ瑙掕壊
     * 
     * @param req
     * @param resp
     * @param username
     * @return
     */
    public static String querole(HttpServletRequest req, HttpServletResponse resp, String username) {
        String name = null;
        try {
            Connection con = Conn.conn();
            String sql = "select r.roleName from role r,account a, account_role ar where (r.id = ar.role_id and a.id = ar.account_id) and a.account=?";
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
     * 鑾峰彇鏁版嵁搴撳凡鐢ㄥ唴瀛�
     * 
     * @param req
     * @param resp
     * @return
     */
    public static String quemysqlsize(HttpServletRequest req, HttpServletResponse resp) {
        String name = null;
        try {
            Connection con = Conn.conn();
            String sql = "select concat(round((sum(data_length)+sum(index_length))/1024/1024,2),'MB') from information_schema.tables where table_schema='test';";
            PreparedStatement ps = con.prepareStatement(sql);
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
     * 灏嗙敤鎴峰悕,瑙掕壊瀛樹簬session
     * 
     * @param req
     * @param resp
     * @param username
     */
    public void session(HttpServletRequest req, HttpServletResponse resp, String username, int userID, String role) {
        try {
            try {
                req.getSession().setMaxInactiveInterval(30 * 60);
                req.getSession().setAttribute("username", username);
                req.getSession().setAttribute("userID", userID);
                req.getSession().setAttribute("role", role);
                req.getSession().setAttribute("zip", getIpAddress());
                req.getSession().setAttribute("ip", InetAddress.getLocalHost());
                req.getSession().setAttribute("mysqlsize", quemysqlsize(req, resp));
                req.getSession().setAttribute("jdk", System.getProperty("java.version"));
                req.getSession().setAttribute("port", req.getLocalPort());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("index.jsp").forward(req, resp);// 璺冲埌娆㈣繋椤甸潰
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getIpAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();

        return address.getHostAddress();
    }
}
