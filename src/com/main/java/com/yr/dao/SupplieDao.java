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
    public static final String webUrl4 = "http://www.ntsc.ac.cn"; // �й���ѧԺ������ʱ����
    static List<Supplie> list = new ArrayList<>();

    /**
     * �޸Ĳ�ѯ
     * 
     * @author zxy
     * @param merId
     * @return
     * @throws SQLException
     * 2017��12��14��  ����3:11:13
     *
     */
    public static List<Supplie> queryAll(Integer merId) throws SQLException{
        Connection conn = Conn.conn();
        List<Supplie> list = new ArrayList<>();
        String sql = "select sup.id ,act.id ,m.id ,act.`name` ,mt.type ,m.`name` ,m.money ,m.number ,spe.netContent ,spe.origin ,spe.packingMethod ,spe.brand ,mo.`month` ,spe.storageMethod ,m.`describe` ,m.upFrameTime ,m.merStatus from merchandise m,supplier sup,merchandise_type mt,account act ,specification_table spe ,month_table mo where m.specificationID = spe.id and spe.qGP = mo.id and m.id = sup.mercd_id and m.account_id = act.id and m.nameTypeID = mt.id and m.id = ? ORDER BY sup.id ASC;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);// ����SQL�����ݿ�
        ps.setInt(1, merId);
        ps.executeQuery(); // ִ�в�ѯ
        ResultSet resu = ps.getResultSet();// ��ȡ��ѯ���
        // ѭ�������
        while (resu.next()) { // ȡ������е���һ����
        	Supplie supp = new Supplie();
        	supp.setSuptId(resu.getInt(1));
            supp.setAccount_id(resu.getInt(2));
            supp.setMerId(resu.getInt(3));
            supp.setAccount(resu.getString(4));
            supp.setTypeName(resu.getString(5));
            supp.setCommo(resu.getString(6));
            supp.setMoney(resu.getInt(7));
            supp.setNumber(resu.getInt(8));
            supp.setNetContent(resu.getString(9));
            supp.setOrigin(resu.getString(10));
            supp.setPackingMethod(resu.getString(11));
            supp.setBrand(resu.getString(12));
            supp.setMonth(resu.getString(13));
            supp.setStorageMethod(resu.getString(14));
            supp.setDescribe(resu.getString(15));
            supp.setUpFrameTime(resu.getString(16));
            supp.setAuditStatus(resu.getInt(17));
            list.add(supp);
        }
        return list;
    }
    
    /**
     * ��ѯ������Ϣ
     * 
     * @author zxy
     * @return
     * @throws SQLException
     * 2017��12��14��  ����3:11:13
     *
     */
    public static List<Supplie> selc() throws SQLException{
    	Connection conn = Conn.conn();
        List<Supplie> list = new ArrayList<>();
        String sql = "select sup.id ,act.id ,m.id ,act.`name` ,mt.type ,m.`name` ,m.money ,m.number ,spe.netContent ,spe.origin ,spe.packingMethod ,spe.brand ,mo.`month` ,spe.storageMethod ,m.`describe` ,m.upFrameTime ,m.merStatus from merchandise m,supplier sup,merchandise_type mt,account act ,specification_table spe ,month_table mo where m.specificationID = spe.id and spe.qGP = mo.id and m.id=sup.mercd_id and m.account_id=act.id and m.nameTypeID=mt.id ORDER BY sup.id ASC;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);// ����SQL�����ݿ�
        ps.executeQuery(); // ִ�в�ѯ
        ResultSet resu = ps.getResultSet();// ��ȡ��ѯ���
        // ѭ�������
        while (resu.next()) { // ȡ������е���һ����
        	Supplie supp = new Supplie();
        	supp.setSuptId(resu.getInt(1));
            supp.setAccount_id(resu.getInt(2));
            supp.setMerId(resu.getInt(3));
            supp.setAccount(resu.getString(4));
            supp.setTypeName(resu.getString(5));
            supp.setCommo(resu.getString(6));
            supp.setMoney(resu.getInt(7));
            supp.setNumber(resu.getInt(8));
            supp.setNetContent(resu.getString(9));
            supp.setOrigin(resu.getString(10));
            supp.setPackingMethod(resu.getString(11));
            supp.setBrand(resu.getString(12));
            supp.setMonth(resu.getString(13));
            supp.setStorageMethod(resu.getString(14));
            supp.setDescribe(resu.getString(15));
            supp.setUpFrameTime(resu.getString(16));
            supp.setAuditStatus(resu.getInt(17));
            list.add(supp);
        }
		return list;
    }
    
    /**
     * �����Ʒ��Ϣ
     * 
     * @author zxy
     * @param acc_id �˺�id
     * @param nameTypeID ����id
     * @param name ��Ʒ��
     * @param money �۸�
     * @param specificationID ���id
     * @param number ����
     * @param merStatus ��Ʒ״̬
     * @param describe ��Ʒ����
     * @param upFrametTime �ϼ�ʱ��
     * @return
     * @throws SQLException
     * @throws ParseException
     * 2017��12��14��  ����5:35:56 
     *
     */
    public static Integer merchandiseAdd(Integer acc_id,String nameTypeID,String name,String money,Integer specificationID,String number,Integer merStatus,String describe,String upFrametTime) throws SQLException, ParseException{
        Connection conn = Conn.conn();
        String str = "insert into merchandise(account_id,nameTypeID,`name`,money,specificationID,number,`describe`,merStatus,upFrameTime) values(?,?,?,?,?,?,?,?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str,Statement.RETURN_GENERATED_KEYS);// ����SQL�����ݿ�
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
     * 
     * @author zxy
     * @param origin
     * @param netContent
     * @param packingMethod
     * @param brand
     * @param qGp
     * @param storageMethod
     * @return
     * @throws SQLException
     * 2017��12��28��  ����10:21:47
     *
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
     * ��ӹ�Ӧ�̵���Ʒ��Ϣ
     * 
     * @author zxy
     * @param name ��Ʒ��
     * @param mercd_id ��Ʒid
     * @return
     * @throws Exception
     * 2017��12��14��  ����3:10:51
     *
     */
    public static Integer suppAdd(String name,Integer mercd_id) throws Exception{
        Connection conn = Conn.conn();
        String str = "insert into supplier(commodity,mercd_id) values(?,?);"; // id������
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str,Statement.RETURN_GENERATED_KEYS);// ����SQL�����ݿ�
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
     * 
     * @author zxy
     * @param webUrl
     * @return
     * 2017��12��16��  ����11:31:57
     *
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
     * ��������е���Ʒ
     * 
     * @author zxy
     * @param account_id  �˻�id
     * @param merId ��Ʒid
     * @throws SQLException
     * 2018��12��4�� ����9:57:52 
     *
     */
    public static void cencel(Integer account_id,Integer merId) throws SQLException{
        Connection conn = Conn.conn();
        String str3 = "update merchandise set merStatus = ? where id = ?";
        PreparedStatement pre = (PreparedStatement) conn.prepareStatement(str3);
        pre.setInt(1, 0);
        pre.setInt(2, merId);
        String sql1 = "delete from auditsupplier where account_id=? and merchandise_id=?";
        PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
        ps1.setInt(1, account_id);
        ps1.setInt(2, merId);
        pre.executeUpdate();
        ps1.executeUpdate();
    }
    
    /**
     * �¼���Ʒ
     * 
     * @author zxy
     * @param merId ��Ʒid
     * @throws SQLException
     * 2017��12��3�� ����9:57:21 
     *
     */
    public static void xiajia(String merId) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update merchandise set merStatus = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setInt(1, 0);
        ps.setString(2, merId);
        String str3 = "delete from release_supplier where wares_id = ?";
        PreparedStatement pre = (PreparedStatement) conn.prepareStatement(str3);
        pre.setString(1, merId);
        ps.executeUpdate();
        pre.executeUpdate();
    }
    
    /**
     * �ϼ�
     * 
     * @author zxy
     * @param sup_id ��Ӧ��id
     * @param mer_id ��Ʒid
     * @param acc_id �˺�id
     * @param upForamTime �ϼ�ʱ��
     * @throws SQLException
     * 2017��12��26�� ����9:56:17 
     *
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
     * 
     * @author zxy
     * @param money
     * @param number
     * @param describe
     * @param id
     * @throws SQLException
     * 2017��12��14��  ����5:41:00
     *
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
     * 
     * @author zxy
     * @param netContent
     * @param packingMethod
     * @param qGp
     * @param speId
     * @throws SQLException
     * 2017��12��28��  ����10:06:30
     * 
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
     * 
     * @author zxy
     * @param pageNow ��ǰҳ
     * @param account �˺���   (ͬʱ�ж��ǲ���ʹ����������)
     * @param sel �ж��Ƿ����˲�ѯ����
     * @return ��������ѯ������
     * 2018��2��3�� ����9:53:50 
     *
     */
    public static List<Supplie> selectemp(Integer pageNow,String account, String sel) {
        Connection conn = Conn.conn();
        String sql = "";
        List<Supplie> list = new ArrayList<>();
        if (pageNow < 1) {
            pageNow = 1;
        }
        
        try {
            if(null != sel && !"".equals(sel)){ // ����
            pageNow = 0;
            sql = "select su.id,acc.id,mer.id,acc.account,su.commodity,mer.money,mer.number,spe.netContent,mo.`month`,spe.brand,spe.origin,spe.packingMethod,spe.storageMethod,mtype.type,mer.`describe`,mer.upFrameTime,mer.merStatus from supplier su,merchandise mer,specification_table spe,merchandise_type mtype,month_table mo, account acc where su.mercd_id=mer.id and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and mer.account_id = acc.id and acc.account like ? ORDER BY su.id ASC limit ?,? ;";
            PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
			String accountName = decodeSpecialCharsWhenLikeUseSlash(sel);
			prepar.setString(1,"%" + accountName + "%");
			prepar.setInt(2,pageNow);
			prepar.setInt(3,Paging.getPageNumber());
            ResultSet resu = prepar.executeQuery();
            while (resu.next()) {
            	Supplie supp = new Supplie();
            	supp.setSuptId(resu.getInt(1));
                supp.setAccount_id(resu.getInt(2));
                supp.setMerId(resu.getInt(3));
                supp.setAccount(resu.getString(4));
                supp.setCommo(resu.getString(5));
                supp.setMoney(resu.getInt(6));
                supp.setNumber(resu.getInt(7));
                supp.setNetContent(resu.getString(8));
                supp.setMonth(resu.getString(9));
                supp.setBrand(resu.getString(10));
                supp.setOrigin(resu.getString(11));
                supp.setPackingMethod(resu.getString(12));
                supp.setStorageMethod(resu.getString(13));
                supp.setTypeName(resu.getString(14));
                supp.setDescribe(resu.getString(15));
                supp.setUpFrameTime(resu.getString(16));
                supp.setAuditStatus(resu.getInt(17));
                list.add(supp);
            }
            return list;
        }else { // ҳ�����ʱ��ѯ ����
        	pageNow = (pageNow - 1) * 10;
            sql = "select su.id,acc.id,mer.id,acc.account,su.commodity,mer.money,mer.number,spe.netContent,mo.`month`,spe.brand,spe.origin,spe.packingMethod,spe.storageMethod,mtype.type,mer.`describe`,mer.upFrameTime,mer.merStatus from supplier su,merchandise mer,specification_table spe,merchandise_type mtype,month_table mo, account acc where su.mercd_id=mer.id and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and mer.account_id = acc.id ORDER BY su.id ASC limit ?,?;";
            PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
            pre.setInt(1, pageNow);
            pre.setInt(2, Paging.getPageNumber());
            pre.executeQuery();
            ResultSet rs = pre.getResultSet();
            while (rs.next()) {
            	Supplie supp = new Supplie();
                supp.setSuptId(rs.getInt(1));
                supp.setAccount_id(rs.getInt(2));
                supp.setMerId(rs.getInt(3));
                supp.setAccount(rs.getString(4));
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
                supp.setAuditStatus(rs.getInt(17));
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
     * ����ģ����ѯ������
     * 
     * @author zxy
     * @param accName �˺���
     * @return
     * 2018��2��3�� ����10:50:22 
     *
     */
    public static String decodeSpecialCharsWhenLikeUseSlash(String accName) {
        String afterDecode = accName.replaceAll("'", "''");
        afterDecode = afterDecode.replaceAll("\\\\", "\\\\\\\\");
        afterDecode = afterDecode.replaceAll("%", "\\\\%");
        afterDecode = afterDecode.replaceAll("_", "\\\\_");
        return afterDecode;
    }
    
    /**
     * �����ҳ��
     * 
     * @author zxy
     * @param account �˺���
     * @return ������ҳ��
     * 2018��2��3�� ����9:55:38 
     *
     */
    public static Integer getPageCount(String account) {
        Connection conn = Conn.conn();
        int total = 0;// �ܹ���������¼
        int pageCount = 0;// ��ҳ��
        try {
            String sql = "select count(*) from supplier su,merchandise mer,specification_table spe,merchandise_type mtype,month_table mo, account acc where su.mercd_id=mer.id and mer.specificationID = spe.id and mer.nameTypeID = mtype.id and spe.qGP = mo.id and mer.account_id = acc.id ";
            PreparedStatement prepar = conn.prepareStatement(sql);
            prepar.executeQuery();
            ResultSet resu = prepar.getResultSet();
            while (resu.next()) {
                total = resu.getInt(1);
            }
            if (account != null && !account.equals("")){
            	sql = sql + "and acc.account = ? ORDER BY su.id ASC ";
            	PreparedStatement pre = conn.prepareStatement(sql);
            	pre.setString(1, account);
            	pre.executeQuery();
                ResultSet re = pre.getResultSet();
                while (re.next()) {
                    total = re.getInt(1);
                }
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
}
