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
	<div id="mypanels" class="easyui-layout" style="width: 100%; height: 620px;">
		<div data-options="region:'center',title:'管理员',iconCls:'icon-ok'">

			<table id="dg" style="width: 100%; height: 554px"
				data-options="rownumbers:true,
                singleSelect:true, autoRowHeight:false,  pagination:true, fitColumns:true,  striped:true,  checkOnSelect:false,
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
					<a href="javascript:void(0)"
						onclick="window.parent.mainPlatform._createWindows('添加用户','${basePath}/sys/user/adduser.html','icon-add','addusere');"
						class="easyui-linkbutton" data-options="iconCls:'icon-add'">
						新增</a> <a href="javascript:void(0)" onclick="editUser();"
						class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑用户</a>
					<a href="#" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'">重置密码</a>
				</div>
			</div>

		</div>
		<!-- 显示角色列表 -->
		<div  data-options="region:'east',split:true,collapsed:true,"
			title="角色设置" style="width: 300px;">
			<div style="padding: 5px 0; text-align: right;">
				<a href="#" onclick="getRoleList();" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'">分配</a> 
					<a href="#" onclick="delUserRole();" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'">移除</a>

			</div>
			<table id="role_dg" class="easyui-datagrid"
				data-options="method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
				<thead>
					<tr>
						<th data-options="field:'name'" width="80">角色名称</th>
						<th data-options="field:'usable',align:'right'" width="80">是否可用</th>
						<th data-options="field:'remarks'" width="100">角色描述</th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- 显示角色列表结束 -->

	</div>
	<!-- 打印角色选择界面 -->
	<div id="dialog" class="easyui-dialog" closed="true"></div>

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
				url : "${basePath}/sys/user/getlist.html",
				rownumbers : true,
				onClickRow : function(rowlndex, rowData) {
				 $("#mypanels").layout('expand','east');
					 $("#role_dg").datagrid({
						 url : "${basePath}/sys/user/getUserRoleList.html?userid="+rowData.userid+"",
						 rownumbers : true
					 });
				}
			});
		});
      //删除用户角色
		function delUserRole(){
			var userid = $('#dg').datagrid('getSelected').userid;
			var rowRole=$('#role_dg').datagrid('getSelections');
			if (userid == '') {
				$.messager.show({
					title : '操作提示',
					msg : '请先选择用户后再进行此操作!',
					showType : 'slide'
				});
				return;
			}
			if (rowRole == '') {
				$.messager.show({
					title : '操作提示',
					msg : '请先选择要删除的数据!',
					showType : 'slide'
				});
				return;
			}
			
			var roleIds=[];	
			for(var i=0;i<rowRole.length;i++){
				roleIds.push(rowRole[i]['funid']);
			}
			
			var param = {};
			param['userId'] = userid;
			param['roleIds'] = roleIds.join(',');
			$.ajax({
   				url:"${basePath}/sys/user/deleteUserRole.html",
				type:"post",
				data:param,
				dataType:"json",
				async:false,
		   		//提交成功后回调的函数
             	success: function(data){
             		if(data){
             			if(data.code == 1){
             				$('#role_dg').datagrid('reload');
             				$.messager.show({ title: '提示',msg: data.msg,timeout: 2000,showType: 'slide'});
             			}else{
             				$.messager.show({ title: '错误',msg: data.msg,timeout: 2000,showType: 'slide'});
             			}
             		}
				} 
			});		
			
			
		}
		//子页面调用后刷新列表
		window.top["reload_Abnormal_Monitor"] = function() {
			$("#dg").datagrid('reload');
		};
//刷新用户对应的角色列表
		function reloadRoleList() {
			$("#role_dg").datagrid('reload');
			$("#dialog").dialog('close');
		}
		//编辑用户
		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				if (row.userid == '') {
					$.messager.show({
						title : '操作提示',
						msg : '请先选择用户后再进行此操作!',
						showType : 'slide'
					});
					return;
				}
				var index = $('#dg').datagrid('getRowIndex', row);
				window.parent.mainPlatform._createWindows("编辑用户",
						"${basePath}/sys/user/edituser.html?userid="
								+ row.userid + "&index=" + index, "icon-add",
						'edit');
			} else {
				$.messager.show({
					title : '操作提示',
					msg : '请选择要操作的数据!',
					timeout : 2000,
					showType : 'slide'
				});
			}
		}
		//打开选择角色界面
		function getRoleList() {
			var row = $('#dg').datagrid('getSelected');
			if (row == null || row == '') {
				$.messager.show({
					title : '操作提示',
					msg : '请先选择用户后再进行此操作!',
					showType : 'slide'
				});
				return;
			}
			var userid = row.userid;
			var url = "${basePath}/sys/role/role.html?userid=" + userid + "";
			$("#dialog")
					.dialog({
								title : '选择角色',
								width : 800,
								height : 400,
								queryParames : {
									userid : row.userid
								},
								modal : true,
								content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/sys/role/role.html?userid="
										+ userid
										+ "' style='width:100%; height:100%; display:block;'></iframe>"
							}).dialog('open');
		}
	</script>

</body>
</html>