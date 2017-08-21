package com.hy.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.core.AbstractBasicController;
import com.hy.sys.entity.SysUserEntity;
import com.hy.sys.service.SysUserService;
import com.hy.sys.utils.ConvertJson;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractBasicController{
	@Autowired
	private SysUserService sysUserService;

	
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping("/adduser")
	public ModelAndView addUser() {
		ModelAndView view = new ModelAndView();
		SysUserEntity entity = new SysUserEntity();

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return view;
	}
	
	/****
	 * 保存用户信息
	 */
	@ResponseBody
	@RequestMapping("/saveuser")
	public Map<String, Object>  saveUser(@ModelAttribute SysUserEntity entity,HttpServletResponse response,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();
		entity.setLoginDate(now);
		entity.setCreateDate(now);
		sysUserService.save(entity);
		map.put("code", "0");
		map.put("msg", "添加成功！");
		//return "user/userlist";
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("/userlist")
	public  ModelAndView showList() { 
		ModelAndView view = new ModelAndView();
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/getlist")
	public  PageInfo<SysUserEntity>  getList(@ModelAttribute SysUserEntity entity,HttpServletResponse response,HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO : IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE : IntegerTools.parseInt(request.getParameter("rows"));
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		PageInfo<SysUserEntity> pages = sysUserService.getList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = ConvertJson.map2json(map);
		writeResult(jsonStr, response);
		return pages;
	}

	
}
