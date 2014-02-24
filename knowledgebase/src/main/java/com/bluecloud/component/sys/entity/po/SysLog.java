package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bluecloud.framework.core.mvc.base.domain.LongIdObject;

@Entity
@Table(name = "sys_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysLog extends LongIdObject {

	private static final long serialVersionUID = 8528253529954484237L;
	private String loginuser;
	private String menuname;
	private String actionname;
	private String createtime;
	private String logcontent;
	private Long logtype;
	private Long delmark;
	
	public String getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getActionname() {
		return actionname;
	}
	public void setActionname(String actionname) {
		this.actionname = actionname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getLogcontent() {
		return logcontent;
	}
	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}
	public Long getLogtype() {
		return logtype;
	}
	public void setLogtype(Long logtype) {
		this.logtype = logtype;
	}
	public Long getDelmark() {
		return delmark;
	}
	public void setDelmark(Long delmark) {
		this.delmark = delmark;
	}

	
	
}