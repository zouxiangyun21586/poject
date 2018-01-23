package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.Paging;
import com.yr.pojo.Seller;
import com.yr.util.ConnectTime;

/**
 * @作者 林水桥
 * 2018年1月23日上午10:29:43
 */
public class AuditSellerDao {
    private static final int NUB_1 = 1;
    private static final int NUB_2 = 2;
    private static final int NUB_3 = 3;
    private static final int NUB_4 = 4;
    private static final int NUB_5 = 5;
    private static final int NUB_6 = 6;
    private static final int NUB_7 = 7;
    private static final int NUB_8 = 8;
    private static final int NUB_9 = 9;

    private static Integer number = null;

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
                String sql1 = "select count(*) from auditseller ads,account ac,merchandise m,merchandise_type mt,seller rs where m.nameTypeID=mt.id and rs.id=ads.release_id and rs.seller_id=ads.account_id and rs.seller_id=ac.id AND m.id=rs.wares_id and m.id=ads.merchandise_id and m.`name` like ?";
                PreparedStatement pre1 = (PreparedStatement) conn.prepareStatement(sql1);
                pre1.setString(NUB_1, "%" + ConnectTime.decodeSpecialCharsWhenLikeUseSlash(sel) + "%");
                ResultSet rs1 = pre1.executeQuery();
                while(rs1.next()){
                    number = rs1.getInt(NUB_1);
                }
                rs1.close();
                pre1.close();
                List<Integer> paramIndex = new ArrayList<>();
                List<Object> param = new ArrayList<>();
                sql = "select ads.release_id,ads.id,ads.account_id,ads.merchandise_id,ac.name,mt.type,m.name,rs.audits,ads.addTime from auditseller ads,account ac,merchandise m,merchandise_type mt,seller rs where m.nameTypeID=mt.id and rs.id=ads.release_id and rs.seller_id=ads.account_id and rs.seller_id=ac.id AND m.id=rs.wares_id and m.id=ads.merchandise_id ";
                if (null != abc && !"".equals(abc)) {
                    sql = sql + " and m.`name` like ?";
                    paramIndex.add(0);
                    param.add(abc);
                }
                sql = sql + " order by ads.id limit ?,?";
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
                number = null;
                sql = "select ads.release_id,ads.id,ads.account_id,ads.merchandise_id,ac.name,mt.type,m.name,rs.audits,ads.addTime from auditseller ads,account ac,merchandise m,merchandise_type mt,seller rs where m.nameTypeID=mt.id and rs.id=ads.release_id and rs.seller_id=ads.account_id and rs.seller_id=ac.id AND m.id=rs.wares_id and m.id=ads.merchandise_id ORDER BY ads.id asc limit ?,?;";
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
                String sql = "select count(*) from auditSeller;";
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
