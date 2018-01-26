package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.Buyers;
import com.yr.pojo.Paging;
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;

/**
 * 买家数据持久层
 * @author 周业好
 * 2018年1月23日 上午10:55:25
 */
public class BuyersDao {
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
    public static List<Buyers> queryGoods(Integer id) {
    	System.out.println("as拳打");
        /*Connection conn = LinkMysql.getCon();
        List<Buyers> list = new ArrayList<>();
        try {
            String sql = "select mt.type,m.`name`,m.`describe`,spt.origin,spt.netContent,spt.packingMethod,spt.brand,mth.`month`,spt.qGP,spt.storageMethod,m.money,m.number,rs.time,rs.downtime,rs.id,m.id,spt.id,m.nameTypeID from `release` rele,seller rs,merchandise m,merchandise_type mt,specification_table spt,month_table mth where rele.wares_id=m.id and rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id and mth.id=spt.qGP;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1, id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
            	Buyers goods = new Buyers();
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
                goods.setTime(rs.getString(NUB_13));
                goods.setDowntime(rs.getString(NUB_14));
                goods.setId(rs.getInt(NUB_15));
                goods.setWares_id(rs.getInt(NUB_16));
                goods.setSpeciID(rs.getInt(NUB_17));
                goods.setNameTypeID(rs.getInt(NUB_18));
                list.add(goods);
            }
            rs.close();
            ps.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
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
    public static List<Buyers> selectGoods(String abc, Integer pageNow, String sel) {
        String sql = "";
        List<Buyers> list = new ArrayList<>();
        if (pageNow < NUB_1) {
            pageNow = NUB_1;
        }
        pageNow = (pageNow - 1) * 10;
        try {
            Connection conn = LinkMysql.getCon();
            if (null != sel && !"".equals(sel)) {
                String sql1 = "select count(*) from `release` rele,merchandise m,merchandise_type mt,specification_table spt where rele.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id and m.`name` like ?";
                PreparedStatement pre1 = (PreparedStatement) conn.prepareStatement(sql1);
                pre1.setString(NUB_1, "%" + ConnectTime.decodeSpecialCharsWhenLikeUseSlash(sel) + "%");
                ResultSet rs1 = pre1.executeQuery();
                while(rs1.next()){
                    number = rs1.getInt(NUB_1);
                }
                
                List<Integer> paramIndex = new ArrayList<>();
                List<Object> param = new ArrayList<>();
                sql = "select rele.id,m.account_id,m.id,m.specificationID,mt.type,m.`name`,m.money,m.`describe`,m.number,m.merStatus from `release` rele,merchandise m,merchandise_type mt,specification_table spt where rele.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id ";
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
                	Buyers goods = new Buyers();
                	goods.setFbId(rs.getInt(NUB_1));//发布表id
                    goods.setAccountId(rs.getInt(NUB_2));//用户id
                    goods.setWares_id(rs.getInt(NUB_3));//商品id
                    goods.setSpeciID(rs.getInt(NUB_4));//规格表id
                    goods.setNameType(rs.getString(NUB_5));//商品类型
                    goods.setName(rs.getString(NUB_6));//商品名字
                    goods.setMoney(rs.getString(NUB_7));//商品价格
                    goods.setDescribe(rs.getString(NUB_8));//商品描述
                    goods.setNumber(rs.getInt(NUB_9));//商品数量
                    goods.setAuditStatus(rs.getInt(NUB_10));//卖家商品状态
                    list.add(goods);
                }
                rs.close();
                pre.close();
                return list;
            } else {// 查询所有数据
                number = null;
                sql = "select rele.id,m.account_id,m.id,m.specificationID,mt.type,m.`name`,m.money,m.`describe`,m.number,m.merStatus from `release` rele,merchandise m,merchandise_type mt,specification_table spt where rele.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id ORDER BY m.id asc limit ?,?;";
                PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
                pre.setInt(NUB_1, pageNow);
                pre.setInt(NUB_2, Paging.getPageNumber());
                pre.executeQuery();
                ResultSet rs = pre.getResultSet();
                while (rs.next()) {
                	Buyers goods = new Buyers();
                    goods.setFbId(rs.getInt(NUB_1));//发布表id
                    goods.setAccountId(rs.getInt(NUB_2));//用户id
                    goods.setWares_id(rs.getInt(NUB_3));//商品id
                    goods.setSpeciID(rs.getInt(NUB_4));//规格表id
                    goods.setNameType(rs.getString(NUB_5));//商品类型
                    goods.setName(rs.getString(NUB_6));//商品名字
                    goods.setMoney(rs.getString(NUB_7));//商品价格
                    goods.setDescribe(rs.getString(NUB_8));//商品描述
                    goods.setNumber(rs.getInt(NUB_9));//商品数量
                    goods.setAuditStatus(rs.getInt(NUB_10));//卖家商品状态
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
                String sql = "select count(*) from `release`";
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
