package com.hy.sys.service;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

public interface SysRoleService extends BasicService<SysRole>{

	public PageInfo<SysRole> getList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize);
	
	public List<SysRole> findListByUserId(String userid);

	/**
	 * @param name
	 * @return
	 */
	public SysRole getRoleName(String name);
   /**
    * 查询用户对应的角色列表
    * @param userId
    * @param pageNo
    * @param pageSize
    * @return
    */
	public PageInfo<SysRole> getPageListByUser(String userId, int pageNo, int pageSize);
	
	/**
	 * 删除用户的角色
	 * @param userId
	 * @param roleIds
	 */
	public void deleteUserRole(String userId, String[] roleIds);
}
