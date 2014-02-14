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
@Table(name = "sys_param")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysParam implements java.io.Serializable {

	// Fields

	private Long id;
	private String paramkey;
	private String paramvalue;

	private String creater;
	private String createtime;
	private String editer;
	private String edittime;
	private Long enabled;
	private String notes;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParamkey() {
		return paramkey;
	}
	public void setParamkey(String paramkey) {
		this.paramkey = paramkey;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}