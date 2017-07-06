package com.hy.sys.core;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import com.hy.sys.utils.DataStateEnums;
import com.hy.sys.utils.StringTools;

 
/** 
 * 
 * @author He.Xu.Dong 
 * @Date 2015年12月12日 上午12:56:17 
 * @version 1.0 
 */
public abstract class BasicServiceImpl<T extends BaseSpuerEntity> implements BasicService<T> {

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
			entity.setDataState(DataStateEnums.DATA_DELETE.getValue());
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
		 return null;
	}

}
