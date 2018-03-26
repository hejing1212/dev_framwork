package com.hy.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hy.sys.core.entity.BaseSpuerEntity;
import com.hy.sys.utils.Comment;

@Entity
@Table(name = "sys_dict_item")
@Comment(value = "数据字典分项表")
public class SysDataDictItem extends BaseSpuerEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;                         // 主键',
	private String dictCode;                   // '分组ID',
	private String itemName;                   // '键值键',
	private String itemValue;                   // '值',
	private Integer sort;                       // '排序',
	private String remarks;                     // '描述',
	
	
	public SysDataDictItem(){
		
	}
	
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", length = 64)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "dict_code", length = 64)
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	
	
	@Column(name = "item_name", length = 128)
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Column(name = "item_value", length = 128)
	public String getItemValue() {
		return itemValue;
	}
	
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	
	
	@Column(name = "sort", length = 10)
	public Integer getSort() {
		return sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
			
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
