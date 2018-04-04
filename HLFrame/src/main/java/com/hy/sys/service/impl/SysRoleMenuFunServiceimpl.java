package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysRoleMenuFunDao;
import com.hy.sys.entity.SysRoleMenuFun;
import com.hy.sys.service.SysRoleMenuFunService;
import com.hy.sys.utils.PageInfo;

@Service("sysRoleMenuFunService")
public class SysRoleMenuFunServiceimpl extends BasicServiceImpl<SysRoleMenuFun> implements SysRoleMenuFunService {

	@Autowired
	private SysRoleMenuFunDao sysRoleMenuFunDao;

	@Override
	public PageInfo<SysRoleMenuFun> getPageList(Map<String, Object> params, SysRoleMenuFun entity, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BasicDao<SysRoleMenuFun> getBasicDao() {
		// TODO Auto-generated method stub
		return sysRoleMenuFunDao;
	}

	/**
	 * 返回角色拥有的菜单 功能权限
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public List<SysRoleMenuFun> getRoleMenuFun(String roleId) {
		List<SysRoleMenuFun> list = sysRoleMenuFunDao.getFunByRoleid(roleId);
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 保存用户菜单权限
	 * 
	 * @param roleId
	 * @param auths
	 * @return
	 */
	@Override
	public Map<String, Object> roleAuthSave(String roleId, String auths) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (roleId != "" && auths != "") {
				sysRoleMenuFunDao.deleteRoleAuthFun(roleId);
				String[] menuId_funId = auths.split(",");
                List<SysRoleMenuFun> list=new ArrayList<SysRoleMenuFun>();
				for (int i = 0; i < menuId_funId.length; i++) {
					String menuid = menuId_funId[i].split("-")[0];
					String funId = menuId_funId[i].split("-")[1];
					if (menuid != null && funId != null) {
						SysRoleMenuFun entity = new SysRoleMenuFun();
						entity.setRoleId(roleId);
						entity.setMenuId(menuid);
						entity.setFunId(funId);
						list.add(entity);
					}
				}
				if(list.size()>0) {
					sysRoleMenuFunDao.saveBatch(list);
				}
				map.put("code", 1);
				map.put("msg", "设置成功！");
			}else {
				map.put("code", 0);
				map.put("msg", "设置失败！");
			}
			
		} catch (DataAccessException e) {
			map.put("code", 0);
			map.put("msg", e.toString());
		}
		return map;
	}
	
	

}
