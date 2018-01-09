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
 * 2017年12月28日 下午8:34:54
 */
public class SupplieServlet extends HttpServlet {
    /**
     * 获取网络时间.
     */
    public static final String webUrl4 = "http://www.ntsc.ac.cn"; // 中国科学院国家授时中心
    /**
     * 供应商 增删改查.
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
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String sup = req.getParameter("sup"); // 页面传过来的值(用来判断执行哪一步)
        String state = req.getParameter("state"); // 供应商账号使用状态
        if("0".equals(state)){ // 如果使用状态为 0 代表账号未注销可以进入增删改查
            if("1".equals(sup)){
                //页面显示值
                PrintWriter out = resp.getWriter();
                String type= req.getParameter("type");
                String select= req.getParameter("select");//搜索功能中输入框的值(账号)
                String interest= req.getParameter("interest");//搜索功能中下拉框的值(角色)
                String pageNow = req.getParameter("pageNow");//获得页面传过来的当前页
                if (null != type && type.equals("list")) {
                    String sel = req.getParameter("select");
                    if (null == pageNow || "".equals(pageNow)) {
                        pageNow = "1";
                    }
                    List<Supplie> list = SupplieDao.selectemp(Integer.valueOf(pageNow));
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
            }else if ("2".equals(sup)) { // 添加商品
                try {
                    req.setCharacterEncoding("UTF-8");
                    String name = req.getParameter("commodity"); // 商品名
                    String nameType = req.getParameter("merType"); // 商品类型
                    String money = req.getParameter("money"); // 商品价格
                    String describe = req.getParameter("describe"); // 商品描述   ------- 没用
                    String origin = req.getParameter("origin"); // 商品产地
                    String netContent = req.getParameter("netContent"); // 商品净含量
                    String packingMethod = req.getParameter("packingMethod"); // 商品包装
                    String brand = req.getParameter("brand"); // 商品品牌
                    String qGp = req.getParameter("qGp"); // 商品保质期
                    String storageMethod = req.getParameter("storageMethod"); // 商品储藏方法
                    String number = req.getParameter("number"); // 商品数量
                    String specificationID = req.getParameter("specificationID"); // 商品规格Id
                    String suptID = req.getParameter("suptID"); // 供应商规格字段Id
                    String merId = req.getParameter("merId"); // 商品ID
                    
                    /**
                     * 获取网络时间
                     */
                    System.out.println(SupplieDao.getWebsiteDatetime(webUrl4));
                    String date = SupplieDao.getWebsiteDatetime(webUrl4);
                    
                    SupplieDao.speciAdd(origin,netContent,packingMethod,brand,qGp,storageMethod); // 给规格字段添信息
                    SupplieDao.merchandiseAdd(nameType, name, money, specificationID, number, date); // 添加商品信息
                    SupplieDao.suppAdd(name,merId,merId); // 添加供应商商品信息
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("3".equals(sup)) { // 撤销商品
                try {
                    String id = req.getParameter("id");
                    System.out.println(ConnectTime.getWebsiteDatetime(webUrl4));
                    String date = (String)ConnectTime.getWebsiteDatetime(webUrl4);
                    SupplieDao.cencel(date, id);
                    resp.getWriter().write("0");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if ("4".equals(sup)) { // 修改 商品信息
                try {
                    String id = req.getParameter("id"); // 供应商Id
                    String merId = req.getParameter("merId"); // 商品ID  
                    String nameType = req.getParameter("merType"); //商品类型
                    String name = req.getParameter("commodity"); // 商品名/供应商商品名
                    String money = req.getParameter("money"); // 商品价格
                    String origin = req.getParameter("origin"); // 商品产地
                    String netContent = req.getParameter("netContent"); // 商品净含量
                    String packingMethod = req.getParameter("packingMethod"); // 商品包装
                    String brand = req.getParameter("brand"); // 商品品牌
                    String qGp = req.getParameter("qGp"); // 商品保质期
                    String storageMethod = req.getParameter("storageMethod"); // 商品储藏方法
                    String number = req.getParameter("number"); // 商品数量
                    String specificationID = req.getParameter("specificationID"); // 商品规格Id
                    String spe = SupplieDao.speUpd(origin,netContent,packingMethod,brand,qGp,storageMethod,specificationID);
                    String supp = SupplieDao.merchandiseUpd(name,money,number,nameType,specificationID,id); //商品信息的修改
                    SupplieDao.suppUpd(name,id); // 供应商ID
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("jump.jsp").forward(req, resp); //转发
            } else if ("5".equals(sup)) { // 查询供应商商品
                resp.setCharacterEncoding("utf-8");
                try { // state 0状态在使用的账号,1状态是已停用账号
                    String strJson = SupplieDao.suppSel(); // 查询
                    resp.getWriter().write(strJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("6".equals(sup)){ // 下架商品
                try {
                    String id = req.getParameter("id");
                    System.out.println(ConnectTime.getWebsiteDatetime(webUrl4));
                    String date = (String)ConnectTime.getWebsiteDatetime(webUrl4);
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
        String strId = req.getParameter("id"); // 获取页面传来的值
        int id = SupplieDao.exsisId(strId);
        resp.getWriter().write(id);
    }

}
