package com.bluecloud.framework.core.mvc.base.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * id为 Long
 * 
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class LongIdObject implements IBaseEntity {
	protected Long id;

	/**
	 * 主键策略
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
