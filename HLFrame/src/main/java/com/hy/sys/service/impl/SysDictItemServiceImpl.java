package com.hy.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysDictItemDao;
import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.service.SysDictItemService;
import com.hy.sys.utils.PageInfo;

@Service("sysDictItemService")
public class SysDictItemServiceImpl extends BasicServiceImpl<SysDataDictItem> implements SysDictItemService{
	
	@Autowired
	private SysDictItemDao dataDictDaoItem;
	
	@Override
	public PageInfo<SysDataDictItem> getPageList(Map<String, Object> params, SysDataDictItem entity, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return dataDictDaoItem.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SysDataDictItem> getBasicDao() {
		// TODO Auto-generated method stub
		return dataDictDaoItem;
	}
	
	/**
	 * 查询是否存在同名的字典分项
	 * @param rolename
	 * @return
	 */
	@Override
	public SysDataDictItem getDictItemName(String itemName) {
		return  dataDictDaoItem.getDictItemName(itemName);
	}

}
