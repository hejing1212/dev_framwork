package com.hy.sys.service.impl;

import java.util.List;
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
public class SysFunctionServiceImpl extends BasicServiceImpl<SysFunction> implements SysFunctionService {

	@Autowired
	private SysFunctionDao sysFunctionDao;

	@Override
	public PageInfo<SysFunction> getPageList(Map<String, Object> params, SysFunction entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 返回菜单对应的功能
	 */
	@Override
	public PageInfo<SysFunction> getFunListByMenuid(int pageNo, int pageSize,String menuId) {
		PageInfo<SysFunction> list = sysFunctionDao.getMenuFunByMenuid(pageNo,pageSize,menuId);
		return list;
	}

	@Override
	protected BasicDao<SysFunction> getBasicDao() {
		// TODO Auto-generated method stub
		return sysFunctionDao;
	}
	/**
	 * 删除菜单功能
	 * @param funids
	 */
	@Override
	public void deleteMenuFun(String[] funids) {
		sysFunctionDao.deleteMenuFun(funids);
	}
	
	/**
	 * 能记录数量获取菜单对应的功
	 * @param menuId
	 * @return
	 */
	@Override
	public long getMenuCountByMenuid(String menuId) {
		return sysFunctionDao.getMenuCountByMenuid(menuId);
	}
	
	/**
	 * 根据用户ID查询当前用户具体的操作功能列表
	 * @param userId
	 * @return
	 */
	@Override
	public List<SysFunction> findFunctionByUserId(String userId){
		return sysFunctionDao.findFunctionByUserId(userId);
	}
	
}
