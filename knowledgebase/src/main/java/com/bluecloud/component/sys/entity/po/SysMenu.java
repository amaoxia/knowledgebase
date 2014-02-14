package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * AbstractSysMenu entity provides the base persistence definition of the
 * SysMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysMenu implements java.io.Serializable {

	// Fields

	private String menucode;
	private String menuname;
	private String menuenname;
	private String parentcode;
	private String menuurl;
	private String menuflag;
	private String menuicon;
	private Long sequ;
	private Long enabled;
	private String syscode;
	// Constructors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String getMenucode() {
		return menucode;
	}
	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenuenname() {
		return menuenname;
	}
	public void setMenuenname(String menuenname) {
		this.menuenname = menuenname;
	}
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	public String getMenuurl() {
		return menuurl;
	}
	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}
	public String getMenuflag() {
		return menuflag;
	}
	public void setMenuflag(String menuflag) {
		this.menuflag = menuflag;
	}
	public String getMenuicon() {
		return menuicon;
	}
	public void setMenuicon(String menuicon) {
		this.menuicon = menuicon;
	}
	public Long getSequ() {
		return sequ;
	}
	public void setSequ(Long sequ) {
		this.sequ = sequ;
	}
	public Long getEnabled() {
		return enabled;
	}
	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}
	public String getSyscode() {
		return syscode;
	}
	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}


	
}