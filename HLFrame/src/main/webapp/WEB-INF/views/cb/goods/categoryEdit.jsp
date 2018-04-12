<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/dict.tld"%>
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
<script type="text/javascript">
var basePath="${basePath}";
</script>
</head>
<body>
	<div class="container">
		<form id="save_user" method="post" >
			<div class="content">
				<div class="column">
					<span class="current">基础信息</span>
					<input type="hidden" name="categoryId" value="${cat.categoryId}" /> 
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="categoryName" value="${cat.categoryName}" data-options="required:true,missingMessage:'请输入商品分类名称！'" style="height:30px"/></td>

							<td class="kv-label">排序</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="sort" value="${cat.sort}" data-options="required:true,missingMessage:'排序编号！'" style="height:30px"/></td>
						</tr>
						
						<tr>
							 

							<td class="kv-label">分类图片</td>
							<td class="kv-content">
							<input id="up_portrait"
								name="uploadFile" class="easyui-filebox"
								data-options="buttonText:'选择图片',accept:'image/*',onChange:function(){upload_cover(this,'${basePath}/cb/goods/fileUpload.html','picture')}"
								style="width: 40%;height: 30px;" />
								<input type="hidden" id="picture"  value="${cat.picture}" name="picture"/>
								 
								<img id="image" class="cover-radius"<c:choose><c:when test="${cat.picture eq ''}">src="${basePath}/static/images/main/user.png"</c:when>
								<c:otherwise>src="${basePath}/${cat.picture}"</c:otherwise></c:choose>
								 width="60" height="60" style="cursor: pointer;" />
								 
							</td>
							
							<td class="kv-label">商家介绍</td>
							<td class="kv-content"  >
							<textarea rows="5" cols="50" name="remarks">${cat.remarks}</textarea></td>
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
	<script type="text/javascript" src="${basePath}/static/js/ajaxfileupload.js"></script> 
<script type="text/javascript" src="${basePath}/static/js/upload.js"></script>	
<script type="text/javascript">
	/**
	 * 提交表单
	 */
	function submitFormData() {
		$('#save_user').form('submit', {
			url : "${basePath}/cb/goods/saveCategory.html",
			onSubmit : function() {
                return $(this).form('validate');
			},
			success : function(data) {
				data = JSON.parse(data);
				if (data.code == '1') {
					$.messager.alert('添加成功', data.msg,'info',function(){
						window.parent.mainPlatform.parentReloadTabGrid();
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
	//子页面调用后刷新列表
	window.top["reload_Abnormal_Monitor"] = function() {
		$("#dg").datagrid('reload');
	};
	
</script>
