package com.bluecloud.component.sys.entity.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * AbstractSysDict entity provides the base persistence definition of the
 * SysDict entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "sys_dict")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SysDict implements java.io.Serializable {

	// Fields

	private Long dictid;
	private String dictcode;
	private String parentcode;
	private String dictkey;
	private String dictname;
	private String descinfo;

	private String creater;
	private String createtime;
	private String editer;
	private String edittime;
	private Long enabled;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getDictid() {
		return dictid;
	}
	public void setDictid(Long dictid) {
		this.dictid = dictid;
	}
	public String getDictcode() {
		return dictcode;
	}
	public void setDictcode(String dictcode) {
		this.dictcode = dictcode;
	}
	public String getDictkey() {
		return dictkey;
	}
	public void setDictkey(String dictkey) {
		this.dictkey = dictkey;
	}
	public String getDictname() {
		return dictname;
	}
	public void setDictname(String dictname) {
		this.dictname = dictname;
	}
	public String getDescinfo() {
		return descinfo;
	}
	public void setDescinfo(String descinfo) {
		this.descinfo = descinfo;
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
	public String getParentcode() {
		return parentcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

}