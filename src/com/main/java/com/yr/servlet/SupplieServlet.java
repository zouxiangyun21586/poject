package com.yr.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.dao.SupplieDao;

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
    public static final String webUrl4 = "http://www.ntsc.ac.cn"; // �й���ѧԺ������ʱ����
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
        String sup = req.getParameter("sup"); // ҳ�洫������ֵ(�����ж�ִ����һ��)
        String state = req.getParameter("state"); // ��Ӧ���˺�ʹ��״̬
        if("0".equals(state)){ // ���ʹ��״̬Ϊ 0 �����˺�δע�����Խ�����ɾ�Ĳ�
            if ("2".equals(sup)) { // �����Ʒ
                try {
                    req.setCharacterEncoding("UTF-8");
                    String name = req.getParameter("commodity"); // ��Ʒ��
                    String nameType = req.getParameter("merType"); // ��Ʒ����
                    String money = req.getParameter("money"); // ��Ʒ�۸�
                    String describe = req.getParameter("describe"); // ��Ʒ����
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
                    System.out.println(SupplieDao.getWebsiteDatetime(webUrl4));
                    String date = SupplieDao.getWebsiteDatetime(webUrl4);
                    
                    SupplieDao.speciAdd(origin,netContent,packingMethod,brand,qGp,storageMethod); // ������ֶ�����Ϣ
                    SupplieDao.merchandiseAdd(nameType, name, money, describe, number, date); // �����Ʒ��Ϣ
                    SupplieDao.suppAdd(name,merId,merId); // ��ӹ�Ӧ����Ʒ��Ϣ
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("3".equals(sup)) { // ɾ��(����IDɾ����Ʒ)
                try {
                    String strId = req.getParameter("supDel"); // ҳ�洫������ֵ
                    SupplieDao.suppDel(strId); // ɾ������
                    SupplieDao.suppSel(); // ��ѯ����
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("jump.jsp").forward(req, resp);
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
            }
        }else{ // ���state״̬��Ϊ 0 ��ô�ʹ���˹�Ӧ���˺���ע��(����ʹ��)
            req.setAttribute("state", state);
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
        String strId = req.getParameter("id"); // ��ȡҳ�洫����ֵ
        int id = SupplieDao.exsisId(strId);
        resp.getWriter().write(id);
    }

}
