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

import com.yr.pojo.Paging;
import com.yr.pojo.Seller;
import com.yr.pojo.Supplie;
import com.yr.util.Conn;
import com.yr.util.JsonUtils;

public class SupplieDao {
    public static final String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
    static List<Supplie> list = new ArrayList<>();
    static Connection conn = Conn.conn();

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
    public static void merchandiseAdd(String nameType,String name,String money,String specification,String number,String upFrametTime) throws SQLException, ParseException{
        String str = "insert into merchandise(nameTypeID,`name`,money,specificationID,number,upFrameTime) values(?,?,?,?,?,?);"; // id自增长
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, nameType);
        ps.setString(2, name);
        ps.setString(3, money);
        ps.setString(4, specification);
        ps.setString(5, number);
        ps.setString(6, upFrametTime);
        ps.executeUpdate();// 执行修改
        ps.close();
    }
    
    /**
     * 添加规格表中信息
     * @author zxy
     * @param origin
     * @param netContent
     * @param packingMethod
     * @param brand
     * @param qGp
     * @param storageMethod
     * @throws SQLException
     * 2017年12月28日  下午10:21:47
     */
    public static void speciAdd(String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod)throws SQLException{
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
     * 撤销审核中的商品
     * @author zxy
     * 2018年1月2日  上午10:31:46
     * @throws SQLException 
     */
    public static void cencel(String date,String id) throws SQLException{
        String str = "update release_supplier set time=? where id=?;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setString(1, date);
        ps.setString(2, id);
        ps.executeUpdate();
        ps.close();
    }
    
    /**
     * 下架商品
     * @author zxy
     * 2018年1月2日  下午5:28:00
     * @throws SQLException 
     */
    public static String xiajia(String date,String id) throws SQLException{
        String str = "update release_supplier set time=? where id=?;";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
        ps.setString(1, date);
        ps.setString(2, id);
        ps.executeUpdate();
        ps.close();
        String sql = "select time from release_supplier where id=?;";
        PreparedStatement psp = (PreparedStatement) conn.prepareStatement(sql);
        psp.setString(1, id);
        psp.executeQuery();
        ResultSet rs = psp.getResultSet();
        while(rs.next()){
            Supplie sup = new Supplie();
            sup.setUpFrameTime(rs.getString(1));
            list.add(sup);
        }
        String sst = JsonUtils.beanListToJson(list);
        return sst;
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
    public static String merchandiseUpd(String name,String money,String number,String nameTypeId,String specificationID,String id) throws SQLException{
        String str = "update merchandise mer set name= ?,money = ?,number = ?,nameTypeID = ?,specificationID = ? where id = ?";

        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, name);
        ps.setString(2, money);
        ps.setString(3, number);
        ps.setString(5, nameTypeId);
        ps.setString(6, specificationID);
        ps.setString(7, id);
        ps.executeUpdate(); // 执行修改
        ResultSet rs = ps.getResultSet(); // 获取查询结果
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
     * 修改规格表
     * @author zxy
     * @param origin
     * @param netContent
     * @param packingMethod
     * @param brand
     * @param qGp
     * @param storageMethod
     * @param speId
     * @return
     * @throws SQLException
     * 2017年12月28日  下午10:06:30
     */
    public static String speUpd(String origin,String netContent,String packingMethod,String brand,String qGp,String storageMethod,String speId) throws SQLException{
        String str = "update specification_table spe set origin= ?,netContent = ?,packingMethod = ?,brand = ?,qGp = ?,storageMethod = ? where id = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.setString(1, origin);
        ps.setString(2, netContent);
        ps.setString(3, packingMethod);
        ps.setString(4, brand);
        ps.setString(5, qGp);
        ps.setString(6, storageMethod);
        ps.setString(7, speId);
        ps.executeUpdate(); // 执行修改
        ResultSet rs = ps.getResultSet(); // 获取查询结果
        while(rs.next()){ // 循环结果集
            Supplie sup = new Supplie();
            sup.setOrigin(rs.getString(1));
            sup.setNetContent(rs.getString(2));
            sup.setPackingMethod(rs.getString(3));
            sup.setBrand(rs.getString(4));
            sup.setqGp(rs.getString(5));
            sup.setStorageMethod(rs.getString(6));
            sup.setSpecificationId(7);
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
        // 获取id
        String str = "select DISTINCT sup.id,sup.commodity,mer.money,mer.`name`,mer.number,merType.type,mota.`month`,spe.brand,spe.netContent,spe.origin,spe.packingMethod,spe.storageMethod,mer.upFrameTime from supplier sup, merchandise mer,merchandise_type merType,month_table mota,specification_table spe where sup.mercd_id=mer.id and mer.nameTypeID=merType.id and sup.sup_mer_id = mer.supplier_id and mota.id = spe.qGP and mer.specificationID= spe.id";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);// 发送SQL到数据库
        ps.executeQuery();// 执行查询
        ResultSet rs = ps.getResultSet();// 获取查询结果
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
            list.add(sup);
        }
        String strJson = JsonUtils.beanListToJson(list);
        rs.close(); // 关闭结果集
        ps.close(); // 关闭发送SQL对象
        return strJson;
    }
    
    
    /**
     * 查询数据并用list封装起来
     * @param pageNow 当前页
     * @return 返回所查询的数据
     */
    public static List<Supplie> selectemp(Integer pageNow) {
        String sql = "";
        if (pageNow < 1) {
            pageNow = 1;
        }
        pageNow = (pageNow - 1) * 10;
        try {
            List<Integer> paramIndex = new ArrayList<>();
            List<Object> param = new ArrayList<>();
            sql = "select DISTINCT sup.id,sup.commodity,mer.money,mer.`name`,mer.number,merType.type,mota.`month`,spe.brand,spe.netContent,spe.origin,spe.packingMethod,spe.storageMethod,mer.upFrameTime from supplier sup, merchandise mer,merchandise_type merType,month_table mota,specification_table spe where sup.mercd_id=mer.id and mer.nameTypeID=merType.id and sup.sup_mer_id = mer.supplier_id and mota.id = spe.qGP and mer.specificationID= spe.id";
            
            sql = sql + " limit ?,?";
            paramIndex.add(1);
            paramIndex.add(1);
            
            
            param.add(pageNow);
            param.add(Paging.getPageNumber());
            
            PreparedStatement prepar = (PreparedStatement) conn.prepareStatement(sql);
            
            for (int i = 0; i < paramIndex.size(); i++) {
                int mark = paramIndex.get(i);
                if(mark == 0 ){
                    prepar.setString( (i+1), (String)param.get(i) );
                }
                else if(mark == 1)
                {
                    prepar.setInt( (i+1), (Integer)param.get(i) );
                }
            }
            
            prepar.executeQuery();
            ResultSet resu = prepar.getResultSet();
            while (resu.next()) {
                Supplie us = new Supplie();
                us.setId(resu.getInt(1));
                us.setCommo(resu.getString(2));
                us.setId(resu.getInt(1));
                us.setCommo(resu.getString(2));
                us.setMoney(resu.getDouble(3));
                us.setSupMerName(resu.getString(4));
                us.setNumber(resu.getInt(5));
                us.setTypeName(resu.getString(6));
                us.setqGp(resu.getString(7));
                us.setBrand(resu.getString(8));
                us.setNetContent(resu.getString(9));
                us.setOrigin(resu.getString(10));
                us.setPackingMethod(resu.getString(11));
                us.setStorageMethod(resu.getString(12));
                us.setUpFrameTime(resu.getString(13));
                list.add(us);
            }
            resu.close();
            prepar.close();
            return list;
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
        try {
            String sql = "select count(*) from supplier";
            PreparedStatement prepar = conn.prepareStatement(sql);
            prepar.executeQuery();
            ResultSet resu = prepar.getResultSet();
            while (resu.next()) {
                total = resu.getInt(1);
            }
            resu.close();
            prepar.close();
//          conn.close();
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
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sId);
            ps.setString(1,id);
            ps.executeQuery();// 执行查询
            ResultSet rs = ps.getResultSet();
            rs.next();
            int count = rs.getInt(1); // sql语句查出总数为1代表id存在,总数为0代表可以添加此id(无人使用)
            if (count == 0) {
                int iid = 0;
            }else{
                int iid = 1;
            }
            rs.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            int iid = 0;
        }
        return 1;
    }
    
}
