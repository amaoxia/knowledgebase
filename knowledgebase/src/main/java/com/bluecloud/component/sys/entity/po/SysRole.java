package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.bluecloud.framework.core.mvc.base.BasePO;
/**
 * AbstractSysRole entity provides the base persistence definition of the
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRole extends BasePO {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1066955322703417632L;
	private Long roleid;
	private String rolename;
	private String roledesc;
	
	private String creater;
	private String createtime;
	private String editer;
	private String edittime;
	private Long enabled;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
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