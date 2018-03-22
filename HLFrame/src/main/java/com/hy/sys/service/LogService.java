/**
 * 
 */
package com.hy.sys.service;

import java.util.Map;

import com.hy.sys.core.service.BasicService;
import com.hy.sys.entity.SysLog;
import com.hy.sys.entity.SysRole;
import com.hy.sys.utils.PageInfo;

/**
 * @author hlin
 *
 */
public interface LogService extends BasicService<SysLog>{

	public PageInfo<SysLog> getPageList(Map<String, Object> params, SysLog entity, int pageNo, int pageSize);
}
