package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.DbDictionaryDao;
import com.hy.sys.entity.DbDictionary;
import com.hy.sys.utils.StringTools;
@Repository("dbDictionaryDao")
public class DbDictionaryDaoImpl extends BasicDaoImpl<DbDictionary> implements DbDictionaryDao {

	@Override
	public Class<DbDictionary> getEntityClass() {
		return DbDictionary.class;
	}

	/**
	 * 返回所有字段信息
	 */
	@Override
	public List<DbDictionary> queryDbDictionary(String tableSchema) {

		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		if (StringTools.isNotBlank(tableSchema)) {
			sql.append(" SELECT ");
			sql.append(" a.TABLE_SCHEMA as tableSchema, ");
			sql.append(" a.TABLE_NAME as tableName, ");
			sql.append(" a.COLUMN_NAME as columnName, ");
			sql.append(" a.ORDINAL_POSITION as ordinalPosition, ");
			sql.append(" a.COLUMN_DEFAULT as columnDefault, ");
			sql.append(" a.IS_NULLABLE as isNullable, ");
			sql.append(" a.COLUMN_TYPE as columnType, ");
			sql.append(" a.COLUMN_COMMENT as columnComment, ");
			sql.append(" a.COLUMN_KEY as columnKey, ");
			sql.append(" a.EXTRA as extra, ");
			sql.append(" b.TABLE_COMMENT as tableComment ");
			sql.append(" from information_schema.COLUMNS a ");
			sql.append(" LEFT JOIN information_schema.TABLES b ON a.TABLE_NAME=b.TABLE_NAME ");
			sql.append(" where a.TABLE_SCHEMA= ? ");
			sql.append(" ORDER BY b.TABLE_NAME ");
			values.add(tableSchema);
			
			return (List<DbDictionary>) this.findListJdbc(sql.toString(), values.toArray(), DbDictionary.class);
		} else {
			return null;
		}

	}

}
