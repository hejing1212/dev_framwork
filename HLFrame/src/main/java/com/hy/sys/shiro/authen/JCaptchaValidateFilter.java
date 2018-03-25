package com.hy.sys.shiro.authen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.hy.sys.jcaptcha.JCaptcha;
import com.hy.sys.shiro.FormAuthenticationFilter;
import com.hy.sys.utils.ServletUtils;
import com.hy.sys.utils.SpringContextHolder;
import com.hy.sys.utils.StringUtils;

/**
 * 验证码验证
 * 
 * @author 
 * @date  
 *
 */
public  class JCaptchaValidateFilter extends AccessControlFilter {

	private boolean jcaptchaEbabled = true;

	private String jcaptchaParam = "jcaptchaCode";

	private String jcapatchaErrorUrl;

	/**
	 * 是否开启jcaptcha
	 *
	 * @param jcaptchaEbabled
	 */
	public void setJcaptchaEbabled(boolean jcaptchaEbabled) {
		this.jcaptchaEbabled = jcaptchaEbabled;
	}

	public boolean isJcaptchaEbabled() {
		return jcaptchaEbabled;
	}

	public String getJcaptchaParam() {
		return jcaptchaParam;
	}

	/**
	 * 前台传入的验证码
	 *
	 * @param jcaptchaParam
	 */
	public void setJcaptchaParam(String jcaptchaParam) {
		this.jcaptchaParam = jcaptchaParam;
	}

	public void setJcapatchaErrorUrl(String jcapatchaErrorUrl) {
		this.jcapatchaErrorUrl = jcapatchaErrorUrl;
	}

	public String getJcapatchaErrorUrl() {
		return jcapatchaErrorUrl;
	}

	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		request.setAttribute("jcaptchaEbabled", jcaptchaEbabled);
		return super.onPreHandle(request, response, mappedValue);
	}

	/**
	 * 判断验证码是否正确
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String useruame = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = SpringContextHolder
				.getBean(RetryLimitHashedCredentialsMatcher.class);
		if (!retryLimitHashedCredentialsMatcher.isRepeatLogin(useruame)) {
			return true;
		}
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 验证码禁用 或不是表单提交 允许访问
		if (jcaptchaEbabled == false || !"post".equals(httpServletRequest.getMethod().toLowerCase())) {
			return true;
		}
		return  JCaptcha.validateResponse(httpServletRequest, httpServletRequest.getParameter(jcaptchaParam));
	}

	/**
	 * 验证失败处理方法
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		this.redirectToLogin(request, response);
		return false;
	}

	/**
	 * 跳到登录失败处理界面
	 */
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		request.setAttribute("error", "验证码错误！");
		//WebUtils.issueRedirect(request, response, req.getContextPath()+getJcapatchaErrorUrl());
		try {
			request.getRequestDispatcher(getJcapatchaErrorUrl()).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 是否需要验证
	 * 
	 * @return
	 */
	public Boolean isValidateCaptcha() {
		// 验证码禁用 或不是表单提交 允许访问
		if (jcaptchaEbabled == false || !"post".equals(ServletUtils.getRequest().getMethod().toLowerCase())) {
			return false;
		}
		return true;
	}

	/**
	 * 是否提交验证码
	 * 
	 * @return
	 */
	public Boolean isSubmitCapcha() {
		return !StringUtils.isEmpty(ServletUtils.getRequest().getParameter(jcaptchaParam));
	}

}
