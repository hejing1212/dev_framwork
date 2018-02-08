/**
 * 
 */
package com.hy.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.entity.SysUser;

/**
 * @author hlin
 *
 */
@Controller
@RequestMapping("/home")
public class IndexController extends AbstractBasicController{

	/* (non-Javadoc)
	 * @see com.hy.sys.core.AbstractBasicController#init(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		basePath="abc";
	}

	@RequestMapping("/index")
	public ModelAndView index(ModelMap mode, HttpServletRequest req) {
		ModelAndView view = new ModelAndView();
		 req.setAttribute("basePath",basePath);
		return view;
	}
	
}