package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SellerDao;
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;
import com.yr.dao.LinkMysql;
import com.yr.util.SellerPage;

import com.yr.util.JsonUtils;
import net.sf.json.JSONObject;

/**
 * 
 * @���� ��ˮ�� 2017��12��13������10:58:37
 */
@WebServlet(urlPatterns = "/sellerServlet")
public class SellerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String webUrl4 = "http://www.ntsc.ac.cn";// �й���ѧԺ������ʱ����
    private static final int NUB_0 = 0;
    private static final int NUB_1 = 1;
    private static final int NUB_2 = 2;
    private static final int NUB_3 = 3;
    private static final int NUB_4 = 4;
    private static final int NUB_5 = 5;
    private static final int NUB_6 = 6;
    private static final int NUB_7 = 7;
    private static final int NUB_8 = 8;
    private static final int NUB_9 = 9;
    private static final int NUB_10 = 10;
    private static final int NUB_11 = 11;
    private static final int NUB_12 = 12;
    private static final int NUB_13 = 13;
    private static final int NUB_14 = 14;
    private static final int NUB_15 = 15;
    private static final int NUB_16 = 16;
    private static final int NUB_17 = 17;
    private static final int NUB_18 = 18;
    private static final int NUB_19 = 19;


    /**
     * ���ұ�
     * i=1 o=1 Ϊ    �鿴��Ʒ��ϸ��Ϣ
     * i=1 o=2 Ϊ    �޸�ҳ��
     * i=2 Ϊ    ɾ���������Ʒ��Ϣ
     * i=3 Ϊ    ����������˵���Ʒ
     * i=4 Ϊ    �¼���Ʒ
     * i=5 Ϊ    �޸���Ʒ��Ϣ
     * i=6 Ϊ    ������Ʒ
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        int seller_id = 7;
        String o = request.getParameter("o");
        String i = request.getParameter("i");
        Connection conn = LinkMysql.getCon();
        List<Seller> list = new ArrayList<>();
        try {
            if ("1".equals(i)) { // �鿴���޸���Ʒ��ϸ��Ϣ
                int id = Integer.valueOf(request.getParameter("id"));
                String sql = "select mt.type,m.`name`,m.`describe`,spt.origin,spt.netContent,spt.packingMethod,spt.brand,mth.`month`,spt.qGP,spt.storageMethod,m.money,m.number,m.upFrameTime,rs.time,rs.downtime,rs.id,m.id,spt.id,m.nameTypeID from release_seller rs,merchandise m,merchandise_type mt,specification_table spt,month_table mth where rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id and mth.id=spt.qGP and rs.id=?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(NUB_1, id);
                ps.executeQuery();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    Seller goods = new Seller();
                    goods.setNameType(rs.getString(NUB_1));
                    goods.setName(rs.getString(NUB_2));
                    goods.setDescribe(rs.getString(NUB_3));
                    goods.setOrigin(rs.getString(NUB_4));
                    goods.setNetContent(rs.getString(NUB_5));
                    goods.setPackingMethod(rs.getString(NUB_6));
                    goods.setBrand(rs.getString(NUB_7));
                    goods.setMonth(rs.getString(NUB_8));
                    goods.setqGP(rs.getInt(NUB_9));
                    goods.setStorageMethod(rs.getString(NUB_10));
                    goods.setMoney(rs.getString(NUB_11));
                    goods.setNumber(rs.getInt(NUB_12));
                    goods.setUpFrameTime(rs.getString(NUB_13));
                    goods.setTime(rs.getString(NUB_14));
                    goods.setDowntime(rs.getString(NUB_15));
                    goods.setId(rs.getInt(NUB_16));
                    goods.setWares_id(rs.getInt(NUB_17));
                    goods.setSpeciID(rs.getInt(NUB_18));
                    goods.setNameTypeID(rs.getInt(NUB_19));
                    list.add(goods);
                }
                rs.close();
                ps.close();
                if ("1".equals(o)) { // ����鿴ҳ��
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("seller/detail.jsp").forward(request, response);
                } else if ("2".equals(o)) { // �����޸�ҳ��
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("seller/update.jsp").forward(request, response);
                }
            } else if ("2".equals(i)) { // ɾ���������Ʒ��Ϣ
                int id = Integer.valueOf(request.getParameter("id"));
                int sellers_id = Integer.valueOf(request.getParameter("seller_id"));
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                String time = String.valueOf(request.getParameter("time"));
                String downtime = String.valueOf(request.getParameter("downtime"));
                System.out.println(ConnectTime.getWebsiteDatetime(webUrl4));
                String date = (String)ConnectTime.getWebsiteDatetime(webUrl4);
                String sql = "insert into recovery_seller(rs_id,seller_id,wares_id,audits,`time`,downtime,deleteTime) values(?,?,?,?,?,?,?);";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(NUB_1, id);
                ps.setInt(NUB_2, sellers_id);
                ps.setInt(NUB_3, wares_id);
                ps.setInt(NUB_4, NUB_0);
                if("".equals(time)){
                    ps.setString(NUB_5, null);
                    ps.setString(NUB_6, null);
                }else {
                    ps.setString(NUB_5, time);
                    ps.setString(NUB_6, downtime);
                }
                ps.setString(NUB_7, date);
                ps.executeUpdate();
                ps.close();
                String sql1 = "delete from release_seller where id = ? and wares_id =?;";
                PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
                ps1.setInt(NUB_1, id);
                ps1.setInt(NUB_2, wares_id);
                ps1.executeUpdate();
                ps1.close();
                response.getWriter().write("0");
            } else if ("3".equals(i)) { // ����������˵���Ʒ
                int id = Integer.valueOf(request.getParameter("id"));
                String sql = "update release_seller set audits = ? where id = ?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(NUB_1, NUB_0);
                ps.setInt(NUB_2, id);
                ps.executeUpdate();
                ps.close();
                response.getWriter().write("0");
            } else if ("4".equals(i)) { // �¼�����˵���Ʒ
                int id = Integer.valueOf(request.getParameter("id"));
                System.out.println(ConnectTime.getWebsiteDatetime(webUrl4));
                String date = (String)ConnectTime.getWebsiteDatetime(webUrl4);
                String sql = "update release_seller set downtime=?,audits=? where id=?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(NUB_1, date);
                ps.setInt(NUB_2, NUB_0);
                ps.setInt(NUB_3, id);
                ps.executeUpdate();
                ps.close();
                String sql1 = "select downtime from release_seller where id=?;";
                PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
                ps1.setInt(NUB_1, id);
                ps1.executeQuery();
                ResultSet rs1 = ps1.getResultSet();
                while(rs1.next()){
                    Seller goods = new Seller();
                    goods.setDowntime(rs1.getString(NUB_1));
                    list.add(goods);
                }
                String sst = JsonUtils.beanListToJson(list);
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(sst);
            } else if ("5".equals(i)) { // �޸���Ʒ��Ϣ
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                int spec_id = Integer.valueOf(request.getParameter("speciID"));
                String nameType = request.getParameter("nameType");
                nameType = new String(nameType.getBytes("ISO-8859-1"),"UTF-8");
                String name = request.getParameter("name");
                name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
                String money = request.getParameter("money");
                money = new String(money.getBytes("ISO-8859-1"),"UTF-8");
                String desc = request.getParameter("desc");
                desc = new String(desc.getBytes("ISO-8859-1"),"UTF-8");
                int number = Integer.valueOf(request.getParameter("number"));
                String origin = request.getParameter("origin");
                origin = new String(origin.getBytes("ISO-8859-1"),"UTF-8");
                String netContent = request.getParameter("netContent");
                netContent = new String(netContent.getBytes("ISO-8859-1"),"UTF-8");
                String packingMethod = request.getParameter("packingMethod");
                packingMethod = new String(packingMethod.getBytes("ISO-8859-1"),"UTF-8");
                String brand = request.getParameter("brand");
                brand = new String(brand.getBytes("ISO-8859-1"),"UTF-8");
                int qGP = Integer.valueOf(request.getParameter("qGP"));
                String storageMethod = request.getParameter("storageMethod");
                storageMethod = new String(storageMethod.getBytes("ISO-8859-1"),"UTF-8");
                // ��ȡ�޸ĺ�� ��Ʒ����ID
                String sql = "select id from merchandise_type where type=?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(NUB_1, nameType);
                ps.executeQuery();
                ResultSet rs = ps.getResultSet();
                int newNTID = 0;
                while (rs.next()) {
                    newNTID = rs.getInt(NUB_1);
                }
                rs.close();
                ps.close();
                // ���ݹ��ID�޸� �������
                String sql1 = "update specification_table set origin=?,netContent=?,packingMethod=?,brand=?,qGP=?,storageMethod=? where id=?;";
                PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
                ps1.setString(NUB_1, origin);
                ps1.setString(NUB_2, netContent);
                ps1.setString(NUB_3, packingMethod);
                ps1.setString(NUB_4, brand);
                ps1.setInt(NUB_5, qGP);
                ps1.setString(NUB_6, storageMethod);
                ps1.setInt(NUB_7, spec_id);
                ps1.executeUpdate();
                ps1.close();
                // ������ƷID�޸� ��Ʒ����
                String sql2 = "update merchandise set nameTypeID=?,`name`=?,money=?,`describe`=?,specificationID=?,number=? where id=?;";
                PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
                ps2.setInt(NUB_1, newNTID);
                ps2.setString(NUB_2, name);
                ps2.setString(NUB_3, money);
                ps2.setString(NUB_4, desc);
                ps2.setInt(NUB_5, spec_id);
                ps2.setInt(NUB_6, number);
                ps2.setInt(NUB_7, wares_id);
                ps2.executeUpdate();
                ps2.close();
                //response.getWriter().write("<script>layui.use(['layer', 'laypage', 'element'], function(){layer.msg('�޸ĳɹ�!',{icon: 1});setTimeout('parent.location.href=parent.location.href;','1000');});</script>");
                //response.getWriter().write("window.location.href = 'about:blank ';");
            } else if ("6".equals(i)) { // ������Ʒ
                int select = Integer.valueOf(request.getParameter("interest")); // ��Ʒ����
                String name = request.getParameter("name");
                name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
                String money = request.getParameter("money");
                money = new String(money.getBytes("ISO-8859-1"),"UTF-8");
                String desc = request.getParameter("desc");
                desc = new String(desc.getBytes("ISO-8859-1"),"UTF-8");
                String origin = request.getParameter("origin");
                origin = new String(origin.getBytes("ISO-8859-1"),"UTF-8");
                String netContent = request.getParameter("netContent");
                netContent = new String(netContent.getBytes("ISO-8859-1"),"UTF-8");
                String packingMethod = request.getParameter("packingMethod");
                packingMethod = new String(packingMethod.getBytes("ISO-8859-1"),"UTF-8");
                String brand = request.getParameter("brand");
                brand = new String(brand.getBytes("ISO-8859-1"),"UTF-8");
                int qGP = Integer.valueOf(request.getParameter("qGP"));
                String storageMethod = request.getParameter("storageMethod");
                storageMethod = new String(storageMethod.getBytes("ISO-8859-1"),"UTF-8");
                int number = Integer.valueOf(request.getParameter("number"));
                // ��ȡ����ʱ��
                System.out.println(ConnectTime.getWebsiteDatetime(webUrl4));
                String date = ConnectTime.getWebsiteDatetime(webUrl4);
                /** ���ӹ������� */
                String sql = "insert into specification_table(origin,netContent,packingMethod,brand,qGP,storageMethod) values(?,?,?,?,?,?);";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setString(NUB_1, origin);
                ps.setString(NUB_2, netContent);
                ps.setString(NUB_3, packingMethod);
                ps.setString(NUB_4, brand);
                ps.setInt(NUB_5, qGP);
                ps.setString(NUB_6, storageMethod);
                ps.executeUpdate();
                ps.close();
                /** ��ȡ����ID */
                String sql1 = "select max(id) from specification_table;";
                PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
                ps1.executeQuery();
                ResultSet rs1 = ps1.getResultSet();
                int s_tb = 0;
                while (rs1.next()) {
                    s_tb = rs1.getInt(1);
                }
                rs1.close();
                ps1.close();
                /** ������Ʒ������ */
                String sql2 = "insert into merchandise(nameTypeID,`name`,money,`describe`,specificationID,number,upFrameTime) values(?,?,?,?,?,?,?);";
                PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
                ps2.setInt(NUB_1, select);
                ps2.setString(NUB_2, name);
                ps2.setString(NUB_3, money);
                ps2.setString(NUB_4, desc);
                ps2.setInt(NUB_5, s_tb);
                ps2.setInt(NUB_6, number);
                ps2.setString(NUB_7, date);
                ps2.executeUpdate();
                ps2.close();
                /** ��ѯ��ƷID */
                String sql3 = "select m.id from merchandise m,specification_table stb where m.specificationID=stb.id and stb.id=?;";
                PreparedStatement ps3 = (PreparedStatement) conn.prepareStatement(sql3);
                ps3.setInt(NUB_1, s_tb);
                ps3.executeQuery();
                ResultSet rs3 = ps3.getResultSet();
                int m_id = 0;
                while (rs3.next()) {
                    m_id = rs3.getInt(NUB_1);
                }
                rs3.close();
                ps3.close();
                /** ���ӷ��������� */
                String sql4 = "insert into release_seller(seller_id,wares_id,time,downtime,audits) values(?,?,?,?,?);";
                PreparedStatement ps4 = (PreparedStatement) conn.prepareStatement(sql4);
                ps4.setInt(NUB_1, seller_id);
                ps4.setInt(NUB_2, m_id);
                ps4.setString(NUB_3, null);
                ps4.setString(NUB_4, null);
                ps4.setInt(NUB_5, NUB_0);
                ps4.executeUpdate();
                ps4.close();
                response.sendRedirect("seller/user.jsp");
            } else if ("7".equals(i)) {//�����ϼ�,�������
                
            } else if ("8".equals(i)) {//�����ϼ�,��˳ɹ�
                
            } else if ("9".equals(i)) {
                
            } else {
                response.sendRedirect("seller/user.jsp");
            }
            conn.close();
        } catch (Exception e) {
            response.getWriter().write("1");
            e.printStackTrace();
        }
    }

    /**
     * ��ѯ���ұ���������
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        //ҳ����ʾֵ
        PrintWriter out = response.getWriter();
        String type= request.getParameter("type");
        String select= request.getParameter("select");//����������������ֵ(�˺�)
        String pageNow = request.getParameter("pageNow");//���ҳ�洫�����ĵ�ǰҳ
        if (null != type && type.equals("list")) {
            String sel = request.getParameter("select");
            if (null == pageNow || "".equals(pageNow)) {
                pageNow = "1";
            }
         // ��ѯ����ҳ
            List<Seller> list = SellerDao.selectGoods(select,Integer.valueOf(pageNow),sel);
         // �����ҳ��
            int pageCount=SellerDao.getPageCount();
         // ��ʾ��ǰҳ
            String pageCode = new SellerPage().getPageCode(Integer.parseInt(pageNow), pageCount);
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
    }
}