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
 * @作者 林水桥 2018年1月2日上午9:36:37
 */
public class SellerDao {
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
     * 查询卖家保存商品的所有信息
     * @param id        卖家商品表ID
     * @return          卖家商品信息
     * List<Seller>
     * 2018年1月24日下午8:21:50
     */
    public static List<Seller> queryGoods(Integer id) {
        Connection conn = LinkMysql.getCon();
        List<Seller> list = new ArrayList<>();
        try {
            String sql = "select mt.type,m.`name`,m.`describe`,spt.origin,spt.netContent,spt.packingMethod,spt.brand,mth.`month`,spt.qGP,spt.storageMethod,m.money,m.number,m.upFrameTime,rs.time,rs.downtime,rs.id,m.id,spt.id,m.nameTypeID from seller rs,merchandise m,merchandise_type mt,specification_table spt,month_table mth where rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id and mth.id=spt.qGP and rs.id=?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1, id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Seller goods = new Seller();
                goods.setNameType(rs.getString(NUB_1));
                goods.setName(rs.getString(NUB_2));
                goods.setDescribe(rs.getString(NUB_3));
                goods.setOrigin(rs.getString(NUB_4));
                goods.setNetContent(rs.getString(NUB_5));
                goods.setPackingMethod(rs.getString(NUB_6));
                goods.setBrand(rs.getString(NUB_7));
                goods.setMonth(rs.getString(NUB_8));
                goods.setqGP(rs.getInt(NUB_9));
                goods.setStorageMethod(rs.getString(NUB_10));
                goods.setMoney(rs.getString(NUB_11));
                goods.setNumber(rs.getInt(NUB_12));
                goods.setUpFrameTime(rs.getString(NUB_13));
                goods.setTime(rs.getString(NUB_14));
                goods.setDowntime(rs.getString(NUB_15));
                goods.setId(rs.getInt(NUB_16));
                goods.setWares_id(rs.getInt(NUB_17));
                goods.setSpeciID(rs.getInt(NUB_18));
                goods.setNameTypeID(rs.getInt(NUB_19));
                list.add(goods);
            }
            rs.close();
            ps.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 删除保存的商品信息
     * @param id            卖家商品表ID
     * @param sellers_id    对应账户ID
     * @param wares_id      商品ID
     * @param time          上架时间
     * @param downtime      下架时间
     * @param date          删除时间
     * void
     * 2018年1月24日下午8:23:44
     */
    public static void delGoods(Integer id,Integer sellers_id,Integer wares_id,String time,String downtime,String date){
        Connection conn = LinkMysql.getCon();
        try {
            String sql = "insert into recovery_seller(rs_id,seller_id,wares_id,audits,`time`,downtime,deleteTime) values(?,?,?,?,?,?,?);";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1, id);
            ps.setInt(NUB_2, sellers_id);
            ps.setInt(NUB_3, wares_id);
            ps.setInt(NUB_4, NUB_0);
            if("".equals(time)){
                ps.setString(NUB_5, null);
                ps.setString(NUB_6, null);
            }else {
                ps.setString(NUB_5, time);
                ps.setString(NUB_6, downtime);
            }
            ps.setString(NUB_7, date);
            ps.executeUpdate();
            ps.close();
            String sql1 = "delete from seller where id = ? and wares_id =?;";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.setInt(NUB_1, id);
            ps1.setInt(NUB_2, wares_id);
            ps1.executeUpdate();
            ps1.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 撤销正在审核的商品
     * @param id            卖家商品表ID
     * @param account_id    对应账户ID
     * @param wares_id      商品ID
     * void
     * 2018年1月24日下午8:26:38
     */
    public static void cancelAudit(Integer id,Integer account_id,Integer wares_id){
        Connection conn = LinkMysql.getCon();
        try {
            String sql1 = "delete from auditSeller where release_id=? and account_id=? and merchandise_id=?";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.setInt(NUB_1, id);
            ps1.setInt(NUB_2, account_id);
            ps1.setInt(NUB_3, wares_id);
            ps1.executeUpdate();
            ps1.close();
            String sql = "update seller set audits = ? where id = ?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1, NUB_0);
            ps.setInt(NUB_2, id);
            ps.executeUpdate();
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 下架发布商品
     * @param id            卖家商品表ID
     * @param seller_id     对应账户ID
     * @param wares_id      商品ID
     * @param date          下架时间
     * @return
     * List<Seller>
     * 2018年1月24日下午8:40:27
     */
    public static List<Seller> underGoods(Integer id,Integer seller_id,Integer wares_id,String date){
        Connection conn = LinkMysql.getCon();
        List<Seller> list = new ArrayList<>();
        try {
            String sql = "update seller set downtime=?,audits=? where id=?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(NUB_1, date);
            ps.setInt(NUB_2, NUB_0);
            ps.setInt(NUB_3, id);
            ps.executeUpdate();
            ps.close();
            String sql2 = "delete from `release` where account_id = ? and wares_id = ?;";
            PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
            ps2.setInt(NUB_1, seller_id);
            ps2.setInt(NUB_2, wares_id);
            ps2.executeUpdate();
            ps2.close();
            String sql1 = "select downtime from seller where id=?;";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.setInt(NUB_1, id);
            ps1.executeQuery();
            ResultSet rs1 = ps1.getResultSet();
            while(rs1.next()){
                Seller goods = new Seller();
                goods.setDowntime(rs1.getString(NUB_1));
                list.add(goods);
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 卖家申请上架商品
     * @param id            卖家商品表ID
     * @param account_id    对应账户ID
     * @param wares_id      商品ID
     * @param date          申请时间
     * void
     * 2018年1月24日下午8:42:44
     */
    public static void upGoods(Integer id,Integer account_id,Integer wares_id,String date){
        Connection conn = LinkMysql.getCon();
        try{
            String sql = "update seller set audits = ? where id = ?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1, NUB_1);
            ps.setInt(NUB_2, id);
            ps.executeUpdate();
            ps.close();
            String sql2 = "insert into auditSeller(release_id,account_id,merchandise_id,addTime) values(?,?,?,?);";
            PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
            ps2.setInt(NUB_1, id);
            ps2.setInt(NUB_2, account_id);
            ps2.setInt(NUB_3, wares_id);
            ps2.setString(NUB_4,date);
            ps2.executeUpdate();
            ps2.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * 修改商品信息
     * @param wares_id          商品ID
     * @param spec_id           规格ID
     * @param nameType          商品类型
     * @param name              商品名称
     * @param money             商品价格
     * @param desc              商品描述
     * @param number            商品数量
     * @param origin            商品产地
     * @param netContent        商品净含量
     * @param packingMethod     包装方式
     * @param brand             品牌
     * @param qGP               保质期ID
     * @param storageMethod     保质期---时间
     * void
     * 2018年1月24日下午8:31:32
     */
    public static void updateGoods(Integer wares_id,Integer spec_id,String nameType,String name,String money,String desc,Integer number,String origin,String netContent,String packingMethod,String brand,Integer qGP,String storageMethod){
        Connection conn = LinkMysql.getCon();
        try {
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
            ps2.setString(NUB_3, money);
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
     * 卖家添加商品
     * @param select            商品类型ID
     * @param name              商品名称
     * @param money             商品价格
     * @param desc              商品描述
     * @param origin            商品产地
     * @param netContent        商品净含量
     * @param packingMethod     包装方式
     * @param brand             品牌
     * @param qGP               保质期ID
     * @param storageMethod     储存方法
     * @param number            商品数量
     * @param date              添加时间
     * @param seller_id         账户ID
     * void
     * 2018年1月24日下午8:34:31
     */
    public static void addGoods(Integer select,String name,String money,String desc,String origin,String netContent,String packingMethod,String brand,Integer qGP,String storageMethod,Integer number,String date,Integer seller_id){
        Connection conn = LinkMysql.getCon();
        try {
            /** 添加规格表数据 */
            String sql = "insert into specification_table(origin,netContent,packingMethod,brand,qGP,storageMethod) values(?,?,?,?,?,?);";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(NUB_1, origin);
            ps.setString(NUB_2, netContent);
            ps.setString(NUB_3, packingMethod);
            ps.setString(NUB_4, brand);
            ps.setInt(NUB_5, qGP);
            ps.setString(NUB_6, storageMethod);
            ps.executeUpdate();
            ps.close();
            /** 获取规格表ID */
            String sql1 = "select max(id) from specification_table;";
            PreparedStatement ps1 = (PreparedStatement) conn.prepareStatement(sql1);
            ps1.executeQuery();
            ResultSet rs1 = ps1.getResultSet();
            int s_tb = 0;
            while (rs1.next()) {
                s_tb = rs1.getInt(1);
            }
            rs1.close();
            ps1.close();
            /** 添加商品表数据 */
            String sql2 = "insert into merchandise(nameTypeID,`name`,money,`describe`,specificationID,number,upFrameTime,account_id) values(?,?,?,?,?,?,?,?);";
            PreparedStatement ps2 = (PreparedStatement) conn.prepareStatement(sql2);
            ps2.setInt(NUB_1, select);
            ps2.setString(NUB_2, name);
            ps2.setString(NUB_3, money);
            ps2.setString(NUB_4, desc);
            ps2.setInt(NUB_5, s_tb);
            ps2.setInt(NUB_6, number);
            ps2.setString(NUB_7, date);
            ps2.setInt(NUB_8, seller_id);
            ps2.executeUpdate();
            ps2.close();
            /** 查询商品ID */
            String sql3 = "select m.id from merchandise m,specification_table stb where m.specificationID=stb.id and stb.id=?;";
            PreparedStatement ps3 = (PreparedStatement) conn.prepareStatement(sql3);
            ps3.setInt(NUB_1, s_tb);
            ps3.executeQuery();
            ResultSet rs3 = ps3.getResultSet();
            int m_id = 0;
            while (rs3.next()) {
                m_id = rs3.getInt(NUB_1);
            }
            rs3.close();
            ps3.close();
            /** 添加发布表数据 */
            String sql4 = "insert into seller(seller_id,wares_id,time,downtime,audits) values(?,?,?,?,?);";
            PreparedStatement ps4 = (PreparedStatement) conn.prepareStatement(sql4);
            ps4.setInt(NUB_1, seller_id);
            ps4.setInt(NUB_2, m_id);
            ps4.setString(NUB_3, null);
            ps4.setString(NUB_4, null);
            ps4.setInt(NUB_5, NUB_0);
            ps4.executeUpdate();
            ps4.close();
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
        pageNow = (pageNow - 1) * 10;
        try {
            Connection conn = LinkMysql.getCon();
            if (null != sel && !"".equals(sel)) {
                String sql1 = "select count(*) from seller rs,merchandise m,merchandise_type mt,specification_table spt where rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id and m.`name` like ?";
                PreparedStatement pre1 = (PreparedStatement) conn.prepareStatement(sql1);
                pre1.setString(NUB_1, "%" + ConnectTime.decodeSpecialCharsWhenLikeUseSlash(sel) + "%");
                ResultSet rs1 = pre1.executeQuery();
                while(rs1.next()){
                    number = rs1.getInt(NUB_1);
                }
                
                List<Integer> paramIndex = new ArrayList<>();
                List<Object> param = new ArrayList<>();
                sql = "select rs.id,rs.seller_id,rs.wares_id,m.specificationID,mt.type,m.`name`,m.money,m.`describe`,m.number,m.upFrameTime,rs.time,rs.downtime,rs.audits from seller rs,merchandise m,merchandise_type mt,specification_table spt where rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id ";
                if (null != abc && !"".equals(abc)) {
                    sql = sql + " and m.`name` like ?";
                    paramIndex.add(0);
                    param.add(abc);
                }
                sql = sql + " order by rs.id limit ?,?";
                paramIndex.add(1);
                paramIndex.add(1);

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
                    goods.setSeller_id(rs.getInt(NUB_2));
                    goods.setWares_id(rs.getInt(NUB_3));
                    goods.setSpeciID(rs.getInt(NUB_4));
                    goods.setNameType(rs.getString(NUB_5));
                    goods.setName(rs.getString(NUB_6));
                    goods.setMoney(rs.getString(NUB_7));
                    goods.setDescribe(rs.getString(NUB_8));
                    goods.setNumber(rs.getInt(NUB_9));
                    goods.setUpFrameTime(rs.getString(NUB_10));
                    goods.setTime(rs.getString(NUB_11));
                    goods.setDowntime(rs.getString(NUB_12));
                    goods.setAuditStatus(rs.getInt(NUB_13));
                    list.add(goods);
                }
                rs.close();
                pre.close();
                return list;
            } else {// 查询所有数据
                number = null;
                sql = "select rs.id,rs.seller_id,rs.wares_id,m.specificationID,mt.type,m.`name`,m.money,m.`describe`,m.number,m.upFrameTime,rs.time,rs.downtime,rs.audits from seller rs,merchandise m,merchandise_type mt,specification_table spt where rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id ORDER BY rs.id asc limit ?,?;";
                PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
                pre.setInt(NUB_1, pageNow);
                pre.setInt(NUB_2, Paging.getPageNumber());
                pre.executeQuery();
                ResultSet rs = pre.getResultSet();
                while (rs.next()) {
                    Seller goods = new Seller();
                    goods.setId(rs.getInt(NUB_1));
                    goods.setSeller_id(rs.getInt(NUB_2));
                    goods.setWares_id(rs.getInt(NUB_3));
                    goods.setSpeciID(rs.getInt(NUB_4));
                    goods.setNameType(rs.getString(NUB_5));
                    goods.setName(rs.getString(NUB_6));
                    goods.setMoney(rs.getString(NUB_7));
                    goods.setDescribe(rs.getString(NUB_8));
                    goods.setNumber(rs.getInt(NUB_9));
                    goods.setUpFrameTime(rs.getString(NUB_10));
                    goods.setTime(rs.getString(NUB_11));
                    goods.setDowntime(rs.getString(NUB_12));
                    goods.setAuditStatus(rs.getInt(NUB_13));
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
                String sql = "select count(*) from seller";
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
        return pageCount;
    }
}
