package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

public class SysOrgPost implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orgid;
	private Long postid;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	public Long getPostid() {
		return postid;
	}
	public void setPostid(Long postid) {
		this.postid = postid;
	}

	
}