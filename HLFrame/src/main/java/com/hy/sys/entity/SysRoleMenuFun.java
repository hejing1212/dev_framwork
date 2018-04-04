package com.hy.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hy.sys.core.entity.AbstractBasicEntity;

/**
 *  
 * 
 */
@Entity
@Table(name = "sys_role_menu_fun")
@NamedQuery(name = "SysRoleMenuFun.findAll", query = "SELECT s FROM SysRoleMenuFun s")
public class SysRoleMenuFun extends AbstractBasicEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	private String id;

	@Column(name = "fun_id")
	private String funId;

	@Column(name = "menu_id")
	private String menuId;

	@Column(name = "role_id")
	private String roleId;

	public SysRoleMenuFun() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunId() {
		return this.funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}