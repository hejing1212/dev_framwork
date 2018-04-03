package com.hy.sys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hy.sys.core.entity.AbstractBasicEntity;


/**
 * The persistent class for the sys_area database table.
 * 
 */
@Entity
@Table(name = "sys_area")
@NamedQuery(name="SysArea.findAll", query="SELECT s FROM SysArea s")
public class SysArea extends AbstractBasicEntity{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private float latitude;

	private int level;

	private float longitude;

	private String name;

	@Column(name="parent_id")
	private String parentId;

	@Column(name="region_name_en")
	private String regionNameEn;

	@Column(name="region_shoriname_en")
	private String regionShorinameEn;

	@Column(name="short_name")
	private String shortName;

	private int sort;

	private int status;
    
	@Transient
	private List<SysArea> childenArea;
	
	
	@Transient
	private String state;
	
	
	

	public SysArea() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRegionNameEn() {
		return this.regionNameEn;
	}

	public void setRegionNameEn(String regionNameEn) {
		this.regionNameEn = regionNameEn;
	}

	public String getRegionShorinameEn() {
		return this.regionShorinameEn;
	}

	public void setRegionShorinameEn(String regionShorinameEn) {
		this.regionShorinameEn = regionShorinameEn;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	public List<SysArea> getChildenArea() {
		return childenArea;
	}

	public void setChildenArea(List<SysArea> childenArea) {
		this.childenArea = childenArea;
	}

	public String getState() {
		String state;
		if(this.level<4) {
			state="closed";
		}else {
			state="open";
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}