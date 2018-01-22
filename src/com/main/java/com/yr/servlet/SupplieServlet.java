package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SuperAdminDao;
import com.yr.dao.SupplieDao;
import com.yr.pojo.Account_Role;
import com.yr.pojo.Seller;
import com.yr.pojo.Supplie;
import com.yr.util.ConnectTime;
import com.yr.util.JsonUtils;
import com.yr.util.PageService;

import net.sf.json.JSONObject;

/**
 * 
 * 2017
 * @author zxy
 * Administrator
 * 2017锟斤拷12锟斤拷28锟斤拷 锟斤拷锟斤拷8:34:54
 */
public class SupplieServlet extends HttpServlet {
    /**
     * 锟斤拷取锟斤拷锟斤拷时锟斤拷.
     */
   
    
    /**
     * 锟斤拷应锟斤拷 锟斤拷删锟侥诧拷.
     *
     * @author zxy
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * 2017锟斤拷12锟斤拷13锟斤拷 锟斤拷锟斤拷9:02:08
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        String sup = req.getParameter("sup"); // 页锟芥传锟斤拷锟斤拷锟斤拷值(锟斤拷锟斤拷锟叫讹拷执锟斤拷锟斤拷一锟斤拷)
        String state = req.getParameter("state"); // 锟斤拷应锟斤拷锟剿猴拷使锟斤拷状态
        if("0".equals(state)){ // 锟斤拷锟绞癸拷锟阶刺� 0 锟斤拷锟斤拷锟剿猴拷未注锟斤拷锟斤拷锟皆斤拷锟斤拷锟斤拷删锟侥诧拷
            if("1".equals(sup)){
                //页锟斤拷锟斤拷示值
                PrintWriter out = resp.getWriter();
                String type= req.getParameter("type");
                String select= req.getParameter("select");//搜索功能中输入框的值(账号)
                select = new String(select.getBytes("ISO-8859-1"),"UTF-8"); // 转码
                String interest= req.getParameter("interest");//搜索功能中下拉框的值(角色)
                String pageNow = req.getParameter("pageNow");//获得页面传过来的当前页
                if (null != type && type.equals("list")) {
                    String sel = req.getParameter("select");
                    if (null == pageNow || "".equals(pageNow)) {
                        pageNow = "1";
                    }
                    List<Supplie> list = SupplieDao.selectemp(Integer.valueOf(pageNow),select);
                    int pageCount=SupplieDao.getPageCount();//获得总页数
                    String pageCode = new PageService().getPageCode(Integer.parseInt(pageNow), pageCount);
                    Map<String, Object> map = new HashMap<>();
                    map.put("list", list);
                    map.put("pageCount", pageCount + "");
                    map.put("pageNow", pageNow);
                    map.put("pageCode", pageCode);
                    String jsonObjectStr = JSONObject.fromObject(map).toString();
                    out.write(jsonObjectStr);
                    out.flush();
                }
            }else if ("2".equals(sup)) { // 锟斤拷锟斤拷锟狡�
                try {
                    req.setCharacterEncoding("UTF-8");
                    String nameType = req.getParameter("merType"); // 商品类型
                    String name = req.getParameter("commodity"); // 商品名
                    String money = req.getParameter("money"); // 商品价格
                    String specificationID = req.getParameter("specificationID"); // 商品规格Id
                    String number = req.getParameter("number"); // 商品数量
                    String origin = req.getParameter("origin"); // 商品产地
                    String netContent = req.getParameter("netContent"); // 商品净含量
                    String packingMethod = req.getParameter("packingMethod"); // 商品包装
                    String brand = req.getParameter("brand"); // 商品品牌
                    String qGp = req.getParameter("qGp"); // 商品保质期
                    String storageMethod = req.getParameter("storageMethod"); // 商品储藏方法
                    String merId = req.getParameter("merId"); // 商品ID
                    String describe = req.getParameter("describe"); // 商品描述
                    
                    /**
                     * 锟斤拷取锟斤拷锟斤拷时锟斤拷
                     */
                    System.out.println(ConnectTime.getWebsiteDatetime());
                    String date = ConnectTime.getWebsiteDatetime();
                    
