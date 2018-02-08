package com.hy.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.entity.SysRole;
import com.hy.sys.service.SysRoleService;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.logs.DeleteLog;
import com.hy.sys.utils.logs.SaveLog;
import com.hy.sys.utils.logs.SysLogOperationType;
import com.hy.sys.utils.logs.UpdateLog;

@SaveLog(operationName = "添加用户信息", operationType = SysLogOperationType.Add)
@UpdateLog(operationName = "修改用户信息", operationType = SysLogOperationType.Update)
@DeleteLog(operationName = "删除用户信息", operationType = SysLogOperationType.Delete)
@Service("sysRoleService")
public class SysRoleServiceImpl extends BasicServiceImpl<SysRole> implements SysRoleService{

	@Override
	public PageInfo<SysRole> getPageList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<SysRole> getList(Map<String, Object> params, SysRole entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BasicDao<SysRole> getBasicDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hy.sys.service.SysRoleService#findListByUserId(java.lang.String)
	 */
	@Override
	public List<SysRole> findListByUserId(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
