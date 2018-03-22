package com.hy.sys.core.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


@MappedSuperclass
public class BaseSpuerEntity extends AbstractBasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	private String del_flag="0";
	

	@Column(name = "create_by", length = 64)
	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	@Column(name = "create_date", length = 64,columnDefinition=(" default  null comment '创建时间'"))
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
		
	@Column(name = "update_by", length = 64,columnDefinition=("varchar(64)  default  null comment '创建人id'"))
	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	@Column(name = "update_date", length = 64,columnDefinition=("default  null comment '修改时间'"))
	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	@Column(name = "del_flag", length = 12,columnDefinition=("varchar(64)  default  null comment '修改人'"))
	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	
}
