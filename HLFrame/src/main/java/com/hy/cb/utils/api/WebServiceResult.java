package com.hy.cb.utils.api;

public class WebServiceResult {

	/**
	 * 状态码
	 */
	private int code=400;
	
	/**
	 * 数据
	 */
	private Object Datas;	
	
	/**
	 * 消息
	 */
	private String message;
	
	/**
	 * 执行结果 TURE 成功 FALSE 失败
	 */
	private Boolean success = false;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Object getDatas() {
		return Datas;
	}
	public void setDatas(Object datas) {
		Datas = datas;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
