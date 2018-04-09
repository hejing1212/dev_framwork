package com.hy.cb.entity.api;

public class VersionsEntity {

	/// <summary>
    /// 版本号
    /// </summary>
    private String versioncode;
    
  /// <summary>
    /// 版本名称
    /// </summary>
    private String versionname;
    
    /// <summary>
    /// 下载链接地址
    /// </summary>
    private String versionurl;
    
  /// <summary>
    /// 更新说明
    /// </summary>
    private String description;
    
  /// <summary>
    /// 更新类型 0否  1是
    /// </summary>
    private String type;

	public String getVersioncode() {
		return versioncode;
	}

	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
	}

	public String getVersionname() {
		return versionname;
	}

	public void setVersionname(String versionname) {
		this.versionname = versionname;
	}

	public String getVersionurl() {
		return versionurl;
	}

	public void setVersionurl(String versionurl) {
		this.versionurl = versionurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
