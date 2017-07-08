package com.hy.sys.core;

import java.util.List;
import java.util.Map;

import com.hy.sys.utils.PageInfo;



/** 
 * 业务逻辑层基类
 * @author He.Xu.Dong 
 * @Date 2015年12月12日 上午12:28:35 
 * @version 1.0 
 */
public  interface BasicService <T extends AbstractBasicEntity> {	
	
	/**
	 * 保存一个对象
	 * @param entity
	 */
	public  void save(T entity);
	/**
	 * 根据ID删除对象
	 * @param id
	 * @param isdel true为物理删除 false为状态删除
	 */
	public  void delete(String id,boolean isdel);
	/**
	 * 删除一批对象
	 * @param ids
	 * @param isdel
	 */
	public  void deleteBatch(String[] ids,boolean isdel);

	/**
	 * 根据ID获取对象
	 * @param id
	 * @return
	 */
	public  T get(String id);
	
	/**
	 * 分页列表
	 * @param params
	 * @param user
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<T> getPageList (Map<String, Object> params, T entity, int pageNo,	int pageSize);
	
	/**
	 * 获取表中有效的所有数据，用于数量较小的表
	 * @return
	 */
	public List<T> findEffectiveALlList();
	
	/**
	 * 获取表中所有数据，包括无效数据
	 * @return
	 */
	public List<T> findALlList();

	
	
	
	

	
}
