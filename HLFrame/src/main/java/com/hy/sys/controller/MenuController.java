package com.hy.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.TreeNode;
import com.hy.sys.service.SysFunctionService;
import com.hy.sys.service.SysMenuService;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.ConvertJson;
import com.hy.sys.utils.StringTools;

/***
 * 菜单管理类
 * 
 * @author hejing
 *
 */
@Controller
@RequestMapping("/sys/menu")
public class MenuController extends AbstractBasicController {
	@Autowired
	private SysMenuService sysMenuService;

	@Autowired
	private SysFunctionService sysFunService;

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/**
	 * 打开添加菜单界面
	 * 
	 * @return
	 */
	@RequestMapping("/addmenu")
	public ModelAndView addMenu() {
		ModelAndView view = new ModelAndView();
		SysMenu entity = new SysMenu();
		return view;
	}

	/****
	 * 保存菜单信息
	 */
	@ResponseBody
	@RequestMapping("/savemenu")
	public Map<String, Object> saveMenu(@ModelAttribute SysMenu entity, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysMenu memu = sysMenuService.findByName(entity.getName());

		if (memu == null) {
			Date now = new Date();
			entity.setCreate_date(now);
			entity.setCreate_by(UserUtils.getUser().getUserid());
			sysMenuService.save(entity);
			map.put("code", "1");
			map.put("msg", "资源添加成功！");
		} else {
			map.put("code", "0");
			map.put("msg", "资源名称已经存在，添加失败！");
		}
		return map;
	}

	/**
	 * 显示菜单列表界面
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menulist")
	public ModelAndView showList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 返回菜单选择树，提供前台页面下拉框显示
	 */
	@ResponseBody
	@RequestMapping("/menuTreeJson")
	public ArrayList<TreeNode> getMenuTreeJsion(@ModelAttribute SysMenu entity, HttpServletResponse response,
			HttpServletRequest request) {
		ArrayList<TreeNode> list = sysMenuService.getMenuTree();
		String jsonStr = JSON.toJSONString(list);
		writeResult(jsonStr, response);
		return list;
	}

	/**
	 * 返回菜单选择树，提供前台列表页面显示
	 */
	@ResponseBody
	@RequestMapping("/getMenuList")
	public ArrayList<SysMenu> getMenuList(@ModelAttribute SysMenu entity, HttpServletResponse response,
			HttpServletRequest request) {
		ArrayList<SysMenu> list = sysMenuService.getMenuList();
		String jsonStr = JSON.toJSONString(list);
		writeResult(jsonStr, response);
		return list;
	}

	/**
	 * 查询菜单对应的功能
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMenuFunList")
	public ArrayList<SysFunction> getMenuFunList(HttpServletResponse response, HttpServletRequest request) {
		String menuId = request.getParameter("menuId");
		ArrayList<SysFunction> list = sysFunService.getFunListByMenuid(menuId);
		String jsonStr = JSON.toJSONString(list);
		writeResult(jsonStr, response);

		return list;
	}

	/**
	 * 显示单个修改的功能信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/showFunction")
	public ModelAndView showFunction(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		String menuid = request.getParameter("menuid");
		if (StringTools.isBlank(menuid)) { 
			String funid=request.getParameter("funid");
			if (StringTools.isNotBlank(funid)) {			
				SysFunction fun = sysFunService.get(funid);
				view.addObject("sysFunction", fun);
				view.setViewName("sys/menu/functionEdit");
			}else {
				//提示页面显示
			}
		} else {
			view.addObject("menuid", menuid);
			view.setViewName("sys/menu/functionAdd");
		}
		return view;
	}

	/**
	 * 保存编辑的功能内容
	 * 
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editFunction")
	public Map<String, Object> editFunction(@ModelAttribute SysFunction entity, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();
		if (entity.getFunid() == null) { // 添加
			entity.setCreate_date(now);
			entity.setCreate_by(UserUtils.getUser().getUserid());
			map.put("code", "1");
			map.put("msg", "添加成功！");
		} else {// 修改
			SysFunction fun=sysFunService.get(entity.getFunid());
			fun.setUpdate_by(UserUtils.getUser().getUserid());
			fun.setCreate_date(now);
			fun.setName(entity.getName());
			fun.setFun_action(entity.getFun_action());
			fun.setMenu_icon(entity.getMenu_icon());
			fun.setPermission(entity.getPermission());
			fun.setSort(entity.getSort());
			fun.setRemarks(entity.getRemarks());
			
			map.put("code", "1");
			map.put("msg", "修改成功！");
		}

		try {
			sysFunService.save(entity); // 数据操作
		} catch (DataAccessException e) {
			map.put("code", "0");
			map.put("msg", e.toString());
		}
		return map;
	}
	
	/***
	 * 批量删除用户权限
	 * @param userId
	 * @param roleIds
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteMenuFun")
	public Map<String,Object> deleteMenuFun(@RequestParam(required = true) String funid,HttpServletResponse response, HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		if(StringTools.isNotBlank(funid)){
			sysFunService.deleteMenuFun(funid.split(","));
			map.put("code", "1");
			map.put("msg", "删除成功");
		}else{
			map.put("code", "0");
			map.put("msg", "参数错误");
		}
		return map;
	}

}