package com.hy.sys.service;

import java.util.Map;

import com.hy.sys.core.BasicService;
import com.hy.sys.entity.SysUserEntity;
import com.hy.sys.utils.PageInfo;

/**
 * �û�����
 * @author He.jing
 * @Date  
 * @version 1.0 
 */
public interface SysUserService  extends BasicService<SysUserEntity>{

	public PageInfo<SysUserEntity> getList(Map<String, Object> params, SysUserEntity entity, int pageNo, int pageSize);
}
