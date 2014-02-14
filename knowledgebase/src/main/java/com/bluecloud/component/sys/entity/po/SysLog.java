package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * AbstractSysLog entity provides the base persistence definition of the SysLog
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "sys_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysLog implements java.io.Serializable {

	// Fields

	private Long logid;
	private String loginuser;
	private String menuname;
	private String actionname;
	private String createtime;
	String logcontent;
	Long logtype;
	Long delmark;
	// Constructors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getLogid() {
		return logid;
	}
	public void setLogid(Long logid) {
		this.logid = logid;
	}
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