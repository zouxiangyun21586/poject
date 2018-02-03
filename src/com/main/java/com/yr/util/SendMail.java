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
  //����SMTP��������ַ
    private static final String ALIDM_SMTP_HOST = "smtp.aliyun.com";
    private static final Integer NUB_1 = 1;
    
    /**
     * ��ƷID��ö�Ӧ�˻�����
     * @param wares_id  ��ƷID
     * @return          �����˻�����
     * String
     * 2018��2��3������4:52:13
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
     * �����ʼ�
     * @param mail      ����
     * @param content   �ʼ�����
     * void
     * 2018��2��3������4:39:43
     */
    public static void sendMail(String mail,String content){
        try{
            // ���÷����ʼ��Ļ�������
            final Properties props = new Properties();
            // ��ʾSMTP�����ʼ�����Ҫ���������֤
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", ALIDM_SMTP_HOST);
    //        props.put("mail.smtp.port", ALIDM_SMTP_PORT);  
            // ���ʹ��ssl����ȥ��ʹ��25�˿ڵ����ã�������������,
             props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
             props.put("mail.smtp.socketFactory.port", "465");
             props.put("mail.smtp.port", "465");
    
    
            // �����˵��˺�   //linshuiqiaolj@aliyun.com
            props.put("mail.user", "linshuiqiaolj@aliyun.com");
            // ����SMTP����ʱ��Ҫ�ṩ������(��������)
            props.put("mail.password", "131452lj");//131452lj
    
            // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // �û���������
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
            Session mailSession = Session.getInstance(props, authenticator);
            // �����ʼ���Ϣ
            MimeMessage message = new MimeMessage(mailSession);
            // ���÷�����
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);
    
            // �����ռ���
            InternetAddress to = new InternetAddress(mail);
            message.setRecipient(MimeMessage.RecipientType.TO, to);
    
            // �����ʼ�����
            message.setSubject("��˻�ִ");
            // �����ʼ���������
            message.setContent(content, "text/html;charset=UTF-8");
    
            // �����ʼ�
            Transport.send(message);
            System.out.println("���ͳɹ�");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
