package com.iot.foundation.email;

import java.util.Properties;

import javax.mail.MessagingException;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * 本类测试简单邮件 直接用邮件发送
 * 
 * @author Administrator
 * 
 */
public class SingleMailSend {
	
	 public static void main(String args[]) throws MessagingException {
		 JavaMailSenderImpl ms = new  JavaMailSenderImpl();
	
	     ms.setHost("smtp.163.com");
	
		 ms.setUsername("wutongpeng803");//这里是自己的邮箱用户名，注意不用加@和后面的内容
		
		 ms.setPassword("xxxxx");//这里要换成自己的密码呀
		
		 Properties pt = new Properties();
		
		 pt.setProperty("mail.smtp.auth", "true");
		
		 ms.setJavaMailProperties(pt);
		
		
		
		 SimpleMailMessage msg = new SimpleMailMessage();
		
		 msg.setFrom("wutongpeng803@163.com");//发件地址
		
		 msg.setTo("wutongpeng803@163.com");//收件地址
		
		 msg.setSubject("Berry Message");//标题
		
		 msg.setText("hello world!");//内容
		
		 try{
		
		     ms.send(msg);//发送邮件
	
		     }catch(Exception e)
		
		     {		
		         e.printStackTrace();		
		     }
	 }
}
