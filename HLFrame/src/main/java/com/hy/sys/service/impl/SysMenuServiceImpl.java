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
import com.hy.sys.dao.SysMenuDao;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysRoleMenu;
import com.hy.sys.entity.TreeNode;
import com.hy.sys.service.SysMenuService;
import com.hy.sys.service.SysRoleMenuService;
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

	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/*
	 * @see 查询菜单是否添加过，避免重复添加
	 */
	@Override
	public SysMenu findByName(String menuname) {
		return sysMenuDao.findByName(menuname);
	}

	@Override
	protected BasicDao<SysMenu> getBasicDao() {
		// TODO Auto-generated method stub
		return  sysMenuDao;
	}

	@Override
	public PageInfo<SysMenu> getPageList(Map<String, Object> params, SysMenu entity, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取当前用户授权菜单
	 * 
	 * @param userid
	 * @return
	 */
	public List<SysMenu> findMenuByUserId(String userId) {
		List<SysMenu> list = sysMenuDao.findMenuByUserId(userId);
		return list;
	}

	/**
	 * 查询所有菜单，遍历后返回
	 */
	@Override
	public ArrayList<TreeNode> getMenuTree() {
		List<SysMenu> list = getBasicDao().findAll(SysMenu.class);
		ArrayList<TreeNode> arraylist = new ArrayList<TreeNode>();
		ArrayList<TreeNode> retlist = new ArrayList<TreeNode>();
		if (list.size() > 0) {
			TreeNode tree = new TreeNode();
			tree.setId("0");
			tree.setText("顶级菜单");
			ArrayList<TreeNode> arlist = CreateMenuTree(list, "0", retlist);
			if (arlist.size() > 0) {
				tree.setChildren(arlist);
			}
			arraylist.add(tree);
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
	public ArrayList<TreeNode> CreateMenuTree(List<SysMenu> list, String menuid, ArrayList<TreeNode> li) {

		for (int i = 0; i < list.size(); i++) {
			SysMenu menu = list.get(i);
			if (menu.getParent_id().equals(menuid)) {
				TreeNode tree = new TreeNode();
				tree.setId(menu.getMenuid());
				tree.setText(menu.getName());
				ArrayList<TreeNode> retli = new ArrayList<TreeNode>();
				ArrayList<TreeNode> childenList = CreateMenuTree(list, menu.getMenuid(), retli);
				if (childenList.size() > 0) {
					tree.setChildren(childenList);
				}
				li.add(tree);
			}
		}
		return li;
	}

	/**
	 * 查询所有菜单，遍历后返回列表
	 */
	@Override
	public ArrayList<SysMenu> getMenuList() {
		List<SysMenu> list = getBasicDao().findAll(SysMenu.class);
		ArrayList<SysMenu> retlist = new ArrayList<SysMenu>();
		if (list.size() > 0) {
			ArrayList<SysMenu> arlist = CreateMenuList(list, "0", retlist);
			return arlist;
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
	@Override
	public ArrayList<SysMenu> CreateMenuList(List<SysMenu> list, String menuid, ArrayList<SysMenu> li) {

		for (int i = 0; i < list.size(); i++) {
			SysMenu menu = list.get(i);
			if (menu.getParent_id().equals(menuid)) {

				ArrayList<SysMenu> retli = new ArrayList<SysMenu>();
				ArrayList<SysMenu> childenList = CreateMenuList(list, menu.getMenuid(), retli);
				if (childenList.size() > 0) {
					menu.setChildren(childenList);
				}
				li.add(menu);
			}
		}
		return li;
	}

	/**
	 * 查询该菜单下的子工菜单数量
	 * 
	 * @param menuid
	 * @return
	 */
	@Override
	public long getChildCountByMenuid(String menuid) {
		return sysMenuDao.getChildCountByMenuid(menuid);
	}

	/**
	 * 删除菜单
	 * 
	 * @param funids
	 */
	@Override
	public void deleteMenu(String menuid) {
		sysMenuDao.deleteMenu(menuid);
	}

	/**
	 * 查询所有可用的菜单
	 * 
	 * @return
	 */
	@Override
	public List<SysMenu> getALLMenuList() {
		List<SysMenu> list = sysMenuDao.getAllList();
		for (int i = 0; i < list.size(); i++) {
			StringBuffer text = new StringBuffer();
			SysMenu menu = list.get(i);
			int v=0;			 
			if (menu.getFunction() != null) {
				for (SysFunction fun:menu.getFunction()) {
					
					if (v + 1 == menu.getFunction().size()) {
						text.append(fun.getMenuId()+"-"+fun.getFunid() + ":" + fun.getName());
					} else {
						text.append(fun.getMenuId()+"-"+fun.getFunid() + ":" + fun.getName() + ",");
					}
					v=v+1;
				}
			}
			menu.setStrFun(text.toString());
			list.set(i, menu);
		}
		
		ArrayList<SysMenu> retlist = new ArrayList<SysMenu>();
		ArrayList<SysMenu> arlist = CreateMenuList(list, "0", retlist);
		
		return arlist;
	}
	
	/**
	 * 保存用户菜单权限
	 * 
	 * @param roleId
	 * @param auths
	 * @return
	 */
	@Override
	public Map<String, Object> roleMenuAuthSave(String roleId, String auths) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (roleId != "" && auths != "") {
				sysRoleMenuService.deleteRoleMenu(roleId); //更新前，册掉之前的
				String[] menuId_funId = auths.split(",");
				for (int i = 0; i < menuId_funId.length; i++) {
					SysRoleMenu entity=new SysRoleMenu();
					String menuid = menuId_funId[i].split("-")[0];
					entity.setMenu_id(menuid);
					entity.setRole_id(roleId);
					sysRoleMenuService.save(entity);
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
