<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>数据字典管理</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/easyui.css">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/icon.css">
<link rel="stylesheet" href="${basePath}/static/css/providers.css">

</head>
<body>
	<div id="mypanels" class="easyui-layout" style="width: 100%; height: 620px;">
		<div data-options="region:'center',title:'数据字典管理',iconCls:'icon-list'">

			<table id="dg" style="width: 100%; height: 554px"
				data-options="rownumbers:true,
                singleSelect:true, autoRowHeight:false,  pagination:true, fitColumns:true,  striped:true,  checkOnSelect:false,
                selectOnCheck:false, collapsible:true,  toolbar:'#tb',  pageSize:10 ">
				<thead>
					<tr>
						<th field="id" width="160" align="center">ID</th>
					<th field="dictName" width="120" align="center">名称</th>
					<th field="dictCode" width="120" align="center">编码</th>
					<th field="remarks" width="200">备注</th>
					</tr>
				</thead>
			</table>

			<div id="tb" style="padding: 0 30px;">
				<div class="conditions">
					字典名称: <input class="easyui-textbox" type="text" name="dict_name"
					style="width: 166px; height: 35px; line-height: 35px;"></input>
			  <a href="#" class="easyui-linkbutton" iconCls="icon-search"
					data-options="selected:true">查询</a> 
					
					
					<shiro:hasPermission name="sys:user:adduser">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addDict()"> 新增</a>
				 </shiro:hasPermission>
				 <shiro:hasPermission name="sys:user:edituser">
						 <a href="javascript:void(0)" onclick="editDict();" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
				 </shiro:hasPermission>	
				 
				  <shiro:hasPermission name="sys:user:edituser">
						 <a href="javascript:void(0)" onclick="editDict();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
				 </shiro:hasPermission>	
				</div>
				 
			</div>

		</div>
		<!-- 显示角色列表 -->
		<div  data-options="region:'east',split:true"
			title="角色设置" style="width: 40%;">
			<div style="padding: 5px 0; text-align: right;">
			<shiro:hasPermission name="sys:role:addAuthorize">
				<a href="#" onclick="addDictItem()" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'">新增</a> 
			 </shiro:hasPermission>
			 <shiro:hasPermission name="sys:user:userRoleRm">
					<a href="#" onclick="editDictItem();" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'">修改</a>
            </shiro:hasPermission>
			</div>
			<table id="dict_item_dg" class="easyui-datagrid"
				data-options="method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
				<thead>
					<tr>
						<th data-options="field:'itemName'" width="80">键值名称</th>
						<th data-options="field:'itemValue',align:'right'" width="80">键值</th>
						<th data-options="field:'remarks'" width="100">描述</th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- 显示字典列表结束 -->

	</div>
	<!-- 打开字典 -->
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
				url : "${basePath}/sys/dict/getDictList.html",
				rownumbers : true,
				onClickRow : function(rowlndex, rowData) {
				 $("#mypanels").layout('expand','east');
					 $("#dict_item_dg").datagrid({
						 url : "${basePath}/sys/dict/getDictItemList.html?dictId="+rowData.id+"",
						 rownumbers : true
					 });
				}
			});
		});
		
		
      //删除字典项
		function delDictItem(){
			var dictId = $('#dg').datagrid('getSelected').id;
			var dictItem=$('#dict_item_dg').datagrid('getSelections');
			if (dictId == '') {
				$.messager.show({
					title : '操作提示',
					msg : '请先选择用户后再进行此操作!',
					showType : 'slide'
				});
				return;
			}
			if (dictItem == '') {
				$.messager.show({
					title : '操作提示',
					msg : '请先选择要删除的数据!',
					showType : 'slide'
				});
				return;
			}
			
			var itemId=[];	
			for(var i=0;i<dictItem.length;i++){
				itemId.push(dictItem[i]['roleid']);
			}
			
			var param = {};
			param['userId'] = dictId;
			param['roleIds'] = itemId.join(',');
			$.ajax({
   				url:"${basePath}/sys/dict/delDictItem.html",
				type:"post",
				data:param,
				dataType:"json",
				async:false,
		   		//提交成功后回调的函数
             	success: function(data){
             		if(data){
             			if(data.code == 1){
             				$('#dict_item_dg').datagrid('reload');
             				$.messager.show({ title: '提示',msg: data.msg,timeout: 2000,showType: 'slide'});
             			}else{
             				$.messager.show({ title: '错误',msg: data.msg,timeout: 2000,showType: 'slide'});
             			}
             		}
				} 
			});								
		}
      
      
      
		//子页面调用后刷新列表
		 function reloadDictList() {
			$("#dg").datagrid('reload');
			$("#dialog").dialog('close');
		};
         //刷新用户对应的角色列表
		function reloadDictItemList() {
			$("#dict_item_dg").datagrid('reload');
			$("#dialog").dialog('close');
		}
         
         
		//添加字典
		function addDict() {
			$("#dialog")
					.dialog({
								title : '编辑数据字典',
								width : 800,
								height : 400,
								modal : true,
								top:$(document).scrollTop()+($(window).height()-250)*0.3,
							    left:$(document).scrollLeft()+($(window).width()-800)*0.5,
								content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/sys/dict/dictAdd.html' style='width:100%; height:100%; display:block;'></iframe>"
							}).dialog('open');
		}
		
		//修改字典
		function editDict() {
			var row = $('#dg').datagrid('getSelected');
			if (row == null || row == '') {
				$.messager.show({
					title : '操作提示',
					msg : '请先选择数据后再进行此操作!',
					showType : 'slide'
				});
				return;
			}
			var id = row.id;
			$("#dialog")
					.dialog({
								title : '编辑数据字典',
								width : 800,
								height : 400,
								queryParames : {
									dictId : id
								},
								modal : true,
								top:$(document).scrollTop()+($(window).height()-250)*0.3,
							    left:$(document).scrollLeft()+($(window).width()-800)*0.5,
								content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/sys/dict/dictEdit.html?dictId="
										+ id
										+ "' style='width:100%; height:100%; display:block;'></iframe>"
							}).dialog('open');
		          }
				
		
		//添加键值项
		function addDictItem(){
			var row = $('#dg').datagrid('getSelected');
			if (row == null || row == '') {
				$.messager.show({
					title : '操作提示',
					msg : '请先选择数据后再进行此操作!',
					showType : 'slide'
				});
				return;
			}
			var id = row.id;
			$("#dialog").dialog({
						title : '编辑数据字典',
						width : 800,
						height : 400,
						modal : true,
						top:$(document).scrollTop()+($(window).height()-250)*0.3,
					    left:$(document).scrollLeft()+($(window).width()-800)*0.5,
						content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/sys/dict/dictItemAdd.html?dictId="+id+"' style='width:100%; height:100%; display:block;'></iframe>"
					}).dialog('open');
		}
		
		//修改键值项
		function editDictItem(){
			var row = $('#dict_item_dg').datagrid('getSelected');
			if (row == null || row == '') {
				$.messager.show({
					title : '操作提示',
					msg : '请先选择数据后再进行此操作!',
					showType : 'slide'
				});
				return;
			}
			$("#dialog").dialog({
						title : '编辑数据字典',
						width : 800,
						height : 400,
						modal : true,
						top:$(document).scrollTop()+($(window).height()-250)*0.3,
					    left:$(document).scrollLeft()+($(window).width()-800)*0.5,
						content : "<iframe scrolling='auto' frameborder='0' src='${basePath}/sys/dict/dictItemEdit.html?itemId="+row.id+"' style='width:100%; height:100%; display:block;'></iframe>"
					}).dialog('open');
		}
	</script>

</body>
</html>