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
		<div data-options="region:'center',title:'菜单管理',iconCls:'icon-ok'">
			<div id="tb" style="padding: 0 30px;">
				<div class="conditions">
					<a href="${basePath}/sys/menu/addmenu.html"
						class="easyui-linkbutton" data-options="iconCls:'icon-add'">
						新增</a> <a href="javascript:void(0)" onclick="editMenu();"
						class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>

					名称: <input class="easyui-textbox" type="text" name="code"
						style="width: 166px; height: 35px; line-height: 35px;"></input>
					权限标识: <input class="easyui-textbox" type="text" name="name"
						style="width: 166px; height: 35px; line-height: 35px;"></input> <a
						href="#" class="easyui-linkbutton" iconCls="icon-search"
						data-options="selected:true">查询</a> <a href="#"
						class="easyui-linkbutton" iconCls="icon-reload">重置</a>
				</div>
			</div>

			<table id="menuTree" title="菜单管理列表" class="easyui-treegrid"
				style="width: 100%; height: 600px"
				data-options="
url: '${basePath}/sys/menu/getMenuList.html',method: 'get',rownumbers: true,idField: 'menuid',treeField: 'name', toolbar:'#tb'">
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
		<!-- 显示功能项列表 -->
		<div data-options="region:'east',split:true,collapsed:true,"
			title="功能编辑" style="width: 300px;">
			<div style="padding: 5px 0; text-align: right;">
				<a href="#" onclick="addFun()" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'">添加</a> 
					<a href="#" onclick="editFun()" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'">编辑</a> 
					<a href="#" onclick=";" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'">移除</a>

			</div>
			<table id="function_dg" class="easyui-datagrid"
				data-options="method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
				<thead>
					<tr>
						<th data-options="field:'name'" width="80" align="center">功能名称</th>
						<th data-options="field:'permission'" width="100" align="center">功能命令</th>
						<th data-options="field:'remarks'" width="80" align="center">描述</th>
					</tr>	
				</thead>
			</table>
		</div>
		<!-- 显示功能列表结束 -->
	</div>
	
	<!-- 打印功能添加或编辑界面 -->
	<div id="dialog" class="easyui-dialog" closed="true"></div>
	
	
	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${basePath}/static/easyui/easyui-lang-zh_CN.js"></script>

           <script type="text/javascript">
                $('#menuTree').treegrid({
                 onClickRow : function(rowlndex, rowData) {
				 $("#mypanels").layout('expand','east');
					 $("#function_dg").datagrid({
						 url : "${basePath}/sys/menu/getMenuFunList.html?menuId="+rowlndex.menuid+"",
						 rownumbers : true
					 });
				}
                });
                
                //刷新菜单对应的功能列表
        		function reloadFunList() {
        			$("#function_dg").datagrid('reload');
        			$("#dialog").dialog('close');
        		}
                
                
        		//打开添加功能界面
        		function addFun() {
        			var row = $('#menuTree').treegrid('getSelected');
        			if (row == null || row == '') {
        				$.messager.show({
        					title : '操作提示',
        					msg : '未选择操作数据!',
        					showType : 'slide'
        				});
        				return;
        			}
        			var menuid = row.menuid;
        			$("#dialog") .dialog({
        								title : '功能添加',
        								width : 800,
        								height : 450,
        								queryParams : {
        									menuid : menuid
        								},
        								modal : true,
        								top:$(document).scrollTop()+($(window).height()-250)*0.3,
        							    left:$(document).scrollLeft()+($(window).width()-800)*0.5,
        							    
        								content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/sys/menu/showFunction.html?menuid="
        										+ menuid
        										+ "' style='width:100%; height:100%; display:block;'></iframe>"
        							}).dialog('open');
        			 
        		     }
        		
        		
        		//编辑选中的功能信息
        		function editFun() {
        			var row = $('#function_dg').datagrid('getSelected');
        			if (row == null || row == '') {
        				$.messager.show({
        					title : '操作提示',
        					msg : '未选择操作数据!',
        					showType : 'slide'
        				});
        				return;
        			}
        			var funid = row.funid;
        			$("#dialog") .dialog({
        								title : '功能编辑',
        								width : 800,
        								height : 450,  								 
        								modal : true,
        								top:$(document).scrollTop()+($(window).height()-250)*0.3,
        							    left:$(document).scrollLeft()+($(window).width()-800)*0.5,
        								content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/sys/menu/showFunction.html?funid="+funid+"'  style='width:100%; height:100%; display:block;'></iframe>"
        							}).dialog('open');
        		}
        		
        		//删除菜单对应功能
        		function delUserFun(){
        			var row = $('#function_dg').datagrid('getSelected');
        			if (row == null || row == '') {
        				$.messager.show({
        					title : '操作提示',
        					msg : '未选择操作数据!',
        					showType : 'slide'
        				});
        				return;
        			}
        			var funid = row.funid;
        			var param = {};
        			param['funid'] = funid;
        			$.ajax({
           				url:"${basePath}/sys/menu/deleteMenuFun.html",
        				type:"post",
        				data:param,
        				dataType:"json",
        				async:false,
        		   		//提交成功后回调的函数
                     	success: function(data){
                     		if(data){
                     			if(data.code == 1){
                     				$('#function_dg').datagrid('reload');
                     				$.messager.show({ title: '提示',msg: data.msg,timeout: 2000,showType: 'slide'});
                     			}else{
                     				$.messager.show({ title: '错误',msg: data.msg,timeout: 2000,showType: 'slide'});
                     			}
                     		}
        				} 
        			});		
        			
        			
        		}

         </script>				
</body>
</html>