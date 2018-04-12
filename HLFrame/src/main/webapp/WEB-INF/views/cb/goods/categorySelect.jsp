<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>流程页</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/easyui.css">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/icon.css">
<link rel="stylesheet" href="${basePath}/static/css/providers.css">

</head>
<body>
	<div class="container">

		<table id="dg" style="width: 100%; height: 554px" title="角色列表"
			data-options="  rownumbers:true,
                singleSelect:false, autoRowHeight:false,  pagination:true, fitColumns:true,  striped:true,  checkOnSelect:false,
                selectOnCheck:false, collapsible:true,  toolbar:'#tb',  pageSize:10">
			<thead>
				<tr>
					<th field="categoryName" width="160" align="center">分类名称</th> 
					<th field="sort" width="120" align="center">排序</th>				 
					<th field="remarks" width="200">备注</th>
				</tr>
			</thead>
		</table>

		<div id="tb" style="padding: 0 30px;">
			<div class="conditions">
				关键字: <input class="easyui-textbox" type="text" name="code"
					style="width: 166px; height: 35px; line-height: 35px;"></input> 
			 <a href="#" class="easyui-linkbutton" onclick="javascript:doSearch()" iconCls="icon-search" data-options="selected:true">查询</a> 
              <a href="javascript:void(0)" onclick="selectCategory();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a>
			</div>

		</div>
		
	</div>

	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/easyui-lang-zh_CN.js"></script>

	<script type="text/javascript">
	
	//查询功能
	function doSearch() {
		var queryParams = $("#dg").datagrid("options").queryParams;
		queryParams["queryKey"] = $.trim($("#queryKey").val());
		$("#dg").datagrid({
			url : "${basePath}/cb/goods/getCategoryList.html"
		});
	}
	
		/*
		 * 显示列表
		 */
		$(function() {
			$('#dg').datagrid({
				url : "${basePath}/cb/goods/getCategoryList.html",
				rownumbers : true,
				pagination : true
			});
		});

		//设置角色权限
		function selectCategory() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$("#categoryId",window.parent.document).val(row.categoryId);//子窗口给父窗口元素赋值
				parent.$("#category").textbox("setValue",row.categoryName); 
				parent.$("#dialog").dialog('close');
		    } else {
		        $.messager.show({
		            title: '操作提示',
		            msg: '请选择要操作的数据!',
		            timeout: 2000,
		            showType: 'slide'
		        });
		    }
			
			
		}
	</script>

</body>
</html>