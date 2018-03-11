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
			 
		</table>

		<div id="tb" style="padding: 0 30px;">
			<div class="conditions">
				角色名称: <input class="easyui-textbox" type="text" name="code"
					style="width: 166px; height: 35px; line-height: 35px;"></input>
					
				<a href="#" class="easyui-linkbutton" iconCls="icon-search"
					data-options="selected:true">查询</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-reload">重置</a>
					
					<a href="javascript:void(0)" onclick="setRole();" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a> 
			</div>
			 
		</div>
      <form id="form_role" method="post">
      <input type="hidden" id="userid" name="user_id" value="${userid}">
      <input type="hidden" name="role_id" id="roles" >
      </form>
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
				url : "${basePath}/sys/role/getRoleList.html",
				rownumbers : true,
				columns:[[  
			        {field:'roleid',checkbox:true},  
			        {field:'name',title:'角色名称',width:120},  
			        {field:'code',title:'英文名称',width:120},  
			        {field:'remarks',title:'备注',width:200}
			         
			    ]], 
			    pagination:true 
			});
		});

		//设置角色权限
		function setRole(){			
			 var checkedItems = $('#dg').datagrid('getChecked');
			   var roleids = [];
			   $.each(checkedItems, function(index, item){
				   roleids.push(item.roleid);
			  });
			 $("#roles").val(roleids.join(","));
			  
			 $('#form_role').form('submit', {
					url : "${basePath}/sys/role/saveUserRole.html",					
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
	</script>

</body>
</html>