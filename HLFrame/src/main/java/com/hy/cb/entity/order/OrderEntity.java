package com.hy.cb.entity.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hy.cb.entity.seller.SellerBasicEntity;


/**
 * The persistent class for the or_order database table.
 * 
 */
@Entity
@Table(name="or_order")
@NamedQuery(name="OrderEntity.findAll", query="SELECT o FROM OrderEntity o")
public class OrderEntity extends SellerBasicEntity {
	private static final long serialVersionUID = 1L;

    @Id
	@Column(name="order_no")
	private String orderNo;
	
	private String address;

	private String area;

	private int balance=0;

	@Column(name="balance_persion")
	private String balancePersion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="balance_time")
	private Date balanceTime;
    /**车号**/
	@Column(name="car_no")
	private String carNo;

	private String city;

	private String community;

	/**代销或合伙**/
	private int consignment=0;

	private String county;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="distribution_persion")
	private String distributionPersion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="distribution_time")
	private Date distributionTime;

	private String drawer;

	@Column(name="invoice_no")
	private String invoiceNo;

	private BigDecimal latitude;

	private BigDecimal longitude;


	/**订单类型，2:代销合伙，4:开单销售订单，6:预定订单，8:采购订单**/
	@Column(name="order_type")
	private int orderType=0;
  
	/**支付状态**/
	@Column(name="pay_status")
	private int payStatus=0;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pay_time")
	private Date payTime;

	/**支付类型**/
	@Column(name="pay_type")
	private int payType;

	private String province;
	
    /**采购商,买方**/
	@Column(name="purchase_no")
	private String purchaseNo;
    
	@Column(name="settlement_persion")
	private String settlementPersion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="settlement_time")
	private Date settlementTime;

	@Column(name="shop_no")
	private String shopNo;

	private int stauts=0;
    /**供应商，卖方**/
	@Column(name="supplier_no")
	private String supplierNo;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "order_no")
	private Set<OrderDetailed> orderDetailed;
	
	
	public OrderEntity() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getBalancePersion() {
		return this.balancePersion;
	}

	public void setBalancePersion(String balancePersion) {
		this.balancePersion = balancePersion;
	}

	public Date getBalanceTime() {
		return this.balanceTime;
	}

	public void setBalanceTime(Date balanceTime) {
		this.balanceTime = balanceTime;
	}

	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCommunity() {
		return this.community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	/** 销售方式{1合伙,2代销}**/
	public int getConsignment() {
		return this.consignment;
	}

	public void setConsignment(int consignment) {
		this.consignment = consignment;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDistributionPersion() {
		return this.distributionPersion;
	}

	public void setDistributionPersion(String distributionPersion) {
		this.distributionPersion = distributionPersion;
	}

	public Date getDistributionTime() {
		return this.distributionTime;
	}

	public void setDistributionTime(Date distributionTime) {
		this.distributionTime = distributionTime;
	}

	public String getDrawer() {
		return this.drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderType() {
		return this.orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public int getPayType() {
		return this.payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getSettlementPersion() {
		return this.settlementPersion;
	}

	public void setSettlementPersion(String settlementPersion) {
		this.settlementPersion = settlementPersion;
	}

	public Date getSettlementTime() {
		return this.settlementTime;
	}

	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}

	public String getShopNo() {
		return this.shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public int getStauts() {
		return this.stauts;
	}

	public void setStauts(int stauts) {
		this.stauts = stauts;
	}

	public String getSupplierNo() {
		return this.supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public Set<OrderDetailed> getOrderDetailed() {
		return orderDetailed;
	}

	public void setOrderDetailed(Set<OrderDetailed> orderDetailed) {
		this.orderDetailed = orderDetailed;
	}

}