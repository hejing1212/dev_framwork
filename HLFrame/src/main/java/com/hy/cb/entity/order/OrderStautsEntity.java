package com.hy.cb.entity.order;

public class OrderStautsEntity {
	private String stautsName;
	private Integer num;
	private String status;
	private String picUrl;
	
	public OrderStautsEntity(){
		
	}
	public String getStautsName() {
		return stautsName;
	}
	public void setStautsName(String stautsName) {
		this.stautsName = stautsName;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
