<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>流程页</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/easyui.css">
<link rel="stylesheet" href="${basePath}/static/easyui/darkblue/icon.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/sys/easyui/darkblue/icon.css">
<link rel="stylesheet" href="${basePath}/static/css/providers.css">

</head>
<body>
	<div class="container">
<div id="tb" style="padding: 0 30px;">
			<div class="conditions">
			<a href="${basePath}/sys/memu/addmemu.html" class="easyui-linkbutton" data-options="iconCls:'icon-add'"> 新增</a>
				<a href="javascript:void(0)" onclick="editMemu();" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
				
				名称: <input class="easyui-textbox" type="text" name="code"
					style="width: 166px; height: 35px; line-height: 35px;"></input>
				权限标识: <input class="easyui-textbox" type="text" name="name"
					style="width: 166px; height: 35px; line-height: 35px;"></input> <a
					href="#" class="easyui-linkbutton" iconCls="icon-search"
					data-options="selected:true">查询</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			</div>
 </div>
		
		<table title="菜单管理列表" class="easyui-treegrid" style="width:100%;height:600px"
			data-options="
url: '${basePath}/sys/memu/getMenuList.html',method: 'get',rownumbers: true,idField: 'menuid',treeField: 'name', toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'name'" width="220" align="center">名称</th>
				<th data-options="field:'url'" width="220" align="center">菜单路径</th>
				<th data-options="field:'permission'" width="220" align="center">权限字符</th>
				<th data-options="field:'isshow'" width="220" align="center">是否显示</th>
				<th data-options="field:'type'" width="100" align="center">资源类型</th>
				<th data-options="field:'create_date'" width="150" align="center">添加日期</th>
				<th data-options="field:'remarks'" width="150" align="center">摘要</th>
			</tr>
		</thead>
	</table>

		

	</div>

	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/easyui-lang-zh_CN.js"></script>

	

</body>
</html>