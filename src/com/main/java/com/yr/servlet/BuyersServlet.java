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

import com.yr.dao.BuyersDao;
import com.yr.dao.LinkMysql;
import com.yr.dao.SellerDao;
import com.yr.pojo.Buyers;
import com.yr.pojo.Order;
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;
import com.yr.util.JsonUtils;
import com.yr.util.PageService;

import net.sf.json.JSONObject;
/**
 * 买家业务逻辑
 * @author 周业好
 * 2018年1月23日 上午10:55:09
 */
public class BuyersServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    /**
     * 卖家表
     * i=1 o=1 为    查看商品详细信息
     * i=1 o=2 为    修改页面
     * i=2 为    删除保存的商品信息
     * i=3 为    撤销正在审核的商品
     * i=4 为    下架商品
     * i=5 为    修改商品信息
     * i=6 为    添加商品
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8"); // 转码 防止中文乱码
        int seller_id = (int)request.getSession().getAttribute("userID");
        String o = request.getParameter("o");
        String i = request.getParameter("i");
        try {
            if ("1".equals(i)) { // 查看或修改商品详细信息
            	int id = Integer.valueOf(request.getParameter("id"));
                List<Buyers> list = BuyersDao.queryGoods(id);
                if ("1".equals(o)) { // 进入查看页面
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("buyer/user.jsp").forward(request, response);
                }
            }else if("2".equals(i)){
            	
            }
        } catch (Exception e) {
            response.getWriter().write("1");
            e.printStackTrace();
        }
    }

    /**
     * 查询卖家表所有数据
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        String i = request.getParameter("i");
        if("1".equals(i)){
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
	            List<Buyers> list = BuyersDao.selectGoods(select,Integer.valueOf(pageNow),sel);
	         // 获得总页数
	            int pageCount=BuyersDao.getPageCount();
	         // 显示当前页
	            String pageCode = new PageService().getPageCode(Integer.parseInt(pageNow), pageCount);
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
        }else if("2".equals(i)){
        	//String id= request.getParameter("id");//自己的发布表id
        	//String accountId= request.getParameter("accountId");//账号id
        	String wares= request.getParameter("wares");//商品id
        	String speciId= request.getParameter("speciId");//规格id
        	//String nameType= request.getParameter("nameType");//类型
        	//String name= request.getParameter("name");//名称
        	//String describe= request.getParameter("describe");//描述
        	//String money= request.getParameter("money");//价格
        	//String number= request.getParameter("number");//数量
        	String much= request.getParameter("much");//购买数量
        	String shuru= request.getParameter("shuru");//商品留言
        	Long start=System.currentTimeMillis();
        	String ddNum = start.toString();//生成的订单号
        	String ddTime = ConnectTime.getWebsiteDatetime();//订单生成时间
        	Order or=  new Order();
        	or.setWares_id(Integer.valueOf(wares));
        	or.setOrderNo(ddNum);
        	or.setOrderTime(ddTime);
        	or.setOrderNumber(Integer.valueOf(much));
        	or.setBuyersAddr("");
        	or.setBuyersName("");
        	or.setBuyersTell("");
        	or.setSpecificationId(Integer.valueOf(speciId));
        	or.setOrderMessage(shuru);
        	or.setOrderlapse("0");
        	//wares  ddNum   ddTime  much  ??? speciId  shuru  0
        	//System.out.println(id+","+accountId+","+wares+","+speciId+","+nameType+","+name+","+describe+","+money+","+number+","+much+","+shuru);
        }
    }
}
