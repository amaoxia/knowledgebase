package com.bluecloud.framework;

/**
 * 通用值对数据结构
 * 
 * @author dafei
 * 
 */
public class Entry {
	Long id;
	String key;
	String value;

	public Entry() {
	}

	public Entry(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
