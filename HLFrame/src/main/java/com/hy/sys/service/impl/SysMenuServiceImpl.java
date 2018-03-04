package com.hy.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.sys.core.dao.BasicDao;
import com.hy.sys.core.service.impl.BasicServiceImpl;
import com.hy.sys.dao.SysMenuDao;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.service.SysMenuService;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.logs.DeleteLog;
import com.hy.sys.utils.logs.SaveLog;
import com.hy.sys.utils.logs.SysLogOperationType;
import com.hy.sys.utils.logs.UpdateLog;

@SaveLog(operationName = "添加用户信息", operationType = SysLogOperationType.Add)
@UpdateLog(operationName = "修改用户信息", operationType = SysLogOperationType.Update)
@DeleteLog(operationName = "删除用户信息", operationType = SysLogOperationType.Delete)
@Service("sysMenuService")
public class SysMenuServiceImpl extends BasicServiceImpl<SysMenu> implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;

	/*
	 * @see 查询菜单是否添加过，避免重复添加
	 */
	@Override
	public SysMenu findByName(String menuname) {
		StringBuffer sql = new StringBuffer();
		List<Object> values = new ArrayList<Object>();
		sql.append(" FROM SysMenu ");
		sql.append(" WHERE 1=1 ");

		if (menuname != "") {
			sql.append(" AND (name = ?)");
			values.add(menuname);
		}

		sql.append(" ORDER BY create_date DESC");
		List<SysMenu> list = getBasicDao().findByHql(sql.toString(), values.toArray());
		if (list.size() > 0) {
			return (SysMenu) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected BasicDao<SysMenu> getBasicDao() {
		// TODO Auto-generated method stub
		return sysMenuDao;
	}

	@Override
	public PageInfo<SysMenu> getPageList(Map<String, Object> params, SysMenu entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询所有菜单，遍历后返回
	 */
	@Override
	public ArrayList<SysMenu> getMenuTree() {
		List<SysMenu> list = getBasicDao().findAll(SysMenu.class);
		ArrayList<SysMenu> arraylist = new ArrayList<SysMenu>();
		ArrayList<SysMenu> retlist = new ArrayList<SysMenu>();
		if (list.size() > 0) {
			SysMenu menu = new SysMenu();
			menu.setMenuid("0");
			menu.setName("顶级菜单");
			ArrayList<SysMenu> arlist = CreateMenuTree(list, "0", retlist);
			if (arlist.size() > 0) {
				menu.setChildren(arlist);
			}
			arraylist.add(menu);
			return arraylist;
		} else {
			return null;
		}
	}

	/**
	 * 根据菜单的子父级关系生成上下级关系的数据
	 * 
	 * @param menu
	 * @return
	 */
	public ArrayList<SysMenu> CreateMenuTree(List<SysMenu> list, String menuid, ArrayList<SysMenu> li) {
 
		for (int i = 0; i < list.size(); i++) {
			SysMenu menu = list.get(i);
			if (menu.getParent_id().equals(menuid)) {
				ArrayList<SysMenu> retli=new ArrayList<SysMenu>();
				ArrayList<SysMenu> childenList = CreateMenuTree(list, menu.getMenuid(), retli);
				if (childenList.size() > 0) {
					menu.setChildren(childenList);
				}
				li.add(menu);
			}
		}
		
		return li;
	}

}
