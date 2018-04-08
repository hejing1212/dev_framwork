<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/dict.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form id="save_user" method="post" enctype="multipart/form-data">
			<div class="content">
				<div class="column">
					<span class="current">基础信息</span>
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">登录名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="username" data-options="required:true,missingMessage:'请输入登录名称！'" /></td>

							<td class="kv-label">登录密码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="password" name="password" data-options="required:true,missingMessage:'请输入用户密码！'"/></td>
						</tr>
						
							<tr>
							<td class="kv-label">手机号</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="mobilephone" data-options="required:true,missingMessage:'请输入手机号！'" /></td>

							<td class="kv-label">真实姓名	</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="realname" data-options="required:true,missingMessage:'请输入真实姓名！'"/></td>
						</tr>						
							<tr> 
							<td class="kv-label">邮箱</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="email" /></td>

							<td class="kv-label">身份证号码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="cardNo" /></td>
						</tr>
						<tr>
							<td class="kv-label">用户类型</td>
							<td class="kv-content">
							<f:dictRadio name="usertype" nodeKey="usertype" required="true" value="1" clazz="easyui-radiobox"/>
							 </td>
                            <td class="kv-label">状态</td>
							<td class="kv-content">
							<f:dictRadio name="status" nodeKey="status" required="true" value="1" clazz="easyui-radiobox"/>
							 </td>							 
						</tr>		
					</tbody>
				</table>
				<div class="column">
					<span class="current">扩展信息</span>
				</div>
				<table class="kv-table">
					<tbody>						
						<tr>
							<td class="kv-label">头像</td>
							<td class="kv-content" >
							<input id="up_portrait"
								name="uploadFile" class="easyui-filebox"
								data-options="buttonText:'选择图片',accept:'image/*',onChange:function(){upload_cover(this,'${basePath}/cb/member/fileUpload.html','portrait')}"
								style="width: 40%;height: 30px;" />
								<input type="hidden" id="portrait" name="portrait"/>
							 </td>
                           <td class="kv-label">QQ号</td>
							<td class="kv-content" >
							<input class="easyui-textbox"
								type="text" name="qq" />
							 </td>
								 
							<tr>
							<td class="kv-label">头像预览</td>
							<td>
							<!-- 图片预览区 --> 
								<img id="image" class="cover-radius" src="${basePath}/static/images/main/user.png" width="100" height="100" style="cursor: pointer;" />
							</td>
							</tr> 
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
	 <!-- 上传文件一定要带上这两个JS -->
<script type="text/javascript" src="${basePath}/static/js/ajaxfileupload.js"></script> 
<script type="text/javascript" src="${basePath}/static/js/upload.js"></script>	
<script type="text/javascript">
	/**
	 * 提交表单
	 */
	function submitFormData() {
		$('#save_user').form('submit', {
			url : "${basePath}/cb/member/saveMember.html",
			onSubmit : function() {
                return $(this).form('validate');
			},
			success : function(data) {
				data = JSON.parse(data);
				if (data.code == '1') {
					$.messager.alert('添加成功', data.msg,'info',function(){
						window.parent.reloadRoleList();
					});
				} else {
					$.messager.alert('添加失败', data.msg, 'error');
				}
			}
		});
	}

	/**
	 *清除表单内容
	 **/
	function clearForm() {
		$('#save_user').form('clear');
	}
</script>
