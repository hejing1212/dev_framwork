package com.hy.sys.service;

import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysUser;
import com.hy.sys.utils.PageInfo;

/**
 * 用户service
 * @author He.jing
 * @Date  
 * @version 1.0 
 */
public interface SysUserService  extends BasicService<SysUser>{

	public PageInfo<SysUser> getList(Map<String, Object> params, SysUser entity, int pageNo, int pageSize);
	
	   public SysUser findByUsername(String username);
	   
	   public SysUser findByEmail(String username);
	   
	   public SysUser findByPhone(String username);
	   
}
