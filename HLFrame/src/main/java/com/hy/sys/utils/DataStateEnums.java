package com.hy.sys.utils;

/**
 * 公用审核状态
 * 
 * @author HeXuDong
 * 
 */
public enum DataStateEnums {

	/**
	 * 无效
	 */
	DATA_INVALID("无效", 0),
	/**
	 * 有效
	 */
	DATA_EFF("有效", 1),
	/**
	 * 删除
	 */
	DATA_DELETE("删除", -9);	

	private String name;
	private Integer value;
	
	public static DataStateEnums valueOf(Integer value) {
		for (DataStateEnums type : DataStateEnums.values()) {
			if (type.getValue().equals(value)) {
				return type;
			}
		}
		return null;
	}

	private DataStateEnums(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}
}
