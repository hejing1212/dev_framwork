package com.hy.sys.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hy.sys.core.entity.BaseSpuerEntity;
import com.hy.sys.utils.Comment;

@Entity
@Table(name = "sys_dict")
@Comment(value = "数据字典分组表")
public class SysDataDict extends BaseSpuerEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;                  //'主键',
	private String dictCode;                  //'分组名称',
	private String dictName;                  //'分组编码',
	private String remarks;                  //'备注',
	private Set<SysDataDictItem> dataDict;
	
	public SysDataDict(){
		
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
	
	@Column(name = "dict_name", length = 128)
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
	@Column(name = "dict_code", length = 128)
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@OneToMany
	@JoinColumn(name="dict_code")
	public Set<SysDataDictItem> getDataDict() {
		return dataDict;
	}
	public void setDataDict(Set<SysDataDictItem> dataDict) {
		this.dataDict = dataDict;
	}
	
	
	
	
	
}
