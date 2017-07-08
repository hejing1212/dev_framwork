package com.hy.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.entity.SysUserEntity;
import com.hy.sys.service.SysUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/adduser")
	public ModelAndView adduser() {
		ModelAndView view = new ModelAndView();
		SysUserEntity entity = new SysUserEntity();

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		entity.setName("hejing");
		entity.setLoginName("admin");
		entity.setPassword("123456");
		entity.setLoginDate(now);
		entity.setEmail("3041241452@qq.com");
		entity.setCreateDate(now);
		entity.setMobile("13595028895");
		entity.setCreateBy("李四");
		entity.setNo("100012121");
		entity.setRemarks("通过上面的配置，现在启动服务来试试看，试着修改jsp或java文件，来验证一下效果");

		sysUserService.save(entity);

		return view;
	}
}
