package com.hy.sys.core.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.sys.utils.CustomDatePropEditor;
import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.logs.LogUtil;


/**
 * 控制器基类
 * 
 * @author HeXuDong
 *
 */
public abstract class AbstractBasicController {

	protected final int DEFUALT_PAGESIZE = 10;

	protected final int PAGE_NO = 1;
	protected final int PAGE_SIZE = 10;

	public static final String RET_CODE = "code";
	public static final String RET_MSG = "msg";

	public static final int CODE_SUCCESS = 1;
	public static final int CODE_FAILE = -1;
	public static final int CODE_ERROR = -2;

	protected static final String CHARACTER_ENCODING = "UTF-8";

	public static  String basePath=""; 
	@RequestMapping
	public void initialize(ModelMap mode, HttpServletRequest req) {
		String currentTheme = getCookie(req, "currentTheme");
		if (StringTools.isBlank(currentTheme)) {
			currentTheme = "cerulean";
		}
		req.setAttribute("currentTheme", currentTheme);
		this.init(mode, req);
		
		/*basePath=req.getScheme()+req.getServerName()+req.getServerPort()+req.getContextPath();
		req.setAttribute("basePath", basePath);*/
	}

	/**
	 * 页面初始化执行方法
	 * 
	 * @param mode
	 * @param req
	 */
	protected abstract void init(ModelMap mode, HttpServletRequest req);

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new CustomDatePropEditor());
	}

	/**
	 * 读取cookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		String data = "";
		try {
			Cookie cookie = findCookie(cookieName, request);
			if (cookie != null) {
				data = URLDecoder.decode(cookie.getValue(), "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return data;
	}

	// 读取cookie
	public static Cookie findCookie(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 【获得Response响应对象, 返回相关数据】
	 * 
	 * @return
	 */
	public void writeResult(String reuslt, HttpServletResponse response) {

		try {
			response.setCharacterEncoding(CHARACTER_ENCODING);
			response.setContentType("text/html;charset=" + CHARACTER_ENCODING);
			response.getOutputStream().write(reuslt.getBytes(CHARACTER_ENCODING));
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(this, e);
		} finally {
			try {
				if (response.getOutputStream() != null) {
					response.getOutputStream().close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.info(this, e);
			}
		}
	}

	public static Map<String, Object> initReturnMap(int code, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RET_CODE, code);
		map.put(RET_MSG, (msg == null) ? "" : msg);
		return map;
	}
}
