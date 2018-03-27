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
                singleSelect:true, autoRowHeight:false,  pagination:true, fitColumns:true,  striped:true,  checkOnSelect:false,
                selectOnCheck:false, collapsible:true,  toolbar:'#tb',  pageSize:10 ,iconCls:'icon-list'">
			<thead>
				<tr>
					<th field="opt_user_name" width="60" align="center">操作人</th>
					<th field="opt_table_name" width="60" align="center">操作表</th>
					<th field="opt_data_id" width="120" align="center">操作数据ID</th>
					<th field="opt_type" width="50" align="center">操作类型</th>
					<th field="opt_content" width="360" align="center">操作内容</th>					 
					<th field="create_date" width="80" align="center">创建日期</th>
					 
				</tr>
			</thead>
		</table>
		<div id="tb" style="padding: 0 30px;">
		<shiro:hasPermission name="sys:menu:fun:deleteMenuFun">
			<div class="conditions">
				操作人: <input class="easyui-textbox" type="text" name="code"
					style="width: 166px; height: 35px; line-height: 35px;"></input>
			  <a href="#" class="easyui-linkbutton" iconCls="icon-search"
					data-options="selected:true">查询</a> 

			 
			</div>
		 </shiro:hasPermission>		
		</div>
	</div>

	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/easyui-lang-zh_CN.js"></script>

	<script type="text/javascript">	
		/*
		 * 显示列表
		 */
		$(function() {
			$('#dg').datagrid({
				url : "${basePath}/sys/log/getLogList.html",
				rownumbers : true,
			});
		});
		
	    
		
	</script>

</body>
</html>