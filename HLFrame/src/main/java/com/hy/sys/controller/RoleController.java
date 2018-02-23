/**
 * 
 */
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

import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;
import com.hy.sys.service.SysRoleService;
import com.hy.sys.utils.ConvertJson;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;

/**
 * @author hlin
 *
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends AbstractBasicController{

	@Autowired
	private SysRoleService sysRoleService;
	/* (non-Javadoc)
	 * @see com.hy.sys.core.controller.AbstractBasicController#init(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping("/addrole")
	public ModelAndView addUser() {
		ModelAndView view = new ModelAndView();
		SysRole entity = new SysRole();

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return view;
	}

	/****
	 * 保存用户信息
	 */
	@ResponseBody
	@RequestMapping("/saverole")
	public Map<String, Object> saveRole(@ModelAttribute SysRole entity, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysRole role=sysRoleService.getRoleName(entity.getName());
		 
		if (role== null) {
			Date now = new Date();
			entity.setCreate_date(now);
			sysRoleService.save(entity);
			map.put("code", "1");
			map.put("msg", "添加成功！");
		} else {
			map.put("code", "0");
			map.put("msg", "角色名称已经存在，添加失败！");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/rolelist")
	public ModelAndView showList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	@ResponseBody
	@RequestMapping("/getRolelist")
	public PageInfo<SysRole> getList(@ModelAttribute SysRole entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();

		PageInfo<SysRole> pages = sysRoleService.getList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = ConvertJson.map2json(map);
		writeResult(jsonStr, response);
		
		return pages;
	}

	
}
