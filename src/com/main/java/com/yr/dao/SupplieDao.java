package com.yr.dao;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.yr.pojo.Supplie;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

public class SupplieDao {
    public static final String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心

    /**
     * 添加商品信息
     * @author zxy
     * @param nameType 商品类型
     * @param name  商品名
     * @param money 商品价格
     * @param describe  商品描述
     * @param specification 商品规格
     * @param number    商品数量
     * @param upFrametTime  商品上架时间
     * @throws SQLException
     * 2017年12月14日  下午5:35:56
     * @throws ParseException 
     */
    public static void merchandiseAdd(String nameType,String name,String money,String describe,String number,String upFrametTime) throws SQLException, ParseException{
        
        Connection conn = Conn.conn();
        String str = "insert into merchandise(nameTypeID,`name`,money,`describe`,number,upFrameTime) values(?,?,?,?,?,?);"; // id自增长
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, nameType);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setString(4, describe);
        ps.setString(5, number);
        ps.setString(6, upFrametTime);
        ps.executeUpdate();// 执行修改
        ps.close();
    }
    
    public static void speciAdd(String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod)throws SQLException{
        Connection conn = Conn.conn();
        String str = "insert into specification_table(origin,netContent,packingMethod,brand,qGp,storageMethod) values(?,?,?,?,?,?);"; // id自增长
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, origin);
        ps.setString(2, netContent);
        ps.setString(3, packingMethod);
        ps.setString(4, brand);
        ps.setString(5, qGp);
        ps.setString(6, storageMethod);
        ps.executeUpdate();// 执行修改
        ps.close();
    }
    
    /**
     * 添加供应商的商品信息
     * @author zxy
     * @param strName 商品信息
     * @return
     * @throws Exception
     * 2017年12月14日  下午3:10:51
     */
    public static void suppAdd(String name,String mercd_id,String sup_mer_id) throws Exception{
        Connection conn = Conn.conn();
        String str = "insert into supplier(commodity,mercd_id,sup_mer_id) values(?,?,?);"; // id自增长
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, name); // 供应商id 
        ps.setString(2, mercd_id); // 商品id
        ps.setString(3, sup_mer_id); // 供应商对应的商品id
        ps.executeUpdate();// 执行修改
        suppSel();
        ps.close();
    }
    
    /**
     * 获取指定网站的日期时间 
     * @author zxy
     * @param webUrl
     * @return
     * 2017年12月16日  上午11:31:57
     */
    public static String getWebsiteDatetime(String webUrl){
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出网络时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 删除供应商的商品信息
     * @author zxy
     * @param strId 供应商商品id
     * @return
     * @throws Exception
     * 2017年12月14日  下午3:10:57
     */
    public static void suppDel(String strId) throws Exception{
        Connection conn = Conn.conn();
        String str = "delete from supplier where id = ?";
        strId = new String(strId.getBytes("ISO-8859-1"), "utf-8"); // 解决乱码问题
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, strId);
        ps.executeUpdate(); // 执行修改
        ps.close();
        conn.close();
        suppSel();
    }
    
    /**
     * 删除商品表的商品信息
     * 
     * @author zxy
     * @param strId
     * 2017年12月20日  下午4:47:30
     */
    public static void merDel(String strId) throws Exception{
        Connection conn = Conn.conn();
        String str = "delete from merchandise where id = ?";
        strId = new String(strId.getBytes("ISO-8859-1"), "utf-8"); // 解决乱码问题
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, strId);
        ps.executeUpdate(); // 执行修改
        ps.close();
        conn.close();
        suppSel();
    }
    
    /**
     * 修改商品
     * @author zxy
     * @param name
     * @param money
     * @param number
     * @param nameTypeId
     * @param specificationID
     * @param id
     * @return
     * @throws SQLException
     * 2017年12月14日  下午5:41:00
     */
    public static String merchandiseUpd(String name,String money,String number,Integer nameTypeId,Integer specificationID,Integer id) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update merchandise mer set name= ?,money = ?,number = ?,nameTypeID = ?,specificationID = ? where id = ?";

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, name);
        ps.setString(2, money);
        ps.setString(3, number);
        ps.setInt(5, nameTypeId);
        ps.setInt(6, specificationID);
        ps.setInt(7, id);
        ps.executeUpdate(); // 执行修改
        ResultSet rs = ps.getResultSet(); // 获取查询结果
        List<Supplie> list = new ArrayList<>();
        while(rs.next()){ // 循环结果集
            Supplie sup = new Supplie();
            sup.setCommo(rs.getString(1));
            sup.setMoney(rs.getDouble(2));
            sup.setNumber(rs.getInt(3));
            sup.setNameTypeId(rs.getInt(4));
            sup.setSpecificationId(rs.getInt(5));
            sup.setId(rs.getInt(6));
            list.add(sup);
        }
        String strJson = JsonUtils.beanToJson(ps);
        rs.close();
        ps.close();
        return strJson;
    }
    /**
     * 修改供应商的商品信息
     * @author zxy
     * @param commodity 供应商的商品名
     * @param id    供应商的商品ID
     * @throws Exception
     * 2017年12月14日  下午3:11:06
     */
    public static String suppUpd(String commodity,String id) throws Exception{
        Connection conn = Conn.conn();
        String str = "update supplier set commodity = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, commodity);
        ps.setString(2, id);
        ps.executeUpdate(); // 执行修改
        String strJson = JsonUtils.beanToJson(ps);
        suppSel();
        ps.close();
        return strJson;
    }
    /**
     * 查询供应商商品信息
     * @author zxy
     * @return
     * @throws Exception
     * 2017年12月14日  下午3:11:13
     */
    public static String suppSel() throws Exception{
        Connection conn = Conn.conn();
        // 获取id
        String str = "select DISTINCT sup.id,sup.commodity,mer.money,mer.`name`,mer.number,merType.type,mota.`month`,spe.brand,spe.netContent,spe.origin,spe.packingMethod,spe.storageMethod,mer.upFrameTime from supplier sup, merchandise mer,merchandise_type merType,month_table mota,specification_table spe where sup.mercd_id=mer.id and mer.nameTypeID=merType.id and sup.sup_mer_id = mer.supplier_id and mota.id = spe.qGP and mer.specificationID= spe.id";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.executeQuery();// 执行查询
        ResultSet rs = ps.getResultSet();// 获取查询结果
        List<Supplie> selist = new ArrayList<>();
        // 循环结果集
        while (rs.next()) {// 取结果集中的下一个。
            Supplie sup = new Supplie();
            sup.setId(rs.getInt(1));
            sup.setCommo(rs.getString(2));
            sup.setMoney(rs.getDouble(3));
            sup.setSupMerName(rs.getString(4));
            sup.setNumber(rs.getInt(5));
            sup.setTypeName(rs.getString(6));
            sup.setqGp(rs.getString(7));
            sup.setBrand(rs.getString(8));
            sup.setNetContent(rs.getString(9));
            sup.setOrigin(rs.getString(10));
            sup.setPackingMethod(rs.getString(11));
            sup.setStorageMethod(rs.getString(12));
            sup.setUpFrameTime(rs.getString(13));
            selist.add(sup);
        }
        String strJson = JsonUtils.beanListToJson(selist);
        rs.close(); // 关闭结果集
        ps.close(); // 关闭发送SQL对象
        return strJson;
    }
    
    /**
     * 判断id是否重复
     * @author zxy
     * @param id
     * @return
     * 2017年12月15日  下午2:36:38
     */
    public static int exsisId(String id){
        String sId= "select count(*) from supplier where id = ?";
        try {
            Connection conn = Conn.conn();
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sId);
            ps.setString(1,id);
            ps.executeQuery();// 执行查询
            ResultSet rs = ps.getResultSet();
            rs.next();
            int count = rs.getInt(1); // sql语句查出总数为1代表id存在,总数为0代表可以添加此id(无人使用)
            if (count == 0) {
//                resp.getWriter().write("0");
                int iid = 0;
            }else{
//                resp.getWriter().write("1");
                int iid = 1;
            }
            rs.close();
            return count;
        } catch (Exception e) {
//            resp.getWriter().write("0");
            e.printStackTrace();
            int iid = 0;
        }
        return 1;
    }
    
}
