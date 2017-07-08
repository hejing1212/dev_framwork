package com.hy.sys.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.BasicDao;
import com.hy.sys.core.BasicServiceImpl;
import com.hy.sys.dao.SysUserDao;
import com.hy.sys.entity.SysUserEntity;
import com.hy.sys.service.SysUserService;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.logs.DeleteLog;
import com.hy.sys.utils.logs.SaveLog;
import com.hy.sys.utils.logs.SysLogOperationType;
import com.hy.sys.utils.logs.UpdateLog;

/* ��֯��������
* 
* @author
* @Date 
* @version 1.0
*/
@SaveLog(operationName = "添加用户信息", operationType = SysLogOperationType.Add)
@UpdateLog(operationName = "修改用户信息", operationType = SysLogOperationType.Update)
@DeleteLog(operationName = "删除用户信息", operationType = SysLogOperationType.Delete)
@Service("sysUserService")
public class SysUserServiceImpl extends BasicServiceImpl<SysUserEntity> implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public PageInfo<SysUserEntity> getPageList(Map<String, Object> params, SysUserEntity entity, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BasicDao<SysUserEntity> getBasicDao() {
		// TODO Auto-generated method stub
		return sysUserDao;
	}

}
