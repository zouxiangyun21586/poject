package com.yr.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @PropertiesUtils.java
 * @description : properties工具类
 * @author LYF 2016-01-26
 * @version 1.0
 * 
 */
public class PropertiesUtils {
    
    private static Properties properties = new Properties();
    private static String filePath = "jdbc.properties";
    public static Map<String,Object> map = new HashMap<String,Object>();
    /**
     * 加载属性文件
     * 
     * @param filePath
     *            文件路径
     * @return
     */
    public static Properties loadProps() {
        try {
            properties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 读取配置文件
     * 
     * @param props
     *            配置文件
     * @param key
     * @return
     */
    public static String getString(String key) {
        loadProps();
        return properties.getProperty(key);
    }
    
    
    //写入Properties信息
    public static void writeProperties (String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        
        InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);
        //从输入流中读取属性列表（键和元素对） 
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        String path = PropertiesUtils.class.getClassLoader().getResource("").getPath();
        OutputStream out = new FileOutputStream(path+ File.separator + "jdbc.properties");
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，  
        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
        pps.store(out, "Update " + pKey + " name");
    }
    
    
    //读取Properties的全部信息
    public static void getAllProperties1() throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
    }
    
  //读取Properties的全部信息 到map里面
    public static void getAllProperties() throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
//            System.out.println(strKey + "=" + strValue);
            map.put(strKey, strValue);
        }
    }
}