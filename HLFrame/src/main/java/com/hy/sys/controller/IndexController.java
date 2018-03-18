/**
 * 
 */
package com.hy.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.hy.sys.service.SysUserService;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.ConvertJson;

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
		String jsonStr = JSON.toJSONString(list);
		writeResult(jsonStr, response);
		return null;
	}
	
}
