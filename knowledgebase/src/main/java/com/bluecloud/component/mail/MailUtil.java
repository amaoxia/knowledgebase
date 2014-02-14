package com.bluecloud.component.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮件工具类
 * @author 艾铮
 * @since  2012-03-06
 */
public final class MailUtil {

	private static JavaMailSenderImpl sender;
	
	public static JavaMailSenderImpl getSender() {
		return sender;
	}

	public static void setSender(JavaMailSenderImpl sender) {
		MailUtil.sender = sender;
	}

	public static boolean send(MailMessage message) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();  
			 
			mailMessage.setTo(message.getConsignee()); // 收件人
			mailMessage.setFrom(sender.getUsername()); // 发件人
			mailMessage.setSubject(message.getSubject()); // 主题
			mailMessage.setText(message.getText()); // 内容  
			
			// 发送邮件邮件  
			//sender.send(mailMessage);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

}
