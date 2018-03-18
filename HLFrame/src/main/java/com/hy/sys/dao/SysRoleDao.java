package com.hy.sys.dao;

import java.util.List;
import java.util.Map;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

public interface SysRoleDao extends BasicDao<SysRole>{

	/**
	 * 删除用户对应的角色
	 * @param userId
	 * @param roleIds
	 */
	public void deleteUserRole(String userId, String[] roleIds);

	/**
	 * 根据用户ID查询用户对应角色列表
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<SysRole> getPageListByUser(String userId, int pageNo, int pageSize);

	/**
	 * 查询用户名是否存在
	 * @param rolename
	 * @return
	 */
	public SysRole getRoleName(String rolename);

	/**
	 * 查询角色列表，带分页面
	 * @param params
	 * @param entity
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<SysRole> getList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize);

	
	/**
	 * 根据用户了ID查询用户对应的角色列表
	 * @param userId
	 * @return
	 */
	public List<SysRole> getRoleListByUserid(String userId);

}
