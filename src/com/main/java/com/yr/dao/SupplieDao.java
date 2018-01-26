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
     * �޸Ĳ�ѯ
     * @author zxy
     * @return
     * 2017��12��14��  ����3:11:13
     * @throws SQLException 
     */
    public static List<Supplie> queryAll(Integer merId) throws SQLException{
        Connection conn = Conn.conn();
        List<Supplie> list = new ArrayList<>();
        String sql = "select su.id,mer.id,spe.id,act.`name`,su.commodity,mtype.type,mer.money,mer.number,spe.netContent,spe.origin,spe.packingMethod,spe.brand,mer.`describe`,mo.`month`,spe.storageMethod,mer.upFrameTime from supplier su,merchandise mer,specification_table spe,merchandise_type mtype,month_table mo,account act,auditsupplier asp where su.mercd_id=mer.id and su.commodity = mer.`name` and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and mer.account_id=asp.account_id and mer.account_id=act.id and mer.id=asp.merchandise_id and mer.id = ? ORDER BY su.id ASC";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);// ����SQL�����ݿ�
        ps.setInt(1, merId);
        ps.executeQuery(); // ִ�в�ѯ
        ResultSet rs = ps.getResultSet();// ��ȡ��ѯ���
        // ѭ�������
        while (rs.next()) { // ȡ������е���һ����
            Supplie supp = new Supplie();
            supp.setSuptId(rs.getInt(1));
            supp.setMerId(rs.getInt(2));
            supp.setSpeId(rs.getInt(3));
            supp.setAccount(rs.getString(4));
            supp.setCommo(rs.getString(5));
            supp.setTypeName(rs.getString(6));
            supp.setMoney(rs.getInt(7));
            supp.setNumber(rs.getInt(8));
            supp.setNetContent(rs.getString(9));
            supp.setOrigin(rs.getString(10));
            supp.setPackingMethod(rs.getString(11));
            supp.setBrand(rs.getString(12));
            supp.setDescribe(rs.getString(13));
            supp.setMonth(rs.getString(14));
            supp.setStorageMethod(rs.getString(15));
            supp.setUpFrameTime(rs.getString(16));
            list.add(supp);
        }
        return list;
    }
    
    /**
     * ��ѯ����
     * @author zxy
     * @return
     * 2017��12��14��  ����3:11:13
     * @throws SQLException 
     */
    public static List<Supplie> selc() throws SQLException{
    	Connection conn = Conn.conn();
        List<Supplie> list = new ArrayList<>();
        String sql = "select sup.id ,asp.id ,act.id ,m.id ,act.`name` ,mt.type ,m.`name` ,m.money ,m.number ,spe.netContent ,spe.origin ,spe.packingMethod ,spe.brand ,mo.`month` ,spe.storageMethod ,m.`describe` ,m.upFrameTime ,m.merStatus ,asp.addTime from merchandise m,auditsupplier asp,supplier sup,merchandise_type mt,account act ,specification_table spe ,month_table mo where m.specificationID = spe.id and m.`name` = sup.commodity and spe.qGP = mo.id and m.id=sup.mercd_id and sup.id=asp.release_id and m.id=asp.merchandise_id and m.account_id=asp.account_id and m.account_id=act.id and m.nameTypeID=mt.id ORDER BY sup.id ASC;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);// ����SQL�����ݿ�
        ps.executeQuery(); // ִ�в�ѯ
        ResultSet rs = ps.getResultSet();// ��ȡ��ѯ���
        // ѭ�������
        while (rs.next()) { // ȡ������е���һ����
            Supplie supp = new Supplie();
            supp.setSuptId(rs.getInt(1));
            supp.setRelease_supplierId(rs.getInt(2));
            supp.setAccount_id(rs.getInt(3));
            supp.setMerId(rs.getInt(4));
            supp.setAccount(rs.getString(5));
            supp.setTypeName(rs.getString(6));
            supp.setCommo(rs.getString(7));
            supp.setMoney(rs.getInt(8));
            supp.setNumber(rs.getInt(9));
            supp.setNetContent(rs.getString(10));
            supp.setOrigin(rs.getString(11));
            supp.setPackingMethod(rs.getString(12));
            supp.setBrand(rs.getString(13));
            supp.setMonth(rs.getString(14));
            supp.setStorageMethod(rs.getString(15));
            supp.setDescribe(rs.getString(16));
            supp.setUpFrameTime(rs.getString(17));
            supp.setAuditStatus(rs.getInt(18));
            supp.setReleaseTime(rs.getString(19));
            list.add(supp);
        }
		return list;
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
    public static Integer merchandiseAdd(Integer acc_id,String nameTypeID,String name,String money,Integer specificationID,String number,Integer merStatus,String describe,String upFrametTime) throws SQLException, ParseException{
        Connection conn = Conn.conn();
        String str = "insert into merchandise(account_id,nameTypeID,`name`,money,specificationID,number,`describe`,merStatus,upFrameTime) values(?,?,?,?,?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setInt(1, acc_id);
        ps.setString(2, nameTypeID);
        ps.setString(3, name);
        ps.setString(4, money);
        ps.setInt(5, specificationID);
        ps.setString(6, number);
        ps.setString(7, describe);
        ps.setInt(8, merStatus);
        ps.setString(9, upFrametTime);
        ps.executeUpdate();// ִ���޸�
        int autoInckey = -1;
        ResultSet res = ps.getGeneratedKeys();
        if(res.next()){
            autoInckey = res.getInt(1);
        }
        return autoInckey; // ���ػ�ȡ��id
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
    public static Integer speciAdd(String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod)throws SQLException{
        Connection conn = Conn.conn();
        String str = "insert into specification_table(origin,netContent,packingMethod,brand,qGp,storageMethod) values(?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str,Statement.RETURN_GENERATED_KEYS);// ����SQL�����ݿ�  ��ȡ�ղ����������id 
        ps.setString(1, origin);
        ps.setString(2, netContent);
        ps.setString(3, packingMethod);
        ps.setString(4, brand);
        ps.setString(5, qGp);
        ps.setString(6, storageMethod);
        ps.executeUpdate();// ִ���޸�
        int autoInckey = -1;
        ResultSet res = ps.getGeneratedKeys();
        if(res.next()){
            autoInckey = res.getInt(1);
        }
        return autoInckey; // ���ػ�ȡ��id
    }
    
    /**
     * ��ӹ�Ӧ�̷���������Ϣ
     * @author zxy
     * @param relea_id
     * @param acc_id
     * @param mer_id
     * @param time
     * @return
     * @throws SQLException
     * 2017��12��28��  ����10:21:47
     */
    public static void auditsupplier(Integer relea_id,Integer acc_id,Integer mer_id,String time)throws SQLException{
        Connection conn = Conn.conn();
        String str = "INSERT INTO auditsupplier(release_id,account_id,merchandise_id,addTime) VALUES(?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str,Statement.RETURN_GENERATED_KEYS);// ����SQL�����ݿ�  ��ȡ�ղ����������id 
        ps.setInt(1, relea_id);
        ps.setInt(2, acc_id);
        ps.setInt(3, mer_id);
        ps.setString(4, time);
        ps.executeUpdate();// ִ���޸�
    }
    
    /**
     * ��ӹ�Ӧ�̵���Ʒ��Ϣ
     * @author zxy
     * @param strName ��Ʒ��Ϣ
     * @return
     * @throws Exception
     * 2017��12��14��  ����3:10:51
     */
    public static Integer suppAdd(String name,Integer mercd_id) throws Exception{
        Connection conn = Conn.conn();
        String str = "insert into supplier(commodity,mercd_id) values(?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// ����SQL�����ݿ�
        ps.setString(1, name); // ��Ӧ��id 
        ps.setInt(2, mercd_id); // ��Ʒid
        ps.executeUpdate();// ִ���޸�
        int autoInckey = -1;
        ResultSet res = ps.getGeneratedKeys();
        if(res.next()){
            autoInckey = res.getInt(1);
        }
        return autoInckey; // ���ػ�ȡ��id
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
    
    /**
     * ��������е���Ʒ
     * @author zxy
     * @param release_supplierId ������id
     * @param account_id �˻�id
     * @param id ��Ʒid
     * @throws SQLException
     */
    public static void cencel(Integer release_supplierId,Integer account_id,Integer merId) throws SQLException{
        Connection conn = Conn.conn();
        String sql1 = "delete from auditsupplier where release_id=? and account_id=? and merchandise_id=?";
        PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
        ps1.setInt(1, release_supplierId);
        ps1.setInt(2, account_id);
        ps1.setInt(3, merId);
        ps1.executeUpdate();
        ps1.close();
        String str = "update release_supplier set auditsupplier = ? where id= ? ;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setInt(1, 0);
        ps.setInt(2, merId);
        ps.executeUpdate();
    }
    
   /**
    * �¼���Ʒ
    * @author zxy
    * @param date
    * @param release_id
    * @return
    * @throws SQLException
    */
    public static String xiajia(String date,String release_id) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update release_supplier set time= ? wares_id = ? where id=?;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setString(1, date);
        ps.setInt(2, 0);
        ps.setString(3, release_id);
        ps.executeUpdate();
        String sql = "select time from release_supplier where id=?;";
        PreparedStatement psp = (PreparedStatement) conn.prepareStatement(sql);
        psp.setString(1, release_id);
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
     * �ϼ�
     * @author zxy
     * @param sup_id ��Ӧ��id
     * @param reles_id ��Ʒ����id
     * @param acc_id �˻�id
     * @param upForamTime �ϼ�ʱ��
     * @throws SQLException 
     */
    public static void shanjia(Integer sup_id,Integer mer_id,Integer acc_id,String upForamTime) throws SQLException{
    	Connection conn = Conn.conn();
        String sql = "update merchandise set merStatus = ? where id = ?;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setInt(2, mer_id);
        String sql2 = "insert into auditsupplier(release_id,account_id,merchandise_id,addTime) values(?,?,?,?);";
        PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
        ps2.setInt(1, sup_id);
        ps2.setInt(2, acc_id);
        ps2.setInt(3, mer_id);
        ps2.setString(4,upForamTime);
        ps.executeUpdate();
        ps2.executeUpdate();
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
            if(null != sel && !"".equals(sel)){ // ����
            List<Integer> paramIndex = new ArrayList<>();
            List<Object> param = new ArrayList<>();
            sql = "select su.id,aud.id,acc.id,mer.id,su.commodity,mer.money,mer.number,mo.`month`,spe.brand,spe.origin,spe.packingMethod,spe.storageMethod,mer.upFrameTime from supplier su ,merchandise mer ,specification_table spe ,merchandise_type mtype ,month_table mo,auditsupplier aud,account acc where su.mercd_id = mer.id and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and aud.release_id = su.mercd_id and mer.id = aud.merchandise_id and mer.account_id = aud.account_id and mer.account_id = acc.id ORDER BY su.id ASC";
            
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
                supp.setSuptId(resu.getInt(1));
                supp.setRelease_supplierId(resu.getInt(2));
                supp.setAccount_id(resu.getInt(3));
                supp.setMerId(resu.getInt(4));
                supp.setCommo(resu.getString(5));
                supp.setMoney(resu.getInt(6));
                supp.setNumber(resu.getInt(7));
                supp.setMonth(resu.getString(8));
                supp.setBrand(resu.getString(9));
                supp.setOrigin(resu.getString(10));
                supp.setPackingMethod(resu.getString(11));
                supp.setStorageMethod(resu.getString(12));
                supp.setUpFrameTime(resu.getString(13));
                list.add(supp);
            }
            return list;
        }else { // ҳ�����ʱ��ѯ ����
            sql = "select su.id,asp.id,acc.id,mer.id,su.commodity,mer.money,mer.number,spe.netContent,mo.`month`,spe.brand,spe.origin,spe.packingMethod,spe.storageMethod,mtype.type,mer.`describe`,mer.upFrameTime from supplier su,merchandise mer,specification_table spe,merchandise_type mtype,month_table mo,auditsupplier asp,account acc where su.mercd_id=mer.id and su.commodity = mer.`name` and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and mer.id = asp.merchandise_id and mer.account_id = asp.account_id and mer.account_id = acc.id ORDER BY su.id ASC limit ?,?;";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setInt(1, pageNow);
            pre.setInt(2, Paging.getPageNumber());
            pre.executeQuery();
            ResultSet rs = pre.getResultSet();
            while (rs.next()) {
                Supplie supp = new Supplie();
                supp.setSuptId(rs.getInt(1));
                supp.setRelease_supplierId(rs.getInt(2));
                supp.setAccount_id(rs.getInt(3));
                supp.setMerId(rs.getInt(4));
                supp.setCommo(rs.getString(5));
                supp.setMoney(rs.getInt(6));
                supp.setNumber(rs.getInt(7));
                supp.setNetContent(rs.getString(8));
                supp.setMonth(rs.getString(9));
                supp.setBrand(rs.getString(10));
                supp.setOrigin(rs.getString(11));
                supp.setPackingMethod(rs.getString(12));
                supp.setStorageMethod(rs.getString(13));
                supp.setTypeName(rs.getString(14));
                supp.setDescribe(rs.getString(15));
                supp.setUpFrameTime(rs.getString(16));
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