                    SupplieDao.speciAdd(origin,netContent,packingMethod,brand,qGp,storageMethod); // 给规格字段添信息
                    SupplieDao.merchandiseAdd(nameType, name, money, specificationID, number, date); // 添加商品信息
                    SupplieDao.suppAdd(name,merId); // 添加供应商商品信息
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("3".equals(sup)) { // 锟斤拷锟斤拷锟斤拷品
                try {
                    String id = req.getParameter("id");
                    System.out.println(ConnectTime.getWebsiteDatetime());
                    String date = (String)ConnectTime.getWebsiteDatetime();
                    SupplieDao.cencel(date, id);
                    resp.getWriter().write("0");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if ("4".equals(sup)) { // 锟睫革拷 锟斤拷品锟斤拷息
                try {
                    String id = req.getParameter("id"); // 锟斤拷应锟斤拷Id
                    String merId = req.getParameter("merId"); // 锟斤拷品ID  
                    String nameType = req.getParameter("merType"); //锟斤拷品锟斤拷锟斤拷
                    String name = req.getParameter("commodity"); // 锟斤拷品锟斤拷/锟斤拷应锟斤拷锟斤拷品锟斤拷
                    String money = req.getParameter("money"); // 锟斤拷品锟桔革拷
                    String origin = req.getParameter("origin"); // 锟斤拷品锟斤拷锟斤拷
                    String netContent = req.getParameter("netContent"); // 锟斤拷品锟斤拷锟斤拷锟斤拷
                    String packingMethod = req.getParameter("packingMethod"); // 锟斤拷品锟斤拷装
                    String brand = req.getParameter("brand"); // 锟斤拷品品锟斤拷
                    String qGp = req.getParameter("qGp"); // 锟斤拷品锟斤拷锟斤拷锟斤拷
                    String storageMethod = req.getParameter("storageMethod"); // 锟斤拷品锟斤拷锟截凤拷锟斤拷
                    String number = req.getParameter("number"); // 锟斤拷品锟斤拷锟斤拷
                    String specificationID = req.getParameter("specificationID"); // 锟斤拷品锟斤拷锟絀d
                    String spe = SupplieDao.speUpd(origin,netContent,packingMethod,brand,qGp,storageMethod,specificationID);
                    String supp = SupplieDao.merchandiseUpd(name,money,number,nameType,specificationID,merId); //商品信息的修改
                    SupplieDao.suppUpd(name,merId); // 供应商ID
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("jump.jsp").forward(req, resp); //转锟斤拷
            } else if ("5".equals(sup)) { // 锟斤拷询锟斤拷应锟斤拷锟斤拷品
                resp.setCharacterEncoding("utf-8");
                try { // state 0状态锟斤拷使锟矫碉拷锟剿猴拷,1状态锟斤拷锟斤拷停锟斤拷锟剿猴拷
                    String strJson = SupplieDao.suppSel(); // 锟斤拷询
                    resp.getWriter().write(strJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("6".equals(sup)){ // 锟铰硷拷锟斤拷品
                try {
                    String id = req.getParameter("id");
                    System.out.println(ConnectTime.getWebsiteDatetime());
                    String date = (String)ConnectTime.getWebsiteDatetime();
                    String so = SupplieDao.xiajia(date,id);
                    resp.setContentType("application/json; charset=utf-8");
                    resp.getWriter().write(so);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 锟叫讹拷Id锟角凤拷锟截革拷
     * 
     * @author zxy
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * 2017锟斤拷12锟斤拷13锟斤拷 锟斤拷锟斤拷10:35:08
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        String strId = req.getParameter("id"); // 锟斤拷取页锟芥传锟斤拷锟斤拷值
        int id = SupplieDao.exsisId(strId);
        resp.getWriter().write(id);
    }

}
