package com.yr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.yr.pojo.Power;
import com.yr.util.Conn;

/**
 * 权限管理Dao
 * 
 * @author 周业好 2017年12月19日 下午9:35:35
 */
public class PowerDao {

    /**
     * 修改url
     * @param arr 所有url,id的数组
     * @return String:good表示成功
     */
    public static String update(String arr) {
        try {
        	
            Connection conn = Conn.conn();
            String sql = "update permission set url=? where id=?;";
            String zhi[] = arr.split(" ");
            PreparedStatement pre = conn.prepareStatement(sql);
    		pre.setString(1, zhi[0]);
    		Integer id = Integer.valueOf(zhi[1]);
    		pre.setInt(2, id);
        	pre.executeUpdate();
        	pre.close();
            //conn.close();
            return "good";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
    
    
    
    /**
     * 停用菜单
     * 没用到
     * @param idStr
     *            权限表id
     */
    public static void stop(String idStr) {
        try {
            Integer id = Integer.valueOf(idStr);
            String sql = "update permission set state=1 where id=?;";
            Connection conn = Conn.conn();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();
            pre.close();
            //conn.close();
            // 将java对象List集合转换成json字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启用菜单
     * 没用到
     * @param idStr
     *            权限表id
     */
    public static void start(String idStr) {
        try {
            Integer id = Integer.valueOf(idStr);
            String sql = "update permission set state=0 where id=?;";
            Connection conn = Conn.conn();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();
            pre.close();
            //conn.close();
            // 将java对象List集合转换成json字符串
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询级联的值
     * 
     * @param id
     * @return
     */
    public static List<Power> jilian(Integer id, String sql) {
        try {
            Connection con = Conn.conn();
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            List<Power> list = new ArrayList<>();
            while (rs.next()) {
                Power j = new Power();
                j.setId(rs.getInt(1));
                j.setName(rs.getString(2));
                j.setUrl(rs.getString(3));
                j.setIcon(rs.getString(4));
                j.setState(rs.getInt(5));
                if (j.getState() == 0) {
                    j.setStaStr("使用中");
                } else {
                    j.setStaStr("已停用");
                }
                list.add(j);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
