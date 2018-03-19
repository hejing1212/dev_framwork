package com.hy.sys.shiro;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hy.sys.service.SysMenuService;

import com.hy.sys.utils.CacheUtils;
import com.hy.sys.utils.Exceptions;
import com.hy.sys.utils.IpUtils;
import com.hy.sys.utils.SpringContextHolder;
import com.hy.sys.utils.StringUtils;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.entity.SysLog;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;
import com.hy.sys.service.LogService;
import com.hy.sys.utils.SysFunctions;

public class LogUtils {

	public static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";
	private static LogService logService = SpringContextHolder.getBean(LogService.class);
	private static SysMenuService menuService = SpringContextHolder.getBean(SysMenuService.class);

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title) {
		saveLog(request, null, null, title, null);
	}

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title, String content) {
		saveLog(request, null, null, title, content);
	}

	public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title) {
		saveLog(request, handler, ex, title, null);
	}

	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title, String content) {
		SysUser user = UserUtils.getUser();
		if (user != null && user.getUserid() != null) {
			SysLog log = new SysLog();
			log.setTitle(title);
			log.setType(ex == null ? SysLog.TYPE_ACCESS : SysLog.TYPE_EXCEPTION);
			log.setRemoteAddr(IpUtils.getIpAddr(request));
			log.setUserAgent(request.getHeader("user-agent"));
			log.setRequestUri(request.getRequestURI());
			log.setParams(request.getParameterMap());
			log.setMethod(request.getMethod());
			log.setContent(content);
			// 异步保存日志
			new SaveLogThread(log, handler, ex).start();
		}
	}

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread {

		private SysLog log;
		private Object handler;
		private Exception ex;

		public SaveLogThread(SysLog log, Object handler, Exception ex) {
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
		}

		@Override
		public void run() {
			// 获取日志标题
			if (StringUtils.isBlank(log.getTitle())) {
				String permission = "";
				if (handler instanceof HandlerMethod) {
					Method m = ((HandlerMethod) handler).getMethod();
					RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
					permission = (rp != null ? StringUtils.join(rp.value(), ",") : "");
				}
				log.setTitle(getMenuNamePath(log.getRequestUri(), permission));
			}
			// 如果有异常，设置异常信息
			log.setException(Exceptions.getStackTraceAsString(ex));
			// 如果无标题并无异常日志，则不保存信息
			if (StringUtils.isEmpty(log.getTitle()) && StringUtils.isEmpty(log.getException())) {
				return;
			}
			// 保存日志信息
			logService.save(log);
		}
	}

	/**
	 * 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
	 */
	public static String getMenuNamePath(String requestUri, String permission) {
		String url = StringUtils.substringAfter(requestUri, SysFunctions.getAdminUrlPrefix() + "/");
		@SuppressWarnings("unchecked")
		Map<String, String> menuMap = (Map<String, String>) CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);
		if (menuMap == null) {
			menuMap = Maps.newHashMap();
			//List<SysMenu> menuList = menuService.list();
			List<SysMenu> menuList = menuService.findALlList();
			for (SysMenu menu : menuList) {
				// 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
				String namePath = "";
				if (menu.getParentIds() != null) {
					List<String> namePathList = Lists.newArrayList();
					for (String id : StringUtils.split(menu.getParentIds(), "/")) {
						/*
						 * if (Menu.getRootId().equals(id)){ continue; // 过滤跟节点
						 * }
						 */
						for (SysMenu m : menuList) {
							if (m.getMenuid().equals(id)) {
								namePathList.add(m.getName());
								break;
							}
						}
					}
					namePathList.add(menu.getName());
					namePath = StringUtils.join(namePathList, "-");
				}
				// 设置菜单名称路径
				if (StringUtils.isNotBlank(menu.getUrl())) {
					menuMap.put(menu.getUrl(), namePath);
				}else if(menu.getFunction()!=null){
					for (SysFunction fun:menu.getFunction()) {
						menuMap.put(fun.getPermission(), namePath);
					}
				}
				/*} else if (StringUtils.isNotBlank(menu.getPermission())) {
					for (String p : StringUtils.split(menu.getPermission())) {
						menuMap.put(p, namePath);
					}
				}*/

			}
			CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, menuMap);
		}
		String menuNamePath = menuMap.get(url);
		if (menuNamePath == null) {
			for (String p : StringUtils.split(permission)) {
				menuNamePath = menuMap.get(p);
				if (StringUtils.isNotBlank(menuNamePath)) {
					break;
				}
			}
			if (menuNamePath == null) {
				return "";
			}
		}
		return menuNamePath;
	}
}
