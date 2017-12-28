package com.yr.dao;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.yr.pojo.Supplie;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

public class SupplieDao {
    public static final String webUrl4 = "http://www.ntsc.ac.cn";// �й���ѧԺ������ʱ����

    /**
     * �����Ʒ��Ϣ
     * @author zxy
     * @param nameType ��Ʒ����
     * @param name  ��Ʒ��
     * @param money ��Ʒ�۸�
     * @param describe  ��Ʒ����
     * @param specification ��Ʒ���
     * @param number    ��Ʒ����
     * @param upFrametTime  ��Ʒ�ϼ�ʱ��
     * @throws SQLException
     * 2017��12��14��  ����5:35:56
     * @throws ParseException 
     */
    public static void merchandiseAdd(String nameType,String name,String money,String describe,String number,String upFrametTime) throws SQLException, ParseException{
        
        Connection conn = Conn.conn();
        String str = "insert into merchandise(nameTypeID,`name`,money,`describe`,number,upFrameTime) values(?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, nameType);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setString(4, describe);
        ps.setString(5, number);
        ps.setString(6, upFrametTime);
        ps.executeUpdate();// ִ���޸�
        ps.close();
    }
    
    public static void speciAdd(String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod)throws SQLException{
        Connection conn = Conn.conn();
        String str = "insert into specification_table(origin,netContent,packingMethod,brand,qGp,storageMethod) values(?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, origin);
        ps.setString(2, netContent);
        ps.setString(3, packingMethod);
        ps.setString(4, brand);
        ps.setString(5, qGp);
        ps.setString(6, storageMethod);
        ps.executeUpdate();// ִ���޸�
        ps.close();
    }
    
    /**
     * ��ӹ�Ӧ�̵���Ʒ��Ϣ
     * @author zxy
     * @param strName ��Ʒ��Ϣ
     * @return
     * @throws Exception
     * 2017��12��14��  ����3:10:51
     */
    public static void suppAdd(String name,String mercd_id,String sup_mer_id) throws Exception{
        Connection conn = Conn.conn();
        String str = "insert into supplier(commodity,mercd_id,sup_mer_id) values(?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, name); // ��Ӧ��id 
        ps.setString(2, mercd_id); // ��Ʒid
        ps.setString(3, sup_mer_id); // ��Ӧ�̶�Ӧ����Ʒid
        ps.executeUpdate();// ִ���޸�
        suppSel();
        ps.close();
    }
    
