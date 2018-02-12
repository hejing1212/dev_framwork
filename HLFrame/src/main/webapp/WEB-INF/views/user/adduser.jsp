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
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/easyui.css">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/icon.css">
<link href="${basePath}/static/css/basic_info.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form id="save_user" method="post">
			<div class="content">
				<div class="column">
					<span class="current">基础信息</span>
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">登录名</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="username" data-options="required:true" /></td>

							<td class="kv-label">真实姓名</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="realname" data-options="required:true" /></td>
						</tr>
						<tr>
							<td class="kv-label">登录密码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="password" name="password" data-options="required:true" /></td>

							<td class="kv-label">确认密码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="password" name="passwords" data-options="required:true" /></td>
						</tr>
						<tr>
							<td class="kv-label">联系电话</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="phone" data-options="required:true" /></td>
							<td class="kv-label">邮箱</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="email"
								data-options="required:true,validType:'email" /></td>
						</tr>
						<tr>
							<td class="kv-label">状态</td>
							<td class="kv-content" colspan="3"><input type="radio"
								name="status" value="1" checked="checked"
								class="easyui-radiobox"> 正常 <input type="radio"
								name="status" value="0" class="easyui-radiobox"> 锁定</td>
						</tr>

					</tbody>
				</table>
				<div class="column">
					<span class="current">扩展信息</span>
				</div>
				<table class="kv-table">
					<tbody>
						
						<tr>
							<td class="kv-label">备注</td>
							<td class="kv-content" colspan="5">
							<textarea rows="5" cols="50" name="remarks"></textarea></td>

						</tr>
					</tbody>
				</table>
				<div class="easyui-panel" style="padding: 10px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'" onclick="submitFormData()">保存</a>
					&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
						onclick="clearForm()">重 置</a>
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
	/**
	 * 提交表单
	 */
	function submitFormData() {
		$('#save_user').form('submit', {
			url : "${basePath}/user/saveuser.html",
			onSubmit : function() {

			},
			success : function(data) {
				data = JSON.parse(data);

				if (data.code == '1') {
					$.messager.alert('添加成功', data.msg);
				} else {
					$.messager.alert('添加失败', data.msg, 'error');
				}
			}
		});
	}

	/**
	 *清除表单内容
	 **/
	function clearForm() {
		$('#save_user').form('clear');
	}
</script>
