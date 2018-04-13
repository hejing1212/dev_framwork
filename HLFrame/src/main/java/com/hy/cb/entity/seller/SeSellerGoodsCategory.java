package com.hy.cb.entity.seller;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the se_seller_category database table.
 * 
 */
@Entity
@Table(name="se_seller_goods_category")
@NamedQuery(name="SeSellerGoodsCategory.findAll", query="SELECT s FROM SeSellerGoodsCategory s")
public class SeSellerGoodsCategory extends SellerBasicEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "tableIdGenerator")
	@GenericGenerator(name = "tableIdGenerator", strategy = UUID_GENERATED)
	private String gid;

	@Column(name="category_id")
	private String categoryId;

	@Column(name="seller_id")
	private String sellerId;

	private int sort;

	public SeSellerGoodsCategory() {
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

}