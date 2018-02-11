<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>基本信息</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/sys/easyui/darkblue/easyui.css">
<link href="${basePath}/static/css/basic_info.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form id="save_user"   method="post">
			<div class="content">
				<div class="column">
					<span class="current">基础信息</span>
				</div>
				<table class="kv-table"  >
					<tbody>
						<tr>
							<td class="kv-label">登录名</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="username" /></td>
								
								<td class="kv-label">真实姓名</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="realname" /></td>
								</tr>
								<tr>
							<td class="kv-label">登录密码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="password" /></td>
								
							<td class="kv-label">确认密码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="passwords" /></td>
						</tr>
						

					</tbody>
				</table>
				<div class="column">
					<span class="current">扩展信息</span>
				</div>

				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">联系电话</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="phone" /></td>
							<td class="kv-label">邮箱</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="email" /></td>
							<td class="kv-label">状态</td>
							<td class="kv-content"><input class="easyui-textbox"
								 type="redio" name="status" /></td>
						</tr>
						
					</tbody>
				</table>
				<div class="easyui-panel" style="padding: 5px;">
			 
				
				 
					<button type="button" id="reset" class="easyui-linkbutton"
						data-options="toggle:true">重 置</button>
						<input type="button" value="提交" class="easyui-linkbutton" data-options="toggle:true,selected:true" onclick="submitFormData()"> 
				</div>

			</div>
		</form>
	</div>

</body>
</html>
<script type="text/javascript"
	src="${basePath}/static/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
  function submitFormData(){
		$('#save_user').form('submit', {
			url :"${basePath}/user/saveuser.html",
			onSubmit : function() {
			 
			},
			success : function(data) {
				data=JSON.parse(data);
				 
				alert(data.msg);
			}
		});
  }
</script>
