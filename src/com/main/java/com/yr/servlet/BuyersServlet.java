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
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;
import com.yr.util.JsonUtils;
import com.yr.util.PageService;

import net.sf.json.JSONObject;
/**
 * ���ҵ���߼�
 * @author ��ҵ��
 * 2018��1��23�� ����10:55:09
 */
public class BuyersServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    /**
     * ���ұ�
     * i=1 o=1 Ϊ    �鿴��Ʒ��ϸ��Ϣ
     * i=1 o=2 Ϊ    �޸�ҳ��
     * i=2 Ϊ    ɾ���������Ʒ��Ϣ
     * i=3 Ϊ    ����������˵���Ʒ
     * i=4 Ϊ    �¼���Ʒ
     * i=5 Ϊ    �޸���Ʒ��Ϣ
     * i=6 Ϊ    �����Ʒ
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8"); // ת�� ��ֹ��������
        int seller_id = (int)request.getSession().getAttribute("userID");
        String o = request.getParameter("o");
        String i = request.getParameter("i");
        try {
            if ("1".equals(i)) { // �鿴���޸���Ʒ��ϸ��Ϣ
            	int id = Integer.valueOf(request.getParameter("id"));
                List<Buyers> list = BuyersDao.queryGoods(id);
                if ("1".equals(o)) { // ����鿴ҳ��
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("commodity/user.jsp").forward(request, response);
                }
            }else if("2".equals(i)){
            	
            }
        } catch (Exception e) {
            response.getWriter().write("1");
            e.printStackTrace();
        }
    }

    /**
     * ��ѯ���ұ���������
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        //ҳ����ʾֵ
        PrintWriter out = response.getWriter();
        String type= request.getParameter("type");
        String select= request.getParameter("select");//����������������ֵ(�˺�)
        String pageNow = request.getParameter("pageNow");//���ҳ�洫�����ĵ�ǰҳ
        if (null != type && type.equals("list")) {
            String sel = request.getParameter("select");
            if (null == pageNow || "".equals(pageNow)) {
                pageNow = "1";
            }
         // ��ѯ����ҳ
            List<Buyers> list = BuyersDao.selectGoods(select,Integer.valueOf(pageNow),sel);
         // �����ҳ��
            int pageCount=BuyersDao.getPageCount();
         // ��ʾ��ǰҳ
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
