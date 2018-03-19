/**
 * 
 */
package com.hy.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysUser;
import com.hy.sys.entity.TreeNode;
import com.hy.sys.service.SysUserService;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.SysFunctions;

/**
 * @author hlin
 *
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends AbstractBasicController{

	@Autowired
	private SysUserService sysUserService;
	
	/* (non-Javadoc)
	 * @see com.hy.sys.core.AbstractBasicController#init(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		basePath="abc";
	}

	@RequestMapping("/main")
	public ModelAndView main(ModelMap mode, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		SysUser user=UserUtils.getUser();
		
		view.addObject("user", user);
		return view;
	}
	
	 
	@RequestMapping("/index")
	public ModelAndView index(ModelMap mode, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		 req.setAttribute("basePath",basePath);
		return view;
	}
	
	@RequestMapping("/getMenuList")
	public List<SysMenu> getMenuList(HttpServletResponse response,
			HttpServletRequest request) {
		List<SysMenu> list=UserUtils.getMenuList();
		ArrayList<TreeNode> retlist = new ArrayList<TreeNode>();
		ArrayList<TreeNode> arlist = CreateMenuFormat(list, SysFunctions.TopMenuNO(), retlist);
		String jsonStr = JSON.toJSONString(arlist);
		writeResult(jsonStr, response);
		return null;
	}
	
	/**
	 * 根据菜单的子父级关系生成上下级关系的数据
	 * 
	 * @param menu
	 * @return
	 */
	public ArrayList<TreeNode> CreateMenuFormat(List<SysMenu> list, String menuid, ArrayList<TreeNode> li) {

		for (int i = 0; i < list.size(); i++) {
			SysMenu menu = list.get(i);
			if (menu.getParent_id().equals(menuid)) {
				TreeNode tree = new TreeNode();
				tree.setId(menu.getMenuid());
				tree.setText(menu.getName());
				tree.setHref(menu.getUrl());
				tree.setIconCls(menu.getMenu_icon());
				tree.setMenuid(menu.getMenuid());
				//tree.setIsCurrent("true");
				ArrayList<TreeNode> retli = new ArrayList<TreeNode>();
				ArrayList<TreeNode> childenList = CreateMenuFormat(list, menu.getMenuid(), retli);
				if (childenList.size() > 0) {
					tree.setChildren(childenList);
				}
				li.add(tree);
			}
		}
		return li;
	}
	
}
