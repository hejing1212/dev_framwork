package com.hy.cb.entity.seller;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the se_customer database table.
 * 
 */
@Entity
@Table(name="se_customer")
@NamedQuery(name="SeCustomer.findAll", query="SELECT s FROM SeCustomer s")
public class SeCustomer extends SellerBasicEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "tableIdGenerator")
	@GenericGenerator(name = "tableIdGenerator", strategy = UUID_GENERATED)
	private String custid;
	
	@Column(name="common_use")
	private int commonUse;

	

	private String email;

	private String enterprise;

	@Column(name="ep_name")
	private String epName;

	private String groups;

	private String phone;

	@Lob
	private String remarks;

	@Column(name="sale_type")
	private int saleType;

	@Column(name="seller_id")
	private String sellerId;

	private int sort;

	private String tel;

	public SeCustomer() {
	}

	public int getCommonUse() {
		return this.commonUse;
	}

	public void setCommonUse(int commonUse) {
		this.commonUse = commonUse;
	}

	public String getCustid() {
		return this.custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getEpName() {
		return this.epName;
	}

	public void setEpName(String epName) {
		this.epName = epName;
	}

	public String getGroups() {
		return this.groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getSaleType() {
		return this.saleType;
	}

	public void setSaleType(int saleType) {
		this.saleType = saleType;
	}

	public String getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}