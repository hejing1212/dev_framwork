<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>菜单添加</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/easyui.css">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/icon.css">
<link href="${basePath}/static/css/basic_info.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form id="form_edit_fun" method="post">
			<div class="content">
				<div class="column">
					<span class="current">功能信息</span>
					<input type="hidden" name="funid" value="${sysFunction.funid}"> 
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">功能名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="name" value="${sysFunction.name}" data-options="required:true,missingMessage:'请输入功能名称！'"
								style="height: 35px;" /></td>

							<td class="kv-label">方法名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="fun_action" value="${sysFunction.fun_action}" data-options="required:true,missingMessage:'请输入方法名称！'"
								style="height: 35px;" /></td>


						</tr>

						<tr>
							<td class="kv-label">权限字符</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="permission" value="${sysFunction.permission}" data-options="required:true,missingMessage:'请输入权限字符！'"
								style="height: 35px;" /></td>
							<td class="kv-label">功能图标</td>
							<td class="kv-content"> 
								<input class="easyui-filebox" name="menu_icon" data-options="prompt:'请选择文件...', buttonText:' 选 择 '"   style="width:80%;height:35px;">
								</td>
						</tr>
						<tr>
							<td class="kv-label">排序</td>
							<td class="kv-content" colspan="3"><input
								class="easyui-textbox" type="text" value="${sysFunction.sort}" name="sort"
								data-options="required:true" style="height: 35px;" /></td>
						</tr>
						<tr>
						<tr>
							<td class="kv-label">功能说明</td>
							<td class="kv-content" colspan="3"><textarea rows="5"
									cols="50" name="remarks">${sysFunction.remarks}</textarea></td>

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
		$('#form_edit_fun').form('submit', {
			url : "${basePath}/sys/menu/editFunction.html",
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(data) {
				data = JSON.parse(data);

				if (data.code == '1') {
					$.messager.alert('修改成功', data.msg,'info',function(){
						window.parent.reloadFunList();
					});
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
