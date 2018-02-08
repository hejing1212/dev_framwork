package com.hy.sys.core.entity;

import java.io.Serializable;

import javax.persistence.Column;


public class AbstractBasicEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String UUID_GENERATED = "com.hy.sys.utils.HibernateIdGenerator";
	private String id;
	private Integer delFlag;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	/***
	 * 公共删除标记
	 * @return
	 */
	@Column(name = "delFlag", length = 11)
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
}
