package com.hy.sys.utils.logs;

/**
 * 操作类型
 * 
 * @author hyz<br/>
 * @see {@link com.pub.boss.entity.sys.SysLogEntity}
 */
public enum SysLogOperationType {

	/**
	 * 新增
	 */
	Add("新增", 1),
	/**
	 * 删除
	 */
	Delete("删除", 2),
	/**
	 * 修改
	 */
	Update("修改", 3),
	/**
	 * 查询
	 */
	Query("查询", 4);

	private String name;
	private Integer value;

	private SysLogOperationType(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public SysLogOperationType valueOf(Integer value) {
		for (SysLogOperationType type : SysLogOperationType.values()) {
			if (type.getValue().equals(value)) {
				return type;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
