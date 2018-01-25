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

import com.mysql.jdbc.Statement;
import com.yr.pojo.Paging;
import com.yr.pojo.Supplie;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

public class SupplieDao {
    public static final String webUrl4 = "http://www.ntsc.ac.cn";// �й���ѧԺ������ʱ����
    static List<Supplie> list = new ArrayList<>();

    /**
     * ��ѯ
     * @author zxy
     * @return
     * 2017��12��14��  ����3:11:13
     * @throws SQLException 
     */
    public static List<Supplie> queryAll(Integer id) throws SQLException{
        Connection conn = Conn.conn();
        List<Supplie> list = new ArrayList<>();
        String sql = "select su.id,acc.`name`,su.commodity,mtype.type,mer.money,mer.number,spe.netContent,spe.origin,spe.packingMethod,spe.brand,mer.`describe`,mo.`month`,spe.storageMethod,mer.upFrameTime,mer.id,spe.id from supplier su ,merchandise mer ,specification_table spe ,merchandise_type mtype ,month_table mo,auditsupplier aud,account acc where su.mercd_id = mer.supplier_id and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and aud.release_id = su.mercd_id and mer.account_id = aud.account_id and aud.account_id = acc.id and su.id = ? ORDER BY su.id ASC;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);// ����SQL�����ݿ�
        ps.setInt(1, id);
        ps.executeQuery(); // ִ�в�ѯ
        ResultSet rs = ps.getResultSet();// ��ȡ��ѯ���
        // ѭ�������
        while (rs.next()) { // ȡ������е���һ����
            Supplie supp = new Supplie();
            supp.setId(rs.getInt(1));
            supp.setAccount(rs.getString(2));
            supp.setCommo(rs.getString(3));
            supp.setTypeName(rs.getString(4));
            supp.setMoney(rs.getInt(5));
            supp.setNumber(rs.getInt(6));
            supp.setNetContent(rs.getString(7));
            supp.setOrigin(rs.getString(8));
            supp.setPackingMethod(rs.getString(9));
            supp.setBrand(rs.getString(10));
            supp.setDescribe(rs.getString(11));
            supp.setMonth(rs.getString(12));
            supp.setStorageMethod(rs.getString(13));
            supp.setUpFrameTime(rs.getString(14));
            supp.setSpeId(rs.getInt(15));
            supp.setMerId(rs.getInt(16));
            list.add(supp);
        }
        return list;
    }
    
