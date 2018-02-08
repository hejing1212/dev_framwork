/**
 * 
 */
package com.hy.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hy.sys.core.entity.AbstractBasicEntity;
import com.hy.sys.utils.Comment;

/**
 * @author hlin
 *
 */
@Entity
@Table(name = "sys_role")
@Comment(value = "用户角色管理表")
public class SysRole extends AbstractBasicEntity {

	private static final long serialVersionUID = 1L;

	private String roleid; // '角色ID',
	private String name; // '角色名称',
	private String code; // '英文名称',
	private String is_sys; // '是否系统数据',
	private String usable; // '是否可用',
	private String create_by; // '创建者',
	private Date create_date; // '创建时间',
	private String update_by; // '更新者',
	private Date update_date; // '更新时间',
	private String remarks; // '备注信息',
	private String del_flag; // '删除标记',

	public SysRole() {

	}

	public SysRole(String roleid, String name, String code, String is_sys, String usable, String create_by,
			Date create_date, String update_by, Date update_date, String remarks, String del_flag) {
		this.roleid = roleid;
		this.name = name;
		this.code = code;
		this.is_sys = is_sys;
		this.usable = usable;
		this.create_by = create_by;
		this.create_date = create_date;
		this.update_by = update_by;
		this.update_date = update_date;
		this.remarks = remarks;
		this.del_flag = del_flag;

	}

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

	@Column(name = "create_by", length = 64)
	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	@Column(name = "create_date", length = 64)
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	@Column(name = "update_by", length = 64)
	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	@Column(name = "update_date", length = 64)
	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	@Column(name = "remarks", length = 64)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "del_flag", length = 64)
	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

}