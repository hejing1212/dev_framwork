/**
 * 
 */
package com.hy.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hy.sys.core.controller.AbstractBasicController;
import com.hy.sys.shiro.FormAuthenticationFilter;
import com.hy.sys.shiro.RepeatAuthenticationException;
import com.hy.sys.shiro.UserRealm.Principal;
import com.hy.sys.shiro.UserUtils;
import com.hy.sys.shiro.authen.RetryLimitHashedCredentialsMatcher;

/**
 * @author hlin
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends AbstractBasicController{

	@Autowired
	private RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;
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
	
	
    @RequestMapping(method=RequestMethod.POST,produces = "text/html; charset=utf-8")
	public ModelAndView login(HttpServletRequest request, HttpServletRequest response, Model model) throws Exception {
		System.out.println("开始登录===============ok==============a");
		Principal principal = UserUtils.getPrincipal(); // 如果已经登录，则跳转到管理首页
		if (principal != null && !principal.isMobileLogin()) {
			return new ModelAndView("redirect:/admin");
		}

		String useruame = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		
		String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		
		// 是否开启验证码
		if (RepeatAuthenticationException.class.getName().equals(exception)||retryLimitHashedCredentialsMatcher.isShowCaptcha(useruame)) { // 重复认证异常加入验证码。
			model.addAttribute("showCaptcha", "1");
		} else {
			model.addAttribute("showCaptcha", "0");
		}
		// 强制登陆跳转
		if (ExcessiveAttemptsException.class.getName().equals(exception)
				|| retryLimitHashedCredentialsMatcher.isForceLogin(useruame)) { // 重复认证异常加入验证码。
			// model.addAttribute("showCaptcha", "1");
		}

		return new ModelAndView("/login/login");
	}

}
