package com.hy.sys.service;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysDataDict;
import com.hy.sys.entity.SysRole;

public interface SysDictService extends BasicService<SysDataDict>{

	
	/**
	 * 查询是否存在同名的字典
	 * @param dictName
	 * @return
	 */
public SysDataDict getDictName(String dictName);

	
}
