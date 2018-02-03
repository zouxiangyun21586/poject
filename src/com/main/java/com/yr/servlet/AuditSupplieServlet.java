package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.AuditSupplieDao;
import com.yr.pojo.Supplie;
import com.yr.util.ConnectTime;
import com.yr.util.PageService;

import net.sf.json.JSONObject;

/**
 * 
 * @作者 林水桥
 * 2018年1月24日下午10:07:39
 */
@WebServlet(urlPatterns = "/auditSupplieServlet")
public class AuditSupplieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuditSupplieServlet() {
        super();
    }

	/** 供应商审核表
	 *  i=0为         搜索查看与最初查看
     *  i=1 o=1为        修改查看
     *  i=1 0=2为        查看商品详细信息
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        
        String i = request.getParameter("i");
        if("0".equalsIgnoreCase(i)){
          //页面显示值
            PrintWriter out = response.getWriter();
            String type= request.getParameter("type");
            String select= request.getParameter("select");//搜索功能中输入框的值
            String pageNow = request.getParameter("pageNow");//获得页面传过来的当前页
            if (null != type && type.equals("list")) {
                if (null == pageNow || "".equals(pageNow)) {
                    pageNow = "1";
                }
                if(null != select && !"".equals(select)){
                    pageNow = "1";
                }
                // 查询并分页
                List<Supplie>  list = AuditSupplieDao.selectemp(Integer.valueOf(pageNow),select);
                // 获得总页数
                int pageCount = AuditSupplieDao.getPageCount();
                // 显示当前页数
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
        }else if("1".equalsIgnoreCase(i)){
            String o = request.getParameter("o");
            int auditId = Integer.valueOf(request.getParameter("auditId"));
            List<Supplie> list = AuditSupplieDao.queryAudit(auditId);
            request.setAttribute("list",list);
            if("1".equalsIgnoreCase(o)){// 修改查看
                request.getRequestDispatcher("auditSupplie/update.jsp").forward(request,response);
            }else if("2".equalsIgnoreCase(o)){// 查看详细信息
                request.getRequestDispatcher("auditSupplie/detail.jsp").forward(request,response);
            }
        }

	}

	/**
	 * i=1 为允许上架
	 * i=2 为禁止上架
	 * i=3为修改数据
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        System.out.println("1");
        String i = request.getParameter("i");
        if("1".equals(i)){// 允许上架
            int suptId = Integer.valueOf(request.getParameter("suptId"));
            int auditId = Integer.valueOf(request.getParameter("auditId"));
            int account_id = Integer.valueOf(request.getParameter("account_id"));
            int merId = Integer.valueOf(request.getParameter("merId"));
            String date = ConnectTime.getWebsiteDatetime();
            AuditSupplieDao.passAudit(suptId, auditId, account_id, merId, date);
            response.getWriter().write("0");
        }else if("2".equals(i)){// 禁止上架
            int merId = Integer.valueOf(request.getParameter("merId"));
            int auditId = Integer.valueOf(request.getParameter("auditId"));
            AuditSupplieDao.NoneAudit(merId, auditId);
            response.getWriter().write("0");
        }else if("3".equals(i)){// 修改商品信息
            System.out.println("2");
            int merId = Integer.valueOf(request.getParameter("merId"));
            int speId = Integer.valueOf(request.getParameter("speId"));
            Integer nameTypeId = Integer.valueOf(request.getParameter("nameTypeId"));
            String commo = request.getParameter("commo");
            commo = new String(commo.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
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
            int mt_id = Integer.valueOf(request.getParameter("mt_id"));
            String storageMethod = request.getParameter("storageMethod");
            storageMethod = new String(storageMethod.getBytes("ISO-8859-1"),"UTF-8"); // 转为utf-8格式 防止中文乱码
            AuditSupplieDao.updateAudit(merId, speId, nameTypeId, commo, money, desc, number, origin, netContent, packingMethod, brand, mt_id, storageMethod);
            response.getWriter().write("0");
        }
	}

}
