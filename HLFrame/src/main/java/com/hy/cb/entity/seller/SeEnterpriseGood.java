package com.hy.cb.entity.seller;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the se_enterprise_goods database table.
 * 
 */
@Entity
@Table(name="se_enterprise_goods")
@NamedQuery(name="SeEnterpriseGood.findAll", query="SELECT s FROM SeEnterpriseGood s")
public class SeEnterpriseGood extends SellerBasicEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "tableIdGenerator")
	@GenericGenerator(name = "tableIdGenerator", strategy = UUID_GENERATED)
	@Column(name="ep_goods_id")
	private String epGoodsId;

	@Column(name="ep_goods_name")
	private String epGoodsName;

	@Column(name="goods_id")
	private String goodsId;

	@Column(name="shop_no")
	private String shopNo;
	
	@Column(name="ep_alias")
	private String epAlias;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ep_create_teim")
	private Date epCreateTeim;

	

	@Column(name="supplier_no")
	private String supplierNo;

	@Column(name="unit_price")
	private BigDecimal unitPrice;

	@Column(name="wrapper_type")
	private int wrapperType;

	public SeEnterpriseGood() {
	}

	public String getEpAlias() {
		return this.epAlias;
	}

	public void setEpAlias(String epAlias) {
		this.epAlias = epAlias;
	}

	public Date getEpCreateTeim() {
		return this.epCreateTeim;
	}

	public void setEpCreateTeim(Date epCreateTeim) {
		this.epCreateTeim = epCreateTeim;
	}

	public String getEpGoodsId() {
		return this.epGoodsId;
	}

	public void setEpGoodsId(String epGoodsId) {
		this.epGoodsId = epGoodsId;
	}

	public String getEpGoodsName() {
		return this.epGoodsName;
	}

	public void setEpGoodsName(String epGoodsName) {
		this.epGoodsName = epGoodsName;
	}

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getShopNo() {
		return this.shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getWrapperType() {
		return this.wrapperType;
	}

	public void setWrapperType(int wrapperType) {
		this.wrapperType = wrapperType;
	}

}