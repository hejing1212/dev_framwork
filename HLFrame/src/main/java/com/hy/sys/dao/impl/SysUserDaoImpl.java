package com.hy.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.hy.sys.core.BasicDaoImpl;
import com.hy.sys.dao.SysUserDao;
import com.hy.sys.entity.SysUserEntity;

/**
 * �û�����
 * 
 * @author He.jing
 * @Date 2017 
 * @version 1.0
 */
@Repository("sysUserDao")
public class SysUserDaoImpl extends BasicDaoImpl<SysUserEntity> implements SysUserDao {

	@Override
	public Class<SysUserEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return SysUserEntity.class;
	}

}
