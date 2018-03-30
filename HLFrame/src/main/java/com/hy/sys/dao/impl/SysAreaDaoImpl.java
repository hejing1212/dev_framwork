package com.hy.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysAreaDao;
import com.hy.sys.entity.SysArea;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Repository("sysAreaDao")
public class SysAreaDaoImpl extends BasicDaoImpl<SysArea> implements SysAreaDao{

	@Override
	public Class<SysArea> getEntityClass() {
		// TODO Auto-generated method stub
		return SysArea.class;
	}
	
	@Override
	public PageInfo<SysArea> getList(Map<String, Object> params, SysArea entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_area   ");
		sql.append(" WHERE 1=1 ");

		if (StringTools.mapGetKeyIsEmpty(params, "queryKey")) {
			sql.append(" AND ( name like ? OR short_name like ?)");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
			values.add("%" + key + "%");
		}

		sql.append(" ORDER BY sort DESC");
		return (PageInfo<SysArea>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SysRole.class);
	}
	

}