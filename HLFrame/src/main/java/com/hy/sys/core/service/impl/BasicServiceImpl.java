package com.hy.sys.core.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.entity.AbstractBasicEntity;
import com.hy.sys.core.service.BasicService;
import com.hy.sys.utils.DataStateEnums;

 
/** 
 * 
 * @author He.Xu.Dong 
 * @Date  
 * @version 1.0 
 */
public abstract class BasicServiceImpl<T extends AbstractBasicEntity> implements BasicService<T> {

	protected abstract BasicDao<T> getBasicDao();
 
	@Override
	public void save(T entity) {
		entity=pushEntity(entity);
		getBasicDao().save(entity);
		
	}

	@Override
	public void delete(String id, boolean isdel) {
		T entity =getBasicDao().get(id);
		if(isdel){
			getBasicDao().delete(entity);
		}else{
			entity=pushEntity(entity);
			//entity.setDelFlag(DataStateEnums.DATA_DELETE.getValue());
			getBasicDao().save(entity);			
		}
	}

	@Override
	public void deleteBatch(String[] ids, boolean isdel) {
		for(String id:ids){
			delete(id,isdel);
		}
		
	}

	@Override
	public T get(String id) {		
		return getBasicDao().get(id);
	}
	
	@Override
	public List<T> findEffectiveALlList(){
		return getBasicDao().findAllEffective(getTClass());
	}
	
	@Override
	public List<T> findALlList(){
		return getBasicDao().findAll(getTClass());
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getTClass() {
		Class<T> tClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return tClass;
	}

	protected T pushEntity(T entity) {
		 return entity;
	}

}
