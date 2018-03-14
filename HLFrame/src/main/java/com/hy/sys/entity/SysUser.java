package com.hy.sys.entity;
// Generated 2017-6-26 17:18:09 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.hy.sys.core.entity.BaseSpuerEntity;
import com.hy.sys.utils.Comment;

/**
 * SysUser generated by hbm2java
 */
@Entity
@Table(name = "sys_user")
@Comment(value = "用户管理表")
public class SysUser extends BaseSpuerEntity {

	/**
	 * 是否锁定（1：正常；-1：删除；0：锁定；）
	 */
	public static final String STATUS_DELETE = "-1";
	public static final String STATUS_LOCKED = "0";
	public static final String STATUS_NORMAL = "1";

	private static final long serialVersionUID = 1L;
	private String userid;
	private String realname;
	private String username;
	private String portrait;
	private String password;
	private String salt; // 加密随机数
	private String email;
	private String phone;
	private String status;
	
	private String remarks;

	private Set<SysRole> roleList;

	public SysUser() {
	}

	public SysUser(String userid) {
		this.userid = userid;
	}

	public SysUser(String userid, String realname, String username, String portrait, String password, String salt,
			String email, String phone, String status,String remarks, Set<SysRole> roleList) {
		this.userid = userid;
		this.realname = realname;
		this.username = username;
		this.portrait = portrait;
		this.password = password;
		this.salt = salt;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.remarks = remarks;
		this.roleList = roleList;
	}

	@Id
	@GeneratedValue(generator = "tableIdGenerator")
	@GenericGenerator(name = "tableIdGenerator", strategy = UUID_GENERATED)
	@Column(name = "userid", unique = true, nullable = false, length = 64)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "realname", length = 64)
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "username", length = 64)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "portrait", length = 250)
	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	@Column(name = "password", length = 64)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "salt", length = 64)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "email", length = 64)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 32)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "status", length = 32)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	@Column(name = "remarks", length = 2048)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	@ManyToMany
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	public Set<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<SysRole> roleList) {
		this.roleList = roleList;
	}

	@Transient
	public String getCredentialsSalt() {
		return username + salt;
	}

	@Transient
	public Set<String> getRolesName() {
		Set<SysRole> roles = getRoleList();
		if (roles!=null && roles.size() > 0) {
			Set<String> set = new HashSet<String>();
			for (SysRole role : roles) {
				set.add(role.getName());
			}
			return set;
		} else {
			return null;
		}
	}

}
