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
 * 2017��12��28�� ����8:34:54
 */
public class SupplieServlet extends HttpServlet {
    /**
     * ��ȡ����ʱ��.
     */
   
    
    /**
     * ��Ӧ�� ��ɾ�Ĳ�.
     *
     * @author zxy
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * 2017��12��13�� ����9:02:08
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        String sup = req.getParameter("sup"); // ҳ�洫������ֵ(�����ж�ִ����һ��)
        String state = req.getParameter("state"); // ��Ӧ���˺�ʹ��״̬
        if("0".equals(state)){ // ���ʹ��״̬Ϊ 0 �����˺�δע�����Խ�����ɾ�Ĳ�
            if("1".equals(sup)){
                //ҳ����ʾֵ
                PrintWriter out = resp.getWriter();
                String type= req.getParameter("type");
                String select= req.getParameter("select");//����������������ֵ(�˺�)
                String interest= req.getParameter("interest");//�����������������ֵ(��ɫ)
                String pageNow = req.getParameter("pageNow");//���ҳ�洫�����ĵ�ǰҳ
                if (null != type && type.equals("list")) {
                    String sel = req.getParameter("select");
                    if (null == pageNow || "".equals(pageNow)) {
                        pageNow = "1";
                    }
                    List<Supplie> list = SupplieDao.selectemp(Integer.valueOf(pageNow));
                    int pageCount=SupplieDao.getPageCount();//�����ҳ��
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
            }else if ("2".equals(sup)) { // �����Ʒ
                try {
                    req.setCharacterEncoding("UTF-8");
                    String name = req.getParameter("commodity"); // ��Ʒ��
                    String nameType = req.getParameter("merType"); // ��Ʒ����
                    String money = req.getParameter("money"); // ��Ʒ�۸�
                    String describe = req.getParameter("describe"); // ��Ʒ����   ------- û��
                    String origin = req.getParameter("origin"); // ��Ʒ����
                    String netContent = req.getParameter("netContent"); // ��Ʒ������
                    String packingMethod = req.getParameter("packingMethod"); // ��Ʒ��װ
                    String brand = req.getParameter("brand"); // ��ƷƷ��
                    String qGp = req.getParameter("qGp"); // ��Ʒ������
                    String storageMethod = req.getParameter("storageMethod"); // ��Ʒ���ط���
                    String number = req.getParameter("number"); // ��Ʒ����
                    String specificationID = req.getParameter("specificationID"); // ��Ʒ���Id
                    String suptID = req.getParameter("suptID"); // ��Ӧ�̹���ֶ�Id
                    String merId = req.getParameter("merId"); // ��ƷID
                    
                    /**
                     * ��ȡ����ʱ��
                     */
                    System.out.println(ConnectTime.getWebsiteDatetime());
                    String date = ConnectTime.getWebsiteDatetime();
                    
                    SupplieDao.speciAdd(origin,netContent,packingMethod,brand,qGp,storageMethod); // ������ֶ�����Ϣ
                    SupplieDao.merchandiseAdd(nameType, name, money, specificationID, number, date); // �����Ʒ��Ϣ
                    SupplieDao.suppAdd(name,merId,merId); // ��ӹ�Ӧ����Ʒ��Ϣ
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("3".equals(sup)) { // ������Ʒ
                try {
                    String id = req.getParameter("id");
                    System.out.println(ConnectTime.getWebsiteDatetime());
                    String date = (String)ConnectTime.getWebsiteDatetime();
                    SupplieDao.cencel(date, id);
                    resp.getWriter().write("0");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if ("4".equals(sup)) { // �޸� ��Ʒ��Ϣ
                try {
                    String id = req.getParameter("id"); // ��Ӧ��Id
                    String merId = req.getParameter("merId"); // ��ƷID  
                    String nameType = req.getParameter("merType"); //��Ʒ����
                    String name = req.getParameter("commodity"); // ��Ʒ��/��Ӧ����Ʒ��
                    String money = req.getParameter("money"); // ��Ʒ�۸�
                    String origin = req.getParameter("origin"); // ��Ʒ����
                    String netContent = req.getParameter("netContent"); // ��Ʒ������
                    String packingMethod = req.getParameter("packingMethod"); // ��Ʒ��װ
                    String brand = req.getParameter("brand"); // ��ƷƷ��
                    String qGp = req.getParameter("qGp"); // ��Ʒ������
                    String storageMethod = req.getParameter("storageMethod"); // ��Ʒ���ط���
                    String number = req.getParameter("number"); // ��Ʒ����
                    String specificationID = req.getParameter("specificationID"); // ��Ʒ���Id
                    String spe = SupplieDao.speUpd(origin,netContent,packingMethod,brand,qGp,storageMethod,specificationID);
                    String supp = SupplieDao.merchandiseUpd(name,money,number,nameType,specificationID,id); //��Ʒ��Ϣ���޸�
                    SupplieDao.suppUpd(name,id); // ��Ӧ��ID
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("jump.jsp").forward(req, resp); //ת��
            } else if ("5".equals(sup)) { // ��ѯ��Ӧ����Ʒ
                resp.setCharacterEncoding("utf-8");
                try { // state 0״̬��ʹ�õ��˺�,1״̬����ͣ���˺�
                    String strJson = SupplieDao.suppSel(); // ��ѯ
                    resp.getWriter().write(strJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("6".equals(sup)){ // �¼���Ʒ
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
     * �ж�Id�Ƿ��ظ�
     * 
     * @author zxy
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * 2017��12��13�� ����10:35:08
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        String strId = req.getParameter("id"); // ��ȡҳ�洫����ֵ
        int id = SupplieDao.exsisId(strId);
        resp.getWriter().write(id);
    }

}
