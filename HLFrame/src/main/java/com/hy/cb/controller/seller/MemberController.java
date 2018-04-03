package com.hy.cb.controller.seller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.core.controller.AbstractBasicController;

@Controller
@RequestMapping("/cb/member")
public class MemberController extends AbstractBasicController{

	

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 显示用户列表
	 * @return
	 */
	@RequestMapping("/memberList")
	public ModelAndView memberList() {
		ModelAndView view = new ModelAndView();
		return view;
	}
	
	/**
	 * 显示添加模板
	 * 
	 * @return
	 */
	@RequestMapping("/addMember")
	public ModelAndView addMember() {
		ModelAndView view = new ModelAndView();
		return view;
	}
}
