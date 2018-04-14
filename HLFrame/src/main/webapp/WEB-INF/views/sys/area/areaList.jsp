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
<script type="text/javascript">
var basePath="${basePath}";
</script>
</head>
<body>
	<div class="container">
		<table id="menuTree" title="区域管理列表" class="easyui-treegrid"
			style="width: 100%; height: 600px"
			data-options="method: 'get',rownumbers: true,idField: 'id',treeField: 'name', toolbar:'#tb',iconCls:'icon-list'">
			<thead>
				<tr>
					<th data-options="field:'name'" width="150" align="left">地区名称</th>
					<th data-options="field:'shortName'" width="220" align="left">简称</th>
					<th data-options="field:'sort'"
						width="80" align="center">排序</th>
					<th data-options="field:'status',formatter:SetDictNameMap"
						width="80" align="center">状态</th>
					
				</tr>
			</thead>
		</table>

		<div id="tb" style="padding: 0 30px;">
			<div class="conditions">
				关键字: <input class="easyui-textbox" type="text" id="queryKey"
					style="width: 166px; height: 35px; line-height: 35px;"></input> <a
					href="#" class="easyui-linkbutton" iconCls="icon-search"
					onclick="javascript:doSearch()" data-options="selected:true">查询</a>


				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-add'"
					onclick="window.parent.mainPlatform._createWindows('添加地区','${basePath}/sys/area/areaAdd.html','icon-add','addArea');">
					新增</a> <a href="javascript:void(0)" onclick="editRole();"
					class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
				<a href="javascript:void(0)" onclick="setRole();"
					class="easyui-linkbutton" data-options="iconCls:'icon-set'">删除</a>

			</div>

		</div>
	</div>

	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basePath}/static/js/dict.js"></script>
	<script type="text/javascript">
		//查询功能
		function doSearch() {
			var queryParams = $("#dg").datagrid("options").queryParams;
			queryParams["queryKey"] = $.trim($("#queryKey").val());
			$("#dg").datagrid({
				url : "${basePath}/sys/role/getRoleList.html"
			});
		}

	 
		
		$("#menuTree").treegrid({
			    url: '${basePath}/sys/area/getAreaList.html',
			    idField: 'id',
			    treeField: 'name',
			    rownumbers: true,	
			    pagination: true,	
			    fitColumns: true,	
			    autoRowHeight: false,
			    animate:true,
		        collapsible:true,  
			    onLoadSuccess: function() {
			        delete $(this).treegrid('options').queryParams['id'];
			    },			    
				 onBeforeExpand:function(row){    //每次展开前都会调用
		             //动态设置展开查询的url  
		           var url = '${basePath}/sys/area/getAreaChildrenList.html?parentId='+row.id; 
		           $("#menuTree").treegrid("options").url = url;  
		           return true;  //返回false表示停止展开节点    
		          },
		        onExpand : function(row){ //每次展后都会调用;传入的row已经包含了 children
		           var children = $("#menuTree").treegrid('getChildren',row.id);
		           if(children.length<=0){ 
		               row.leaf=true;
		               $("#menuTree").treegrid('refresh', row.id);
		           }
		        },
		        onDblClickRow: function(row){
		         
		         }
			});
	</script>

</body>
</html>