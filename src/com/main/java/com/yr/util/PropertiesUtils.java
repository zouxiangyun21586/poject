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
 * @description : properties������
 * @author LYF 2016-01-26
 * @version 1.0
 * 
 */
public class PropertiesUtils {
    
    private static Properties properties = new Properties();
    private static String filePath = "jdbc.properties";
    public static Map<String,Object> map = new HashMap<String,Object>();
    /**
     * ���������ļ�
     * 
     * @param filePath
     *            �ļ�·��
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
     * ��ȡ�����ļ�
     * 
     * @param props
     *            �����ļ�
     * @param key
     * @return
     */
    public static String getString(String key) {
        loadProps();
        return properties.getProperty(key);
    }
    
    
    //д��Properties��Ϣ
    public static void writeProperties (String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        
        InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath);
        //���������ж�ȡ�����б�����Ԫ�ضԣ� 
        pps.load(in);
        //���� Hashtable �ķ��� put��ʹ�� getProperty �����ṩ�����ԡ�  
        //ǿ��Ҫ��Ϊ���Եļ���ֵʹ���ַ���������ֵ�� Hashtable ���� put �Ľ����
        String path = PropertiesUtils.class.getClassLoader().getResource("").getPath();
        OutputStream out = new FileOutputStream(path+ File.separator + "jdbc.properties");
        pps.setProperty(pKey, pValue);
        //���ʺ�ʹ�� load �������ص� Properties ���еĸ�ʽ��  
        //���� Properties ���е������б�����Ԫ�ضԣ�д�������  
        pps.store(out, "Update " + pKey + " name");
    }
    
    
    //��ȡProperties��ȫ����Ϣ
    public static void getAllProperties1() throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //�õ������ļ�������
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
    }
    
  //��ȡProperties��ȫ����Ϣ ��map����
    public static void getAllProperties() throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //�õ������ļ�������
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
//            System.out.println(strKey + "=" + strValue);
            map.put(strKey, strValue);
        }
    }
}