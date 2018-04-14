package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysDictItemDao;
import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

@Repository("dataDictDaoItem")
public class SysDictItemDaoImpl extends BasicDaoImpl<SysDataDictItem> implements SysDictItemDao {

	@Override
	public Class<SysDataDictItem> getEntityClass() {
		// TODO Auto-generated method stub
		return SysDataDictItem.class;
	}

	/**
	 * 获取键值项
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<SysDataDictItem> getPageList(Map<String, Object> params, SysDataDictItem entity, int pageNo,
			int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_dict_item   ");
		sql.append(" WHERE 1=1 ");

		if (params.containsKey("item_name") && params.get("item_name") != null && !"".equals(params.get("item_name"))) {
			sql.append(" AND ( item_name like ?)");
			String key = params.get("item_name").toString().trim();
			values.add("%" + key + "%");			
		}
		if (params.containsKey("dict_code") && params.get("dict_code") != null && !"".equals(params.get("dict_code"))) {
			sql.append(" AND ( dict_code = ?)");
			String key = params.get("dict_code").toString().trim();
			values.add(key);			
		}

		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysDataDictItem>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SysDataDictItem.class);
	}
	
	/**
	 * 查询是否存在同名的字典项
	 * @param dictName 数据字典名称
	 * @return
	 */
	@Override
	public SysDataDictItem getDictItemName(String itemName) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysDataDictItem ");
		sql.append(" WHERE 1=1 ");

		if (itemName != "") {
			sql.append(" AND ( itemName = ?)");
			values.add(itemName);
		}
		sql.append(" ORDER BY create_date DESC");
		List<SysDataDictItem> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysDataDictItem) list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 查询当前字典是否存在键值
	 * @param dictName 数据字典名称
	 * @return
	 */
	@Override
	public SysDataDictItem getDictItemByDictId(String dict_code) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysDataDictItem ");
		sql.append(" WHERE 1=1 ");

		if (dict_code != "") {
			sql.append(" AND ( dictCode = ?)");
			values.add(dict_code);
		}
		sql.append(" ORDER BY create_date DESC");
		List<SysDataDictItem> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysDataDictItem) list.get(0);
		} else {
			return null;
		}
	}
	
	/****
	 * 根据字典编码查询对应的建值
	 * @param dictCode
	 * @return
	 */
	@Override
	public List<SysDataDictItem> getDictItemOption(String dictCode){
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysDataDictItem a,SysDataDict b ");
		sql.append(" WHERE a.dictCode=b.id ");

		if (dictCode != "") {
			sql.append(" AND ( b.dictCode = ?)");
			values.add(dictCode);
		}
		sql.append(" ORDER BY sort DESC");
		List<SysDataDictItem> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
}
