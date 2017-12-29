package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.Seller;
import com.yr.pojo.Paging;
import com.yr.dao.LinkMysql;

import com.yr.util.JsonUtils;

/**
 * @���� ��ˮ�� 2017��12��29������9:18:57
 */
public class SellerDao {
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

    /**
     * ��ѯ���ҷ��������������Ϣ
     */
    public static String queryGoods() {
        Connection conn = LinkMysql.getCon();
        List<Seller> list = new ArrayList<>();
        try {
            String sql = "select rs.id,rs.seller_id,rs.wares_id,m.specificationID,mt.type,m.`name`,m.money,m.`describe`,m.number,m.upFrameTime,rs.time,rs.downtime,rs.audits from release_seller rs,merchandise m,merchandise_type mt,specification_table spt where rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id;";// ,account
                                                                                                                                                                                                                                                                                                                                           // acc
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.executeQuery(); // ִ�в�ѯ
            ResultSet rs = ps.getResultSet();
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
            ps.close();
            conn.close();
            String sst = JsonUtils.beanListToJson(list);
            System.out.println(sst);
            return sst;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ѯ���ݲ���list��װ����
     * 
     * @param abc
     *            �˺�
     * @param rolename
     *            ��ɫ��
     * @param pageNow
     *            ��ǰҳ
     * @param sel
     *            �ж��Ƿ����˲�ѯ����
     * @return ��������ѯ������
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
                List<Integer> paramIndex = new ArrayList<>();
                List<Object> param = new ArrayList<>();
                sql = "select rs.id,rs.seller_id,rs.wares_id,m.specificationID,mt.type,m.`name`,m.money,m.`describe`,m.number,m.upFrameTime,rs.time,rs.downtime,rs.audits from release_seller rs,merchandise m,merchandise_type mt,specification_table spt where rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id ";
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
                        pre.setString((i + 1), "%" + (String) param.get(i) + "%");
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
                return list;
            } else {// ��ѯ��������
                sql = "select rs.id,rs.seller_id,rs.wares_id,m.specificationID,mt.type,m.`name`,m.money,m.`describe`,m.number,m.upFrameTime,rs.time,rs.downtime,rs.audits from release_seller rs,merchandise m,merchandise_type mt,specification_table spt where rs.wares_id=m.id and m.nameTypeID=mt.id and m.specificationID=spt.id ORDER BY rs.id asc limit ?,?;";
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
        int total = 0;// �ܹ���������¼
        int pageCount = 0;// ��ҳ��
        try {
            Connection conn = LinkMysql.getCon();
            String sql = "select count(*) from release_seller";
            PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
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
}
