package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.Seller;

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
    
}
