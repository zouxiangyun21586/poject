package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;
import com.yr.util.SellerPage;
import com.yr.util.SendMail;

import net.sf.json.JSONObject;

/**
 * 
 * @作者 林水桥 2018年1月2日下午3:42:59
 */
@WebServlet(urlPatterns = "/auditSellerServlet")
public class AuditSellerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
     *  i=3 o=2为        查看商品详细信息
     *  i=4 为        修改数据
     *  i=5 o=1 为        禁止提醒
     *  i=5 0=2 为       上架提醒
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        String i = request.getParameter("i");
        try{
            if("1".equalsIgnoreCase(i)){// 允许上架
                int id = Integer.valueOf(request.getParameter("id"));
                int auditID = Integer.valueOf(request.getParameter("auditID"));
                int seller_id = Integer.valueOf(request.getParameter("seller_id"));
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                String date = ConnectTime.getWebsiteDatetime();
                String remind = request.getParameter("remind");
                String email = SendMail.Email(wares_id);
                SendMail.sendMail(email, "成功上架."+remind);
                AuditSellerDao.passAudit(id, auditID, seller_id, wares_id, date);
                response.getWriter().write("0");
            }else if("2".equalsIgnoreCase(i)){// 禁止上架
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                int auditID = Integer.valueOf(request.getParameter("auditID"));
                String remind = request.getParameter("remind");
                String email = SendMail.Email(wares_id);
                SendMail.sendMail(email, "禁止上架."+remind);
                AuditSellerDao.NoneAudit(wares_id, auditID);
                response.getWriter().write("0");
            }else if("3".equalsIgnoreCase(i)){// 修改查看 和 查看详细信息
                String o = request.getParameter("o");
                int auditID = Integer.valueOf(request.getParameter("auditID"));
                List<Seller> list = AuditSellerDao.queryAudit(auditID);
                request.setAttribute("list",list);
                if("1".equalsIgnoreCase(o)){// 修改查看
                    request.getRequestDispatcher("auditSeller/update.jsp").forward(request,response);
                }else if("2".equalsIgnoreCase(o)){// 查看详细信息
                    request.getRequestDispatcher("auditSeller/detail.jsp").forward(request,response);
                }
            }else if("4".equalsIgnoreCase(i)){// 修改数据
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                int spec_id = Integer.valueOf(request.getParameter("speciID"));
                Integer nameTypeID = Integer.valueOf(request.getParameter("nameTypeID"));
                String name = request.getParameter("name");
                name = new String(name.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                Integer money = Integer.valueOf(request.getParameter("money"));
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
                AuditSellerDao.updateAudit(wares_id, spec_id, nameTypeID, name, money, desc, number, origin, netContent, packingMethod, brand, qGP, storageMethod);
                response.getWriter().write("0");
            }else if("5".equalsIgnoreCase(i)){
                String o = request.getParameter("o");
                int id = Integer.valueOf(request.getParameter("id"));
                int auditID = Integer.valueOf(request.getParameter("auditID"));
                int seller_id = Integer.valueOf(request.getParameter("seller_id"));
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                List<Seller> list = new ArrayList<>();
                Seller seller = new Seller();
                seller.setId(id);
                seller.setAuditID(auditID);
                seller.setSeller_id(seller_id);
                seller.setWares_id(wares_id);
                list.add(seller);
                request.setAttribute("list",list);
                if("1".equalsIgnoreCase(o)){
                    request.getRequestDispatcher("auditSeller/prohibit.jsp").forward(request,response);
                }else if("2".equalsIgnoreCase(o)){
                    request.getRequestDispatcher("auditSeller/pass.jsp").forward(request,response);
                }
                
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
            if(null != select && !"".equals(select)){
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
