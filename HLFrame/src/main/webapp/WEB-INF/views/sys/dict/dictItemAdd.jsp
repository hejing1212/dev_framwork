<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>键值添加</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/easyui.css">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/icon.css">
<link href="${basePath}/static/css/basic_info.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form id="save_user_form" method="post" enctype="multipart/form-data">
			<div class="content">
				 
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">名称</td>
							<td class="kv-content">
							<input type="hidden" id="dict_num" name="dictCode" value="${dictId}" />
							<input class="easyui-textbox" type="text" name="itemName" data-options="required:true,validType:['stringCheck','length[1,32]'],missingMessage:'请输入键值名称'" /></td>

							<td class="kv-label">键值</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="itemValue" data-options="required:true,missingMessage:'请输入键值！'" /></td>
						</tr>
						<tr>
							<td class="kv-label">排序</td>
							<td class="kv-content"  colspan="3">
							<input class="easyui-numberbox" value="0"
								type="text" name="sort" data-options="required:true,missingMessage:'请输入排序数字！'" /></td>
					    </tr>
						<tr>
							<td class="kv-label">备注</td>
							<td class="kv-content"  colspan="3">
							<textarea rows="5" cols="50" name="remarks"></textarea></td>
					    </tr>

					</tbody>
				</table>
				 
				 
				<div class="easyui-panel" style="padding: 10px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="submitFormData();">保存</a>
					&nbsp;&nbsp;&nbsp; 
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="clearForm();">重 置</a>
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
<script type="text/javascript"
		src="${basePath}/static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basePath}/static/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basePath}/static/js/upload.js"></script>		
<script type="text/javascript">
	/**
	 * 提交表单
	 */
	function submitFormData() {
		$('#save_user_form').form('submit', {
			url : "${basePath}/sys/dict/saveDictItem.html",
			onSubmit : function() {
                return $(this).form('validate');
			},
			success : function(data) {
				data = JSON.parse(data);
				if (data.code == '1') {
					$.messager.alert('添加成功', data.msg,'info',function(){
						window.parent.reloadDictItemList();
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
		$("#save_user_form").form('clear');
	}
	
	
</script>
