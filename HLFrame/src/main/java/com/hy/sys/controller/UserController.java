package com.hy.sys.controller;

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

import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;
import com.hy.sys.service.SysRoleService;
import com.hy.sys.service.SysUserService;
import com.hy.sys.service.impl.PasswordServiceImpl;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.utils.ConvertJson;
import com.hy.sys.utils.IntegerTools;
import com.hy.sys.utils.PageInfo;
import com.hy.sys.utils.StringTools;

@Controller
@RequestMapping("/sys/user")
public class UserController extends AbstractBasicController {
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private PasswordServiceImpl passwordService;

	@Autowired
	private SysRoleService sysRoleService;
	
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub

	}

	/**
	 * 添加界面显示
	 * 
	 * @return
	 */
	@RequestMapping("/adduser")
	public ModelAndView addUser() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/****
	 * 保存用户信息
	 */
	@ResponseBody
	@RequestMapping("/saveuser")
	public Map<String, Object> saveUser(@ModelAttribute SysUser user, HttpServletResponse response,
			HttpServletRequest request) {
		Date now = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringTools.isBlank(user.getUserid())) { //如果未获取到用户ID则为空
			SysUser saveuser = sysUserService.findByUsername(user.getUsername());
			if (saveuser == null) {
				saveuser = sysUserService.findByPhone(user.getPhone());
				if (saveuser == null) {
					saveuser = sysUserService.findByEmail(user.getEmail());
				}
			}
			if (saveuser == null) {
				user.setCreate_by(UserUtils.getUser().getUserid());
				user.setCreate_date(now);
				passwordService.encryptPassword(user);
				sysUserService.save(user);
				map.put("code", "1");
				map.put("msg", "添加成功！");
			} else {
				map.put("code", "0");
				map.put("msg", "用户已经存在，添加失败！");
			}
		}else {//如果D不为空则为修改用户信息 
			SysUser saveuser = sysUserService.findByUsername(user.getUsername(),user.getUserid());
			map.put("code", "2");
			if (saveuser == null) {
				saveuser = sysUserService.findByPhone(user.getPhone(),user.getUserid());
				map.put("code", "4");
				if (saveuser == null) {
					saveuser = sysUserService.findByEmail(user.getEmail(),user.getUserid());
					map.put("code", "6");
				}
			}
			if (saveuser == null) {
				SysUser dbuser=sysUserService.get(user.getUserid());
				dbuser.setUsername(user.getUsername());
				dbuser.setPhone(user.getPhone());
				dbuser.setEmail(user.getEmail());
				dbuser.setStatus(user.getStatus());
				dbuser.setRealname(user.getRealname());
				dbuser.setRemarks(user.getRemarks());
				dbuser.setUpdate_date(now);
				dbuser.setUpdate_by(UserUtils.getUser().getUserid());
				sysUserService.save(dbuser);
				
				map.put("code", "1");
				map.put("msg", "修改用户信息成功！");
			}else {
				map.put("msg", "修改用户信息失败,");
			}
		}
		return map;
	}

	/**
	 * 列表界面显示
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userlist")
	public ModelAndView showList() {
		ModelAndView view = new ModelAndView();
		return view;
	}

	/**
	 * 列表数据生成
	 * 
	 * @param entity
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getlist")
	public PageInfo<SysUser> getList(@ModelAttribute SysUser entity, HttpServletResponse response,
			HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));

		Map<String, Object> params = new HashMap<String, Object>();
		PageInfo<SysUser> pages = sysUserService.getList(params, entity, pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = ConvertJson.map2json(map);
		writeResult(jsonStr, response);

		return pages;
	}
	/**
	 * 显示编辑用户信息
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/edituser")
	public ModelAndView editUser(HttpServletResponse response, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		SysUser user = sysUserService.get(request.getParameter("userid"));
		view.addObject("user", user);
		return view;
	}
	
	/**
	 * 显示用户对应的角色列表
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserRoleList")
	public PageInfo<SysRole> getUserRoleList( HttpServletResponse response, HttpServletRequest request) {
		int pageNo = (request.getParameter("page") == null) ? PAGE_NO
				: IntegerTools.parseInt(request.getParameter("page"));
		int pageSize = (request.getParameter("rows") == null) ? PAGE_SIZE
				: IntegerTools.parseInt(request.getParameter("rows"));
        String userId=request.getParameter("userid");
        PageInfo<SysRole> pages = sysRoleService.getPageListByUser(userId,pageNo,pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pages.getTotalrecond());
		map.put("rows", pages.getResultlist());
		String jsonStr = ConvertJson.map2json(map);
		writeResult(jsonStr, response);

		return pages;
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
	@RequestMapping("/deleteUserRole")
	public Map<String,Object> deleteUserRole(@RequestParam(required = true) String userId,String roleIds,HttpServletResponse response, HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		if(StringTools.isNotBlank(userId)){
			sysRoleService.deleteUserRole(userId,roleIds.split(","));
			map.put("code", "1");
			map.put("msg", "删除成功");
		}else{
			map.put("code", "0");
			map.put("msg", "参数错误");
		}
		return map;
	}

	/**
	 * 重置密码
	 * @param userId
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resetPassWord")
	public Map<String,Object> resetPassWord(@RequestParam(required = true) String userId,HttpServletResponse response, HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		Date now = new Date();
		try {
		SysUser user=sysUserService.get(userId);
		String restPassword="@888888";
		user.setPassword(restPassword);
		user.setUpdate_date(now);
		user.setUpdate_by(UserUtils.getUser().getUserid());
		passwordService.encryptPassword(user);
		map.put("code", "1");
		map.put("msg", "重置密码成功");
		}catch (DataAccessException e) {
			map.put("code", "0");
			map.put("msg", "重置密码失败");
		}
		return map;
		
		
	}
}
