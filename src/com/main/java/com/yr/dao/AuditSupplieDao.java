package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.Paging;
import com.yr.pojo.Supplie;
import com.yr.util.Conn;
import com.yr.util.ConnectTime;

/**
 * 
 * @author zxy
 *
 */
public class AuditSupplieDao {
    private static final int NUB_0 = 0;
    private static final int NUB_1 = 1;
    private static final int NUB_2 = 2;
    private static final int NUB_3 = 3;
    private static final int NUB_4 = 4;
    private static final int NUB_5 = 5;
    private static final int NUB_6 = 6;
    private static final int NUB_7 = 7;
    private static final int NUB_8 = 8;
    private static final int NUB_9 = 9;
    private static final int NUB_10 = 10;
    private static final int NUB_11 = 11;
    private static final int NUB_12 = 12;
    private static final int NUB_13 = 13;
    private static final int NUB_14 = 14;
    private static final int NUB_15 = 15;
    private static final int NUB_16 = 16;
    private static final int NUB_17 = 17;
    private static final int NUB_18 = 18;
    private static final int NUB_19 = 19;

    private static Integer num = null;
    
    /**
     * 允许上架
     * @param suptId        供应商表ID
     * @param auditId       审核表ID
     * @param account_id    账户ID
     * @param merId         商品ID
     * void
     * 2018年2月2日上午9:07:07
     */
    public static void passAudit(Integer suptId, Integer auditId, Integer account_id, Integer merId, String date){
        Connection conn = Conn.conn();
        try{
            String sql2 = "insert into `release`(account_id,wares_id,time) values(?,?,?);";
            PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
            ps2.setInt(NUB_1, account_id);
            ps2.setInt(NUB_2, merId);
            ps2.setString(NUB_3, date);
            ps2.executeUpdate();
            ps2.close();
            String sql3 = "update merchandise set merStatus = ? where id = ?;";
            PreparedStatement ps3 = (PreparedStatement) conn.prepareStatement(sql3);
            ps3.setInt(NUB_1, NUB_2);
            ps3.setInt(NUB_2, merId);
            ps3.executeUpdate();
            ps3.close();
            String sql1 = "delete from auditsupplier where id = ?;";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.setInt(NUB_1,auditId);
            ps1.executeUpdate();
            ps1.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 禁止上架
     * @param merId             商品ID
     * @param auditId           供应商审核表ID
     * void
     * 2018年2月2日上午8:58:37
     */
    public static void NoneAudit(Integer merId, Integer auditId){
        Connection conn = Conn.conn();
        try{
            String sql = "update merchandise m set m.merStatus = ? where id = ?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1,NUB_0);
            ps.setInt(NUB_2,merId);
            ps.executeUpdate();
            ps.close();
            String sql1 = "delete from auditsupplier where id = ?;";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.setInt(NUB_1,auditId);
            ps1.executeUpdate();
            ps1.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 审核供应商修改
     * @param merId             商品ID
     * @param speId             规格ID
     * @param typeName          商品类型
     * @param commo             商品名称
     * @param money             商品价格
     * @param describe          商品描述
     * @param number            商品数量
     * @param origin            商品产地
     * @param netContent        商品净含量    
     * @param packingMethod     包装方式
     * @param brand             品牌
     * @param month_tableId     保质期ID
     * @param storageMethod     储存方法
     * void
     * 2018年1月26日下午7:27:04
     */
    public static void updateAudit(Integer merId,Integer speId,String typeName,String commo,Integer money,String describe,Integer number,String origin,String netContent,String packingMethod,String brand,Integer month_tableId,String storageMethod){
        Connection conn = Conn.conn();
        try{
         // 获取修改后的 商品类型ID
            String sql = "select id from merchandise_type where type=?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(NUB_1, typeName);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            int newNTID = 0;
            while (rs.next()) {
                newNTID = rs.getInt(NUB_1);
            }
            rs.close();
            ps.close();
            // 根据规格ID修改 规格数据
            String sql1 = "update specification_table set origin=?,netContent=?,packingMethod=?,brand=?,qGP=?,storageMethod=? where id=?;";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.setString(NUB_1, origin);
            ps1.setString(NUB_2, netContent);
            ps1.setString(NUB_3, packingMethod);
            ps1.setString(NUB_4, brand);
            ps1.setInt(NUB_5, month_tableId);
            ps1.setString(NUB_6, storageMethod);
            ps1.setInt(NUB_7, speId);
            ps1.executeUpdate();
            ps1.close();
            // 根据商品ID修改 商品数据
            String sql2 = "update merchandise set nameTypeID=?,`name`=?,money=?,`describe`=?,specificationID=?,number=? where id=?;";
            PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
            ps2.setInt(NUB_1, newNTID);
            ps2.setString(NUB_2, commo);
            ps2.setInt(NUB_3, money);
            ps2.setString(NUB_4, describe);
            ps2.setInt(NUB_5, speId);
            ps2.setInt(NUB_6, number);
            ps2.setInt(NUB_7, merId);
            ps2.executeUpdate();
            ps2.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 查询供应商审核商品
     * @param auditID 审核表ID
     * @return        审核数据
     * List<Seller>
     * 2018年1月24日下午8:15:24
     */
    public static List<Supplie> queryAudit(Integer auditID){
        Connection conn = Conn.conn();
        List<Supplie> list = new ArrayList<>();
        try{
            String sql = "select ads.id,ads.release_id,ads.merchandise_id,ads.account_id,spt.id,a.`name`,mt.type,m.`name`,m.`describe`,spt.origin,spt.netContent,spt.packingMethod,spt.brand,spt.qGP,mth.`month`,spt.storageMethod,m.money,m.number,ads.addTime from supplier rs,merchandise m,merchandise_type mt,specification_table spt,month_table mth,auditsupplier ads,account a where a.id=ads.account_id and ads.release_id=rs.id and ads.merchandise_id=rs.mercd_id and ads.account_id=m.account_id and rs.mercd_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id and mth.id=spt.qGP and m.`name`=rs.commodity and ads.id=?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1,auditID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Supplie goods = new Supplie();
                goods.setAuditId(rs.getInt(NUB_1));
                goods.setSuptId(rs.getInt(NUB_2));
                goods.setMerId(rs.getInt(NUB_3));
                goods.setAccount_id(rs.getInt(NUB_4));
                goods.setSpeId(rs.getInt(NUB_5));
                goods.setAccount(rs.getString(NUB_6));
                goods.setTypeName(rs.getString(NUB_7));
                goods.setCommo(rs.getString(NUB_8));
                goods.setDescribe(rs.getString(NUB_9));
                goods.setOrigin(rs.getString(NUB_10));
                goods.setNetContent(rs.getString(NUB_11));
                goods.setPackingMethod(rs.getString(NUB_12));
                goods.setBrand(rs.getString(NUB_13));
                goods.setMonth_tableId(rs.getInt(NUB_14));
                goods.setMonths(rs.getString(NUB_15));
                goods.setStorageMethod(rs.getString(NUB_16));
                goods.setMoney(Integer.valueOf(rs.getString(NUB_17)));
                goods.setNumber(rs.getInt(NUB_18));
                goods.setReleaseTime(rs.getString(NUB_19));
                list.add(goods);
            }
            rs.close();
            ps.close();
            return list;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 查询数据并用list封装起来
     * @param pageNow 当前页
     * @param sel 判断是否用了查询功能
     * @return 返回所查询的数据
     */
    public static List<Supplie> selectemp(Integer pageNow, String sel) {
        Connection conn = Conn.conn();
        String sql = "";
        List<Supplie> list = new ArrayList<>();
        if (pageNow < NUB_1) {
            pageNow = NUB_1;
        }
        try {
            if(null != sel && !"".equals(sel)){
                pageNow=NUB_0;
                String sql1 = "select count(*) from merchandise m,auditsupplier asp,supplier sup,merchandise_type mt,account act where m.id=sup.mercd_id and sup.id=asp.release_id and m.id=asp.merchandise_id and m.account_id=asp.account_id and m.account_id=act.id and m.nameTypeID=mt.id and m.`name` like ?";
                PreparedStatement pre1 = (PreparedStatement) conn.prepareStatement(sql1);
                pre1.setString(NUB_1, "%" + ConnectTime.decodeSpecialCharsWhenLikeUseSlash(sel) + "%");
                ResultSet rs1 = pre1.executeQuery();
                while(rs1.next()){
                    num = rs1.getInt(NUB_1);
                }
                rs1.close();
                pre1.close();
                List<Integer> paramIndex = new ArrayList<>();
                List<Object> param = new ArrayList<>();
                sql = "select sup.id,asp.id,act.id,m.id,act.`name`,mt.type,m.`name`,m.merStatus,asp.addTime from merchandise m,auditsupplier asp,supplier sup,merchandise_type mt,account act where m.id=sup.mercd_id and sup.id=asp.release_id and m.id=asp.merchandise_id and m.account_id=asp.account_id and m.account_id=act.id and m.nameTypeID=mt.id ";
                if (null != sel && !"".equals(sel)) {
                    sql = sql + " and m.`name` like ?";
                    paramIndex.add(NUB_0);
                    param.add(sel);
                }
                sql = sql + " order by asp.id limit ?,?";
                paramIndex.add(NUB_1);
                paramIndex.add(NUB_1);
                
                param.add(pageNow);
                param.add(Paging.getPageNumber());
                
                PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
                
                for (int i = 0; i < paramIndex.size(); i++) {
                    int mark = paramIndex.get(i);
                    if (mark == 0) {
                        pre.setString((i + 1), "%" + ConnectTime.decodeSpecialCharsWhenLikeUseSlash((String) param.get(i)) + "%");
                    } else if (mark == 1) {
                        pre.setInt((i + 1), (Integer) param.get(i));
                    }
                }
                pre.executeQuery();
                ResultSet rs = pre.getResultSet();
                while (rs.next()) {
                    Supplie supp = new Supplie();
                    supp.setSuptId(rs.getInt(NUB_1));
                    supp.setAuditId(rs.getInt(NUB_2));
                    supp.setAccount_id(rs.getInt(NUB_3));
                    supp.setMerId(rs.getInt(NUB_4));
                    supp.setAccount(rs.getString(NUB_5));
                    supp.setTypeName(rs.getString(NUB_6));
                    supp.setCommo(rs.getString(NUB_7));
                    supp.setAuditStatus(rs.getInt(NUB_8));
                    supp.setAuditTime(rs.getString(NUB_9));
                    list.add(supp);
                }
                rs.close();
                pre.close();
                return list;
            }else{
                pageNow = (pageNow - 1) * 10;
                num = null;
                sql = "select sup.id,asp.id,act.id,m.id,act.`name`,mt.type,m.`name`,m.merStatus,asp.addTime from merchandise m,auditsupplier asp,supplier sup,merchandise_type mt,account act where m.id=sup.mercd_id and sup.id=asp.release_id and m.id=asp.merchandise_id and m.account_id=asp.account_id and m.account_id=act.id and m.nameTypeID=mt.id ORDER BY asp.id ASC limit ?,?;";
                PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
                pre.setInt(1, pageNow);
                pre.setInt(2, Paging.getPageNumber());
                pre.executeQuery();
                ResultSet rs = pre.getResultSet();
                while (rs.next()) {
                    Supplie supp = new Supplie();
                    supp.setSuptId(rs.getInt(NUB_1));
                    supp.setAuditId(rs.getInt(NUB_2));
                    supp.setAccount_id(rs.getInt(NUB_3));
                    supp.setMerId(rs.getInt(NUB_4));
                    supp.setAccount(rs.getString(NUB_5));
                    supp.setTypeName(rs.getString(NUB_6));
                    supp.setCommo(rs.getString(NUB_7));
                    supp.setAuditStatus(rs.getInt(NUB_8));
                    supp.setAuditTime(rs.getString(NUB_9));
                    list.add(supp);
                }
                rs.close();
                pre.close();
                return list;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获得总页数
     *
     * @return 返回总页数
     */
    public static Integer getPageCount() {
        int total = 0;// 总共多少条记录
        int pageCount = 0;// 总页数
        if(null == num) {
            Connection conn = Conn.conn();
            try {
                String sql = "select count(*) from auditsupplier;";
                PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
                prepar.executeQuery();
                ResultSet resu = prepar.getResultSet();
                while (resu.next()) {
                    total = resu.getInt(1);
                }
                resu.close();
                prepar.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            total = num;
        }
        if (total % Paging.getPageNumber() == 0) {
            pageCount = total / Paging.getPageNumber();
        } else {
            pageCount = total / Paging.getPageNumber() + 1;
        }
        return pageCount;
    }
}
