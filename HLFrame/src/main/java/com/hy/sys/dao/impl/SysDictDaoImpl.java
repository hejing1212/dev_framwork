package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysDictDao;
import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;


@Repository("dataDictDao")
public class SysDictDaoImpl  extends BasicDaoImpl<SysDataDict> implements  SysDictDao{

	@Override
	public Class<SysDataDict> getEntityClass() {
		// TODO Auto-generated method stub
		return SysDataDict.class;
	}

	
	@Override
	public PageInfo<SysDataDict> getPageList(Map<String, Object> params, SysDataDict entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_dict   ");
		sql.append(" WHERE 1=1 ");

		if (params.containsKey("dict_name") && params.get("dict_name") != null && !"".equals(params.get("dict_name"))) {
			sql.append(" AND ( dict_name like ?)");
			String key = params.get("dict_name").toString().trim();
			values.add("%" + key + "%");			
		}

		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysDataDict>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SysDataDict.class);
	}
	
	
	/**
	 * 查询是否存在同名的字典
	 * @param dictName 数据字典名称
	 * @return
	 */
	@Override
	public SysDataDict getDictName(String dictName) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysDataDict ");
		sql.append(" WHERE 1=1 ");

		if (dictName != "") {
			sql.append(" AND ( dict_name = ?)");
			values.add(dictName);
		}
		sql.append(" ORDER BY create_date DESC");
		List<SysDataDict> list = this.findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysDataDict) list.get(0);
		} else {
			return null;
		}
	}

}
