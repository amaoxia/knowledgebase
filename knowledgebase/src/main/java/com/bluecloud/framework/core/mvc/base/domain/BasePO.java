package com.bluecloud.framework.core.mvc.base.domain;


/**
 * POJO基类
 * @date 2011/04/07
 */
public abstract class BasePO extends LongIdObject {
	private static final long serialVersionUID = -5972679257248081155L;
	
	private String sortcolumns;

	public String getSortcolumns() {
		return sortcolumns;
	}

	public void setSortcolumns(String sortcolumns) {
		this.sortcolumns = sortcolumns;
	}
}
