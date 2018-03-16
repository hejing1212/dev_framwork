/**
 * 
 */
package com.hy.sys.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hy.sys.core.entity.BaseSpuerEntity;
import com.hy.sys.utils.Comment;

/**
 * @author hlin
 *
 */
@Entity
@Table(name = "sys_role")
@Comment(value = "用户角色管理表")
public class SysRole extends BaseSpuerEntity {

	private static final long serialVersionUID = 1L;

	private String roleid; // '角色ID',
	private String name; // '角色名称',
	private String code; // '英文名称',
	private String is_sys; // '是否系统数据',
	private String usable; // '是否可用',
	private String remarks; // '备注信息',
	
	

	public SysRole() {

	}

	public SysRole(String roleid, String name, String code, String is_sys, String usable, String remarks) {
		this.roleid = roleid;
		this.name = name;
		this.code = code;
		this.is_sys = is_sys;
		this.usable = usable;		
		this.remarks = remarks;
		

	}

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "roleid", length = 64)
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", length = 64)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "is_sys", length = 64)
	public String getIs_sys() {
		return is_sys;
	}

	public void setIs_sys(String is_sys) {
		this.is_sys = is_sys;
	}

	@Column(name = "usable", length = 64)
	public String getUsable() {
		return usable;
	}

	public void setUsable(String usable) {
		this.usable = usable;
	}

	 
	@Column(name = "remarks", length = 64)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	 
}
