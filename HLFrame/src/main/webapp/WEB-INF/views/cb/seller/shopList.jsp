<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商家管理</title>
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
		<table id="dg" style="width: 100%; height: 554px" title="店铺管理"
			data-options="  rownumbers:true,
                singleSelect:true, autoRowHeight:false,  pagination:true, fitColumns:true,  striped:true,  checkOnSelect:false,
                selectOnCheck:false, collapsible:true,  toolbar:'#tb',  pageSize:10 ,iconCls:'icon-list'">
			<thead>
				<tr>
					<th field="name" width="160" align="center">档口名称</th> 
					<th field="contacts" width="120" align="center">联系人</th>	
					<th field="telephone" width="120" align="center">联系人</th>	
					<th field="tel" width="120" align="center">档口电话</th>				 
					<th field="address" width="200">详细地址</th>
					<th field="createDate" width="200">创建日期</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="padding: 0 30px;">
			<div class="conditions">
				关键字: <input class="easyui-textbox" type="text" id="queryKey" style="width: 166px; height: 35px; line-height: 35px;"></input>
			        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="javascript:doSearch()" data-options="selected:true">查询</a> 
					
					 
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="window.parent.mainPlatform._createWindows('添加系统商品','${basePath}/cb/seller/shopAdd.html','icon-add','sys_goodsAdd');"> 新增</a>
				<a href="javascript:void(0)" onclick="editShop();" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
				<a href="javascript:void(0)" onclick="delShop();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>  
			 
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
			url : "${basePath}/cb/seller/getShopList.html"
		});
	}
	
		/*
		 * 显示列表
		 */
		$(function() {
			$('#dg').datagrid({
				url : "${basePath}/cb/seller/getShopList.html",
				rownumbers : true,
			});
		});
		//子页面调用后刷新列表
	    window.top["reload_Abnormal_Monitor"]=function(){
	    	$("#dg").datagrid('reload');
	    };
	    
		//编辑商品
		function editShop() {
		    var row = $('#dg').datagrid('getSelected');
		    if (row) {
		        if (row.shopid == '') {
		            $.messager.show({
		                title: '操作提示',
		                msg: '请先选择记录后再进行此操作!',
		                showType: 'slide'
		            }); 
		            return;
		        }
		        var index = $('#dg').datagrid('getRowIndex', row);
		        window.parent.mainPlatform._createWindows("编辑用户",
						"${basePath}/cb/seller/shopEdit.html?shopid="
								+ row.shopid + "&index=" + index, "icon-edit",
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
		 
		//删除商品
		function delShop(){ 
			var row = $('#dg').datagrid('getSelected');
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
			    param['categoryId'] =row.goodsId;
			$.ajax({
   				url:"${basePath}/cb/goods/delGoods.html",
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
	 
	</script>

</body>
</html>