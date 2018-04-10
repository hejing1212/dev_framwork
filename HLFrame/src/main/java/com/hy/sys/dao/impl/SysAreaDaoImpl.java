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
	
	/**
	 * 查询带分页的列表
	 */
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
		
		if(StringTools.mapGetKeyIsEmpty(params, "parent_id")) {
			sql.append(" AND ( parent_id = ? )");
			values.add(params.get("parent_id"));
		}

		sql.append(" ORDER BY sort asc");
		return (PageInfo<SysArea>) this.findPageInfoByQueryJdbc(pageNo, pageSize, sql.toString(),
				values.toArray(), SysArea.class);
	}
	
	
	/**
	 * 查询列表
	 */
	@Override
	public List<SysArea> getAreaList(Map<String, Object> params, SysArea entity) {
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
		
		if(StringTools.mapGetKeyIsEmpty(params, "parent_id")) {
			sql.append(" AND ( parent_id = ? )");
			values.add(params.get("parent_id"));
		}

		sql.append(" ORDER BY sort asc");
		return (List<SysArea>) this.findListJdbc(sql.toString(), values.toArray(), SysArea.class);
	}
	
	/**
	 * 根据父ID进行递归查询所有子节点
	 */
	@Override
	public List<SysArea> getRecursionAreaListByParentId(String pareatId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" select id,parent_id,name,short_name,longitude,latitude,level,region_name_en,region_shoriname_en,sort from (  ");
		sql.append(" select t1.id,t1.parent_id,t1.name,t1.short_name,t1.longitude,t1.latitude,t1.level,t1.region_name_en,t1.region_shoriname_en,sort,  ");
		sql.append(" if(find_in_set(parent_id, @pids) > 0, @pids := concat(@pids, ',', id), 0) as ischild  ");
		sql.append(" from (  ");
		sql.append(" select id,parent_id,name,short_name,longitude,latitude,level,region_name_en,region_shoriname_en,sort from sys_area t  "); 
		sql.append(" ) t1,  ");
		sql.append(" (select @pids :=?) t2  ");
		sql.append(" ) t3 where ischild != 0 ");
		
		values.add(pareatId);

		sql.append(" ORDER BY sort asc");
		return (List<SysArea>) this.findListJdbc(sql.toString(), values.toArray(), SysArea.class);
	}
	
	/**
	 * 查询顶级地区列表
	 * @param params
	 * @return
	 */
	@Override
	public List<SysArea> getAreaListByParentId(String pareatId) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_area   ");
		sql.append(" WHERE 1=1 ");
		 		 
		sql.append(" AND ( parent_id = ? )");
		values.add(pareatId);
		 
		sql.append(" ORDER BY sort asc");
		return (List<SysArea>) this.findListJdbc(sql.toString(), values.toArray(), SysArea.class);
	}

}
