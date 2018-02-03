package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.Paging;
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;
import com.yr.dao.LinkMysql;

/**
 * 
 * @作者 林水桥
 * 2018年1月24日下午10:07:39
 */
public class AuditSellerDao {
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

    private static Integer number = null;

    /**
     * 修改审核商品信息
     * @param wares_id      商品ID
     * @param spec_id       规格ID
     * @param nameType      商品类型
     * @param name          商品名称
     * @param money         商品价格
     * @param desc          商品描述
     * @param number        商品数量
     * @param origin        商品产地
     * @param netContent    商品净含量
     * @param packingMethod 包装方式
     * @param brand         品牌
     * @param qGP           保质期ID
     * @param storageMethod 储存方法
     * void
     * 2018年1月24日下午8:10:16
     */
    public static void updateAudit(Integer wares_id,Integer spec_id,String nameType,String name,Integer money,String desc,Integer number,String origin,String netContent,String packingMethod,String brand,Integer qGP,String storageMethod){
        Connection conn = LinkMysql.getCon();
        try{
         // 获取修改后的 商品类型ID
            String sql = "select id from merchandise_type where type=?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(NUB_1, nameType);
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
            ps1.setInt(NUB_5, qGP);
            ps1.setString(NUB_6, storageMethod);
            ps1.setInt(NUB_7, spec_id);
            ps1.executeUpdate();
            ps1.close();
            // 根据商品ID修改 商品数据
            String sql2 = "update merchandise set nameTypeID=?,`name`=?,money=?,`describe`=?,specificationID=?,number=? where id=?;";
            PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
            ps2.setInt(NUB_1, newNTID);
            ps2.setString(NUB_2, name);
            ps2.setInt(NUB_3, money);
            ps2.setString(NUB_4, desc);
            ps2.setInt(NUB_5, spec_id);
            ps2.setInt(NUB_6, number);
            ps2.setInt(NUB_7, wares_id);
            ps2.executeUpdate();
            ps2.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * 查询卖家审核商品
     * @param auditID 审核表ID
     * @return        审核数据
     * List<Seller>
     * 2018年1月24日下午8:15:24
     */
    public static List<Seller> queryAudit(Integer auditID){
        Connection conn = LinkMysql.getCon();
        List<Seller> list = new ArrayList<>();
        try{
            String sql = "select ads.id,ads.release_id,ads.merchandise_id,ads.account_id,spt.id,a.account,mt.type,m.`name`,m.`describe`,spt.origin,spt.netContent,spt.packingMethod,spt.brand,spt.qGP,mth.`month`,spt.storageMethod,m.money,m.number,ads.addTime from seller rs,merchandise m,merchandise_type mt,specification_table spt,month_table mth,auditseller ads,account a where a.id=ads.account_id and ads.release_id=rs.id and ads.merchandise_id=rs.wares_id and ads.account_id=m.account_id and rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id and mth.id=spt.qGP and ads.id=?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1,auditID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Seller goods = new Seller();
                goods.setAuditID(rs.getInt(NUB_1));
                goods.setId(rs.getInt(NUB_2));
                goods.setWares_id(rs.getInt(NUB_3));
                goods.setSeller_id(rs.getInt(NUB_4));
                goods.setSpeciID(rs.getInt(NUB_5));
                goods.setAuditName(rs.getString(NUB_6));
                goods.setNameType(rs.getString(NUB_7));
                goods.setName(rs.getString(NUB_8));
                goods.setDescribe(rs.getString(NUB_9));
                goods.setOrigin(rs.getString(NUB_10));
                goods.setNetContent(rs.getString(NUB_11));
                goods.setPackingMethod(rs.getString(NUB_12));
                goods.setBrand(rs.getString(NUB_13));
                goods.setqGP(rs.getInt(NUB_14));
                goods.setMonth(rs.getString(NUB_15));
                goods.setStorageMethod(rs.getString(NUB_16));
                goods.setMoney(rs.getInt(NUB_17));
                goods.setNumber(rs.getInt(NUB_18));
                goods.setAddTime(rs.getString(NUB_19));
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
     * 禁止上架
     * @param wares_id        商品ID
     * @param auditID   审核表ID
     * void
     * 2018年1月24日下午8:16:42
     */
    public static void NoneAudit(Integer wares_id,Integer auditID){
        Connection conn = LinkMysql.getCon();
        try{
            String sql = "update merchandise m set m.merStatus = ? where id = ?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1,NUB_0);
            ps.setInt(NUB_2,wares_id);
            ps.executeUpdate();
            ps.close();
            String sql1 = "delete from auditseller where id = ?;";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.setInt(NUB_1,auditID);
            ps1.executeUpdate();
            ps1.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * 允许上架
     * @param id            卖家商品表ID
     * @param auditID       审核表ID
     * @param seller_id     对应账户ID
     * @param wares_id      商品ID
     * @param date          上架时间
     * void
     * 2018年1月24日下午8:18:00
     */
    public static void passAudit(Integer id,Integer auditID,Integer seller_id,Integer wares_id,String date){
        Connection conn = LinkMysql.getCon();
        try{
            String sql2 = "insert into `release`(account_id,wares_id,time) values(?,?,?);";
            PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
            ps2.setInt(NUB_1, seller_id);
            ps2.setInt(NUB_2, wares_id);
            ps2.setString(NUB_3, date);
            ps2.executeUpdate();
            ps2.close();
            String sql = "update seller rs set rs.time=? where id=?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(NUB_1,date);
            ps.setInt(NUB_2,id);
            ps.executeUpdate();
            ps.close();
            String sql3 = "update merchandise set merStatus = ? where id = ?;";
            PreparedStatement ps3 = (PreparedStatement) conn.prepareStatement(sql3);
            ps3.setInt(NUB_1, NUB_2);
            ps3.setInt(NUB_2, wares_id);
            ps3.executeUpdate();
            ps3.close();
            String sql1 = "delete from auditseller where id = ?;";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.setInt(NUB_1,auditID);
            ps1.executeUpdate();
            ps1.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 查询数据并用list封装起来
     *
     * @param abc
     *            账号
     * @param pageNow
     *            当前页
     * @param sel
     *            判断是否用了查询功能
     * @return 返回所查询的数据
     */
    public static List<Seller> selectGoods(String abc, Integer pageNow, String sel) {
        String sql = "";
        List<Seller> list = new ArrayList<>();
        if (pageNow < NUB_1) {
            pageNow = NUB_1;
        }
        try {
            Connection conn = LinkMysql.getCon();
            if (null != sel && !"".equals(sel)) {
                pageNow=NUB_0;
                String sql1 = "select count(*) from auditseller ads,account ac,merchandise m,merchandise_type mt,seller rs where m.nameTypeID=mt.id and rs.id=ads.release_id and m.account_id=ads.account_id and m.account_id=ac.id AND m.id=rs.wares_id and m.id=ads.merchandise_id and (m.`name` like ? or ac.account like ?);";
                PreparedStatement pre1 = (PreparedStatement) conn.prepareStatement(sql1);
                pre1.setString(NUB_1, "%" + ConnectTime.decodeSpecialCharsWhenLikeUseSlash(sel) + "%");
                pre1.setString(NUB_2, "%" + ConnectTime.decodeSpecialCharsWhenLikeUseSlash(sel) + "%");
                ResultSet rs1 = pre1.executeQuery();
                while(rs1.next()){
                    number = rs1.getInt(NUB_1);
                }
                rs1.close();
                pre1.close();
                List<Integer> paramIndex = new ArrayList<>();
                List<Object> param = new ArrayList<>();
                sql = "select ads.release_id,ads.id,ads.account_id,ads.merchandise_id,ac.account,mt.type,m.`name`,m.merStatus,ads.addTime from auditseller ads,account ac,merchandise m,merchandise_type mt,seller rs where m.nameTypeID=mt.id and rs.id=ads.release_id and m.account_id=ads.account_id and m.account_id=ac.id AND m.id=rs.wares_id and m.id=ads.merchandise_id ";
                if (null != abc && !"".equals(abc)) {
                    sql = sql + " and (m.`name` like ? ";
                    paramIndex.add(NUB_0);
                    param.add(abc);
                    sql = sql + " or ac.account like ?)";
                    paramIndex.add(NUB_0);
                    param.add(sel);
                }
                sql = sql + " order by ads.id limit ?,?";
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
                    Seller goods = new Seller();
                    goods.setId(rs.getInt(NUB_1));
                    goods.setAuditID(rs.getInt(NUB_2));
                    goods.setSeller_id(rs.getInt(NUB_3));
                    goods.setWares_id(rs.getInt(NUB_4));
                    goods.setAuditName(rs.getString(NUB_5));
                    goods.setNameType(rs.getString(NUB_6));
                    goods.setName(rs.getString(NUB_7));
                    goods.setAuditStatus(rs.getInt(NUB_8));
                    goods.setAddTime(rs.getString(NUB_9));
                    list.add(goods);
                }
                rs.close();
                pre.close();
                return list;
            } else {// 查询所有数据
                pageNow = (pageNow - 1) * 10;
                number = null;
                sql = "select ads.release_id,ads.id,ads.account_id,ads.merchandise_id,ac.account,mt.type,m.`name`,m.merStatus,ads.addTime from auditseller ads,account ac,merchandise m,merchandise_type mt,seller rs where m.nameTypeID=mt.id and rs.id=ads.release_id and m.account_id=ads.account_id and m.account_id=ac.id AND m.id=rs.wares_id and m.id=ads.merchandise_id ORDER BY ads.id asc limit ?,?;";
                PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
                pre.setInt(NUB_1, pageNow);
                pre.setInt(NUB_2, Paging.getPageNumber());
                pre.executeQuery();
                ResultSet rs = pre.getResultSet();
                while (rs.next()) {
                    Seller goods = new Seller();
                    goods.setId(rs.getInt(NUB_1));
                    goods.setAuditID(rs.getInt(NUB_2));
                    goods.setSeller_id(rs.getInt(NUB_3));
                    goods.setWares_id(rs.getInt(NUB_4));
                    goods.setAuditName(rs.getString(NUB_5));
                    goods.setNameType(rs.getString(NUB_6));
                    goods.setName(rs.getString(NUB_7));
                    goods.setAuditStatus(rs.getInt(NUB_8));
                    goods.setAddTime(rs.getString(NUB_9));
                    list.add(goods);
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
     * 获得总页数
     *
     * @return 返回总页数
     */
    public static Integer getPageCount() {
        int total = 0;// 总共多少条记录
        int pageCount = 0;// 总页数
        if(null == number) {
            Connection conn = LinkMysql.getCon();
            try {
                String sql = "select count(*) from auditseller;";
                PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
                prepar.executeQuery();
                ResultSet resu = prepar.getResultSet();
                while (resu.next()) {
                    total = resu.getInt(1);
                }
                resu.close();
                prepar.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            total = number;
        }
        if (total % Paging.getPageNumber() == 0) {
            pageCount = total / Paging.getPageNumber();
        } else {
            pageCount = total / Paging.getPageNumber() + 1;
        }
        if(total < 11){
            pageCount = 0;
        }
        return pageCount;
    }
}
