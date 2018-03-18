package com.hy.sys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.hy.sys.core.entity.BaseSpuerEntity;
import com.hy.sys.utils.Comment;
 
@Entity
@Table(name = "sys_menu")
@Comment(value = "菜单管理表")
public class SysMenu extends BaseSpuerEntity {

	private static final long serialVersionUID = 1L;
	
	private String menuid; // '菜单ID',
	private String name; // '资源路径',
	private Integer type; // 菜单类型{1:菜单,2:功能}',
	private String url; // 点击后前往的地址',
	private String parent_id; // '父编号',
	private String parent_ids; // 父编号列表',
	private String permission; // '权限字符串',
	private Integer isshow; // '是否显示',0显示 1不显示
	private Integer sort; // '排序',
	private String menu_icon; // '图标',
	private String remarks; // '摘要',
	private ArrayList<SysMenu>  children;
	private Set<SysFunction> function;
	
	
    private String strFun;          //记录所有功能名称及ID的拼接
	
	public SysMenu() {
		
	}
	public SysMenu(String menuid, String name, Integer type, String url, String parent_id, String parent_ids,
			String permission, Integer isshow, Integer sort, String menu_icon, String remarks, SysUser create_by,
			Date create_date,SysUser update_by,Date update_date,String del_flag) {
		this.menuid=menuid;                            // '菜单ID',
		this.name=name;                                // '资源路径',
		this.type=type;                                // 菜单类型{1:菜单,2:功能}',
		this.url=url;                                  // 点击后前往的地址',
		this.parent_id=parent_id;                       // '父编号',
		this.parent_ids=parent_ids;                     // 父编号列表',
		this.permission=permission;                     // '权限字符串',
		this.isshow=isshow;                             // '是否显示',
		this.sort=sort;                                 // '排序',
		this.menu_icon=menu_icon;                       // '图标',
		this.remarks=remarks;                            // '摘要',
		
	}
	
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "menuid", length = 64)
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	@Column(name = "name", length = 128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 32)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "url", length = 255)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "parent_id", length = 32)
	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	@Column(name = "parent_ids", length = 1000)
	public String getParentIds() {
		return parent_ids;
	}

	public void setParentIds(String parent_ids) {
		this.parent_ids = parent_ids;
	}

	@Column(name = "permission", length = 100)
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Column(name = "isshow", length = 64)
	public Integer getIsshow() {
		return isshow;
	}

	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}

	@Column(name = "sort", length = 5)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "menu_icon", length = 2255)
	public String getMenu_icon() {
		return menu_icon;
	}

	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}

	@Column(name = "remarks", length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**增加了一个数据库没有的字段**/
	@Transient
	public ArrayList<SysMenu> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<SysMenu> children) {
		this.children = children;
	}
	
	
 	
	@OneToMany
	@OrderBy("sort ASC")
	@JoinColumn(name = "menu_id")	
	public Set<SysFunction> getFunction() {
		return function;
	}
	public void setFunction(Set<SysFunction> function) {
		this.function = function;
	}
	
	
	@Transient
	public String getStrFun() {
		return strFun;
	}
	public void setStrFun(String strFun) {
		this.strFun = strFun;
	}
	
	
	

}
