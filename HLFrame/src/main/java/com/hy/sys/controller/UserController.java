package com.hy.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.entity.SysUserEntity;
import com.hy.sys.service.SysUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/adduser")
	public ModelAndView addUser() {
		ModelAndView view = new ModelAndView();
		SysUserEntity entity = new SysUserEntity();

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		/*entity.setName("hejing");
		entity.setLoginName("admin");
		entity.setPassword("123456");
		entity.setLoginDate(now);
		entity.setEmail("3041241452@qq.com");
		entity.setCreateDate(now);
		entity.setMobile("13595028895");
		entity.setCreateBy("李四");
		entity.setNo("100012121");
		entity.setRemarks("通过上面的配置，现在启动服务来试试看，试着修改jsp或java文件，来验证一下效果");

		*/

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
}
