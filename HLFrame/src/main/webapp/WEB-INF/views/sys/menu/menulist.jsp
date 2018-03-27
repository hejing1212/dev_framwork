<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

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
	<div id="mypanels" class="easyui-layout"
		style="width: 100%; height: 620px;">
		<div data-options="region:'center'">
			<div id="tb" style="padding: 0 30px;">
				<div class="conditions">
					<shiro:hasPermission name="sys:menu:menuquery">
					名称: <input class="easyui-textbox" type="text" name="name" style="width: 166px; height: 35px; line-height: 35px;"></input>
					 <a href="#" class="easyui-linkbutton" iconCls="icon-search"
						data-options="selected:true">查询</a>
					</shiro:hasPermission>		
					<shiro:hasPermission name="sys:menu:menuAdd">
						<a href="javascript:void(0)" onclick="window.parent.mainPlatform._createWindows('添加资源','${basePath}/sys/menu/menuAdd.html','icon-add','menuAdd');"
						class="easyui-linkbutton" data-options="iconCls:'icon-add'">
						新增</a> 
						</shiro:hasPermission>	
						<shiro:hasPermission name="sys:menu:menuEdit">
						<a href="javascript:void(0)" onclick="editMenu();"
						class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a> 
						</shiro:hasPermission>	
						<shiro:hasPermission name="sys:menu:deleteMenu">
						<a href="javascript:void(0)" onclick="delMenu();"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
						</shiro:hasPermission>	
				</div>
			</div>

			<table id="menuTree" title="菜单管理列表" class="easyui-treegrid" style="width: 100%; height: 600px" data-options="
url: '${basePath}/sys/menu/getMenuList.html',method: 'get',rownumbers: true,idField: 'menuid',treeField: 'name', toolbar:'#tb',iconCls:'icon-list'">
				<thead>
					<tr>
						<th data-options="field:'name'" width="150" align="center">名称</th>
						<th data-options="field:'url'" width="220" align="center">菜单路径</th>
						<th data-options="field:'isshow',formatter:SetDictNameMap" width="80" align="center">是否显示</th>
						<th data-options="field:'current',formatter:SetDictNameMap" width="80" align="center">默认展开</th>
						<th data-options="field:'type',formatter:SetDictNameMap" width="80" align="center">资源类型</th>
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
			 <shiro:hasPermission name="sys:menu:fun:add">
				<a href="#" onclick="addFun()" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'">添加</a>
			 </shiro:hasPermission>	
			<shiro:hasPermission name="sys:menu:fun:showFunction">		
					 <a href="#" onclick="editFun()" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'">编辑</a> 
			 </shiro:hasPermission>	
			 <shiro:hasPermission name="sys:menu:fun:deleteMenuFun">
					<a href="#" onclick="delMenuFun();" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'">移除</a>
			 </shiro:hasPermission>	

			</div>
			<table id="function_dg" class="easyui-datagrid"
				data-options="method:'get',border:false,singleSelect:false,fit:true,fitColumns:true">
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
   <script type="text/javascript" src="${basePath}/static/js/dict.js"></script>
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
                
        		//编辑菜单内容
        		function editMenu() {
        			var row = $('#menuTree').treegrid('getSelected');
        		    if (row) {
        		        if (row.roleid == '') {
        		            $.messager.show({
        		                title: '操作提示',
        		                msg: '请先选择记录后再进行此操作!',
        		                showType: 'slide'
        		            }); 
        		            return;
        		        }
        		        var index = $('#menuTree').treegrid('getRowIndex', row);
        		        window.parent.mainPlatform._createWindows("编辑菜单",
        						"${basePath}/sys/menu/menuEdit.html?menuid="
        								+ row.menuid + "&index=" + index, "icon-edit",
        						'menuEdit');
        		    } else {
        		        $.messager.show({
        		            title: '操作提示',
        		            msg: '请选择要操作的数据!',
        		            timeout: 2000,
        		            showType: 'slide'
        		        });
        		    }
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
        		
        		
        		//删除菜单
        		function delMenu(){ 
        			var row = $('#menuTree').treegrid('getSelected');
        			if (row == null || row == '') {
        				$.messager.show({
        					title : '操作提示',
        					msg : '未选择操作数据!',
        					showType : 'slide'
        				});
        				return;
        			}
        			 
        			$.messager.confirm('系统提示','删除后不可恢复,您确定要删除选中的记录吗?',function(r){
        				var param = {};
        			    param['menuId'] =row.menuid;
        			$.ajax({
           				url:"${basePath}/sys/menu/deleteMenu.html",
        				type:"post",
        				data:param,
        				dataType:"json",
        				async:false,
        		   		//提交成功后回调的函数
                     	success: function(data){
                     		if(data){
                     			if(data.code == 1){
                     				$('#menuTree').treegrid('reload');
                     				$.messager.show({ title: '提示',msg: data.msg,timeout: 2000,showType: 'slide'});
                     			}else{
                     				$.messager.show({ title: '错误',msg: data.msg,timeout: 2000,showType: 'slide'});
                     			}
                     		}
        				} 
        			});		
        		 });	
        		}
        		
        		//删除菜单对应功能
        		function delMenuFun(){
        			var row = $('#function_dg').datagrid('getSelections');
        			if (row == null || row == '') {
        				$.messager.show({
        					title : '操作提示',
        					msg : '未选择操作数据!',
        					showType : 'slide'
        				});
        				return;
        			}
        			 
        			$.messager.confirm('系统提示','删除后不可恢复,您确定要删除选中的记录吗?',function(r){
        				var funids=[];	
        				for(var i=0;i<row.length;i++){
        					funids.push(row[i]['funid']);
        				}
        				
        				var param = {};
        			    param['funid'] = funids.join(',');
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
        		 });
        			
        		}

         </script>
</body>
</html>