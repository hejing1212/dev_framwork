package com.hy.sys.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.hy.sys.core.entity.AbstractBasicEntity;
import com.hy.sys.utils.Comment;

@Table(name = "sys_user_role")
@Comment(value = "用户 角色 联表")
public class SysUserRole extends AbstractBasicEntity{

	private static final long serialVersionUID = 1L;
	

	 
	private String user_id;        //  '用户编号',
	private String role_id;         // '角色编号',
	
	
	@Column(name = "user_id", length = 32)
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Column(name = "role_id", length = 32)
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
}
