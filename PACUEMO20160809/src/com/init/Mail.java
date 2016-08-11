package com.init;


import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Mail {
    String to = "tw11509@gmail.com"; // 收件人
    String from = "tw50318@gmal.com"; // 寄件人
    String host = "smtp.gmail.com"; // STMP主機
    String username = GlobalService_mine.EMAIL_USERNAME; // User
    String password = GlobalService_mine.EMAIL_PASSWORD; // Password
    String subject = "test"; // title
    String content = "test"; // content
    int port = 587;

    public Mail() {
    }

    public Mail(String to, String from, String host, String username,
            String password, String subject, String content) {
        this.to = to;
        this.from = from;
        this.host = host;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }

    /**
     * 把主题转换为中文
     * 
     * @param strText
     * @return
     */
    public String transferChinese(String strText) {

        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(),
                    "UTF-8"), "UTF-8", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strText;
    }

    /**
     * 发送邮件
     * 
     * @return 成功返回true，失败返回false
     */
    public boolean sendMail() {
        // construct mail session
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress(from));
            
            System.out.println(from);
            
            InternetAddress[] address = { new InternetAddress(to) };
            /**
             * setRecipient（Message.RecipientType type, Address
             * address），用于设置邮件的接收者。<br>
             * 有两个参数，第一个参数是接收者的类型，第二个参数是接收者。<br>
             * 接收者类型可以是Message.RecipientType .TO，Message
             * .RecipientType.CC和Message.RecipientType.BCC，TO表示主要接收人，CC表示抄送人
             * ，BCC表示秘密抄送人。接收者与发送者一样，通常使用InternetAddress的对象。
             */
            msg.setRecipients(Message.RecipientType.TO, address);
 
            subject = transferChinese(subject);
            msg.setSubject(subject);

            Multipart mp = new MimeMultipart();


            MimeBodyPart mbpContent = new MimeBodyPart();

            // mbpContent.setText(content);

            mbpContent.setContent(content, "text/html;charset=utf-8");

            mp.addBodyPart(mbpContent);

            msg.setContent(mp);
            
            msg.setSentDate(new Date());
            
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args){
    	
    	Mail test = new Mail();
    	System.out.println(test.sendMail());
    	
    }
    
    
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}