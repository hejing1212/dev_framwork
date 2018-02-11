<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
					<c:set var="appPath" value='<%=request.getContextPath()%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>贵州海誉快速开发平台系统-用户登录</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link href="${basePath}/static/css/login/login.css" rel="stylesheet">

</head>
<body class="default">
	<div class="login-hd">
		<div class="left-bg"></div>
		<div class="right-bg"></div>
		<div class="hd-inner">
			<span class="logo"></span> <span class="split"></span> <span
				class="sys-name">贵州海誉快速开发平台</span>
		</div>
	</div>
	<form id="loginform" method="post" class="form">
		<div class="login-bd">
			<div class="bd-inner">
				<div class="inner-wrap">
					<div class="lg-zone">
						<div class="lg-box">
							<div class="lg-label">
								<h4>用户登录</h4>
							</div>
							<div class="alert alert-error">
								<i class="iconfont">&#xe62e;</i> <span>请输入用户名</span>
							</div>
							<form>
								<div class="lg-username input-item clearfix">
									<i class="iconfont">&#xe60d;</i>
									 <input type="text"	placeholder="账号/邮箱"  name="username">
								</div>
								<div class="lg-password input-item clearfix">
									<i class="iconfont">&#xe634;</i> <input type="password"
										placeholder="请输入密码" name="password">
								</div>
								<div class="lg-check clearfix">
									<div class="input-item">
										<i class="iconfont">&#xe633;</i> <input type="text"
											placeholder="验证码" name="rememberMe">
									</div>
									<span class="check-code">
<img id="img_jcaptcha"  src="${appPath}/jcaptcha.jpg" width="80" height="36" onclick="changeJcaptchaSrc();" />
                                   </span>
								</div>
								<div class="tips clearfix">
									<label><input type="checkbox" checked="checked">记住用户名</label>
									<a href="javascript:;" class="register">立即注册</a> <a
										href="javascript:;" class="forget-pwd">忘记密码？</a>
								</div>
								<div class="enter">
									 <a href="javascript:;" class="purchaser" onClick="UserLogin()">管理员登录</a>
									 <a 	href="javascript:;" class="supplier" onClick="javascript:window.location='main.html'">供应商登录</a>
								</div>
							</form>
						</div>
					</div>
					<div class="lg-poster"></div>
				</div>
			</div>
		</div>
	</form>
	<div class="login-ft">
		<div class="ft-inner">
			<div class="about-us">
				<a href="javascript:;">关于我们</a> <a href="javascript:;">法律声明</a> <a
					href="javascript:;">服务条款</a> <a href="javascript:;">联系方式</a>
			</div>
			<div class="address">地址：贵族省贵阳市南明区花果园国际中心A座21楼&nbsp;邮编：550001&nbsp;&nbsp;Copyright&nbsp;©&nbsp;2018&nbsp;-&nbsp;2012022&nbsp;hlweb&nbsp;版权所有</div>
			<div class="other-info">建议使用IE8及以上版本浏览器&nbsp;黔ICP备&nbsp;0180665908号&nbsp;E-mail：304241452@qq.com</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${basePath}/static/easyui/jquery.min.js"></script>
<script type="text/javascript">
function UserLogin(){
	$("#loginform").submit()
}
/**
 * 刷新验证码
 */
function changeJcaptchaSrc(){  
    document.getElementById("img_jcaptcha").src='${appPath}/jcaptcha.jpg?_='+(new Date()).getTime();  
}
</script>
