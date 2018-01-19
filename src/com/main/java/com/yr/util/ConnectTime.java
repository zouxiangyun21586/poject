package com.yr.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @浣滆�� 鏋楁按妗� 2017骞�12鏈�28鏃ヤ笅鍗�5:36:40
 */
public class ConnectTime {
    /**
     * 鑾峰彇鎸囧畾缃戠珯鐨勬棩鏈熸椂闂�
     * 
     * @param webUrl
     * @return
     * @author SHANHY
     * @date 2015骞�11鏈�27鏃�
     */
    public static String getWebsiteDatetime() {
        try {
            URL url = new URL("http://www.ntsc.ac.cn");// 鍙栧緱璧勬簮瀵硅薄
            URLConnection uc = url.openConnection();// 鐢熸垚杩炴帴瀵硅薄
            uc.connect();// 鍙戝嚭杩炴帴
            long ld = uc.getDate();// 璇诲彇缃戠珯鏃ユ湡鏃堕棿
            Date date = new Date(ld);// 杞崲涓烘爣鍑嗘椂闂村璞�
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 杈撳嚭缃戠粶鏃堕棿
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
