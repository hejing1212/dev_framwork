package com.hy.sys.shiro;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import com.hy.sys.core.utils.HlFramePropertiesUtil;
import com.hy.sys.entity.SysFunction;
import com.hy.sys.entity.SysMenu;
import com.hy.sys.entity.SysRole;
import com.hy.sys.entity.SysUser;
import com.hy.sys.service.SysFunctionService;
import com.hy.sys.service.SysMenuService;
import com.hy.sys.service.SysRoleService;
import com.hy.sys.service.SysUserService;
import com.hy.sys.shiro.UserRealm.Principal;
import com.hy.sys.utils.CacheUtils;
import com.hy.sys.utils.ServletUtils;
import com.hy.sys.utils.SpringContextHolder;
import com.hy.sys.utils.StringUtils;

/**
 * 
 * All rights Reserved, 
 * 
 * @title: UserUtils.java
 * @package  
 * @description: 用户工具类
 * @author: 
 * @date: 2017年6月26日 下午6:00:39
 * @version V1.0
 * @copyright: 2018  All rights reserved.
 *
 */
@SuppressWarnings("unchecked")
public class UserUtils {

	private static SysUserService userService = SpringContextHolder.getBean(SysUserService.class);
	private static SysRoleService roleService = SpringContextHolder.getBean(SysRoleService.class);
	private static SysMenuService menuService = SpringContextHolder.getBean(SysMenuService.class);
	private static SysFunctionService sysFunService = SpringContextHolder.getBean(SysFunctionService.class);
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_USER_NAME_ = "username_";
	public static final String MENU_CACHE_URL_ = "menu_url_";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";

	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static SysUser get(String id) {
		SysUser user = (SysUser) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			user = userService.get(id);
			if (user == null) {
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getUserid(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_USER_NAME_ + user.getUsername(), user);
		}
		return user;
	}

	/**
	 * 根据用户名获取用户
	 * 
	 * @param username
	 * @return
	 */
	public static SysUser getByUserName(String username) {
		SysUser user = (SysUser) CacheUtils.get(USER_CACHE, USER_CACHE_USER_NAME_ + username);
		if (user == null) {
			user = userService.findByUsername(username);
			if (user == null) {
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getUserid(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_USER_NAME_ + user.getUsername(), user);
		}
		return user;
	}

	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_ROLE_LIST);
		removeCache(CACHE_MENU_LIST);
		UserUtils.clearCache(getUser());
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param user
	 */
	public static void clearCache(SysUser user) {
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getUserid());
		CacheUtils.remove(USER_CACHE, USER_CACHE_USER_NAME_ + user.getUsername());
	}

	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new User()
	 */
	public static SysUser getUser() {
		Principal principal = getPrincipal();
		if (principal != null) {
			SysUser user = get(principal.getId());
			if (user != null) {
				return user;
			}
			return new SysUser();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new SysUser();
	}

	/**
	 * 获取当前用户角色列表
	 * 
	 * @return
	 */
	public static List<SysRole> getRoleList() {
		List<SysRole> roleList = (List<SysRole>) getCache(CACHE_ROLE_LIST);
		if (roleList == null) {
			SysUser user = getUser();
			roleList = roleService.findListByUserId(user.getUserid());
			putCache(CACHE_ROLE_LIST, roleList);
		}
		return roleList;
	}

	public static Set<String> getRoleStringList() {
		Set<SysRole> roles = Sets.newConcurrentHashSet(getRoleList());
		return Sets.newHashSet(Collections2.transform(roles, new Function<SysRole, String>() {
			@Override
			public String apply(SysRole role) {
				return role.getCode();
			}
		}));
	}

	/**
	 * 获取当前用户授权菜单
	 * 
	 * @return
	 */
	public static List<SysMenu> getMenuList() {
		List<SysMenu> menuList = (List<SysMenu>) getCache(CACHE_MENU_LIST);
		if (menuList == null) {
			SysUser user = getUser();
			menuList = menuService.findMenuByUserId(user.getUserid());
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	/**
	 * 获取当前菜单
	 * 
	 * @return
	 */
	public static SysMenu getCurrentMenu() {
		String url = ServletUtils.getRequest().getServletPath();
		if (url.endsWith(".html")) {   //
			return null;
		}
		String adminUrlPrefix = HlFramePropertiesUtil.getConfig("admin.url.prefix");
		url = url.substring(adminUrlPrefix.length() + 1, url.length());
		url = StringUtils.trimFirstAndLastChar(url, '/');
		if (StringUtils.isEmpty(url)) {
			return null;
		}
		// 全匹配查找
		List<SysMenu> menuList = getMenuList();
		return getCurrentMenu(menuList, url);
	}

	private static SysMenu getCurrentMenu(List<SysMenu> menuList, String url) {
		for (SysMenu menu : menuList) {
			if (!StringUtils.isEmpty(menu.getUrl())
					&& url.equals(StringUtils.trimFirstAndLastChar(menu.getUrl(), '/'))) {
				return menu;
			}
		}
		return null;
	}

	/**
	 * 通过ID获得菜单信息
	 * 
	 * @return
	 */
	public static SysMenu getMenuById(String menuid) {
		if (StringUtils.isEmpty(menuid)) {
			return null;
		}
		List<SysMenu> menuList = getMenuList();
		for (SysMenu menu : menuList) {
			if (menuid.equals(menu.getMenuid())) {
				return menu;
			}
		}
		return null;
	}

	/**
	 * 获取当前用户授权的功能
	 * @return
	 */
	public static Set<String> getPermissionsList() {
		SysUser user = getUser();
		Set<SysFunction> funlist = Sets.newConcurrentHashSet(sysFunService.findFunctionByUserId(user.getUserid()));
		
		Set<String> permissionsList = Sets.newConcurrentHashSet();
		for(SysFunction fun:funlist) {
			if (StringUtils.isNotBlank(fun.getPermission())) {
				permissionsList.add(fun.getPermission());
			}
		}
		return permissionsList;
	}

	/**
	 * 获取当前用户授权菜单
	 * 
	 * @return
	 */
	public static SysMenu getTopMenu() {
		SysMenu topMenu = getMenuList().get(0);
		return topMenu;
	}

	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
			// subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
			// subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	// ============== User Cache ==============
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}

	
}
