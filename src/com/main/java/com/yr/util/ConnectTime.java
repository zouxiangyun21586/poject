package com.yr.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @娴ｆ粏锟斤拷 閺嬫鎸夊锟� 2017楠烇拷12閺堬拷28閺冦儰绗呴崡锟�5:36:40
 */
public class ConnectTime {
	//public static String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
	//http://www.taobao.com   淘宝
	//http://www.360.cn       360
	//http://www.baidu.com    百度
	/**
	 * 鑾峰彇鎸囧畾缃戠珯鐨勬棩鏈熸椂闂?
	 *
	 * @return
	 * @author SHANHY
	 * @date 2015骞?11鏈?27鏃?
	 */
	public static String getWebsiteDatetime() {
	    try {
	        URL url = new URL("http://www.baidu.com");// 鍙栧緱璧勬簮瀵硅薄
	        URLConnection uc = url.openConnection();// 鐢熸垚杩炴帴瀵硅薄
	        uc.connect();// 鍙戝嚭杩炴帴
	        long ld = uc.getDate();// 璇诲彇缃戠珯鏃ユ湡鏃堕棿
	        Date date = new Date(ld);// 杞崲涓烘爣鍑嗘椂闂村璞?
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 杈撳嚭缃戠粶鏃堕棿
	        return sdf.format(date);
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
     * 转义特殊字符
     * @param content
     * @return
     */
    public static String decodeSpecialCharsWhenLikeUseSlash(String content) {
        String afterDecode = content.replaceAll("'", "''");
        afterDecode = afterDecode.replaceAll("\\\\", "\\\\\\\\");
        afterDecode = afterDecode.replaceAll("%", "\\\\%");
        afterDecode = afterDecode.replaceAll("_", "\\\\_");
        return afterDecode;
    }

}
