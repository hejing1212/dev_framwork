package com.hy.sys.service;

import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.DbDictionary;

public interface DbDictionaryService extends BasicService<DbDictionary>{

	/**
	 * 返回所有字段信息
	 * @param tableSchema
	 * @return
	 */
	public Map<String, Object> queryDbDictionary(String tableSchema);

}
