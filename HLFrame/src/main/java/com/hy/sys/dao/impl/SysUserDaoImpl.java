package com.hy.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.dao.impl.BasicDaoImpl;
import com.hy.sys.dao.SysUserDao;
import com.hy.sys.entity.SysUser;

/**
 * 用户接口实现类
 * 
 * @author He.jing
 * @Date 2017 
 * @version 1.0
 */
@Repository("sysUserDao")
public class SysUserDaoImpl extends BasicDaoImpl<SysUser> implements SysUserDao {

	@Override
	public Class<SysUser> getEntityClass() {
		// TODO Auto-generated method stub
		return SysUser.class;
	}

}
