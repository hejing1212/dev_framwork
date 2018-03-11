package com.hy.sys.core.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


/** 

 * @author 
 * @Date 2017年11月26日 下午2:47:31 
 * @version 1.0 
 */
public class AbstractBasicDao {

	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * 用于操作jdbc
	 * 
	 * @return
	 */
	protected NamedParameterJdbcTemplate getNameJdbcTemplate() {
		return nameJdbcTemplate;
	}
	
	/**
	 * 用于操作jdbc
	 * 
	 * @return
	 */
	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
