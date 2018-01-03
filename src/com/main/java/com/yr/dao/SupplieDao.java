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

import com.yr.pojo.Paging;
import com.yr.pojo.Seller;
import com.yr.pojo.Supplie;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

public class SupplieDao {
    public static final String webUrl4 = "http://www.ntsc.ac.cn";// �й���ѧԺ������ʱ����
    static List<Supplie> list = new ArrayList<>();
    static Connection conn = Conn.conn();

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
    public static void merchandiseAdd(String nameType,String name,String money,String specification,String number,String upFrametTime) throws SQLException, ParseException{
        String str = "insert into merchandise(nameTypeID,`name`,money,specificationID,number,upFrameTime) values(?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, nameType);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setString(4, specification);
        ps.setString(5, number);
        ps.setString(6, upFrametTime);
        ps.executeUpdate();// ִ���޸�
        ps.close();
    }
    
    /**
     * ��ӹ�������Ϣ
     * @author zxy
     * @param origin
     * @param netContent
     * @param packingMethod
     * @param brand
     * @param qGp
     * @param storageMethod
     * @throws SQLException
     * 2017��12��28��  ����10:21:47
     */
    public static void speciAdd(String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod)throws SQLException{
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
     * ��������е���Ʒ
     * @author zxy
     * 2018��1��2��  ����10:31:46
     * @throws SQLException 
     */
    public static void cencel(String date,String id) throws SQLException{
        String str = "update release_supplier set time=? where id=?;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setString(1, date);
        ps.setString(2, id);
        ps.executeUpdate();
        ps.close();
    }
    
    /**
     * �¼���Ʒ
     * @author zxy
     * 2018��1��2��  ����5:28:00
     * @throws SQLException 
     */
    public static String xiajia(String date,String id) throws SQLException{
        String str = "update release_supplier set time=? where id=?;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setString(1, date);
        ps.setString(2, id);
        ps.executeUpdate();
        ps.close();
        String sql = "select time from release_supplier where id=?;";
        PreparedStatement psp = (PreparedStatement) conn.prepareStatement(sql);
        psp.setString(1, id);
        psp.executeQuery();
        ResultSet rs = psp.getResultSet();
        while(rs.next()){
            Supplie sup = new Supplie();
            sup.setUpFrameTime(rs.getString(1));
            list.add(sup);
        }
        String sst = JsonUtils.beanListToJson(list);
        return sst;
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
    public static String merchandiseUpd(String name,String money,String number,String nameTypeId,String specificationID,String id) throws SQLException{
        String str = "update merchandise mer set name= ?,money = ?,number = ?,nameTypeID = ?,specificationID = ? where id = ?";

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, name);
        ps.setString(2, money);
        ps.setString(3, number);
        ps.setString(5, nameTypeId);
        ps.setString(6, specificationID);
        ps.setString(7, id);
        ps.executeUpdate(); // ִ���޸�
        ResultSet rs = ps.getResultSet(); // ��ȡ��ѯ���
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
     * �޸Ĺ���
     * @author zxy
     * @param origin
     * @param netContent
     * @param packingMethod
     * @param brand
     * @param qGp
     * @param storageMethod
     * @param speId
     * @return
     * @throws SQLException
     * 2017��12��28��  ����10:06:30
     */
    public static String speUpd(String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String speId) throws SQLException{
        String str = "update specification_table spe set origin= ?,netContent = ?,packingMethod = ?,brand = ?,qGp = ?,storageMethod = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, origin);
        ps.setString(2, netContent);
        ps.setString(3, packingMethod);
        ps.setString(4, brand);
        ps.setString(5, qGp);
        ps.setString(6, storageMethod);
        ps.setString(7, speId);
        ps.executeUpdate(); // ִ���޸�
        ResultSet rs = ps.getResultSet(); // ��ȡ��ѯ���
        while(rs.next()){ // ѭ�������
            Supplie sup = new Supplie();
            sup.setOrigin(rs.getString(1));
            sup.setNetContent(rs.getString(2));
            sup.setPackingMethod(rs.getString(3));
            sup.setBrand(rs.getString(4));
            sup.setqGp(rs.getString(5));
            sup.setStorageMethod(rs.getString(6));
            sup.setSpecificationId(7);
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
        // ��ȡid
        String str = "select DISTINCT sup.id,sup.commodity,mer.money,mer.`name`,mer.number,merType.type,mota.`month`,spe.brand,spe.netContent,spe.origin,spe.packingMethod,spe.storageMethod,mer.upFrameTime from supplier sup, merchandise mer,merchandise_type merType,month_table mota,specification_table spe where sup.mercd_id=mer.id and mer.nameTypeID=merType.id and sup.sup_mer_id = mer.supplier_id and mota.id = spe.qGP and mer.specificationID= spe.id";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.executeQuery();// ִ�в�ѯ
        ResultSet rs = ps.getResultSet();// ��ȡ��ѯ���
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
            list.add(sup);
        }
        String strJson = JsonUtils.beanListToJson(list);
        rs.close(); // �رս����
        ps.close(); // �رշ���SQL����
        return strJson;
    }
    
    
    /**
     * ��ѯ���ݲ���list��װ����
     * @param pageNow ��ǰҳ
     * @return ��������ѯ������
     */
    public static List<Supplie> selectemp(Integer pageNow) {
        String sql = "";
        if (pageNow < 1) {
            pageNow = 1;
        }
        pageNow = (pageNow - 1) * 10;
        try {
            List<Integer> paramIndex = new ArrayList<>();
            List<Object> param = new ArrayList<>();
            sql = "select DISTINCT sup.id,sup.commodity,mer.money,mer.`name`,mer.number,merType.type,mota.`month`,spe.brand,spe.netContent,spe.origin,spe.packingMethod,spe.storageMethod,mer.upFrameTime from supplier sup, merchandise mer,merchandise_type merType,month_table mota,specification_table spe where sup.mercd_id=mer.id and mer.nameTypeID=merType.id and sup.sup_mer_id = mer.supplier_id and mota.id = spe.qGP and mer.specificationID= spe.id";
            
            sql = sql + " limit ?,?";
            paramIndex.add(1);
            paramIndex.add(1);
            
            
            param.add(pageNow);
            param.add(Paging.getPageNumber());
            
            PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
            
            for (int i = 0; i < paramIndex.size(); i++) {
                int mark = paramIndex.get(i);
                if(mark == 0 ){
                    prepar.setString( (i+1), (String)param.get(i) );
                }
                else if(mark == 1)
                {
                    prepar.setInt( (i+1), (Integer)param.get(i) );
                }
            }
            
            prepar.executeQuery();
            ResultSet resu = prepar.getResultSet();
            while (resu.next()) {
                Supplie us = new Supplie();
                us.setId(resu.getInt(1));
                us.setCommo(resu.getString(2));
                us.setId(resu.getInt(1));
                us.setCommo(resu.getString(2));
                us.setMoney(resu.getDouble(3));
                us.setSupMerName(resu.getString(4));
                us.setNumber(resu.getInt(5));
                us.setTypeName(resu.getString(6));
                us.setqGp(resu.getString(7));
                us.setBrand(resu.getString(8));
                us.setNetContent(resu.getString(9));
                us.setOrigin(resu.getString(10));
                us.setPackingMethod(resu.getString(11));
                us.setStorageMethod(resu.getString(12));
                us.setUpFrameTime(resu.getString(13));
                list.add(us);
            }
            resu.close();
            prepar.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * �����ҳ��
     * 
     * @return ������ҳ��
     */
    public static Integer getPageCount() {
        int total = 0;// �ܹ���������¼
        int pageCount = 0;// ��ҳ��
        try {
            String sql = "select count(*) from supplier";
            PreparedStatement prepar = conn.prepareStatement(sql);
            prepar.executeQuery();
            ResultSet resu = prepar.getResultSet();
            while (resu.next()) {
                total = resu.getInt(1);
            }
            resu.close();
            prepar.close();
//          conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (total % Paging.getPageNumber() == 0) {
            pageCount = total / Paging.getPageNumber();
        } else {
            pageCount = total / Paging.getPageNumber() + 1;
        }
        return pageCount;
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
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sId);
            ps.setString(1,id);
            ps.executeQuery();// ִ�в�ѯ
            ResultSet rs = ps.getResultSet();
            rs.next();
            int count = rs.getInt(1); // sql���������Ϊ1����id����,����Ϊ0���������Ӵ�id(����ʹ��)
            if (count == 0) {
                int iid = 0;
            }else{
                int iid = 1;
            }
            rs.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            int iid = 0;
        }
        return 1;
    }
    
}
