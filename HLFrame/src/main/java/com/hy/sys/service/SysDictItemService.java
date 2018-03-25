package com.hy.sys.service;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysDataDictItem;

public interface SysDictItemService extends BasicService<SysDataDictItem>{

    /**
     * 查询是否存在同名的字典分项
     * @param itemName
     * @return
     */
	public SysDataDictItem getDictItemName(String itemName);

}
