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

import com.yr.pojo.User;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

public class SupplieServlet extends HttpServlet {

    /**
     * 供应商 增删改查
     * 
     * @author zxy
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * 2017年12月13日 下午9:02:08
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sup = req.getParameter("sup"); // 页面传过来的值
        if ("2".equals(sup)) { // 添加
            try {
                req.setCharacterEncoding("UTF-8");
                String strName = req.getParameter("commodity");
                Connection conn = Conn.conn();
                String str = "insert into supplier(commodity) values(?);"; // id自增长
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
                ps.setString(1, strName);
                ps.executeUpdate();// 执行查询
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 查询
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if ("3".equals(sup)) { // 删除
            try {
                Connection conn = Conn.conn();
                String str = "delete from supplier where id = ?";
                String strId = req.getParameter("supDel");
                strId = new String(strId.getBytes("ISO-8859-1"), "utf-8"); // 解决乱码问题
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
                ps.setString(1, strId);
                ps.executeUpdate(); // 执行修改
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 查询
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if ("4".equals(sup)) { // 修改
            try {
                String id = req.getParameter("id");
                String commodity = req.getParameter("commodity");
                Connection conn = Conn.conn();
                String str = "update supplier set commodity = ? where id = ?";

                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
                ps.setString(1, commodity);
                ps.setString(2, id);

                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 查询
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if ("5".equals(sup)) { // 查询
            resp.setCharacterEncoding("utf-8");
            try { // state 0状态在使用的账号,1状态是已停用账号
                Connection conn = Conn.conn();
                // 获得id  状态
                String state = req.getParameter("state");
                String str = "select Distinct su.id,su.commodity,me.money,me.`describe`,me.specificationID,me.upFrameTime,me.number from supplier su,merchandise me,account_role ar where su.commodity = me.`name` and ar.state = ?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
                ps.executeQuery();// 执行查询
                ResultSet rs = ps.getResultSet();// 获取查询结果
                List<User> selist = new ArrayList<>();
                // 循环结果集
                while (rs.next()) {// 取结果集中的下一个。
                    User la = new User();
                    la.setState(rs.getInt(state));
                    selist.add(la);
                }
                String strJson = JsonUtils.beanListToJson(selist);
                resp.getWriter().write(strJson);
                rs.close(); // 关闭结果集
                ps.close(); // 关闭发送SQL对象
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 判断Id是否重复
     * 
     * @author zxy
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * 2017年12月13日 下午10:35:08
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        String sId= "select count(*) from ajaxtable where id = ?";
        try {
            Connection conn = Conn.conn();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sId);
            ps.setString(1,strId);
            ps.executeQuery();// 执行查询
            ResultSet rs = ps.getResultSet();
            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                resp.getWriter().write("0");
            }else{
                resp.getWriter().write("1");
            }
        } catch (Exception e) {
            resp.getWriter().write("0");
            e.printStackTrace();
        }
    }

}
