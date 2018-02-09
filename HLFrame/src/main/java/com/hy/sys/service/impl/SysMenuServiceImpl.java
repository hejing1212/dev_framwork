package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysMenuDao;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;
import com.hy.sys.service.SysMenuService;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.logs.DeleteLog;
import com.hy.sys.utils.logs.SaveLog;
import com.hy.sys.utils.logs.SysLogOperationType;
import com.hy.sys.utils.logs.UpdateLog;

@SaveLog(operationName = "添加用户信息", operationType = SysLogOperationType.Add)
@UpdateLog(operationName = "修改用户信息", operationType = SysLogOperationType.Update)
@DeleteLog(operationName = "删除用户信息", operationType = SysLogOperationType.Delete)
@Service("sysMenuService")
public class SysMenuServiceImpl extends BasicServiceImpl<SysMenu> implements SysMenuService{

	@Autowired
	private SysMenuDao sysMenuDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<SysMenu> getPageList(Map<String, Object> params, SysMenu entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_menu   ");
		sql.append(" WHERE 1=1 ");

		if (params.containsKey("queryKey")
				&& params.get("queryKey") != null
				&& !"".equals(params.get("queryKey"))) {
			sql.append(" AND ( name like ? OR type = ? OR url like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
			values.add("%" + key + "%");
			values.add("%" + key + "%");
		}
		
		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysMenu>) getBasicDao()
				.findPageInfoByQueryJdbc(pageNo, pageSize,
						sql.toString(), values.toArray(),
						SysUser.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<SysMenu> getList(Map<String, Object> params, SysMenu entity, int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" SELECT  * ");
		sql.append(" FROM sys_menu   ");
		sql.append(" WHERE 1=1 ");

		if (params.containsKey("queryKey")
				&& params.get("queryKey") != null
				&& !"".equals(params.get("queryKey"))) {
			sql.append(" AND ( name like ? OR type = ? OR url like ? )");
			String key = params.get("queryKey").toString().trim();
			values.add("%" + key + "%");
			values.add("%" + key + "%");
			values.add("%" + key + "%");
		}
		
		sql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysMenu>) getBasicDao()
				.findPageInfoByQueryJdbc(pageNo, pageSize,
						sql.toString(), values.toArray(),
						SysUser.class);
	}

	@Override
	protected BasicDao<SysMenu> getBasicDao() {
		// TODO Auto-generated method stub
		return sysMenuDao;
	}

	 
	 

}
