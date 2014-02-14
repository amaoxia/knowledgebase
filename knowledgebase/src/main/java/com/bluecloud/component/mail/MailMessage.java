package com.bluecloud.component.mail;

/**
 * 邮件信息POJO
 * @author 艾铮
 * @since  2012-03-06
 */
public class MailMessage {

	private String consignee; // 收件人
	private String subject; // 主题
	private String text; // 内容

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