    /**
     * ��ȡָ����վ������ʱ�� 
     * @author zxy
     * @param webUrl
     * @return
     * 2017��12��16��  ����11:31:57
     */
    public static String getWebsiteDatetime(String webUrl){
        try {
            URL url = new URL(webUrl);// ȡ����Դ����
            URLConnection uc = url.openConnection();// �������Ӷ���
            uc.connect();// ��������
            long ld = uc.getDate();// ��ȡ��վ����ʱ��
            Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// �������ʱ��
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ɾ����Ӧ�̵���Ʒ��Ϣ
     * @author zxy
     * @param strId ��Ӧ����Ʒid
     * @return
     * @throws Exception
     * 2017��12��14��  ����3:10:57
     */
    public static void suppDel(String strId) throws Exception{
        Connection conn = Conn.conn();
        String str = "delete from supplier where id = ?";
        strId = new String(strId.getBytes("ISO-8859-1"), "utf-8"); // �����������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, strId);
        ps.executeUpdate(); // ִ���޸�
        ps.close();
        conn.close();
        suppSel();
    }
    
    /**
     * ɾ����Ʒ�����Ʒ��Ϣ
     * 
     * @author zxy
     * @param strId
     * 2017��12��20��  ����4:47:30
     */
    public static void merDel(String strId) throws Exception{
        Connection conn = Conn.conn();
        String str = "delete from merchandise where id = ?";
        strId = new String(strId.getBytes("ISO-8859-1"), "utf-8"); // �����������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, strId);
        ps.executeUpdate(); // ִ���޸�
        ps.close();
        conn.close();
        suppSel();
    }
    
    /**
     * �޸���Ʒ
     * @author zxy
     * @param name
     * @param money
     * @param number
     * @param nameTypeId
     * @param specificationID
     * @param id
     * @return
     * @throws SQLException
     * 2017��12��14��  ����5:41:00
     */
    public static String merchandiseUpd(String name,String money,String number,Integer nameTypeId,Integer specificationID,Integer id) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update merchandise mer set name= ?,money = ?,number = ?,nameTypeID = ?,specificationID = ? where id = ?";

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, name);
        ps.setString(2, money);
        ps.setString(3, number);
        ps.setInt(5, nameTypeId);
        ps.setInt(6, specificationID);
        ps.setInt(7, id);
        ps.executeUpdate(); // ִ���޸�
        ResultSet rs = ps.getResultSet(); // ��ȡ��ѯ���
        List<Supplie> list = new ArrayList<>();
        while(rs.next()){ // ѭ�������
            Supplie sup = new Supplie();
            sup.setCommo(rs.getString(1));
            sup.setMoney(rs.getDouble(2));
            sup.setNumber(rs.getInt(3));
            sup.setNameTypeId(rs.getInt(4));
            sup.setSpecificationId(rs.getInt(5));
            sup.setId(rs.getInt(6));
            list.add(sup);
        }
        String strJson = JsonUtils.beanToJson(ps);
        rs.close();
        ps.close();
        return strJson;
    }
    /**
     * �޸Ĺ�Ӧ�̵���Ʒ��Ϣ
     * @author zxy
     * @param commodity ��Ӧ�̵���Ʒ��
     * @param id    ��Ӧ�̵���ƷID
     * @throws Exception
     * 2017��12��14��  ����3:11:06
     */
    public static String suppUpd(String commodity,String id) throws Exception{
        Connection conn = Conn.conn();
        String str = "update supplier set commodity = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, commodity);
        ps.setString(2, id);
        ps.executeUpdate(); // ִ���޸�
        String strJson = JsonUtils.beanToJson(ps);
        suppSel();
        ps.close();
        return strJson;
    }
    /**
     * ��ѯ��Ӧ����Ʒ��Ϣ
     * @author zxy
     * @return
     * @throws Exception
     * 2017��12��14��  ����3:11:13
     */
    public static String suppSel() throws Exception{
        Connection conn = Conn.conn();
        // ��ȡid
        String str = "select DISTINCT sup.id,sup.commodity,mer.money,mer.`name`,mer.number,merType.type,mota.`month`,spe.brand,spe.netContent,spe.origin,spe.packingMethod,spe.storageMethod,mer.upFrameTime from supplier sup, merchandise mer,merchandise_type merType,month_table mota,specification_table spe where sup.mercd_id=mer.id and mer.nameTypeID=merType.id and sup.sup_mer_id = mer.supplier_id and mota.id = spe.qGP and mer.specificationID= spe.id";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.executeQuery();// ִ�в�ѯ
        ResultSet rs = ps.getResultSet();// ��ȡ��ѯ���
        List<Supplie> selist = new ArrayList<>();
        // ѭ�������
        while (rs.next()) {// ȡ������е���һ����
            Supplie sup = new Supplie();
            sup.setId(rs.getInt(1));
            sup.setCommo(rs.getString(2));
            sup.setMoney(rs.getDouble(3));
            sup.setSupMerName(rs.getString(4));
            sup.setNumber(rs.getInt(5));
            sup.setTypeName(rs.getString(6));
            sup.setqGp(rs.getString(7));
            sup.setBrand(rs.getString(8));
            sup.setNetContent(rs.getString(9));
            sup.setOrigin(rs.getString(10));
            sup.setPackingMethod(rs.getString(11));
            sup.setStorageMethod(rs.getString(12));
            sup.setUpFrameTime(rs.getString(13));
            selist.add(sup);
        }
        String strJson = JsonUtils.beanListToJson(selist);
        rs.close(); // �رս����
        ps.close(); // �رշ���SQL����
        return strJson;
    }
    
    /**
     * �ж�id�Ƿ��ظ�
     * @author zxy
     * @param id
     * @return
     * 2017��12��15��  ����2:36:38
     */
    public static int exsisId(String id){
        String sId= "select count(*) from supplier where id = ?";
        try {
            Connection conn = Conn.conn();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sId);
            ps.setString(1,id);
            ps.executeQuery();// ִ�в�ѯ
            ResultSet rs = ps.getResultSet();
            rs.next();
            int count = rs.getInt(1); // sql���������Ϊ1����id����,����Ϊ0���������Ӵ�id(����ʹ��)
            if (count == 0) {
//                resp.getWriter().write("0");
                int iid = 0;
            }else{
//                resp.getWriter().write("1");
                int iid = 1;
            }
            rs.close();
            return count;
        } catch (Exception e) {
//            resp.getWriter().write("0");
            e.printStackTrace();
            int iid = 0;
        }
        return 1;
    }
    
}
