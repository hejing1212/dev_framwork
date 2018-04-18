package com.hy.sys.dao;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.DbDictionary;

public interface DbDictionaryDao extends BasicDao<DbDictionary>{

	/**
	 * 显示数据字典
	 * @param tableSchema
	 * @return
	 */
	public List<DbDictionary> queryDbDictionary(String tableSchema);

}
