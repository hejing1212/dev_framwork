package com.hy.sys.entity;

import com.hy.sys.core.entity.AbstractBasicEntity;

public class DbDictionary extends AbstractBasicEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tableSchema;       //数据库名
    private String tableName;         //表明
    private String ordinalPosition;   //序号
    private String columnName;        //字段名
    private String columnType;        //字段类型
    private String columnDefault;     //字段默认
    private String isNullable;        //可否空
    private String extra;             //其他
    private String columnKey;         //主键约束
    private String columnComment;     //字段注释
    private String tableComment;     //表注释
    
    
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOrdinalPosition() {
		return ordinalPosition;
	}
	public void setOrdinalPosition(String ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnDefault() {
		return columnDefault;
	}
	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
}
