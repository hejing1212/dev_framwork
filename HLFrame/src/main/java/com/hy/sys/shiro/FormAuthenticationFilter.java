package com.hy.sys.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;

import com.hy.sys.shiro.UserRealm.Principal;
import com.hy.sys.shiro.authen.RepeatAuthenticationException;
import com.hy.sys.utils.IpUtils;
import com.hy.sys.utils.StringUtils;
/**
 * 
 * All rights Reserved,  
 * @title:  FormAuthenticationFilter.java   
 * @package  shiro.web.filter.authc   
 * @description:   表单验证  
 * @author:    
 * @date:   2017年6月26日 下午5:56:03   
 * @version V1.0 
 * @copyright: 2017  All rights reserved. 
 *
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM ="captcha";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_ERROR_PARAM = "error";
	public static final String DEFAULT_MESSAGE_SUCCESS_PARAM = "success";
	public static final String DEFAULT_MESSAGE_NORMAL_PARAM = "normal";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageErrorParam = DEFAULT_MESSAGE_ERROR_PARAM;
	private String messageSuccessParam = DEFAULT_MESSAGE_SUCCESS_PARAM;
	private String messageNormallParam = DEFAULT_MESSAGE_NORMAL_PARAM;
 

	/**
	 * 验证码验证通过后运行该方法
	 */
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}		 
		boolean rememberMe = isRememberMe(request);   //是否希望记住身份
		String host = IpUtils.getIpAddr((HttpServletRequest) request);   //请求IP
		String captcha = getCaptcha(request);                            //验证码
		boolean mobile = isMobileLogin(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	public void setMobileLoginParam(String mobileLoginParam) {
		this.mobileLoginParam = mobileLoginParam;
	}

	public void setMessageErrorParam(String messageErrorParam) {
		this.messageErrorParam = messageErrorParam;
	}

	public void setMessageSuccessParam(String messageSuccessParam) {
		this.messageSuccessParam = messageSuccessParam;
	}

	public void setMessageNormallParam(String messageNormallParam) {
		this.messageNormallParam = messageNormallParam;
	}

	public String getMessageErrorParam() {
		return messageErrorParam;
	}

	public String getMessageSuccessParam() {
		return messageSuccessParam;
	}

	public String getMessageNormallParam() {
		return messageNormallParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}

	protected boolean isMobileLogin(ServletRequest request) {
		return WebUtils.isTrue(request, getMobileLoginParam());
	}

	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}

	/**
	 * 登录成功//
	 */
	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		Principal p = UserUtils.getPrincipal();
		UserUtils.clearCache();
		WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		super.onLoginFailure(token, e, request, response);
			String className = e.getClass().getName(), message = "";
			if (IncorrectCredentialsException.class.getName().equals(className)
					|| UnknownAccountException.class.getName().equals(className)) {
				message = "用户或密码错误, 请重试！";
			} else if (RepeatAuthenticationException.class.getName().equals(className)) {
				message = "请勿重复提交认证！";
			} else if (ExcessiveAttemptsException.class.getName().equals(className)) {
				message = "请勿重复提交认证,请半小时之后登录";
			} else if (StringUtils.isNoneBlank(e.getMessage())) {
				message = e.getMessage();
			} else if(LockedAccountException.class.getName().equals(className)) {
				message = "用户被锁定，无法登录！";
			} else {
				message = "系统出现点问题，请稍后再试！";
				e.printStackTrace(); // 输出到控制台
			}
			request.setAttribute(getFailureKeyAttribute(), className);
			request.setAttribute(getMessageErrorParam(), message);
			return true;
	}
	
	 
}