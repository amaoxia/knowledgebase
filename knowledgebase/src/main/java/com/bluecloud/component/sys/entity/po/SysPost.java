package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

public class SysPost implements java.io.Serializable {

	// Fields

	private Long postid;
	private String postcode;
	private String postname;
	private String postdesc;
	
	private String creater;
	private String createtime;
	private String editer;
	private String edittime;
	private Long enabled;
	public Long getPostid() {
		return postid;
	}
	public void setPostid(Long postid) {
		this.postid = postid;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPostname() {
		return postname;
	}
	public void setPostname(String postname) {
		this.postname = postname;
	}
	public String getPostdesc() {
		return postdesc;
	}
	public void setPostdesc(String postdesc) {
		this.postdesc = postdesc;
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


	

}