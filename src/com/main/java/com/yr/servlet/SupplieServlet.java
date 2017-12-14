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
     * ��Ӧ�� ��ɾ�Ĳ�
     * 
     * @author zxy
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * 2017��12��13�� ����9:02:08
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sup = req.getParameter("sup"); // ҳ�洫������ֵ
        if ("2".equals(sup)) { // ���
            try {
                req.setCharacterEncoding("UTF-8");
                String strName = req.getParameter("commodity");
                Connection conn = Conn.conn();
                String str = "insert into supplier(commodity) values(?);"; // id������
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
                ps.setString(1, strName);
                ps.executeUpdate();// ִ�в�ѯ
            } catch (Exception e) {
                e.printStackTrace();
            }
            // ��ѯ
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if ("3".equals(sup)) { // ɾ��
            try {
                Connection conn = Conn.conn();
                String str = "delete from supplier where id = ?";
                String strId = req.getParameter("supDel");
                strId = new String(strId.getBytes("ISO-8859-1"), "utf-8"); // �����������
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
                ps.setString(1, strId);
                ps.executeUpdate(); // ִ���޸�
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // ��ѯ
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if ("4".equals(sup)) { // �޸�
            try {
                String id = req.getParameter("id");
                String commodity = req.getParameter("commodity");
                Connection conn = Conn.conn();
                String str = "update supplier set commodity = ? where id = ?";

                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
                ps.setString(1, commodity);
                ps.setString(2, id);

                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // ��ѯ
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if ("5".equals(sup)) { // ��ѯ
            resp.setCharacterEncoding("utf-8");
            try { // state 0״̬��ʹ�õ��˺�,1״̬����ͣ���˺�
                Connection conn = Conn.conn();
                // ���id  ״̬
                String state = req.getParameter("state");
                String str = "select Distinct su.id,su.commodity,me.money,me.`describe`,me.specificationID,me.upFrameTime,me.number from supplier su,merchandise me,account_role ar where su.commodity = me.`name` and ar.state = ?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
                ps.executeQuery();// ִ�в�ѯ
                ResultSet rs = ps.getResultSet();// ��ȡ��ѯ���
                List<User> selist = new ArrayList<>();
                // ѭ�������
                while (rs.next()) {// ȡ������е���һ����
                    User la = new User();
                    la.setState(rs.getInt(state));
                    selist.add(la);
                }
                String strJson = JsonUtils.beanListToJson(selist);
                resp.getWriter().write(strJson);
                rs.close(); // �رս����
                ps.close(); // �رշ���SQL����
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * �ж�Id�Ƿ��ظ�
     * 
     * @author zxy
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * 2017��12��13�� ����10:35:08
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");
        String sId= "select count(*) from ajaxtable where id = ?";
        try {
            Connection conn = Conn.conn();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sId);
            ps.setString(1,strId);
            ps.executeQuery();// ִ�в�ѯ
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
