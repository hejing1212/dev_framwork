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
<link href="${basePath}/sys/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/sys/easyui/darkblue/easyui.css">
<link href="${basePath}/sys/css/basic_info.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form id="save_user"   method="post">
			<div class="content">
				<div class="column">
					<span class="current">基础信息</span>
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">登录名</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="loginName" /></td>
							<td class="kv-label">登录密码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="password" /></td>
							<td class="kv-label">确认密码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="passwords" /></td>
						</tr>
						<!-- <tr>
							<td class="kv-label">手机号</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="mobile" /></td>
							<td class="kv-label">真实姓名</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="name" /></td>
							<td class="kv-label">邮箱</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="email" /></td>
						</tr>


						<tr>
							<td class="kv-label">用户类型</td>
							<td class="kv-content"><select class="easyui-combobox"
								name="userType">
									<option value="ar">系统管理员</option>
									<option value="ar">后台工作人员</option>
							</select></td>
							<td class="kv-label">用户状态</td>
							<td class="kv-content"><input class="easyui-radio"
								name=status " type="radio"> 正常 <input
								class="easyui-radio" name=status " type="radio">锁定</td>
							<td class="kv-label">用户头像</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="photo" /></td>
						</tr> -->

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
							<td class="kv-label">工号</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="no" /></td>
							<td class="kv-label">所在机构</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="orgId" /></td>
						</tr>
						<tr>
							<td class="kv-label">所在部门</td>
							<td class="kv-content" colspan="5"><input
								class="easyui-textbox" type="text" name="officeId" /></td>

						</tr>
						<tr>
							<td class="kv-label">备注</td>
							<td class="kv-content" colspan="5"><textarea rows="5"
									cols="50" name="remarks"></textarea></td>

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
	src="${basePath}/sys/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath}/sys/easyui/jquery.easyui.min.js"></script>
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
