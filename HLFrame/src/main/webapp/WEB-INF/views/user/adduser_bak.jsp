<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>添加用户</title>
<link href="${basePath}/botui/css/bootstrap.min.css?v=3.3.5"
	rel="stylesheet">
<link href="${basePath}/botui/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="${basePath}/botui/css/plugins/iCheck/custom.css"
	rel="stylesheet">


<link href="${basePath}/botui/css/animate.min.css" rel="stylesheet">
<link href="${basePath}/botui/css/style.min.css?v=4.0.0"
	rel="stylesheet">
<base target="_blank">

</head>

<body class="gray-bg">
	<div class="wrapper ">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							添加用户 <small>后台用户或管理员</small>
						</h5>

					</div>
					<div class="ibox-content">
						<form action="saveuser.html" method="post"  class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">登录名称</label>

								<div class="col-sm-10">
									<input type="text" placeholder="登录系统的账号" name="login_name" minlength="3" class="form-control"  required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2  control-label">登录密码</label>

								<div class="col-sm-10">
									<input type="password" name="password" placeholder="登录系统的密码"
										class="form-control" minlength="6" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2 control-label">确认密码</label>

								<div class="col-sm-10">
									<input type="password" name="confirm_password"  placeholder="必要与上面密码一致"
										class="form-control" minlength="6" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">真实姓名</label>

								<div class="col-sm-10">
									<input type="text" placeholder="请输入真实姓名" name="name" class="form-control" minlength="6" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">邮箱</label>

								<div class="col-sm-10">
									<input type="email" name="email" placeholder="请输入邮箱" class="form-control" required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2  control-label" minlength="6" required>联系电话</label>

								<div class="col-sm-10">
									<input type="text" name="phone" placeholder="请输入联系电话" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">手机号</label>

								<div class="col-sm-10">
									<input type="text" name="mobile" placeholder="请输入手机号" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">工号</label>

								<div class="col-sm-10">
									<input type="text" placeholder="请输入工作证号" name="no" class="form-control">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">内联复选框</label>

								<div class="col-sm-10">
									<div class="radio i-checks">
										<label> <input type="radio" value="0" name="status"
											checked=""> <i></i> 启用
										</label> <label> <input type="radio" value="1" name="status">
											<i></i> 信用
										</label>
									</div>
								</div>
							</div>
							<!-- -------------------------- -->


							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button class="btn btn-primary" type="submit">保存内容</button>
									<button class="btn btn-white" type="submit">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="${basePath}/botui/js/jquery.min.js?v=2.1.4"></script>
	<script src="${basePath}/botui/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${basePath}/botui/js/content.min.js?v=1.0.0"></script>
	<script src="${basePath}/botui/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${basePath}/botui/js/plugins/iCheck/jquery.validate.min.js"></script>
	<script src="${basePath}/botui/js/plugins/iCheck/messages_zh.min.js"></script>
	<script>
		$(document).ready(function() {
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green",
			})
		});
	</script>

</body>
</html>