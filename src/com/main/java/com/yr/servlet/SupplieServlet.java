package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SupplieDao;
import com.yr.pojo.Supplie;
import com.yr.util.ConnectTime;
import com.yr.util.SuppliePage;

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
        if("0".equals(state)){ // 商品状态  0 为未提交 , 1为 未审核 , 2 为 已审核
            if("1".equals(sup)){
                //查询
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
                    List<Supplie>  list = SupplieDao.selectemp(Integer.valueOf(pageNow),select);
                    int pageCount=SupplieDao.getPageCount();//获得总页数
                    String pageCode = new SuppliePage().getPageCode(Integer.parseInt(pageNow), pageCount);
                    Map<String, Object> map = new HashMap<>();
                    map.put("list", list);
                    map.put("pageCount", pageCount + "");
                    map.put("pageNow", pageNow);
                    map.put("pageCode", pageCode);
                    String jsonObjectStr = JSONObject.fromObject(map).toString();
                    out.write(jsonObjectStr);
                    out.flush();
                }
            }else if ("2".equals(sup)) { // 添加
                try {
                    req.setCharacterEncoding("UTF-8");
                    String nameType = req.getParameter("merType"); // 商品类型
                    nameType = new String(nameType.getBytes("ISO-8859-1"),"UTF-8");
                    String name = req.getParameter("commodity"); // 商品名
                    name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
                    String money = req.getParameter("money"); // 商品价格
                    String number = req.getParameter("number"); // 商品数量
                    String origin = req.getParameter("origin"); // 商品产地
                    origin = new String(origin.getBytes("ISO-8859-1"),"UTF-8");
                    String netContent = req.getParameter("netContent"); // 商品净含量
                    String packingMethod = req.getParameter("packingMethod"); // 商品包装
                    packingMethod = new String(packingMethod.getBytes("ISO-8859-1"),"UTF-8");
                    String brand = req.getParameter("brand"); // 商品品牌
                    brand = new String(brand.getBytes("ISO-8859-1"),"UTF-8");
                    String qGp = req.getParameter("qGp"); // 商品保质期
                    qGp = new String(qGp.getBytes("ISO-8859-1"),"UTF-8");
                    String storageMethod = req.getParameter("storageMethod"); // 商品储藏方法
                    storageMethod = new String(storageMethod.getBytes("ISO-8859-1"),"UTF-8");
                    String describe = req.getParameter("describe"); // 商品描述
                    describe = new String(describe.getBytes("ISO-8859-1"),"UTF-8");
                    int auditStatus = Integer.valueOf(req.getParameter("state")); // 商品状态
                    int account_id = (int)req.getSession().getAttribute("userID"); // 获取当前登录账号                    
                    
                    /**
                     * 获取网络时间
                     */
                    System.out.println(ConnectTime.getWebsiteDatetime());
                    String date = ConnectTime.getWebsiteDatetime();
                    
                    Integer speId  = SupplieDao.speciAdd(origin,netContent,packingMethod,brand,qGp,storageMethod); // 给规格字段添信息(可以获取到刚插入的规格字段id)
                    System.out.println("规格表id " + speId);
                    Integer merId = SupplieDao.merchandiseAdd(account_id,nameType, name, money, speId, number,auditStatus,describe,date); // 添加商品信息
                    System.out.println("商品表id " + merId);
                    Integer suppId = SupplieDao.suppAdd(name,merId); // 添加供应商商品信息(商品id)
                    System.out.println("供应商表id " + suppId);
                    SupplieDao.auditsupplier(suppId,account_id,merId,date); // 添加供应商发布信息
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("3".equals(sup)) { // 撤销审核中的商品
                try {
                    String release_supplierId = req.getParameter("release_supplierId");
                    String merId = req.getParameter("merId");
                    int account_id = (int)req.getSession().getAttribute("userID"); // 获取
                    SupplieDao.cencel(Integer.valueOf(release_supplierId), Integer.valueOf(account_id), Integer.valueOf(merId));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if ("4".equals(sup)) { // 修改
                try {
                    String speId = req.getParameter("speId"); // 规格id
                    String merId = req.getParameter("merId"); // 商品id
                    String money = req.getParameter("money"); // 价格
                    String number = req.getParameter("number"); // 数量
                    String netContent = req.getParameter("netContent"); // 净含量
                    String packingMethod = req.getParameter("packingMethod"); // 包装
                    String qGp = req.getParameter("qGp"); // 保质期
                    String describe = req.getParameter("describe");
                    describe = new String(describe.getBytes("ISO-8859-1"),"UTF-8");
                    SupplieDao.speUpd(netContent,packingMethod,qGp,speId);
                    SupplieDao.merchandiseUpd(money,number,describe,merId); //商品信息的修改
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("5".equals(sup)) { // 查询所有(在修改中使用到)
                req.setCharacterEncoding("utf-8");
                try {
                    int id = Integer.valueOf(req.getParameter("merId"));
                    List<Supplie> list = SupplieDao.queryAll(id); // 锟斤拷询
                    req.setAttribute("list", list);
                    req.getRequestDispatcher("supplie/upd.jsp").forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("6".equals(sup)){ // 下架
                try {
                	resp.setContentType("application/json; charset=utf-8");
                    String release_id = req.getParameter("release_id");
                    System.out.println(ConnectTime.getWebsiteDatetime());
                    String date = (String)ConnectTime.getWebsiteDatetime();
                    String so = SupplieDao.xiajia(date,release_id);
                    resp.getWriter().write(so);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if ("7".equals(sup)){ // 审核
                try {
					List<Supplie> list = SupplieDao.selc();
					req.setAttribute("list", list);
					req.getRequestDispatcher("auditSupplie/detail.jsp").forward(req,resp);;
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }else if("8".equals(sup)){ // 上架审核中
                try {
                	resp.setContentType("application/json; charset=utf-8");
                	int sup_id = Integer.valueOf(req.getParameter("sup_id"));
                	int reles_id = Integer.valueOf(req.getParameter("reles_id"));
                	int account_id = (int)req.getSession().getAttribute("userID");
                	/**
                	 * 获取网络时间
                	 */
                	System.out.println(ConnectTime.getWebsiteDatetime());
                	String upforamTime = ConnectTime.getWebsiteDatetime();
					SupplieDao.shanjia(sup_id,reles_id,account_id,upforamTime);
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
