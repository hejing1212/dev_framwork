package com.hy.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/adduser")
	public ModelAndView adduser() {
		ModelAndView view = new ModelAndView();
		return view;
	}
}
