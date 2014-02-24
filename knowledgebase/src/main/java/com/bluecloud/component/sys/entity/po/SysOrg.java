package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bluecloud.framework.core.mvc.base.domain.BasePO;

@Entity
@Table(name = "sys_org")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysOrg extends BasePO {

	private static final long serialVersionUID = -3713423716502155921L;
	private String orgcode;
	private String scode;
	private String parentcode;
	private String orgtype;
	private String orgname;
	private String orgasname;
	
	private String orgtel;
	private String orgfax;
	private String orgaddress;
	
	
	private String creater;
	private String createtime;
	private String editer;
	private String edittime;
	private Long enabled;
	
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgasname() {
		return orgasname;
	}
	public void setOrgasname(String orgasname) {
		this.orgasname = orgasname;
	}
	public String getOrgtel() {
		return orgtel;
	}
	public void setOrgtel(String orgtel) {
		this.orgtel = orgtel;
	}
	public String getOrgfax() {
		return orgfax;
	}
	public void setOrgfax(String orgfax) {
		this.orgfax = orgfax;
	}
	public String getOrgaddress() {
		return orgaddress;
	}
	public void setOrgaddress(String orgaddress) {
		this.orgaddress = orgaddress;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getEditer() {
		return editer;
	}
	public void setEditer(String editer) {
		this.editer = editer;
	}
	public String getEdittime() {
		return edittime;
	}
	public void setEdittime(String edittime) {
		this.edittime = edittime;
	}
	public Long getEnabled() {
		return enabled;
	}
	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}
	public String getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}

}