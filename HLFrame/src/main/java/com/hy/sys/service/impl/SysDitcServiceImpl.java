package com.hy.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysDictDao;
import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysRole;
import com.hy.sys.service.SysDictService;
import com.hy.sys.utils.PageInfo;


@Service("dataDictService")
public class SysDitcServiceImpl extends BasicServiceImpl<SysDataDict> implements SysDictService{

	@Autowired
	private  SysDictDao dataDictDao;
	
	/**
	 * 获取数据字典列表
	 */
	@Override
	public PageInfo<SysDataDict> getPageList(Map<String, Object> params, SysDataDict entity, int pageNo, int pageSize) {
		return dataDictDao.getPageList(params, entity, pageNo, pageSize);
	}

	@Override
	protected BasicDao<SysDataDict> getBasicDao() {
		// TODO Auto-generated method stub
		return this.dataDictDao;
	}
	
	/**
	 * 查询是否存在同名的字典
	 * @param rolename
	 * @return
	 */
	@Override
	public SysDataDict getDictName(String dictName) {
		return  dataDictDao.getDictName(dictName);
	}

}
