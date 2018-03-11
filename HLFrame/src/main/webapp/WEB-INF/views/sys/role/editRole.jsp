<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>角色编辑</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/easyui.css">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/icon.css">
<link href="${basePath}/static/css/basic_info.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form id="form_role" method="post">
			<div class="content">
				<div class="column">
					<span class="current">基础信息</span>
					<input type="hidden" id="roleid" name="roleid" value="${role.roleid}">
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">角色名称</td>
							<td class="kv-content"><input class="easyui-textbox" value="${role.name}"
								type="text" name="name" data-options="required:true,missingMessage:'请输入角色名称！'" /></td>
							<td class="kv-label">英文名称</td>
							<td class="kv-content"><input class="easyui-textbox" value="${role.code}"
								type="text" name="code" data-options="required:true,missingMessage:'请输入角色名称！'"/></td>
						</tr>
						<tr>
							<td class="kv-label">是否系统数据</td>
							<td class="kv-content">
							<input type="radio" name="is_sys" value="1" <c:if test="${role.is_sys eq 1 }">checked="checked" </c:if> class="easyui-radiobox">是
                               <input type="radio" name="is_sys" value="0" <c:if test="${role.is_sys eq 0 }">checked="checked" </c:if> class="easyui-radiobox" > 否
							 </td>
							<td class="kv-label">是否可用</td>
							<td class="kv-content">
                              <input type="radio" name="usable" value="1" <c:if test="${role.usable eq 1 }">checked="checked" </c:if> class="easyui-radiobox">是
                               <input type="radio" name="usable" value="0" <c:if test="${role.usable eq 0 }">checked="checked" </c:if> class="easyui-radiobox"> 否</td>
						</tr>
					</tbody>
				</table>
				<div class="column">
					<span class="current">扩展信息</span>
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">备注</td>
							<td class="kv-content" colspan="5">
							<textarea rows="5" cols="50" name="remarks">${role.remarks}</textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="easyui-panel" style="padding: 10px;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'" onclick="submitFormData()">保存</a>
					&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)"
						class="easyui-linkbutton" data-options="iconCls:'icon-remove'"
						onclick="clearForm()">重 置</a>
				</div>

			</div>
		</form>
	</div>

</body>
</html>
<script type="text/javascript"
	src="${basePath}/static/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	/**
	 * 提交表单
	 */
	function submitFormData(){
	    	var roleid = $("#roleid").val();//选择的当前角色信息id
	  		$("#form_role").form('submit',{
				url:"${basePath}/sys/role/saveRole.html",
				//提交的表单验证
				onSubmit: function(){    
	      			var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;   
	   		}, 
	  			//提交成功后回调的函数
				success: function(data){
			 		var result = eval('(' + data + ')');
	      	 		if(result.code == "1"){
	      	 			$.messager.alert("提示", result.msg  ,"info",function(){
	      	 				window.parent.mainPlatform.parentReloadTabGrid();//关闭当前页面,刷新父datarid
      	 				});
	         	 	} else{
	         			$.messager.alert("操作提示", result.msg ,"info");
	          		}
				} 
			});
	   }
	
	/**
	 *清除表单内容
	 **/
	function clearForm() {
		$('#form_role').form('clear');
	}
</script>
