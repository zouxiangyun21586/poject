package com.yr.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SupplieDao;

public class SupplieServlet extends HttpServlet {
    public static final String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
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
        String sup = req.getParameter("sup"); // 页面传过来的值(用来判断执行哪一步)
        String state = req.getParameter("state"); // 供应商账号使用状态
        if("0".equals(state)){ // 如果使用状态为 0 代表账号未注销可以进入增删改查
            if ("2".equals(sup)) { // 添加商品
                try {
                    req.setCharacterEncoding("UTF-8");
                    String name = req.getParameter("commodity"); // 商品名
                    String nameType = req.getParameter("merType"); // 商品类型
                    String money = req.getParameter("money"); // 商品价格
                    String describe = req.getParameter("describe"); // 商品描述
                    String origin = req.getParameter("origin"); // 商品产地
                    String netContent = req.getParameter("netContent"); // 商品净含量
                    String packingMethod = req.getParameter("packingMethod"); // 商品包装
                    String brand = req.getParameter("brand"); // 商品品牌
                    String qGp = req.getParameter("qGp"); // 商品保质期
                    String storageMethod = req.getParameter("storageMethod"); // 商品储藏方法
                    String number = req.getParameter("number"); // 商品数量
                    String specificationID = req.getParameter("specificationID"); // 商品规格Id
                    String suptID = req.getParameter("suptID"); // 供应商规格字段Id
                    
                    /**
                     * 获取网络时间
                     */
                    System.out.println(SupplieDao.getWebsiteDatetime(webUrl4));
                    String date = SupplieDao.getWebsiteDatetime(webUrl4);
                    
                    SupplieDao.speciAdd(origin,netContent,packingMethod,brand,qGp,storageMethod); // 给规格字段添信息
                    SupplieDao.merchandiseAdd(nameType, name, money, describe, number, date); // 添加商品信息
                    SupplieDao.suppAdd(name,specificationID,suptID); // 添加供应商商品信息
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("3".equals(sup)) { // 删除(根据ID删除商品)
                try {
                    String strId = req.getParameter("supDel"); // 页面传过来的值
                    SupplieDao.suppDel(strId); // 删除方法
                    SupplieDao.suppSel(); // 查询方法
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("jump.jsp").forward(req, resp);
            } else if ("4".equals(sup)) { // 修改 商品信息
                try {
                    String id = req.getParameter("id"); // 供应商Id
                    String commodity = req.getParameter("commodity"); // 供应商的商品信息
                    String merId = req.getParameter("merId"); // 商品ID  
                    String nameType = req.getParameter("merType"); //商品类型
                    String name = req.getParameter("commodity"); // 商品名
                    String money = req.getParameter("money"); // 商品价格
                    String describe = req.getParameter("describe"); // 商品描述
                    String origin = req.getParameter("origin"); // 商品产地
                    String netContent = req.getParameter("netContent"); // 商品净含量
                    String packingMethod = req.getParameter("packingMethod"); // 商品包装
                    String brand = req.getParameter("brand"); // 商品品牌
                    String qGp = req.getParameter("qGp"); // 商品保质期
                    String storageMethod = req.getParameter("storageMethod"); // 商品储藏方法
                    String number = req.getParameter("number"); // 商品数量
                    String specificationID = req.getParameter("specificationID"); // 商品规格Id
                    String suptID = req.getParameter("suptID"); // 供应商规格字段Id
                    String supp = SupplieDao.merchandiseUpd(merId,nameType,name,money,describe,origin,netContent,packingMethod,brand,qGp,storageMethod,number,specificationID,suptID); //商品信息修改
                    SupplieDao.suppUpd(commodity,id); // 供应商ID
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
            }
        }else{ // 如果state状态不为 0 那么就代表此供应商账号已注销(不能使用)
            req.setAttribute("state", state);
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
