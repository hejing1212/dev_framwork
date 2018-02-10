package com.hy.sys.entity;
// Generated 2017-6-26 17:18:09 by Hibernate Tools 4.3.5.Final

import java.util.Date;
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

import com.hy.sys.core.entity.AbstractBasicEntity;
import com.hy.sys.utils.Comment;

/**
 * SysUser generated by hbm2java
 */
@Entity
@Table(name = "sys_user")
@Comment(value="用户管理表")
public class SysUser  extends AbstractBasicEntity{

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
	private String salt; //加密随机数
	private String email;
	private String phone;
	private String status;
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	private String remarks;
	private String del_flag;
	private Set<SysRole> roleList;

	public SysUser() {
	}

	public SysUser(String userid) {
		this.userid = userid;
	}

	public SysUser(String userid, String realname, String username, String portrait, String password, String salt,
			String email, String phone, String status, String create_by, Date create_date, String update_by, Date update_date,
			String remarks, String del_flag,Set<SysRole> roleList) {
		this.userid = userid;
		this.realname = realname;
		this.username = username;
		this.portrait = portrait;
		this.password = password;
		this.salt = salt;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.create_by = create_by;
		this.create_date = create_date;
		this.update_by = update_by;
		this.update_date = update_date;
		this.remarks = remarks;
		this.del_flag = del_flag;
		this.roleList = roleList;
	}

	@Id
	@GeneratedValue(generator = "tableIdGenerator")
	@GenericGenerator(name = "tableIdGenerator", strategy =UUID_GENERATED)	
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

	@Column(name = "remarks", length = 2048)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "del_flag", length = 32)
	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	@ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")}) 
	public Set<SysRole>  getRoleList() {
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
	        Set<String> set = new HashSet<String>();
	        for (SysRole role : roles) {
			set.add(role.getName());
	        }
	        return set;
	    }

	
	
	
	
}
