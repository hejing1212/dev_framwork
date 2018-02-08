/**
 * 
 */
package com.hy.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.core.controller.AbstractBasicController;

/**
 * @author hlin
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends AbstractBasicController{

	/* 
	 * 
	 */
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	
	 
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest req) throws Exception {
		return "/login/login";
	}

}
