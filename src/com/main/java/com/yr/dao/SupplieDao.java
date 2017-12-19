package com.yr.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.yr.pojo.Supplie;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

public class SupplieDao {
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
     */
    public static void merchandiseAdd(String nameType,String name,String money,String describe,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String number,String upFrametTime) throws SQLException{
        Connection conn = Conn.conn();
        String str = "insert into merchandise(nameTypeID,name,money,describe,specificationID,number,upFrametTime) values(?,?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, nameType);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setString(4, describe);
        ps.setString(5, origin);
        ps.setString(6, netContent);
        ps.setString(7, packingMethod);
        ps.setString(8, brand);
        ps.setString(9, qGp);
        ps.setString(10, storageMethod);
        ps.setString(6, number);
        ps.setString(7, upFrametTime);
        ps.executeUpdate();// ִ���޸�
        String strJson = JsonUtils.beanToJson(ps);
    }
    /**
     * ��ӹ�Ӧ�̵���Ʒ��Ϣ
     * @author zxy
     * @param strName ��Ʒ��Ϣ
     * @return
     * @throws Exception
     * 2017��12��14��  ����3:10:51
     */
    public static String suppAdd(String nameType,String name,String money,String describe,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String number,String upFrametTime,String mercd_id,String sup_mer_id) throws Exception{
        Connection conn = Conn.conn();
        String str = "insert into supplier(commodity,mercd_id,sup_mer_id) values(?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, name); // ��Ӧ��id 
        ps.setString(2, mercd_id); // ��Ʒid
        ps.setString(3, sup_mer_id); // ��Ӧ�̶�Ӧ����Ʒid
        ps.executeUpdate();// ִ���޸�
        String strJson = JsonUtils.beanToJson(ps);
        merchandiseAdd(nameType, name, money, describe, origin, netContent, packingMethod, brand, qGp, storageMethod, number, upFrametTime);;
        suppSel();
        return strJson;
    }
    
    /**
     * ��ȡָ����վ������ʱ�� 
     * @author zxy
     * @param webUrl
     * @return
     * 2017��12��16��  ����11:31:57
     */
    /*private static String getWebsiteDatetime(String webUrl){
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
    }*/
    
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
     * �޸���Ʒ
     * @author zxy
     * @param id ��Ʒid
     * @param nameType  ��Ʒ����
     * @param name  ��Ʒ��
     * @param money ��Ʒ�۸�
     * @param describe  ��Ʒ����
     * @param specification ��Ʒ���
     * @param number    ��Ʒ����
     * @param upFrametTime  ��Ʒ�ϼ�ʱ��
     * @throws SQLException
     * 2017��12��14��  ����5:41:00
     */
    public static void merchandiseUpd(String nameTypeID,String name,String money,String describe,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String number,String upFrametTime,String specificationID,String suptID) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update merchandise mer ,specification_table supt set mer.nameTypeID = ?,mer.`name` = ?,mer.money = ?,mer.`describe` = ?,supt.origin = ?,supt.netContent = ?,supt.packingMethod = ?,supt.brand = ?,supt.qGp = ?,supt.storageMethod = ?,number = ? where mer.specificationID = ? and supt.id = ?;";

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, nameTypeID);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setString(4, describe);
        ps.setString(5, origin);
        ps.setString(6, netContent);
        ps.setString(7, packingMethod);
        ps.setString(8, brand);
        ps.setString(9, qGp);
        ps.setString(10, storageMethod);
        ps.setString(11, number);
        ps.setString(12, specificationID);
        ps.setString(13, suptID);
        ps.executeUpdate(); // ִ���޸�
        ResultSet rs = ps.getResultSet(); // ��ȡ��ѯ���
        List<Supplie> list = new ArrayList<>();
        while(rs.next()){ // ѭ�������
            Supplie sup = new Supplie();
            sup.setMerType(rs.getString(1));
            sup.setCommo(rs.getString(2));
            sup.setMoney(rs.getInt(3));
            sup.setDescribe(rs.getString(4));
            sup.setOrigin(rs.getString(5));
            sup.setNetContent(rs.getString(6));
            sup.setPackingMethod(rs.getString(7));
            sup.setBrand(rs.getString(8));
            sup.setqGp(rs.getString(9));
            sup.setStorageMethod(rs.getString(10));
            sup.setNumber(rs.getInt(11));
            sup.setUpFrameTime(rs.getShort(12));
            sup.setId(rs.getInt(13));
            list.add(sup);
        }
        String strJson = JsonUtils.beanToJson(ps);
    }
    /**
     * �޸Ĺ�Ӧ�̵���Ʒ��Ϣ
     * @author zxy
     * @param commodity ��Ӧ�̵���Ʒ��
     * @param id    ��Ӧ�̵���ƷID
     * @throws Exception
     * 2017��12��14��  ����3:11:06
     */
    public static String suppUpd(String commodity,String id,String merId,String nameType,String name,String money,String describe,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String number,String specificationID,String suptID) throws Exception{
        Connection conn = Conn.conn();
        String str = "update supplier set commodity = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, commodity);
        ps.setString(2, id);
        ps.executeUpdate(); // ִ���޸�
        String strJson = JsonUtils.beanToJson(ps);
        SupplieDao.merchandiseUpd(merId,nameType,name,money,describe,origin,netContent,packingMethod,brand,qGp,storageMethod,number,specificationID,suptID); //��Ʒ��Ϣ�޸�
        suppSel();
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
        String str = "SELECT su.id,su.commodity,mertype.type,mer.money,mer.`describe`,spectable.origin,spectable.netContent,spectable.packingMethod,spectable.brand,spectable.qGp,spectable.storageMethod,mer.number,mer.upFrameTime,mer.id,mer.specificationID,su.mercd_id FROM supplier su, merchandise mer ,merchandise_type mertype , specification_table spectable where su.mercd_id = mer.id and mer.nameTypeID = mertype.id and mer.specificationID = spectable.id and su.sup_mer_id = mer.supplier_id;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.executeQuery();// ִ�в�ѯ
        ResultSet rs = ps.getResultSet();// ��ȡ��ѯ���
        List<Supplie> selist = new ArrayList<>();
        // ѭ�������
        while (rs.next()) {// ȡ������е���һ����
            Supplie la = new Supplie();
            la.setId(rs.getInt(1));
            la.setMerType(rs.getString(3));
            la.setCommo(rs.getString(2));
            la.setMoney(rs.getDouble(4));
            la.setDescribe(rs.getString(5));
            la.setOrigin(rs.getString(6));
            la.setNetContent(rs.getString(7));
            la.setPackingMethod(rs.getString(8));
            la.setBrand(rs.getString(9));
            la.setqGp(rs.getString(10));
            la.setStorageMethod(rs.getString(11));
            la.setNumber(rs.getInt(12));
            la.setUpFrameTime(rs.getInt(13));
            la.setMerId(rs.getInt(14));
            la.setSpecificationID(rs.getInt(15));
            la.setSuptID(rs.getInt(16));
            selist.add(la);
        }
        rs.close(); // �رս����
        ps.close(); // �رշ���SQL����
        String strJson = JsonUtils.beanListToJson(selist);
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
            return count;
        } catch (Exception e) {
//            resp.getWriter().write("0");
            e.printStackTrace();
            int iid = 0;
        }
        return 1;
    }
    
}
