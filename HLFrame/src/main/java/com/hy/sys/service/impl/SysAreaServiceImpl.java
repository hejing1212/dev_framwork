package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysAreaDao;
import com.hy.sys.entity.SysArea;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.TreeNode;
import com.hy.sys.service.SysAreaService;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.SysFunctions;
@Service("sysAreaService")
public class SysAreaServiceImpl extends BasicServiceImpl<SysArea> implements SysAreaService{

	
	@Autowired
	private SysAreaDao sysAreaDao;
	
	@Override
	public PageInfo<SysArea> getPageList(Map<String, Object> params, SysArea entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return sysAreaDao.getList(params, entity, pageNo, pageSize);
	}

	/**
	 * 查询带分页区域列表
	 */
	@Override
	protected BasicDao<SysArea> getBasicDao() {
		// TODO Auto-generated method stub
		return this.sysAreaDao;
	}
	/**
	 * 查询区域列表
	 * @param params
	 * @param entity
	 * @return
	 */
	@Override
	public List<SysArea> getAreaList(Map<String, Object> params, SysArea entity){
		return sysAreaDao.getAreaList(params, entity);
	}
	

	
}
