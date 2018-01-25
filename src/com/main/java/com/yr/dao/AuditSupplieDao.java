package com.yr.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.yr.pojo.Supplie;
import com.yr.util.Conn;

/**
 * 
 * @作者 林水桥
 * 2018年1月25日上午11:12:56
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

    private static Integer number = null;
    
    /**
     * 查询数据并用list封装起来
     * @param pageNow       当前页
     * @param sel           判断是否用了查询功能
     * @return              返回所查询的数据
     * List<Supplie>
     * 2018年1月25日上午11:20:02
     */
    public static List<Supplie> selectemp(Integer pageNow, String sel) {
        Connection conn = Conn.conn();
        String sql = "";
        List<Supplie> list = new ArrayList<>();
        if (pageNow < NUB_1) {
            pageNow = NUB_1;
        }
        pageNow = (pageNow - 1) * 10;
        try {
            if(null != sel && !"".equals(sel)){
                List<Integer> paramIndex = new ArrayList<>();
                List<Object> param = new ArrayList<>();
            }else{
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
