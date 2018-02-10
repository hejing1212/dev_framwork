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
import com.hy.sys.utils.Comment;

/**
 * @author hlin
 *
 */
@Entity
@Table(name = "sys_log")
@Comment(value = "操作日志管理表")
public class SysLog extends AbstractBasicEntity {

	private static final long serialVersionUID = 1L;

	// 日志类型（1：接入日志；2：错误日志）
	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";
	private String id;
	private String type; // '日志类型',
	private String title; // '日志标题',
	private String content; // 日志内容',
	private String logtype; // '操作方式',
	private SysUser create_by; // '创建者',
	private Date create_date; // '创建时间',
	private String remote_addr; // '操作IP地址',
	private String user_agent; // '用户代理',
	private String request_uri; // '请求URI',
	private String method; // '操作方式',
	private Map<String, String[]> params; // '操作提交的数据',
	private String exception; // '异常信息',

	public SysLog() {

	}

	public SysLog(String type, String title, String content, String logtype, SysUser create_by, Date create_date,
			String remote_addr, String user_agent, String request_uri, String method, Map<String, String[]> params,
			String exception) {
		this.type = type;
		this.title = title;
		this.content = content;
		this.logtype = logtype;
		this.create_by = create_by;
		this.create_date = create_date;
		this.remote_addr = remote_addr;
		this.user_agent = user_agent;
		this.request_uri = request_uri;

		this.method = method;
		this.params = params;
		this.exception = exception;

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

	/** 日志类型 */
	@Column(name = "type", nullable = true, length = 1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** 日志标题 */
	@Column(name = "title", nullable = true, length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/** 日志内容 */
	@Column(name = "content", nullable = true, length = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/** 操作方式 */
	@Column(name = "logtype", nullable = true, length = 4)
	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	/** 创建者 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_by")
	@JsonIgnore
	public SysUser getCreateBy() {
		return create_by;
	}

	public void setCreateBy(SysUser create_by) {
		this.create_by = create_by;
	}

	/** 创建时间 */
	@Column(name = "create_date", nullable = true, length = 19)
	public Date getCreateDate() {
		return create_date;
	}

	public void setCreateDate(Date create_date) {
		this.create_date = create_date;
	}

	/** 操作IP地址 */
	@Column(name = "remote_addr", nullable = true, length = 255)
	public String getRemoteAddr() {
		return remote_addr;
	}

	public void setRemoteAddr(String remote_addr) {
		this.remote_addr = remote_addr;
	}

	/** 用户代理 */
	@Column(name = "user_agent", nullable = true, length = 255)
	public String getUserAgent() {
		return user_agent;
	}

	public void setUserAgent(String user_agent) {
		this.user_agent = user_agent;
	}

	/** 请求URI */
	@Column(name = "request_uri", nullable = true, length = 255)
	public String getRequestUri() {
		return request_uri;
	}

	public void setRequestUri(String request_uri) {
		this.request_uri = request_uri;
	}

	/** 操作方式 */
	@Column(name = "method", nullable = true, length = 5)
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "params", nullable = true, length = 65535)
	@Type(type = "com.hy.sys.core.hibernate.type.ObjectSerializeUserType")
	public Map<String, String[]> getParams() {
		return params;
	}

	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}

	/** 异常信息 */
	@Column(name = "exception", nullable = true, length = 65535)
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
