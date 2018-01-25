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
import com.yr.util.PageService;

import net.sf.json.JSONObject;

/**
 * 
 * @作者 林水桥
 * 2018年1月24日下午10:07:39
 */
@WebServlet("/auditSupplieServlet")
public class AuditSupplieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuditSupplieServlet() {
        super();
    }

	/**
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
        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
