package com.hy.sys.core.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.hy.sys.core.dao.AbstractBasicDao;
import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.entity.AbstractBasicEntity;
import com.hy.sys.utils.DataStateEnums;
import com.hy.sys.utils.PageInfo;



/**
 * DAO公用方法
 * 
 * @author  
 * @Date 2015年11月21日 下午2:28:02
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class BasicDaoImpl<T extends AbstractBasicEntity> extends AbstractBasicDao implements BasicDao<T>  {

	private Session getCurrentSession() {
		return super.getSessionFactory().getCurrentSession();
	}

	public abstract Class<T> getEntityClass();

	@Override
	public void saveSql(String sql) {
		this.getJdbcTemplate().execute(sql);
	}

	@Override
	public void save(T entity) {
		try {
		this.getCurrentSession().saveOrUpdate(entity);
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void saveBatch(Collection<T> entities) {
		Session session = this.getCurrentSession();
		int i = 0;
		for (T tmp : entities) {
			session.save(tmp);
			if (i % 20 == 0) {
				session.flush();
				session.clear();
			}
			i++;
		}
	}

	@Override
	public void delete(T entity) {
		this.getCurrentSession().delete(entity);
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();
	}

	@Override
	public void deleteBatch(Collection<T> entities) {
		for (T tmp : entities) {
			this.getCurrentSession().delete(tmp);
		}

	}

	@Override
	public T get(String guid) {
		return (T) this.getCurrentSession().get(getEntityClass(), guid);
	}

	@Override
	public List<T> findByHql(String hql, Object[] params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	@Override
	public long getTotalCount(String hql, Object[] values) {
		try {
			Query query = this.getCurrentSession().createQuery(hql);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			Long i = (long) 0;
			if (query.list().size() > 0) {
				i = (Long) query.list().get(0);
			}
			return i;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<T> findAllEffective(Class entityClass) {
		Criteria criteria = this.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq("dataState", DataStateEnums.DATA_EFF.getValue()));
		// this.getCurrentSession().createCriteria(entityClass).list();
		return criteria.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<T> findAll(Class entityClass) {
		return this.getCurrentSession().createCriteria(entityClass).list();
	}

	@Override
	public void update(String hql, Object[] params) {
		Query query = this.getCurrentSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.executeUpdate();

	}

	@Override
	public List<T> findByHql(String hql, Object[] params, int start, int max) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(max);
		return query.list();
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria ca) {
		Assert.notNull(ca);
		Criteria criteria = ca.getExecutableCriteria(this.getCurrentSession());
		return criteria.list();
	}

	@Override
	public List<?> findPageByQuery(int pageNo, int pageSize, String hql, Object[] values) {
		List<Object> result = null;
		try {
			Query query = this.getCurrentSession().createQuery(hql);
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);
			result = query.list();
		} catch (RuntimeException re) {
			throw re;
		}
		return result;
	}

	@Override
	public PageInfo<T> findPageInfoByQuery(int pageNo, int pageSize, String hql, String hqlCount, Object[] values) {
		try {
			List<T> resutlList = (List<T>) this.findPageByQuery(pageNo, pageSize, hql, values);
			long rowCount = this.getTotalCount(hqlCount, values);
			PageInfo<T> pager = new PageInfo<T>(resutlList, rowCount, new Long(pageNo), new Long(pageSize));
			return pager;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<?> findPageListJdbc(String sql, int page, int pageSize, Object[] values, Class<?>... c) {
		Class<?> t = getEntityClass();
		if (c != null && c.length >= 1) {
			t = c[0];
		}
		String pageSql = "select _a.* from (" + sql + ") _a limit ?,?";

		return this.getJdbcTemplate().query(pageSql, fmObject(page, pageSize, values), BeanPropertyRowMapper.newInstance(t));
	}

	@Override
	public long getTotalCountJdbc(String sql, Object[] values) {
		int size = this.getJdbcTemplate().queryForObject("select count(*) from (" + sql + ") as _a", values, Integer.class);
		return size;
	}

	@Override
	public PageInfo<?> findPageInfoByQueryJdbc(int pageNo, int pageSize, String sql, Object[] values, Class<?>... clazz) {
		try {
			Class<?> t = getEntityClass();
			if (clazz != null && clazz.length >= 1) {
				t = clazz[0];
			}
			List<?> resutlList = this.findPageListJdbc(sql, pageNo, pageSize, values, t);
			long rowCount = this.getTotalCountJdbc(sql, values);
			PageInfo<?> pager = new PageInfo(resutlList, rowCount, new Long(pageNo), new Long(pageSize));
			return pager;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化参数
	 * 
	 * @param page
	 * @param pageSize
	 * @param values
	 * @return
	 */
	private static Object[] fmObject(int page, int pageSize, Object[] values) {
		if (values == null || values.length == 0)
			return new Object[] { (page - 1) * pageSize, pageSize };
		List<Object> rvalues = new ArrayList<Object>();
		for (int i = 0; i < values.length; i++) {
			rvalues.add(values[i]);
		}
		if (page <= 0) {
			page = 1;
		}
		rvalues.add((page - 1) * pageSize);
		rvalues.add(pageSize);
		return rvalues.toArray();
	}

	@Override
	public List<?> findListJdbc(String sql, Object[] values, Class<?>... clazz) {
		Class<?> t = getEntityClass();
		if (clazz != null && clazz.length >= 1) {
			t = clazz[0];
		}
		return this.getJdbcTemplate().query(sql, values, BeanPropertyRowMapper.newInstance(t));
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return super.getJdbcTemplate();
	}
	
	
	public List<T> findBySql(String sql,Class<?> clazz) {
		Query query = this.getCurrentSession().createSQLQuery(sql).addEntity(clazz);
		return query.list();
	}
	
	
	/**
	 * 根据sql查询出listMap数据
	 *@param sql
	 *@return List<Map<String,Object>> 
	 *@author 
	 *@Time  2017年4月5日 上午11:09:45
	 */
	public List<Map<String, Object>> findBysqlListMap(String sql){
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		  //设定结果结果集中的每个对象为Map类型   
		  query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		  return query.list();
	}

}
