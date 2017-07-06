package com.hy.sys.core;

import java.io.Serializable;


public class AbstractBasicEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String UUID_GENERATED = "com.pub.boss.utils.uuid.HibernateIdGenerator";
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
