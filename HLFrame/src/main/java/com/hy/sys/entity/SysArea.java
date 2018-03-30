package com.hy.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hy.sys.core.entity.AbstractBasicEntity;


/**
 * The persistent class for the sys_area database table.
 * 
 */
@Entity
@Table(name="sys_area")
@NamedQuery(name="SysArea.findAll", query="SELECT s FROM SysArea s")
public class SysArea extends AbstractBasicEntity{
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float latitude;

	private int level;

	private float longitude;

	private String name;

	@Column(name="parent_id")
	private int parentId;

	@Column(name="region_name_en")
	private String regionNameEn;

	@Column(name="region_shoriname_en")
	private String regionShorinameEn;

	@Column(name="short_name")
	private String shortName;

	private int sort;

	private int status;

	public SysArea() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
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

}