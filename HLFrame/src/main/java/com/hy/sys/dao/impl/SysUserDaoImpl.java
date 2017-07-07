package com.hy.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.BasicDaoImpl;
import com.hy.sys.dao.SysUserDao;
import com.hy.sys.entity.SysUserEntity;
/**
 * 用户管理
 * @author He.jing
 * @Date 2017年7月7日 下午2:57:07 
 * @version 1.0 
 */
@Repository("sysUserDao")
public class SysUserDaoImpl extends BasicDaoImpl<SysUserEntity> implements SysUserDao{


	@Override
	public Class<SysUserEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return SysUserEntity.class;
	}

}