    /**
     * ��ѯ
     * @author zxy
     * @return
     * 2017��12��14��  ����3:11:13
     * @throws SQLException 
     */
    public static String selc() throws SQLException{
        Connection conn = Conn.conn();
        List<Supplie> list = new ArrayList<>();
        String sql = "select DISTINCT su.id,su.commodity,mtype.type,mer.money,mer.number,spe.netContent,spe.origin,spe.packingMethod,spe.brand,mer.`describe`,mo.`month`,spe.storageMethod,mer.upFrameTime from supplier su ,merchandise mer ,specification_table spe ,merchandise_type mtype ,specification_table sfta,month_table mo where su.mercd_id = mer.supplier_id and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and sfta.qGP = mo.id ;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);// ����SQL�����ݿ�
        ps.executeQuery(); // ִ�в�ѯ
        ResultSet rs = ps.getResultSet();// ��ȡ��ѯ���
        // ѭ�������
        while (rs.next()) { // ȡ������е���һ����
            Supplie supp = new Supplie();
            supp.setId(rs.getInt(1));
            supp.setCommo(rs.getString(2));
            supp.setTypeName(rs.getString(3));
            supp.setMoney(rs.getInt(4));
            supp.setNumber(rs.getInt(5));
            supp.setNetContent(rs.getString(6));
            supp.setOrigin(rs.getString(7));
            supp.setBrand(rs.getString(9));
            supp.setDescribe(rs.getString(10));
            supp.setMonth(rs.getString(11));
            supp.setStorageMethod(rs.getString(12));
            supp.setUpFrameTime(rs.getString(13));
            list.add(supp);
        }
        String sst = JsonUtils.beanListToJson(list);
        return sst;
    }
    
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
     * @param describe ��Ʒ����
     * @param auditStatus ��Ʒ״̬
     * @param supplie_id ��Ӧ����Ʒid
     * @throws SQLException
     * 2017��12��14��  ����5:35:56
     * @throws ParseException 
     */
    public static void merchandiseAdd(String nameTypeID,String name,String money,Integer specificationID,String number,String describe,String auditStatus,Integer supplie_id,String upFrametTime) throws SQLException, ParseException{
        Connection conn = Conn.conn();
        String str = "insert into merchandise(nameTypeID,`name`,money,specificationID,number,`describe`,auditStatus,supplier_id,upFrameTime) values(?,?,?,?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, nameTypeID);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setInt(4, specificationID);
        ps.setString(5, number);
        ps.setString(6, describe);
        ps.setString(7, auditStatus);
        ps.setInt(8, supplie_id);
        ps.setString(9, upFrametTime);
        ps.executeUpdate();// ִ���޸�
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
    public static Integer speciAdd(Integer account_id,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod)throws SQLException{
        Connection conn = Conn.conn();
        String str = "insert into specification_table(origin,netContent,packingMethod,brand,qGp,storageMethod,account_id) values(?,?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str,Statement.RETURN_GENERATED_KEYS);// ����SQL�����ݿ�  ��ȡ�ղ����������id 
        ps.setString(1, origin);
        ps.setString(2, netContent);
        ps.setString(3, packingMethod);
        ps.setString(4, brand);
        ps.setString(5, qGp);
        ps.setString(6, storageMethod);
        ps.setInt(7, account_id);
        ps.executeUpdate();// ִ���޸�
        int autoInckey = -1;
        ResultSet res = ps.getGeneratedKeys();
        if(res.next()){
            autoInckey = res.getInt(1);
        }
        return autoInckey; // ���ػ�ȡ��id
    }
    
    /**
     * ��ӹ�Ӧ�̵���Ʒ��Ϣ
     * @author zxy
     * @param strName ��Ʒ��Ϣ
     * @return
     * @throws Exception
     * 2017��12��14��  ����3:10:51
     */
    public static void suppAdd(String name,Integer mercd_id) throws Exception{
        Connection conn = Conn.conn();
        String str = "insert into supplier(commodity,mercd_id) values(?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, name); // ��Ӧ��id 
        ps.setInt(2, mercd_id); // ��Ʒid
        ps.executeUpdate();// ִ���޸�
        selc();
    }
    
    /**
     * ��ȡָ����վ������ʱ�� 
     * @author zxy
     * @param webUrl
     * @return
     * 2017��12��16��  ����11:31:57
     */
    public static String getWebsiteDatetime(String webUrl){
        Connection conn = Conn.conn();
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
        selc();
    }
    
    /*public static void selId(){
        Connection coon  = Conn.c
    }*/
    
    /**
     * ��������е���Ʒ
     * @author zxy
     * 2018��1��2��  ����10:31:46
     * @throws SQLException 
     */
    public static void cencel(String date,Integer release_supplierId,Integer account_id,Integer id) throws SQLException{
        Connection conn = Conn.conn();
        String sql1 = "delete from auditsupplier where release_id=? and account_id=? and merchandise_id=?";
        PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
        ps1.setInt(1, release_supplierId);
        ps1.setInt(2, account_id);
        ps1.setInt(3, id);
        ps1.executeUpdate();
        ps1.close();
        String str = "update release_supplier set auditsupplier = ? where id= ? ;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setInt(1, 0);
        ps.setInt(2, id);
        ps.executeUpdate();
    }
    
    /**
     * �¼���Ʒ
     * @author zxy
     * 2018��1��2��  ����5:28:00
     * @throws SQLException 
     */
    public static String xiajia(String date,String id) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update release_supplier set time= ? wares_id = ? where id=?;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setString(1, date);
        ps.setInt(2, 0);
        ps.setString(3, id);
        ps.executeUpdate();
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
    public static void merchandiseUpd(String money,String number,String describe,String id) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update merchandise mer set money = ?,number = ?,`describe` = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, money);
        ps.setString(2, number);
        ps.setString(3, describe);
        ps.setString(4, id);
        ps.executeUpdate(); // ִ���޸�
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
    public static void speUpd(String netContent,String packingMethod,String qGp,String speId) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update specification_table spe set netContent = ?,packingMethod = ?,qGp = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, netContent);
        ps.setString(2, packingMethod);
        ps.setString(3, qGp);
        ps.setString(4, speId);
        ps.executeUpdate(); // ִ���޸�
    }
    
    /**
     * ��ѯ���ݲ���list��װ����
     * @param pageNow ��ǰҳ
     * @param sel �ж��Ƿ����˲�ѯ����
     * @return ��������ѯ������
     */
    public static List<Supplie> selectemp(Integer pageNow, String sel) {
        Connection conn = Conn.conn();
        String sql = "";
        if (pageNow < 1) {
            pageNow = 1;
        }
        pageNow = (pageNow - 1) * 10;
        try {
            if(null != sel && !"".equals(sel)){
            List<Integer> paramIndex = new ArrayList<>();
            List<Object> param = new ArrayList<>();
            sql = "select su.id,su.commodity,mer.money,mer.number,mo.`month`,spe.brand,spe.origin,spe.packingMethod,spe.storageMethod,mer.`describe` from supplier su ,merchandise mer ,specification_table spe ,merchandise_type mtype ,month_table mo,auditsupplier aud where su.mercd_id = mer.supplier_id and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and aud.release_id = su.mercd_id";
            
            sql = sql + " limit ?,?";
            paramIndex.add(1);
            paramIndex.add(1);
            
            
            param.add(pageNow);
            param.add(Paging.getPageNumber());
            
            PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
            
            for (int i = 0; i < paramIndex.size(); i++) {
                int mark = paramIndex.get(i);
                if(mark == 0 ){
                    prepar.setString( (i+1), "%" + "\\" + (String)param.get(i)  + "%");
                }
                else if(mark == 1)
                {
                    prepar.setInt( (i+1), (Integer)param.get(i) );
                }
            }
            
            prepar.executeQuery();
            ResultSet resu = prepar.getResultSet();
            while (resu.next()) {
                Supplie supp = new Supplie();
                supp.setId(resu.getInt(1));
                supp.setCommo(resu.getString(2));
                supp.setMoney(resu.getInt(3));
                supp.setNumber(resu.getInt(4));
                supp.setqGp(resu.getString(5));
                supp.setBrand(resu.getString(6));
                supp.setOrigin(resu.getString(7));
                supp.setPackingMethod(resu.getString(8));
                supp.setStorageMethod(resu.getString(9));
                supp.setDescribe(resu.getString(10));
                list.add(supp);
            }
            return list;
        }else {
            sql = "select su.id,su.commodity,mer.money,mer.number,mtype.type,mo.`month`,spe.brand,spe.netContent,spe.origin,spe.packingMethod,spe.storageMethod,mer.upFrameTime,mer.auditStatus,mer.`describe`,mer.account_id,aud.release_id from supplier su ,merchandise mer ,specification_table spe ,merchandise_type mtype ,month_table mo,auditsupplier aud where su.mercd_id = mer.supplier_id and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and aud.account_id = mer.account_id and aud.merchandise_id = mer.supplier_id and aud.release_id = su.mercd_id ORDER BY su.id ASC limit ?,?;";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setInt(1, pageNow);
            pre.setInt(2, Paging.getPageNumber());
            pre.executeQuery();
            ResultSet rs = pre.getResultSet();
            while (rs.next()) {
                Supplie supp = new Supplie();
                supp.setId(rs.getInt(1));
                supp.setCommo(rs.getString(2));
                supp.setMoney(rs.getInt(3));
                supp.setNumber(rs.getInt(4));
                supp.setTypeName(rs.getString(5));
                supp.setqGp(rs.getString(6));
                supp.setBrand(rs.getString(7));
                supp.setNetContent(rs.getString(8));
                supp.setOrigin(rs.getString(9));
                supp.setPackingMethod(rs.getString(10));
                supp.setStorageMethod(rs.getString(11));
                supp.setUpFrameTime(rs.getString(12));
                supp.setAuditStatus(rs.getInt(13));
                supp.setDescribe(rs.getString(14));
                supp.setAccount_id(rs.getInt(15));
                supp.setRelease_supplierId(rs.getInt(16));
                list.add(supp);
            }
            rs.close();
            pre.close();
            return list;
        }
        
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
        Connection conn = Conn.conn();
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
        Connection conn = Conn.conn();
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
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            int iid = 0;
        }
        return 1;
    }
    
}
