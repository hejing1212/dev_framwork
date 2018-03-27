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
	<div id="mypanels" class="easyui-layout"
		style="width: 100%; height: 620px;">
		<div data-options="region:'center'">
			<div id="tb" style="padding: 0 30px;">
				<div class="conditions">

					<input type="checkbox" onclick="checkAll();" name="checkall">
					全选 <input type="checkbox" onclick="uncheckAll();" name="checkall">
					取消全选 <a href="javascript:void(0)" onclick="saveAuths();"
						class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-save'">保存</a>

				</div>
			</div>                                                      
			<input type="hidden" id="roleId" name="roleId" value="${roleId}">
			<table id="menuTree" title="菜单管理列表" class="easyui-treegrid"
				style="width: 100%; height: 600px"
				data-options="method: 'get',idField: 'menuid',treeField:'name', toolbar:'#tb',iconCls:'icon-ok'">
				<thead>
					<tr>
						<th data-options="field:'name'" width="180" align="center">名称</th>

						<th data-options="field:'isshow'" width="80" align="center">是否显示</th>
						<th data-options="field:'type'" width="80" align="center">资源类型</th>

						<th data-options="field:'strFun',formatter:formatAuth" width="60%"
							align="left">功能</th>
					</tr>
				</thead>
			</table>
		</div>

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
				 url :"${basePath}/sys/role/getMenuAllList.html",
				 rownumbers : true,
				 onLoadSuccess: function(data){	//加载数据成功后执行事件
					empowerment();
		         }
       });
	
		//菜单权限格式化
		function formatAuth(value, text) {
			if (value) {
				var arr = value.split(",");
				var innerHtml = "";
				for (var i = 0; i < arr.length; i++) {
					var id = arr[i].split(":")[0];
					innerHtml += "<input name='funName' id="
							+ id
							+ " onclick='javascript:changCssAndProp(this);' type='checkbox' value="
							+ id + ">" + "<label for="+id+">"
							+ arr[i].split(":")[1] + "</label>";
				}
				return innerHtml;
			}
			return "";
		}

		//根据选中的权限改变样式及添加属性
		function changCssAndProp(eventObj) {
			$(eventObj).next("span").addClass("spanStyle");
			$(eventObj).attr("checked", "checked");
			var checked = $(eventObj).is(':checked');
			if (checked) { //点击复选框选中

			} else { //点击复选框取消选中
				$(eventObj).removeAttr("checked");
				$(eventObj).next("span").removeClass("spanStyle");

			}
		}

		//全选
		function checkAll() {
			$('input[name="funName"]').each(function(i, v) {
				$(this).prop('checked', true);
				$(this).attr('checked', 'checked');
			});
		}
		//全不选
		function uncheckAll() {
			$('input[name="funName"]').each(function(i, v) {
				$(this).prop('checked', false);
				$(this).removeAttr('checked');
			});
		}

		//保存权限
		function saveAuths() {
			var set_roleId = $("#roleId").val();
			var arrChk = "";
			$($("input[name='funName'][checked]")).each(function() {
				arrChk += this.value + ",";
			});
			var chkFunIds = arrChk.substr(0, arrChk.length - 1);
			if (chkFunIds == null || chkFunIds == "" || chkFunIds == undefined) {
				$.messager.alert("操作提示", "请勾选权限后再继续操作", "info");
			} else {
				$.ajax({
							url : "${basePath}/sys/role/authByRoleSave.html",
							type : "post",
							data : {
								roleId : set_roleId,
								auths : chkFunIds
							},
							dataType : "json",
							//提交成功后回调的函数
							success : function(data) {
								if (data.code == "1") {
									$.messager.alert("操作提示",
											"权限已分配成功, 请重新登录获取新权限", "info");
								} else {
									$.messager.alert("操作提示", data.msg, "info");
								}
							}
						});
			}
		}

		//根据角色查询权限赋权
		function empowerment() {
			var roleId = $("#roleId").val();
			$.ajax({
				url : "${basePath}/sys/role/getRoleFunction.html",
				type : "post",
				data : {
					roleId : roleId
				},
				dataType : "json",
				async : false,
				//提交成功后回调的函数
				success : function(data) {
					if (data) {
						for (var i = 0; i < data.length; i++) {
							var funId = data[i].menu_id + "-" + data[i].fun_id;
							$($("input[name='funName']")).each(function() {
										if (funId == this.value) {
											$(this).attr('checked', true); 
										}
									});
						}
					}
				}
			});
		}
	</script>
</body>
</html>