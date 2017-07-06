package com.hy.sys.core;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


@MappedSuperclass
public class BaseSpuerEntity extends AbstractBasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String createUserId;// 添加人ID
	private String createUserName;// 创建人用户名称
	private Date createTime;// 添加时间	
	private String updateUserId;// 最后一次操作用户ID
	private String updateUserName;// 最后一次操作用户名称
	private Date updateTime;// 最后一次更新时间
	private Integer optLock;// 乐观锁
	private Integer dataState=1;//数据状态
	
	@Column(name = "create_user_id", length = 32)
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	@Column(name = "create_user_name", length = 20)
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Column(name = "create_time", length = 20)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_user_id", length = 32)
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "update_user_name", length = 20)
	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	@Column(name = "update_time", length = 20)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Version
	@Column(name = "opt_lock", length = 11)
	public Integer getOptLock() {
		return optLock;
	}

	public void setOptLock(Integer optLock) {
		this.optLock = optLock;
	}
	
	@Column(name = "data_state", length = 11)
	public Integer getDataState() {
		return dataState;
	}

	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}
	
}
