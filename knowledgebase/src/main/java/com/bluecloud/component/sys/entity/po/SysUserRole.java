package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bluecloud.framework.core.mvc.base.domain.LongIdObject;

@Entity
@Table(name = "sys_user_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysUserRole extends LongIdObject {

	private static final long serialVersionUID = -1200368108352832916L;

	private Long userid;
	private Long roleid;
	public Long getUserid() {
		return this.userid;
	}
	public Long getRoleid() {
		return this.roleid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
}