package com.bluecloud.component.sys.entity.po;

import com.bluecloud.framework.core.mvc.base.domain.LongIdObject;

public class SysOrgPost extends LongIdObject {

	private static final long serialVersionUID = -5765079303449788125L;
	private Long orgid;
	private Long postid;

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