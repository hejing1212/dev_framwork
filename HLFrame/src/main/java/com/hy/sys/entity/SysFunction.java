package com.hy.sys.entity;

import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hy.sys.core.entity.BaseSpuerEntity;
import com.hy.sys.utils.Comment;

@Entity
@Table(name = "sys_function")
@Comment(value = "菜单功能管理")
public class SysFunction extends BaseSpuerEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String funid;
	private String fun_action; // '请求方法名称',
	private String name; // '功能名称',
	private String menu_id; // 对应菜单 ID',
	private String permission; // '权限字符串',
	private Integer sort=0; // '排序',
	private String menu_icon; // '按钮图标',
	private String remarks; // '备注',
    
	public SysFunction() {
		
	}
	
	

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "funid", length = 32)
	public String getFunid() {
		return funid;
	}

	public void setFunid(String funid) {
		this.funid = funid;
	}
	
	
	@Column(name = "fun_action", length = 255)
	public String getFun_action() {
		return fun_action;
	}

	public void setFun_action(String fun_action) {
		this.fun_action = fun_action;
	}

	
	@Column(name = "name", length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Column(name = "menu_id", length = 32)
	public String getMenuId() {
		return menu_id;
	}

	public void setMenuId(String menu_id) {
		this.menu_id = menu_id;
	}

	
	@Column(name = "permission", length = 255)
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	
	@Column(name = "sort", length = 11)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
	@Column(name = "menu_icon", length = 255)
	public String getMenu_icon() {
		return menu_icon;
	}

	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}

	
	@Column(name = "remarks", length = 1000)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

class FunComparator implements Comparator<SysFunction>{  
    @Override  
    public int compare(SysFunction funa, SysFunction funb) {      
        return funa.getSort()-funb.getSort();  //o1.score - o2.score;  
    }      
}  
