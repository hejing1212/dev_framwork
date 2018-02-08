package com.hy.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hy.sys.core.entity.AbstractBasicEntity;
import com.hy.sys.utils.Comment;

@Entity
@Table(name = "sys_role_menu")
@Comment(value = "角色菜单关联表")
public class SysRoleMenu extends AbstractBasicEntity{

	 
	private static final long serialVersionUID = 1L;
	private String menu_id;          // '菜单编号'
	private String role_id;          // '角色编号'
	
	
	
	 
	@Column(name = "menu_id", length = 32)
	public String getMenu_id() {
		return menu_id;
	}
	
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	
	
	@Column(name = "role_id", length = 32)
	public String getRole_id() {
		return role_id;
	}
	
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	
	
	
}
