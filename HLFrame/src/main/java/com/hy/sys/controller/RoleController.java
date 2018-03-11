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

import org.apache.shiro.dao.DataAccessException;
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
import com.hy.sys.entity.SysUserRole;
import com.hy.sys.service.SysRoleService;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.ConvertJson;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

/**
 * @author hlin
 *
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController extends AbstractBasicController {

	@Autowired
	private SysRoleService sysRoleService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hy.sys.core.controller.AbstractBasicController#init(org.springframework.
	 * ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}
/**
 * 显示添加模板
 * @return
 */
	@RequestMapping("/addRole")
	public ModelAndView addRole() {
		ModelAndView view = new ModelAndView();
		return view;
	}
/**
 * 显示编辑模板及数据
 * @param response
 * @param request
 * @return
 */
	@RequestMapping("/editRole")
	public ModelAndView editRole(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		SysRole role = sysRoleService.get(request.getParameter("roleid"));
		view.addObject("role", role);
		return view;
	}

	/**
	 * 显示角色选择列表
	 * @return
	 */
		@RequestMapping("/role")
		public ModelAndView Role(HttpServletResponse response, HttpServletRequest request) {
			ModelAndView view = new ModelAndView();
			String userId=request.getParameter("userid");
			view.addObject("userid", userId);
			return view;
		}
	
	/****
	 * 保存用户信息
	 */
	@ResponseBody
	@RequestMapping("/saveRole")
	public Map<String, Object> saveRole(@ModelAttribute SysRole role, HttpServletResponse response,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date now = new Date();
		if (StringTools.isBlank(role.getRoleid())) {
			SysRole dbrole = sysRoleService.getRoleName(role.getName());
			if (dbrole == null) {
				role.setCreate_date(now);
				role.setCreate_by(UserUtils.getUser().getUserid());
				sysRoleService.save(role);
				map.put("code", "1");
				map.put("msg", "添加成功！");
			} else {
				map.put("code", "0");
				map.put("msg", "角色名称已经存在，添加失败！");
			}
		}else {
			try {
			SysRole dbrole = sysRoleService.get(role.getRoleid());
			dbrole.setName(role.getName());
			dbrole.setCode(role.getCode());
			dbrole.setIs_sys(role.getIs_sys());
			dbrole.setUsable(role.getUsable());
			dbrole.setRemarks(role.getRemarks());
			dbrole.setUpdate_by(UserUtils.getUser().getUserid());
			dbrole.setUpdate_date(now);
			sysRoleService.save(dbrole);
			map.put("code", 1);
			map.put("msg", "修改角色信息成功！");
			}catch (DataAccessException e) {
				map.put("code", 1);
				map.put("msg", e.toString());
			}
		}
		return map;
	}

	/**
	 * 显示角色管理列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/roleList")
	public ModelAndView roleList() {
		ModelAndView view = new ModelAndView();
		return view;
	}
/**
 * 获取角色列表数据
 * @param entity
 * @param response
 * @param request
 * @return
 */
	@ResponseBody
	@RequestMapping("/getRoleList")
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
	@ResponseBody
	@RequestMapping("/saveUserRole")
	public Map<String,Object> saveUserRole(@ModelAttribute SysUserRole role, HttpServletResponse response,
			HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String roleids=role.getRole_id();
		map.put("code", 1);
		map.put("msg", "修改角色信息成功！");
		return map;
	}

}
