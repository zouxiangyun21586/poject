package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.Paging;
import com.yr.pojo.Seller;
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
                goods.setMonth(rs.getString(NUB_15));
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
                sql = sql + " and m.`name` like ?";
                paramIndex.add(NUB_0);
                param.add(sel);
                
                sql = sql + " limit ?,?";
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
