package com.bluecloud.framework.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


/**
 * 邮件发送
 * @author User
 *
 */
public class SpringMailUtil {
	
	
	/**
	 * 发送文本文件
	 * @param sender
	 * @throws Exception
	 */
	private void sendTextMail(JavaMailSender sender,String email) throws Exception {
		// 声明spring的简单邮件消息
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom("dafei@163.com");
		mail.setSubject("test by amigo");
		mail.setText("spring Mail的简单测试");
		//sender.send(mail);
	}
}
