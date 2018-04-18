package com.hy.cb.entity.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hy.cb.entity.seller.SellerBasicEntity;



/**
 * The persistent class for the or_order_detailed database table.
 * 
 */
@Entity
@Table(name="or_order_detailed")
@NamedQuery(name="OrderDetailed.findAll", query="SELECT o FROM OrderDetailed o")
public class OrderDetailed extends SellerBasicEntity {
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(generator = "tableIdGenerator")
	@GenericGenerator(name = "tableIdGenerator", strategy = UUID_GENERATED)
	@Column(name="id")
	private String Id;
	
	@Column(name="order_no")
	private String orderNo;
	
	private BigDecimal amount;

	@Column(name="before_price")
	private BigDecimal beforePrice;

	@Column(name="goods_no")
	private String goodsNo;

	private float price;

	private BigDecimal subtotal;

	@Column(name="suggest_price")
	private BigDecimal suggestPrice;

	@Column(name="valuation_method")
	private int valuationMethod;

	private float weight;

	
	public OrderDetailed() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBeforePrice() {
		return this.beforePrice;
	}

	public void setBeforePrice(BigDecimal beforePrice) {
		this.beforePrice = beforePrice;
	}

	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getSuggestPrice() {
		return this.suggestPrice;
	}

	public void setSuggestPrice(BigDecimal suggestPrice) {
		this.suggestPrice = suggestPrice;
	}

	public int getValuationMethod() {
		return this.valuationMethod;
	}

	public void setValuationMethod(int valuationMethod) {
		this.valuationMethod = valuationMethod;
	}

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}