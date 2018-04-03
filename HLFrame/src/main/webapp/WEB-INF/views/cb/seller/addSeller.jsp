<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>角色编辑</title>
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
							<td class="kv-label">角色名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="name" data-options="required:true,missingMessage:'请输入角色名称！'" /></td>

							<td class="kv-label">英文名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="code" data-options="required:true,missingMessage:'请输入角色名称！'"/></td>
						</tr>
						<tr>
							<td class="kv-label">是否系统数据</td>
							<td class="kv-content">
							<input type="radio" name="is_sys" value="1" checked="checked" class="easyui-radiobox">是
                               <input type="radio" name="is_sys" value="0" class="easyui-radiobox"> 否
							 </td>
							<td class="kv-label">是否可用</td>
							<td class="kv-content">
                              <input type="radio" name="usable" value="1" checked="checked" class="easyui-radiobox">是
                               <input type="radio" name="usable" value="0" class="easyui-radiobox"> 否</td>
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
			url : "${basePath}/sys/role/saveRole.html",
			onSubmit : function() {
                return $(this).form('validate');
			},
			success : function(data) {
				data = JSON.parse(data);
				if (data.code == '1') {
					$.messager.alert('添加成功', data.msg,'info',function(){
						window.parent.reloadRoleList();
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
