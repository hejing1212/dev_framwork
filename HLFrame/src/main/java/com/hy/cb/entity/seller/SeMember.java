package com.hy.cb.entity.seller;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the se_member database table.
 * 
 */
@Entity
@Table(name="se_member")
@NamedQuery(name="SeMember.findAll", query="SELECT s FROM SeMember s")
public class SeMember extends SellerBasicEntity {
	private static final long serialVersionUID = 1L;
    
	/** 用户编号**/
	@Id
	@Column(name="userid")
	private String userid;

	/**用户登录ID **/
	private String username;

	/**用户类型(0老板，2:店长,4:配货,6:结算) **/
	private int usertype;
	
	/**身份证号码 **/
	@Column(name="card_no")
	private String cardNo;

	/**创建时间 **/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creat_time")
	private Date creatTime;

	/**邮箱 **/
	private String email;

	/**推广人编号 **/
	@Column(name="ep_no")
	private String epNo;

	/** **/
	private String extension;

	/** 手势密码**/
	private String gesture;

	/**后台登录时间 **/
	@Temporal(TemporalType.TIMESTAMP)
	private Date lasttime;

	/** 用户手机号**/
	private String mobilephone;

	/**用户密码 **/
	private String password;

	/**头像地址**/
	private String portrait;

	/**QQ号 **/
	private String qq;

	/**角色编号 **/
	private String roleid;

	/**所属档口编号 **/
	@Column(name="shop_no")
	private String shopNo;

	/** 状态{0禁用/1启用}**/
	private int status;

	

	/** 微信号 openid**/
	private String weixin;

	public SeMember() {
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEpNo() {
		return this.epNo;
	}

	public void setEpNo(String epNo) {
		this.epNo = epNo;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getGesture() {
		return this.gesture;
	}

	public void setGesture(String gesture) {
		this.gesture = gesture;
	}

	public Date getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPortrait() {
		return this.portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getShopNo() {
		return this.shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUsertype() {
		return this.usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getWeixin() {
		return this.weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

}