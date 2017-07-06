package com.hy.sys.core;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hy.sys.utils.PageInfo;


/**
 * 
 * @author He.Xu.Dong
 * @date 2015-11-21 下午02:54:40
 */
public interface BasicDao<T extends AbstractBasicEntity> {

	/**
	 * 自定义sql保存数据
	 * 
	 * @param sql
	 */
	public void saveSql(String sql);

	/**
	 * 提交数据
	 * 
	 * @param entity
	 */
	public void save(final T entity);

	/**
	 * 批量提交
	 * 
	 * @param entities
	 */
	public void saveBatch(final Collection<T> entities);

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	public void delete(final T entity);

	/**
	 * 提交数据
	 */
	public void flush();

	/**
	 * 批量删除
	 * 
	 * @param entities
	 */
	public void deleteBatch(final Collection<T> entities);

	/**
	 * 获取
	 * 
	 * @param guid
	 * @return
	 */
	public T get(final String guid);

	/**
	 * 用hql语名查询列表，不分页
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> findByHql(String hql, Object[] params);

	/**
	 * 根据hql查询总数
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public long getTotalCount(String hql, Object[] values);

	/**
	 * 获取全部数据
	 * 
	 * @param entityClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<T> findAll(Class entityClass);

	/**
	 * 获取有效的全部数据
	 * 
	 * @param entityClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<T> findAllEffective(Class entityClass);

	/**
	 * 根据Hql更新
	 * 
	 * @param hql
	 * @param params
	 */
	public void update(final String hql, final Object[] params);

	/**
	 * 查询列表并分页
	 * 
	 * @param hql
	 * @param params
	 * @param start
	 * @param max
	 * @return
	 */
	public List<T> findByHql(String hql, Object[] params, int start, int max);

	public List<T> findByCriteria(DetachedCriteria ca);

	public List<?> findPageByQuery(int pageNo, int pageSize, String hql, Object[] values);

	/**
	 * 分页数据获取
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param hql
	 * @param hqlCount
	 * @param values
	 * @return
	 */
	public PageInfo<T> findPageInfoByQuery(int pageNo, int pageSize, String hql, String hqlCount, Object[] values);

	/**
	 * jdbc分页获取数据
	 * 
	 * @param sql
	 * @param page
	 * @param pageSize
	 * @param clazz
	 * @param values
	 * @return
	 */
	public List<?> findPageListJdbc(String sql, int page, int pageSize, Object[] values, Class<?>... clazz);

	/**
	 * jdbc查询总数
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public long getTotalCountJdbc(String sql, Object[] values);

	/**
	 * 分页数据获取
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param hql
	 * @param hqlCount
	 * @param values
	 * @return
	 */
	public PageInfo<?> findPageInfoByQueryJdbc(int pageNo, int pageSize, String sql, Object[] values, Class<?>... clazz);

	/**
	 * jdbc不分页获取全部数据
	 * 
	 * @param sql
	 * @param page
	 * @param pageSize
	 * @param clazz
	 * @param values
	 * @return
	 */
	public List<?> findListJdbc(String sql, Object[] values, Class<?>... clazz);

	/**
	 * 获取JdbcTemplate对象
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();
	
	/**
	 * 根据sql查询出listMap数据
	 *@param sql
	 *@return List<Map<String,Object>> 
	 *@author lignlin
	 *@Time  2017年4月5日 上午11:09:45
	 */
	public List<Map<String, Object>> findBysqlListMap(String sql);
}
