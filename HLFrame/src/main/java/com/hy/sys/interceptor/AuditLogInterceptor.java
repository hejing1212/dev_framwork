package com.hy.sys.interceptor;

 
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.hy.sys.entity.SysLog;
import com.hy.sys.entity.SysUser;
import com.hy.sys.shiro.UserUtils;

/** 

 * @author He.Xu.Dong 
 * @Date 2016年8月2日 下午8:48:54 
 * @version 1.0 
 */
@Component("entityInterceptor")
public class AuditLogInterceptor extends EmptyInterceptor {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AuditLogUtil auditLogUtil;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 存放插入操作
	 */
	private Set inserts = new HashSet();  
	/**
	 * 存放更新操作
	 */ 
    private Set updates = new HashSet();  
    /**
	 * 存放删除操作
	 */ 
    private Set deletes = new HashSet(); 

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,String[] propertyNames, Type[] types) {
		boolean is=HibernateUtil.isRecordLog(entity.getClass());
		if(is){
			SysLog logEntity = new SysLog();
			logEntity.setOpt_data_id(id.toString());
			logEntity.setOpt_table_name(HibernateUtil.getTableName(entity.getClass()));
			logEntity.setOpt_table_comment(HibernateUtil.getTableComment(entity.getClass()));
			logEntity.setOpt_type(1);
			SysUser user = UserUtils.getUser(); 
			if (user != null) {
				logEntity.setOpt_user_id(user.getUserid()); 
				logEntity.setOpt_user_name(user.getUsername());;
			}
			StringBuffer sb =  new StringBuffer("|");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for (int i = 0; i < state.length; i++) {
				if(state[i]!=null&&!"".equals(state[i])){
					if(types[i] instanceof TimestampType){
						String str=formatter.format(state[i]);
						sb.append(propertyNames[i] + "值为：" + str + "|");
					}else{ 
						sb.append(propertyNames[i] + "值为：" + state[i] + "|");
					}
				}
				
			}
			logEntity.setOpt_content("操作记录：" + sb.toString());
			inserts.add(logEntity);
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,String[] propertyNames, Type[] types) {		
		SysLog logEntity = new SysLog();
		logEntity.setOpt_data_id(id.toString());
		logEntity.setOpt_table_name(HibernateUtil.getTableName(entity.getClass()));
		logEntity.setOpt_table_comment(HibernateUtil.getTableComment(entity.getClass()));
		logEntity.setOpt_type(4);
		SysUser user = UserUtils.getUser(); 
		logEntity.setOpt_user_id(user.getUserid());
		logEntity.setOpt_user_name(user.getUsername());
		StringBuffer sb =  new StringBuffer("|");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < state.length; i++) {
			if(state[i]!=null&&!"".equals(state[i])){
				if(types[i] instanceof TimestampType){
					String str=formatter.format(state[i]);
					sb.append(propertyNames[i] + "值为：" + str + "|");
				}else{ 
					sb.append(propertyNames[i] + "值为：" + state[i] + "|");
				}
			}
		}
		logEntity.setOpt_content("表："+ HibernateUtil.getTableName(entity.getClass()) + ",表名："	+ HibernateUtil.getTableComment(entity.getClass())
				+ "操作记录：" + sb.toString() + "操作人："+ user.getUsername() + "");
		
		deletes.add(logEntity);
		super.onDelete(entity, id, state, propertyNames, types);
	}

	

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,	Object[] currentState, Object[] previousState,	String[] propertyNames, Type[] types) {
		try {
			SysLog  logEntity = new SysLog();
			logEntity.setOpt_data_id(id.toString());
			logEntity.setOpt_table_name(HibernateUtil.getTableName(entity.getClass()));
			logEntity.setOpt_table_comment(HibernateUtil.getTableComment(entity.getClass()));
			logEntity.setOpt_type(2);
			
			SysUser user = UserUtils.getUser(); 
			if(user != null){
				logEntity.setOpt_user_id(user.getUserid());
				logEntity.setOpt_user_name(user.getUsername());
				
				StringBuffer sb = new StringBuffer("|");
				if(previousState==null){
					String sql= "select * from "+HibernateUtil.getTableName(entity.getClass())+" where id=?";
					   Object dben= jdbcTemplate.queryForObject(sql,new Object[]{id},BeanPropertyRowMapper.newInstance(entity.getClass()));
					   previousState=getDbValue(dben,propertyNames);
				}
				if(previousState!=null){
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					for (int i = 0; i < propertyNames.length; i++) {
						boolean isadd = false;
						if (currentState[i] == null &&!"".equals(currentState[i])&& previousState[i] != null&&!"".equals(previousState[i])) {
							isadd = true;
						} else if (currentState[i] != null&&!"".equals(currentState[i]) && previousState[i] == null&&!"".equals(previousState[i])) {
							isadd = true;
						} else if (currentState[i] != null&&!"".equals(currentState[i]) &&previousState[i]!=null&&!"".equals(previousState[i]) &&!currentState[i].equals(previousState[i])) {
							isadd = true;
						}
						logEntity.setOpt_type(2);
						if(propertyNames[i].endsWith("dataState")&&(Integer)currentState[i]==-9){
							logEntity.setOpt_type(3);
						}
						if (isadd) {
							if(types[i] instanceof TimestampType){
								String str=formatter.format(previousState[i]);
								String str1=formatter.format(currentState[i]);
								if(!str.equals(str1)){
									sb.append(propertyNames[i] + "由：" + str+ "变为：" + str1 + "|");
								}							
							}else{ 
								sb.append(propertyNames[i] + "由：" + previousState[i]+ "变为：" + currentState[i] + "|");
							}
						}
						
					}
				}
				logEntity.setOpt_content("操作记录：" + sb.toString());
				updates.add(logEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return super.onFlushDirty(entity, id, currentState, previousState,propertyNames, types);
	}

	@Override
	public void postFlush(Iterator entities) {
		try {
			for (Iterator it = inserts.iterator(); it.hasNext();) {
				SysLog logEntity = (SysLog ) it.next();
				auditLogUtil.LogIt(logEntity);
				
			}
			for (Iterator it = updates.iterator(); it.hasNext();) {
				SysLog logEntity = (SysLog) it.next();
				auditLogUtil.LogIt(logEntity);
			}
			for (Iterator it = deletes.iterator(); it.hasNext();) {
				SysLog logEntity = (SysLog) it.next();
				auditLogUtil.LogIt(logEntity);
			}
		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}
		super.postFlush(entities);
	}
	
	@Override
	public String onPrepareStatement(String sql) {
		return super.onPrepareStatement(sql);
	}
	
	/**
	 * 根据对象数据获取对应的顺序列表
	 * @param dben
	 * @param propertyNames
	 * @return
	 */
	private Object[] getDbValue(Object dben, String[] propertyNames) {
		if (propertyNames != null && propertyNames.length > 0 && dben != null) {
			Object[] robj = new Object[propertyNames.length];
			for (int i = 0; i < propertyNames.length; i++) {
				try {
					String firstLetter = propertyNames[i].substring(0, 1).toUpperCase();
					String getter = "get" + firstLetter	+ propertyNames[i].substring(1);
					Method method = dben.getClass().getMethod(getter,new Class[] {});
					Object value = method.invoke(dben, new Object[] {});
					robj[i] = value;
				} catch (Exception e) {
					robj[i]="未知值，无法确定更改";
				}

			}
			return robj;
		}
		return null;
	}
	
	
	

}
