package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysUserRoleDao;
import com.hy.sys.entity.SysUserRole;
import com.hy.sys.service.SysUserRoleService;
import com.hy.sys.utils.PageInfo;
@Service("sysRoleUserService")
public class SysUserRoleServiceImpl extends BasicServiceImpl<SysUserRole> implements SysUserRoleService{

	@Autowired
	private SysUserRoleDao sysRoleUserDao;
	@Override
	public PageInfo<SysUserRole> getPageList(Map<String, Object> params, SysUserRole entity, int pageNo, int pageSize) {
			return null; 
	}

	/**
	 * 根据用户ID查询用户对应角色列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<SysUserRole> getPageListByUser(String userId, int pageNo, int pageSize) {
		StringBuffer hql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		hql.append(" FROM SysUser ");
		hql.append(" WHERE 1=1 ");
		
		String hqlCount ="select count(*)  FROM SysUser WHERE 1=1";
				
		if (userId != "") {
			hql.append(" AND ( userid = ?)");
			values.add(userId);
		}
		hql.append(" ORDER BY create_date DESC");
		return (PageInfo<SysUserRole>)getBasicDao().findPageInfoByQuery(pageNo, pageSize, hql.toString(),hqlCount, new Object[] {userId});
	}
	@Override
	protected BasicDao<SysUserRole> getBasicDao() {
		// TODO Auto-generated method stub
		return this.sysRoleUserDao;
	}

	@Override
	public void deleteRoleByUserId(String userId) {
		sysRoleUserDao.deleteRoleByUserId(userId);
		
	}

}
