package com.yr.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.yr.dao.LinkMysql;

public class SendMail {
  //阿里SMTP服务器地址
    private static final String ALIDM_SMTP_HOST = "smtp.aliyun.com";
    private static final Integer NUB_1 = 1;
    
    /**
     * 商品ID获得对应账户邮箱
     * @param wares_id  商品ID
     * @return          返回账户邮箱
     * String
     * 2018年2月3日下午4:52:13
     */
    public static String Email(Integer wares_id){
        Connection conn = LinkMysql.getCon();
        try{
            String sql = "select ac.youxiang from account ac,merchandise m where m.account_id=ac.id and m.id = ?;";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(NUB_1, wares_id);
            ResultSet rs = ps.executeQuery();
            String email = null;
            while(rs.next()){
                email = rs.getString(NUB_1);
            }
            rs.close();
            ps.close();
            return email;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 发送邮件
     * @param mail      邮箱
     * @param content   邮件内容
     * void
     * 2018年2月3日下午4:39:43
     */
    public static void sendMail(String mail,String content){
        try{
            // 配置发送邮件的环境属性
            final Properties props = new Properties();
            // 表示SMTP发送邮件，需要进行身份验证
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", ALIDM_SMTP_HOST);
    //        props.put("mail.smtp.port", ALIDM_SMTP_PORT);  
            // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
             props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
             props.put("mail.smtp.socketFactory.port", "465");
             props.put("mail.smtp.port", "465");
    
    
            // 发件人的账号   //linshuiqiaolj@aliyun.com
            props.put("mail.user", "linshuiqiaolj@aliyun.com");
            // 访问SMTP服务时需要提供的密码(邮箱密码)
            props.put("mail.password", "131452lj");//131452lj
    
            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);
    
            // 设置收件人
            InternetAddress to = new InternetAddress(mail);
            message.setRecipient(MimeMessage.RecipientType.TO, to);
    
            // 设置邮件标题
            message.setSubject("审核回执");
            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
    
            // 发送邮件
            Transport.send(message);
            System.out.println("发送成功");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
