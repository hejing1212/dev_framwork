package com.hy.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysUser;
import com.hy.sys.service.SysMenuService;

/***
 * 菜单管理类
 * @author hejing
 *
 */
@Controller
@RequestMapping("/sys/memu")
public class MemuController extends AbstractBasicController{
SysMenuService sysMenuService;
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 打开添加菜单界面
	 * @return
	 */
	@RequestMapping("/addmemu")
	public ModelAndView addMemu() {
		ModelAndView view = new ModelAndView();
		SysMenu entity = new SysMenu();
		return view;
	}
	
	/****
	 * 保存菜单信息
	 */
	@ResponseBody
	@RequestMapping("/saveuser")
	public Map<String, Object> saveUser(@ModelAttribute SysMenu entity, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysMenu memu=sysMenuService.findByName(entity.getName());
		
		if (memu== null) {
			Date now = new Date();
			entity.setCreate_date(now);
			sysMenuService.save(entity);
			map.put("code", "1");
			map.put("msg", "添加成功！");
		} else {
			map.put("code", "0");
			map.put("msg", "用户已经存在，添加失败！");
		}
		return map;
	}
}
