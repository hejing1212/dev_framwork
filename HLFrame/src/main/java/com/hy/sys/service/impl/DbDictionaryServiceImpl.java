package com.hy.sys.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.DbDictionaryDao;
import com.hy.sys.entity.DbDictionary;
import com.hy.sys.service.DbDictionaryService;
import com.hy.sys.utils.PageInfo;
@Service("dbDictionaryService")
public class DbDictionaryServiceImpl extends BasicServiceImpl<DbDictionary> implements DbDictionaryService {

	@Autowired
	private DbDictionaryDao dbDictionaryDao;

	@Override
	public PageInfo<DbDictionary> getPageList(Map<String, Object> params, DbDictionary entity, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BasicDao<DbDictionary> getBasicDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> queryDbDictionary(String tableSchema) {
		List<DbDictionary> dbDictionaries = dbDictionaryDao.queryDbDictionary(tableSchema);
		System.err.println(dbDictionaries.toString());
		Map<String, Object> map = null;
		String schema = tableSchema; // 数据库名称
		if (dbDictionaries != null && dbDictionaries.size() > 0) {
			map = new HashMap<>();

			List<DbDictionary> columns = null;
			Map<String, List<DbDictionary>> tables = null;
			tables = new TreeMap<>();
			for (DbDictionary db : dbDictionaries) {
				columns = new LinkedList<>();

				String tableName = db.getTableName();
				if (tables.containsKey(tableName)) {
					tables.get(tableName).add(db);
				} else {
					columns.add(db);
					tables.put(tableName, columns);
				}
			}
			map.put("schema", schema);
			map.put("tables", tables);
			System.err.println(map.toString());
			return map;
		}

		return null;
	}

}
