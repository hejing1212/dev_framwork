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
					<th field="roleid" width="160" align="center">角色ID</th>
					<th field="name" width="120" align="center">角色名称</th>
					<th field="code" width="120" align="center">英文名称</th>
					<th field="is_sys" width="80" align="center" data-options="formatter:SetDictNameMap">是否系统数据</th>
					<th field="usable" width="80" align="center" data-options="formatter:SetDictNameMap">是否可用</th>					 
					<th field="create_date" width="120" align="center">创建日期</th>
					<th field="remarks" width="200">备注</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="padding: 0 30px;">
			<div class="conditions">
				关键字: <input class="easyui-textbox" type="text" id="queryKey" style="width: 166px; height: 35px; line-height: 35px;"></input>
			        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="javascript:doSearch()" data-options="selected:true">查询</a> 
					
					 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="window.parent.mainPlatform._createWindows('添加角色','${basePath}/sys/role/addRole.html','icon-add','addRole');"> 新增</a>
				<a href="javascript:void(0)" onclick="editRole();" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
				<a href="javascript:void(0)" onclick="setRole();" class="easyui-linkbutton" data-options="iconCls:'icon-set'">授权</a>  
			 
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
	
		/*
		 * 显示列表
		 */
		$(function() {
			$('#dg').datagrid({
				url : "${basePath}/sys/role/getRoleList.html",
				rownumbers : true,
			});
		});
		//子页面调用后刷新列表
	    window.top["reload_Abnormal_Monitor"]=function(){
	    	$("#dg").datagrid('reload');
	    };
	    
		//编辑角色
		function editRole() {
		    var row = $('#dg').datagrid('getSelected');
		    if (row) {
		        if (row.roleid == '') {
		            $.messager.show({
		                title: '操作提示',
		                msg: '请先选择记录后再进行此操作!',
		                showType: 'slide'
		            }); 
		            return;
		        }
		        var index = $('#dg').datagrid('getRowIndex', row);
		        window.parent.mainPlatform._createWindows("编辑用户",
						"${basePath}/sys/role/editRole.html?roleid="
								+ row.roleid + "&index=" + index, "icon-edit",
						'edit');
		    } else {
		        $.messager.show({
		            title: '操作提示',
		            msg: '请选择要操作的数据!',
		            timeout: 2000,
		            showType: 'slide'
		        });
		    }
		}		
		//设置角色权限 
		function setRole(){
			 var row = $('#dg').datagrid('getSelected');
			 if (row==null ||row=='') {
		            $.messager.alert('操作提示','请先选择记录后再进行此操作!','info'); 
		            return;
		        }
			 
			 var index = $('#dg').datagrid('getRowIndex', row);
			 window.parent.mainPlatform._createWindows("角色授权",
						"${basePath}/sys/role/roleAuthorize.html?roleId="
								+ row.roleid + "&index=" + index, "icon-set",
						'setRoleAuthorize');
		}
	</script>

</body>
</html>