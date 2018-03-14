package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysFunctionDao;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.service.SysFunctionService;
import com.hy.sys.utils.PageInfo;

@Service("sysFunService")
public class SysFunctionServiceImpl  extends BasicServiceImpl<SysFunction> implements SysFunctionService {

	@Autowired
	private SysFunctionDao  sysFunctionDao;
	
	@Override
	public PageInfo<SysFunction> getPageList(Map<String, Object> params, SysFunction entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回菜单对应的功能
	 */
	@Override
	public ArrayList<SysFunction> getFunListByMenuid(String menuId) {
		ArrayList<SysFunction> list=sysFunctionDao.getMenuFunByMenuid(menuId);
		return list;
	}

	@Override
	protected BasicDao<SysFunction> getBasicDao() {
		// TODO Auto-generated method stub
		return sysFunctionDao;
	}

	
	 
}
