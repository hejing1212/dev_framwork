<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form id="save_menu" method="post">
			<div class="content">
				<div class="column">
					<span class="current">菜单信息</span>
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
						<td class="kv-label">上级资源</td>
							<td class="kv-content">
							<select class="easyui-combotree" name="parent_id"  id="parent_id" data-options="required:true"   style="height:35px;width:40%">
							</select>
                             </td>
								
							<td class="kv-label">资源名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="name" data-options="required:true" style="height:35px;" /></td>

							
						</tr>
						 
						<tr>
								<td class="kv-label">地址</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="url" data-options="required:true"  style="height:35px;"/></td>
						 <td class="kv-label">图标样式</td>
							<td class="kv-content"> 
							<input class="easyui-textbox"
								type="text" name="menu_icon" data-options="required:true,missingMessage:'请输入图标样式名称！'"
								value="${menu.menu_icon}" style="height: 35px;" />
								<a href="javascript:void(0)" onclick="showIcon();">选择图标</a>
							</td>
						</tr>
						
						<tr>
						<td class="kv-label">就否显示</td>
							<td class="kv-content"  ><input type="radio"
								name="isshow" value="1" checked="checked"
								class="easyui-radiobox"> 显示 <input type="radio"
								name="isshow" value="0" class="easyui-radiobox"> 隐藏</td>
							<td class="kv-label">类型</td>
							<td class="kv-content"  ><input type="radio"
								name="type" value="1" checked="checked"
								class="easyui-radiobox"> 模块 <input type="radio"
								name="type" value="0" class="easyui-radiobox"> 菜单</td>
						</tr>
						<tr>
						   <td class="kv-label">设置默认展开</td>
							<td class="kv-content"  >
							<input type="radio"
								name="current" value="0" checked="checked"
								class="easyui-radiobox">否 <input type="radio"
								name="current" value="1" class="easyui-radiobox">是
							 </td>
							<td class="kv-label">排序</td>	 
							<td class="kv-content"  >
							<input class="easyui-textbox"
								type="text" name="sort" data-options="required:true"  style="height:35px;"/></td>
								
						</tr>
						 <tr>
							<td class="kv-label">摘要</td>
							<td class="kv-content" colspan="3">
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
<!-- 打开选择ICON界面 -->
	<div id="dialog" class="easyui-dialog" closed="true"></div>
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
		$('#save_menu').form('submit', {
			url : "${basePath}/sys/menu/savemenu.html",
			onSubmit : function() {
                return $(this).form('validate');
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
	/***
	*设置父级菜单选择树
	**/
	$(function(){ 
		$("#parent_id").combotree({
			url:"${basePath}/sys/menu/menuTreeJson.html",
			onLoadSuccess:function(node, data){
				$("#parent_id").combotree('setValue',data[0].id);
			}
		});
	})
	

	//打开选择ICON界面
		function showIcon() {
			$("#dialog")
					.dialog({
								title : '选择图标',
								width : 800,
								height : 400,
								modal : true,
								top:$(document).scrollTop()+($(window).height()-250)*0.3,
							    left:$(document).scrollLeft()+($(window).width()-800)*0.5,
								content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/static/fonts/demo.html' style='width:100%; height:100%; display:block;'></iframe>"
							}).dialog('open');
		}
</script>
