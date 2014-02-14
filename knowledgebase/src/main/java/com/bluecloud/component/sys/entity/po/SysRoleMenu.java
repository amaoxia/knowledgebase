package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * AbstractSysUser entity provides the base persistence definition of the
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "sys_role_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysRoleMenu implements java.io.Serializable {

	// Fields

	private Long id;
	private Long roleid;
	private String menucode;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}
	public Long getRoleid() {
		return this.roleid;
	}
	public String getMenucode() {
		return this.menucode;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}
	
}