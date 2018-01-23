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

import com.yr.dao.AuditSellerDao;
import com.yr.dao.LinkMysql;
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;
import com.yr.util.SellerPage;

import net.sf.json.JSONObject;

/**
 * 
 * @作者 林水桥 2018年1月2日下午3:42:59
 */
@WebServlet(urlPatterns = "/auditSellerServlet")
public class AuditSellerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
     * @see HttpServlet#HttpServlet()
     */
    public AuditSellerServlet() {
        super();
    }

    /** 卖家审核表
     *  i=1 为        允许上架
     *  i=2 为        禁止上架
     *  i=3 o=1为        修改查看
     *  i=3 0=2为        查看商品详细信息
     *  i=4 为        修改数据
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        String i = request.getParameter("i");
        Connection conn = LinkMysql.getCon();
        List<Seller> list = new ArrayList<>();
        try{
            if("1".equalsIgnoreCase(i)){// 允许上架
                int id = Integer.valueOf(request.getParameter("id"));
                int auditID = Integer.valueOf(request.getParameter("auditID"));
                int seller_id = Integer.valueOf(request.getParameter("seller_id"));
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                String date = ConnectTime.getWebsiteDatetime();
                String sql2 = "insert into `release`(account_id,wares_id,time) values(?,?,?);";
                PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
                ps2.setInt(NUB_1, seller_id);
                ps2.setInt(NUB_2, wares_id);
                ps2.setString(NUB_3, date);
                ps2.executeUpdate();
                ps2.close();
                String sql = "update seller rs set rs.audits=?,rs.time=? where id=?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(NUB_1,NUB_2);
                ps.setString(NUB_2,date);
                ps.setInt(NUB_3,id);
                ps.executeUpdate();
                ps.close();
                String sql1 = "delete from auditSeller where id = ?;";
                PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
                ps1.setInt(NUB_1,auditID);
                ps1.executeUpdate();
                ps1.close();
                response.getWriter().write("0");
            }else if("2".equalsIgnoreCase(i)){// 禁止上架
                int id = Integer.valueOf(request.getParameter("id"));
                int auditID = Integer.valueOf(request.getParameter("auditID"));
                String sql = "update seller rs set rs.audits=? where id=?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(NUB_1,NUB_0);
                ps.setInt(NUB_2,id);
                ps.executeUpdate();
                ps.close();
                String sql1 = "delete from auditSeller where id = ?;";
                PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
                ps1.setInt(NUB_1,auditID);
                ps1.executeUpdate();
                ps1.close();
                response.getWriter().write("0");
            }else if("3".equalsIgnoreCase(i)){// 修改查看 和 查看详细信息
                String o = request.getParameter("o");
                int auditID = Integer.valueOf(request.getParameter("auditID"));
                String sql = "select ads.id,ads.release_id,ads.merchandise_id,ads.account_id,spt.id,a.`name`,mt.type,m.`name`,m.`describe`,spt.origin,spt.netContent,spt.packingMethod,spt.brand,spt.qGP,mth.`month`,spt.storageMethod,m.money,m.number,ads.addTime from seller rs,merchandise m,merchandise_type mt,specification_table spt,month_table mth,auditseller ads,account a where a.id=ads.account_id and a.id=rs.seller_id and ads.release_id=rs.id and ads.merchandise_id=rs.wares_id and ads.account_id=rs.seller_id and rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id and mth.id=spt.qGP and ads.id=?;";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.setInt(NUB_1,auditID);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Seller goods = new Seller();
                    goods.setAuditID(rs.getInt(NUB_1));
                    goods.setId(rs.getInt(NUB_2));
                    goods.setWares_id(rs.getInt(NUB_3));
                    goods.setSeller_id(rs.getInt(NUB_4));
                    goods.setSpeciID(rs.getInt(NUB_5));
                    goods.setAuditName(rs.getString(NUB_6));
                    goods.setNameType(rs.getString(NUB_7));
                    goods.setName(rs.getString(NUB_8));
                    goods.setDescribe(rs.getString(NUB_9));
                    goods.setOrigin(rs.getString(NUB_10));
                    goods.setNetContent(rs.getString(NUB_11));
                    goods.setPackingMethod(rs.getString(NUB_12));
                    goods.setBrand(rs.getString(NUB_13));
                    goods.setqGP(rs.getInt(NUB_14));
                    goods.setMonth(rs.getString(NUB_15));
                    goods.setStorageMethod(rs.getString(NUB_16));
                    goods.setMoney(rs.getString(NUB_17));
                    goods.setNumber(rs.getInt(NUB_18));
                    goods.setAddTime(rs.getString(NUB_19));
                    list.add(goods);
                }
                rs.close();
                ps.close();
                request.setAttribute("list",list);
                if("1".equalsIgnoreCase(o)){// 修改查看
                    request.getRequestDispatcher("auditSeller/update.jsp").forward(request,response);
                }else if("2".equalsIgnoreCase(o)){// 查看详细信息
                    request.getRequestDispatcher("auditSeller/detail.jsp").forward(request,response);
                }
            }else if("4".equalsIgnoreCase(i)){// 修改数据
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                int spec_id = Integer.valueOf(request.getParameter("speciID"));
                String nameType = request.getParameter("nameType");
                nameType = new String(nameType.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String name = request.getParameter("name");
                name = new String(name.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String money = request.getParameter("money");
                money = new String(money.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String desc = request.getParameter("desc");
                desc = new String(desc.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                int number = Integer.valueOf(request.getParameter("number"));
                String origin = request.getParameter("origin");
                origin = new String(origin.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String netContent = request.getParameter("netContent");
                netContent = new String(netContent.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String packingMethod = request.getParameter("packingMethod");
                packingMethod = new String(packingMethod.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String brand = request.getParameter("brand");
                brand = new String(brand.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                int qGP = Integer.valueOf(request.getParameter("qGP"));
                String storageMethod = request.getParameter("storageMethod");
                storageMethod = new String(storageMethod.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                // 获取修改后的 商品类型ID
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
                // 根据规格ID修改 规格数据
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
                // 根据商品ID修改 商品数据
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
                response.getWriter().write("1");
            }
        }catch(Exception e){
            response.getWriter().write("1");
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");

        //页面显示值
        PrintWriter out = response.getWriter();
        String type= request.getParameter("type");
        String select= request.getParameter("select");//搜索功能中输入框的值(账号)
        String pageNow = request.getParameter("pageNow");//获得页面传过来的当前页

        if (null != type && type.equals("list")) {
            String sel = request.getParameter("select");
            if (null == pageNow || "".equals(pageNow)) {
                pageNow = "1";
            }
            // 查询并分页
            List<Seller> list = AuditSellerDao.selectGoods(select,Integer.valueOf(pageNow),sel);
            // 获得总页数
            int pageCount=AuditSellerDao.getPageCount();
            // 显示当前页
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
