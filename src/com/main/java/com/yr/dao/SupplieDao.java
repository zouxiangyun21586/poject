package com.yr.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.yr.pojo.Supplie;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

public class SupplieDao {
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
     */
    public static void merchandiseAdd(String nameType,String name,String money,String describe,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String number,String upFrametTime) throws SQLException{
        Connection conn = Conn.conn();
        String str = "insert into merchandise(nameTypeID,name,money,describe,specificationID,number,upFrametTime) values(?,?,?,?,?,?,?);"; // id自增长
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, nameType);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setString(4, describe);
        ps.setString(5, origin);
        ps.setString(6, netContent);
        ps.setString(7, packingMethod);
        ps.setString(8, brand);
        ps.setString(9, qGp);
        ps.setString(10, storageMethod);
        ps.setString(6, number);
        ps.setString(7, upFrametTime);
        ps.executeUpdate();// 执行修改
        String strJson = JsonUtils.beanToJson(ps);
    }
    /**
     * 添加供应商的商品信息
     * @author zxy
     * @param strName 商品信息
     * @return
     * @throws Exception
     * 2017年12月14日  下午3:10:51
     */
    public static String suppAdd(String nameType,String name,String money,String describe,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String number,String upFrametTime,String mercd_id,String sup_mer_id) throws Exception{
        Connection conn = Conn.conn();
        String str = "insert into supplier(commodity,mercd_id,sup_mer_id) values(?,?,?);"; // id自增长
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, name); // 供应商id 
        ps.setString(2, mercd_id); // 商品id
        ps.setString(3, sup_mer_id); // 供应商对应的商品id
        ps.executeUpdate();// 执行修改
        String strJson = JsonUtils.beanToJson(ps);
        merchandiseAdd(nameType, name, money, describe, origin, netContent, packingMethod, brand, qGp, storageMethod, number, upFrametTime);;
        suppSel();
        return strJson;
    }
    
    /**
     * 获取指定网站的日期时间 
     * @author zxy
     * @param webUrl
     * @return
     * 2017年12月16日  上午11:31:57
     */
    /*private static String getWebsiteDatetime(String webUrl){
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
    }*/
    
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
     * 修改商品
     * @author zxy
     * @param id 商品id
     * @param nameType  商品类型
     * @param name  商品名
     * @param money 商品价格
     * @param describe  商品描述
     * @param specification 商品规格
     * @param number    商品数量
     * @param upFrametTime  商品上架时间
     * @throws SQLException
     * 2017年12月14日  下午5:41:00
     */
    public static void merchandiseUpd(String nameTypeID,String name,String money,String describe,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String number,String upFrametTime,String specificationID,String suptID) throws SQLException{
        Connection conn = Conn.conn();
        String str = "update merchandise mer ,specification_table supt set mer.nameTypeID = ?,mer.`name` = ?,mer.money = ?,mer.`describe` = ?,supt.origin = ?,supt.netContent = ?,supt.packingMethod = ?,supt.brand = ?,supt.qGp = ?,supt.storageMethod = ?,number = ? where mer.specificationID = ? and supt.id = ?;";

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, nameTypeID);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setString(4, describe);
        ps.setString(5, origin);
        ps.setString(6, netContent);
        ps.setString(7, packingMethod);
        ps.setString(8, brand);
        ps.setString(9, qGp);
        ps.setString(10, storageMethod);
        ps.setString(11, number);
        ps.setString(12, specificationID);
        ps.setString(13, suptID);
        ps.executeUpdate(); // 执行修改
        ResultSet rs = ps.getResultSet(); // 获取查询结果
        List<Supplie> list = new ArrayList<>();
        while(rs.next()){ // 循环结果集
            Supplie sup = new Supplie();
            sup.setMerType(rs.getString(1));
            sup.setCommo(rs.getString(2));
            sup.setMoney(rs.getInt(3));
            sup.setDescribe(rs.getString(4));
            sup.setOrigin(rs.getString(5));
            sup.setNetContent(rs.getString(6));
            sup.setPackingMethod(rs.getString(7));
            sup.setBrand(rs.getString(8));
            sup.setqGp(rs.getString(9));
            sup.setStorageMethod(rs.getString(10));
            sup.setNumber(rs.getInt(11));
            sup.setUpFrameTime(rs.getShort(12));
            sup.setId(rs.getInt(13));
            list.add(sup);
        }
        String strJson = JsonUtils.beanToJson(ps);
    }
    /**
     * 修改供应商的商品信息
     * @author zxy
     * @param commodity 供应商的商品名
     * @param id    供应商的商品ID
     * @throws Exception
     * 2017年12月14日  下午3:11:06
     */
    public static String suppUpd(String commodity,String id,String merId,String nameType,String name,String money,String describe,String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String number,String specificationID,String suptID) throws Exception{
        Connection conn = Conn.conn();
        String str = "update supplier set commodity = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, commodity);
        ps.setString(2, id);
        ps.executeUpdate(); // 执行修改
        String strJson = JsonUtils.beanToJson(ps);
        SupplieDao.merchandiseUpd(merId,nameType,name,money,describe,origin,netContent,packingMethod,brand,qGp,storageMethod,number,specificationID,suptID); //商品信息修改
        suppSel();
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
        String str = "SELECT su.id,su.commodity,mertype.type,mer.money,mer.`describe`,spectable.origin,spectable.netContent,spectable.packingMethod,spectable.brand,spectable.qGp,spectable.storageMethod,mer.number,mer.upFrameTime,mer.id,mer.specificationID,su.mercd_id FROM supplier su, merchandise mer ,merchandise_type mertype , specification_table spectable where su.mercd_id = mer.id and mer.nameTypeID = mertype.id and mer.specificationID = spectable.id and su.sup_mer_id = mer.supplier_id;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.executeQuery();// 执行查询
        ResultSet rs = ps.getResultSet();// 获取查询结果
        List<Supplie> selist = new ArrayList<>();
        // 循环结果集
        while (rs.next()) {// 取结果集中的下一个。
            Supplie la = new Supplie();
            la.setId(rs.getInt(1));
            la.setMerType(rs.getString(3));
            la.setCommo(rs.getString(2));
            la.setMoney(rs.getDouble(4));
            la.setDescribe(rs.getString(5));
            la.setOrigin(rs.getString(6));
            la.setNetContent(rs.getString(7));
            la.setPackingMethod(rs.getString(8));
            la.setBrand(rs.getString(9));
            la.setqGp(rs.getString(10));
            la.setStorageMethod(rs.getString(11));
            la.setNumber(rs.getInt(12));
            la.setUpFrameTime(rs.getInt(13));
            la.setMerId(rs.getInt(14));
            la.setSpecificationID(rs.getInt(15));
            la.setSuptID(rs.getInt(16));
            selist.add(la);
        }
        rs.close(); // 关闭结果集
        ps.close(); // 关闭发送SQL对象
        String strJson = JsonUtils.beanListToJson(selist);
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
            return count;
        } catch (Exception e) {
//            resp.getWriter().write("0");
            e.printStackTrace();
            int iid = 0;
        }
        return 1;
    }
    
}
