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

		<table id="dg" style="width: 100%; height: 554px" title="全体供应商列表"
			data-options="  rownumbers:true,
                singleSelect:false, autoRowHeight:false,  pagination:true, fitColumns:true,  striped:true,  checkOnSelect:false,
                selectOnCheck:false, collapsible:true,  toolbar:'#tb',  pageSize:10">
			<thead>
				<tr>
					<th field="userid" width="160" align="center">用户编号</th>
					<th field="realname" width="120" align="center">真实姓名</th>
					<th field="username" width="120" align="center">用户名</th>
					<th field="email" width="150" align="center">邮箱</th>
					<th field="phone" width="130" align="center">手机号</th>
					<th field="status" width="100" align="center">状态</th>

					<th field="create_date" width="120" align="center">创建日期</th>
					<th field="remarks" width="200">备注</th>
				</tr>
			</thead>
		</table>

		<div id="tb" style="padding: 0 30px;">
			<div class="conditions">
				真实姓名: <input class="easyui-textbox" type="text" name="code"
					style="width: 166px; height: 35px; line-height: 35px;"></input>
				手机号: <input class="easyui-textbox" type="text" name="name"
					style="width: 166px; height: 35px; line-height: 35px;"></input> <a
					href="#" class="easyui-linkbutton" iconCls="icon-search"
					data-options="selected:true">查询</a> <a href="#"
					class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			</div>
			<div class="opt-buttons">
				<a href="${basePath}/user/adduser.html" class="easyui-linkbutton" data-options="iconCls:'icon-add'"> 新增</a>
				<a href="javascript:void(0)" onclick="editUser();" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a> <a href="#"
					class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置密码</a>  
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
		/*
		 * 显示列表
		 */
		$(function() {
			$('#dg').datagrid({
				//data : getData()
				url : "${basePath}/user/getlist.html",
				rownumbers : true,
			}).datagrid('clientPaging');
		});
		
		//编辑用户
		function editUser() {
		    var row = $('#dg').datagrid('getSelected');
		    if (row) {
		        if (row.userid == '') {
		            $.messager.show({
		                title: '操作提示',
		                msg: '请先选择用户后再进行此操作!',
		                showType: 'slide'
		            }); 
		            return;
		        }
		        var index = $('#dg').datagrid('getRowIndex', row);
		        //关闭子页面
		       // window.parent.closeChildTab("edit");
		    	//打开新增子页面
		       // window.parent.createChildTab("编辑","${basePath}/user/edituser.html?userid=" + row.userid + "&index=" + index,"icon-add",'edit');
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