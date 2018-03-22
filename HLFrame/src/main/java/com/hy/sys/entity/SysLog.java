/**
 * 
 */
package com.hy.sys.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hy.sys.core.entity.AbstractBasicEntity;
import com.hy.sys.core.entity.BaseSpuerEntity;
import com.hy.sys.utils.Comment;

/**
 * @author hlin
 *
 */
@Entity
@Table(name = "sys_log")
@Comment(value = "操作日志管理表")
public class SysLog extends   BaseSpuerEntity {

	private static final long serialVersionUID = 1L;

	// 日志类型（1：接入日志；2：错误日志）
	public static final Integer TYPE_ACCESS = 1;
	public static final Integer TYPE_EXCEPTION = 2;
	 
	
	
	private String  id;   
	private String  opt_data_id;                   //'操作数据id',
	private String  opt_table_name;               //'操作表名',
	private String  opt_table_comment;           //'操作表的注释名',
	private String  opt_user_id;                //'操作人id',
	private String  opt_user_name;              //'操作人姓名',
	private Integer  opt_type;                  //'操作类型： 1.新增  2.更新  3.状态删除  4.物理删除',
	private String  opt_content;                 //'操作内容',
	
	  
	public  SysLog(){
		
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
	
	@Column(name = "opt_data_id", nullable = true, length = 128,columnDefinition=("varchar(64)  default null comment '操作数据id'"))
	public String getOpt_data_id() {
		return opt_data_id;
	}

	public void setOpt_data_id(String opt_data_id) {
		this.opt_data_id = opt_data_id;
	}

	@Column(name = "opt_table_name", nullable = true, length = 64,columnDefinition=("varchar(64)  default  null comment '操作表名'"))
	public String getOpt_table_name() {
		return opt_table_name;
	}

	public void setOpt_table_name(String opt_table_name) {
		this.opt_table_name = opt_table_name;
	}

	@Column(name = "opt_table_comment", nullable = true, length = 64,columnDefinition=("varchar(64)  default  null comment '操作表的注释名'"))
	public String getOpt_table_comment() {
		return opt_table_comment;
	}

	public void setOpt_table_comment(String opt_table_comment) {
		this.opt_table_comment = opt_table_comment;
	}

	@Column(name = "opt_user_id", nullable = true, length = 64,columnDefinition=("varchar(64)  default  null comment '操作人id'"))
	public String getOpt_user_id() {
		return opt_user_id;
	}

	public void setOpt_user_id(String opt_user_id) {
		this.opt_user_id = opt_user_id;
	}

	@Column(name = "opt_user_name", nullable = true, length = 64,columnDefinition=("varchar(64)  default  null comment '操作人姓名'"))
	public String getOpt_user_name() {
		return opt_user_name;
	}

	public void setOpt_user_name(String opt_user_name) {
		this.opt_user_name = opt_user_name;
	}

	@Column(name = "opt_type", nullable = true, length = 11,columnDefinition=("int(11)  default  null comment '操作类型： 1.新增  2.更新  3.状态删除  4.物理删除'"))
	public Integer getOpt_type() {
		return opt_type;
	}

	public void setOpt_type(Integer opt_type) {
		this.opt_type = opt_type;
	}

	@Type(type="text")  
	@Column(name = "opt_content",columnDefinition=(" default  null comment '操作内容'"))
	public String getOpt_content() {
		return opt_content;
	}

	public void setOpt_content(String opt_content) {
		this.opt_content = opt_content;
	}

 
	
}
