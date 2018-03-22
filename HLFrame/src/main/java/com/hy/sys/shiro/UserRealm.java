package com.hy.sys.shiro;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hy.sys.entity.SysUser;
import com.hy.sys.service.SysUserService;

/**
 * 
 * @author 
 *
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;
	 
	/**
	 * 授权的回调方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(UserUtils.getRoleStringList());
		authorizationInfo.setStringPermissions(UserUtils.getPermissionsList());
		return authorizationInfo;
	}

	/**
	 * 认证的回调方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
		String username = authcToken.getUsername();
		SysUser user = sysUserService.findByUsername(username);
		if (user == null) {
			user = sysUserService.findByEmail(username);
			if (user == null) {
				user = sysUserService.findByPhone(username);
			}
		}
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (SysUser.STATUS_LOCKED.equals(user.getStatus())) {
			throw new LockedAccountException(); // 帐号锁定，有错误，后期一定要处理干净
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				new Principal(user, authcToken.isMobileLogin()), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()), 
				getName() // realm name
		);
		// 记录登录日志
	//	LogUtilsDEL.saveLog(ServletUtils.getRequest(), "系统登录");
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;

		private String id; // 编号
		private String username; // 登录名
		private String realname; // 姓名
		private boolean mobileLogin; // 是否手机登录

		public Principal(SysUser user, boolean mobileLogin) {
			this.id = user.getUserid();
			this.username = user.getUsername();
			this.realname = user.getRealname();
			this.mobileLogin = mobileLogin;
		}

		public String getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public String getRealname() {
			return realname;
		}

		public boolean isMobileLogin() {
			return mobileLogin;
		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try {
				return (String) UserUtils.getSession().getId();
			} catch (Exception e) {
				return "";
			}
		}

		@Override
		public String toString() {
			return id;
		}

	}
}
