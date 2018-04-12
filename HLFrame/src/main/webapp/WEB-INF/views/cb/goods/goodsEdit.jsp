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
					<input type="hidden" name="goodsId" value="${goods.goodsId}" />
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="goodsName" value="${goods.goodsName}" data-options="required:true,missingMessage:'请输入商品名称！'" style="height:30px"/></td>

							<td class="kv-label">分类</td>
							<td class="kv-content"><input class="easyui-textbox" editable="false"
								type="text" id="category" name="category"  value="${goods.categoryName}"   data-options="required:true,missingMessage:'选择分类！'" style="height:30px"  />
								<input type="hidden" name="categoryId" id="categoryId" value="${goods.categoryId}" />
								
								<a href="#" onclick="getCategoryList();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">选择</a> 
								</td>
						</tr>						
						<tr>							 
							<td class="kv-label">分类图片</td>
							<td class="kv-content">
							<input id="up_portrait" value="${goods.picture}"
								name="uploadFile" class="easyui-filebox"
								data-options="buttonText:'选择图片',accept:'image/*',onChange:function(){upload_cover(this,'${basePath}/cb/goods/fileUpload.html','picture')}"
								style="width: 40%;height: 30px;" />
								<input type="hidden" id="picture" name="picture" value="${goods.picture}" />								
							</td>						
							<td class="kv-label">图片预览</td>
							<td class="kv-content"  >
							<img id="image" class="cover-radius"<c:choose><c:when test="${goods.picture eq ''}">src="${basePath}/static/images/main/user.png"</c:when>
								<c:otherwise>src="${basePath}/${goods.picture}"</c:otherwise></c:choose>
								 width="60" height="60" style="cursor: pointer;" />
							</td>
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
<div id="dialog" class="easyui-dialog" closed="true"></div>
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
			url : "${basePath}/cb/goods/saveGoods.html",
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


	//打开选择分类界面
	function getCategoryList() {
		$("#dialog")
				.dialog({
							title : '选择商品分类',
							width : 800,
							height : 400,
							top:$(document).scrollTop()+($(window).height()-250)*0.3,
						    left:$(document).scrollLeft()+($(window).width()-800)*0.5, 
							modal : true,
							content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/cb/goods/categorySelect.html' style='width:100%; height:100%; display:block;'></iframe>"
						}).dialog('open');
	}
	
</script>
