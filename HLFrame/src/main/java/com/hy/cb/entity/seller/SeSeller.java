package com.hy.cb.entity.seller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.hy.cb.entity.member.SeMember;


/**
 * The persistent class for the se_seller database table.
 * 
 */
@Entity
@Table(name="se_seller")
@NamedQuery(name="SeSeller.findAll", query="SELECT s FROM SeSeller s")
public class SeSeller extends SellerBasicEntity{
	private static final long serialVersionUID = 1L;


    /**商家编号**/
	@Id
	@GeneratedValue(generator = "tableIdGenerator")
	@GenericGenerator(name = "tableIdGenerator", strategy = UUID_GENERATED)
	@Column(name="seller_id")
	private String seller_id;
	
	/**商家名称**/
	private String name;

	/**是否为采购商{1：是，0：否}**/
	private int purchase;
	
	/**是否为零售商{1：是，0：否}**/
	private int retail;

	/**状态{0禁用/1启用}**/
	private int status;

	/**联系电话**/
	private String tel;

	/**联系人电话**/
	private String telephone;

	/**所在省**/
	private String province;
	
	/**所在市**/
	private String city;
	
	/**所在区**/
	private String region;
	
	/**社区乡镇**/
	private String town;

	/**是否为批发商{1：是，0：否}**/
	private int wholesale;

	/**详细地址**/
	private String address;

	

	/**联系人**/
	private String contacts;

	/**创建时间**/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	/**企业证件号码**/
	@Column(name="id_no")
	private String idNo;

	/**企业介绍**/
	@Column(columnDefinition="TEXT")
	private String introduce;

	/**纬度**/
	@Column(name = "latitude", length = 32)
	private BigDecimal latitude;
	
	/**经度**/
	@Column(name = "longitude", length = 32)
	private BigDecimal longitude;

	/**企业LOGO**/
	private String logo;


	public SeSeller() {
	}
	
	 
	
	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getPurchase() {
		return this.purchase;
	}

	public void setPurchase(int purchase) {
		this.purchase = purchase;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getRetail() {
		return this.retail;
	}

	public void setRetail(int retail) {
		this.retail = retail;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public int getWholesale() {
		return this.wholesale;
	}

	public void setWholesale(int wholesale) {
		this.wholesale = wholesale;
	}

}