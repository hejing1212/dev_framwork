<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>数据字典添加</title>
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
				<div class="column">
					<span class="current">数据字典添加</span>
					<input type="hidden" name="id" value="${dict.id }" />
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">名称</td>
							<td class="kv-content"><input class="easyui-textbox" value="${dict.dictName }"
								type="text" name="dictName" data-options="required:true,validType:['stringCheck','length[1,32]'],missingMessage:'请输入字典名称'" /></td>

							<td class="kv-label">编码</td>
							<td class="kv-content"><input class="easyui-textbox" value="${dict.dictCode }"
								type="text" name="dictCode" data-options="required:true,missingMessage:'请输入字典编码！'" /></td>
						</tr>
						<tr>
							<td class="kv-label">备注</td>
							<td class="kv-content"  colspan="3">
							<textarea rows="5" cols="50" name="remarks">${dict.remarks }</textarea></td>
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
			url : "${basePath}/sys/dict/saveDict.html",
			onSubmit : function() {
                return $(this).form('validate');
			},
			success : function(data) {
				data = JSON.parse(data);

				if (data.code == '1') {
					$.messager.alert('添加成功', data.msg,'info',function(){
						window.parent.reloadDictList();
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
