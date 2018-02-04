package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SellerDao;
import com.yr.dao.SupplieDao;
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;
import com.yr.util.JsonUtils;
import com.yr.util.PageService;

import net.sf.json.JSONObject;

/**
 * 
 * @作者 林水桥 2017年12月13日上午10:58:37
 */
@WebServlet(urlPatterns = "/sellerServlet")
public class SellerServlet extends HttpServlet {
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
                List<Seller> list = SellerDao.queryGoods(id);
                if ("1".equals(o)) { // 进入查看页面
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("seller/detail.jsp").forward(request, response);
                } else if ("2".equals(o)) { // 进入修改页面
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("seller/update.jsp").forward(request, response);
                }
            } else if ("2".equals(i)) { // 删除保存的商品信息
                int id = Integer.valueOf(request.getParameter("id"));
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                String time = String.valueOf(request.getParameter("time"));
                String downtime = String.valueOf(request.getParameter("downtime"));
                String date = (String)ConnectTime.getWebsiteDatetime();
                SellerDao.delGoods(id, wares_id, time, downtime, date);
                response.getWriter().write("0");
            } else if ("3".equals(i)) { // 撤销正在审核的商品
                int id = Integer.valueOf(request.getParameter("id"));
                int account_id = Integer.valueOf(request.getParameter("seller_id"));
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                SellerDao.cancelAudit(id, account_id, wares_id);
                response.getWriter().write("0");
            } else if ("4".equals(i)) { // 下架已审核的商品
                int id = Integer.valueOf(request.getParameter("id"));
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                String date = (String)ConnectTime.getWebsiteDatetime();
                List<Seller> list = SellerDao.underGoods(id, seller_id, wares_id, date);
                String sst = JsonUtils.beanListToJson(list);
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(sst);
            } else if ("5".equals(i)) { // 修改商品信息
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                int spec_id = Integer.valueOf(request.getParameter("speciID"));
                Integer nameTypeID = Integer.valueOf(request.getParameter("nameTypeID"));
                String name = request.getParameter("name");
                name = new String(name.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                Integer money = Integer.valueOf(request.getParameter("money"));
                String desc = request.getParameter("desc");
                desc = new String(desc.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                Integer number = Integer.valueOf(request.getParameter("number"));
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
                /**
                 * 判断是否为空或null,同时判断数量和价格是否正规输入
                 */
                if(money != null && number != null && netContent != null && packingMethod != null && qGP != 0 && desc != null && name != null && brand != null && storageMethod != null && origin != null){
                    if(!money.equals("") && !number.equals("") && !netContent.equals("") && !packingMethod.equals("") && !desc.equals("") && !brand.equals("") && !brand.equals("") && !storageMethod.equals("") && !name.equals("") && !origin.equals("")){
                        Pattern pattern = Pattern.compile("[0-9]*");
                        Matcher moneyIsNum = pattern.matcher(String.valueOf(money));
                        if(moneyIsNum.matches()){ // 如果价格是数字就进入,否则弹出错误
                            Pattern pattern2 = Pattern.compile("[0-9]*");
                            Matcher numberIsNum = pattern2.matcher(String.valueOf(number));
                            if(numberIsNum.matches()){ // 如果number是数字就进入,否则弹出错误
                                SellerDao.updateGoods(wares_id, spec_id, nameTypeID, name, money, desc, number, origin, netContent, packingMethod, brand, qGP, storageMethod);
                            }else{
                                response.getWriter().write("1");
                            }
                        }else{
                            response.getWriter().write("1");
                        }
                    }else{
                        response.getWriter().write("1");
                    }
                }else{
                    response.getWriter().write("1");
                }
                
                response.getWriter().write("0");
            } else if ("6".equals(i)) { // 添加商品
                int select = Integer.valueOf(request.getParameter("interest")); // 商品类型
                String name = request.getParameter("name");// 商品名称
                name = new String(name.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                Integer money = Integer.valueOf(request.getParameter("money"));// 商品价格
                String desc = request.getParameter("desc");// 商品描述
                desc = new String(desc.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String origin = request.getParameter("origin");// 商品产地
                origin = new String(origin.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String netContent = request.getParameter("netContent");// 商品净含量
                netContent = new String(netContent.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String packingMethod = request.getParameter("packingMethod");// 包装方式
                packingMethod = new String(packingMethod.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                String brand = request.getParameter("brand");// 品牌
                brand = new String(brand.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                int qGP = Integer.valueOf(request.getParameter("qGP"));// 保质期
                String storageMethod = request.getParameter("storageMethod");
                storageMethod = new String(storageMethod.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
                Integer number = Integer.valueOf(request.getParameter("number"));
                String date = ConnectTime.getWebsiteDatetime();
                /**
                 * 判断是否为空或null,同时判断数量和价格是否正规输入
                 */
                if(money != null && number != null && netContent != null && packingMethod != null && qGP != 0 && desc != null && name != null && brand != null && storageMethod != null && origin != null){
                    if(!money.equals("") && !number.equals("") && !netContent.equals("") && !packingMethod.equals("") && !desc.equals("") && !brand.equals("") && !brand.equals("") && !storageMethod.equals("") && !name.equals("") && !origin.equals("")){
                        Pattern pattern = Pattern.compile("[0-9]*");
                        Matcher moneyIsNum = pattern.matcher(String.valueOf(money));
                        if(moneyIsNum.matches()){ // 如果价格是数字就进入,否则弹出错误
                            Pattern pattern2 = Pattern.compile("[0-9]*");
                            Matcher numberIsNum = pattern2.matcher(String.valueOf(number));
                            if(numberIsNum.matches()){ // 如果number是数字就进入,否则弹出错误
                                SellerDao.addGoods(select, name, money, desc, origin, netContent, packingMethod, brand, qGP, storageMethod, number, date, seller_id);
                            }else{
                                response.getWriter().write("1");
                            }
                        }else{
                            response.getWriter().write("1");
                        }
                    }else{
                        response.getWriter().write("1");
                    }
                }else{
                    response.getWriter().write("1");
                }
                // 获取网络时间
                response.sendRedirect("seller/user.jsp");
            } else if ("7".equals(i)) {//卖家上架,正在审核
                int id = Integer.valueOf(request.getParameter("id"));// 卖家发布ID
                int account_id = Integer.valueOf(request.getParameter("seller_id"));
                int wares_id = Integer.valueOf(request.getParameter("wares_id"));
                String date = (String)ConnectTime.getWebsiteDatetime();
                SellerDao.upGoods(id, account_id, wares_id, date);
                response.getWriter().write("0");
            } else if ("8".equals(i)) {//卖家上架,审核成功
                
            } else if ("9".equals(i)) {
                
            } else {
                response.sendRedirect("seller/user.jsp");
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
            List<Seller> list = SellerDao.selectGoods(select,Integer.valueOf(pageNow),sel);
         // 获得总页数
            int pageCount=SellerDao.getPageCount();
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
    }
}
