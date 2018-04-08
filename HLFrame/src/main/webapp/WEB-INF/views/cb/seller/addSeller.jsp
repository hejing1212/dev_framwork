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
		<form id="save_user" method="post" >
			<div class="content">
				<div class="column">
					<span class="current">基础信息</span>
				</div>
				<table class="kv-table">
					<tbody>
						<tr>
							<td class="kv-label">名称</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="name" data-options="required:true,missingMessage:'请输入角色名称！'" style="height:30px"/></td>

							<td class="kv-label">联系人</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="contacts" data-options="required:true,missingMessage:'请输入角色名称！'" style="height:30px"/></td>
						</tr>
						<tr>
							<td class="kv-label">联系电话</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="telephone" data-options="required:true,missingMessage:'请输入角色名称！'" style="height:30px"/></td>

							<td class="kv-label">公司电话</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="tel" data-options="required:true,missingMessage:'请输入角色名称！'" style="height:30px"/></td>
						</tr>
						<tr>
							<td class="kv-label">证件号码</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="idNo" data-options="required:true,missingMessage:'请输入角色名称！'"  style="height:30px"/></td>

							<td class="kv-label">LOGO</td>
							<td class="kv-content">
							<input id="up_portrait"
								name="uploadFile" class="easyui-filebox"
								data-options="buttonText:'选择图片',accept:'image/*',onChange:function(){upload_cover(this,'${basePath}/cb/seller/fileUpload.html','logo')}"
								style="width: 40%;height: 30px;" />
								<input type="hidden" id="logo" name="logo"/>
								
								
							</td>
						</tr>
						<tr>
							<td class="kv-label">状态</td>
							<td class="kv-content">
							<f:dictRadio name="status" nodeKey="user_status" required="true" value="1" clazz="easyui-radiobox"/>
							</td>

							<td class="kv-label">采购商</td>
							<td class="kv-content">
								 <f:dictRadio name="purchase" nodeKey="status" required="true" value="1" clazz="easyui-radiobox"/>
								</td>
						</tr>
						
						<tr>
							<td class="kv-label">批发商</td>
							<td class="kv-content">
                               <f:dictRadio name="wholesale" nodeKey="status" required="true" value="1" clazz="easyui-radiobox"/>
							 </td>
							<td class="kv-label">零售商</td>
							<td class="kv-content">
							<f:dictRadio name="retail" nodeKey="status" required="true" value="1" clazz="easyui-radiobox"/>
                              </td>
						</tr>
						<tr>
							<td class="kv-label">所在区域</td>
							<td class="kv-content">
							<select class="easyui-combotree" name="region"  id="areaTree" data-options="required:true" style="height:30px;width:30%"/>
							 <input type="hidden" id="area_code" name="province"/>
							 </td>
							<td class="kv-label">详细地址</td>
							<td class="kv-content">
                              <input class="easyui-textbox"
								type="text" id="address" name="address" data-options="required:true,missingMessage:'详细地址！'" style="height:30px;width:300px;" />
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
							<td class="kv-label">经度</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="longitude"  style="height:30px" /></td>

							<td class="kv-label">纬度</td>
							<td class="kv-content"><input class="easyui-textbox"
								type="text" name="latitude" style="height:30px" /></td>
						</tr>
						<tr>
							<td class="kv-label">商家介绍</td>
							<td class="kv-content"  >
							<textarea rows="5" cols="50" name="introduce"></textarea></td>
							<td class="kv-label">图片预览</td>
							<td class="kv-content"  >
							  <img id="image" class="cover-radius" src="${basePath}/static/images/main/user.png" width="60" height="60" style="cursor: pointer;" />
							</td>
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
	<script type="text/javascript" src="${basePath}/static/js/ajaxfileupload.js"></script> 
<script type="text/javascript" src="${basePath}/static/js/upload.js"></script>	
<script type="text/javascript">
	/**
	 * 提交表单
	 */
	function submitFormData() {
		$('#save_user').form('submit', {
			url : "${basePath}/cb/seller/saveSeller.html",
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
	
	/***
	*设置父级菜单选择树
	**/
	$(function(){ 
		$("#areaTree").combotree({
			url:"${basePath}/sys/area/getAreaTreeList.html",
			onLoadSuccess:function(node, data){
				$("#parent_id").combotree('setValue','0');
			},			 			    
			onBeforeExpand:function(row){    //每次展开前都会调用
	             //动态设置展开查询的url  
	           var url = "${basePath}/sys/area/getAreaTreeList.html?parentId="+row.id; 
	           $("#areaTree").combotree("tree").tree("options").url = url;  
	           return true;  //返回false表示停止展开节点    
	          },
	        onExpand : function(row){ //每次展后都会调用;传入的row已经包含了 children
	        	var tree = $('#areaTree').combotree('tree'); 
	        	var children = tree.tree('getChildren', row.id);
	           if(children.length<=0){ 
	               row.leaf=true;
	               $("#areaTree").combotree('refresh', row.id);
	           }
	        },
	        onSelect: function (item){
	        	 var parent = item;  
                 var tree = $('#areaTree').combotree('tree');  
                 var text = new Array();  
                 var value = new Array();
                 do {  
                	 text.unshift(parent.text);  
                	 value.unshift(parent.id);
                     var parent = tree.tree('getParent', parent.target);  
                 } while (parent);  
                 var textStr="";  
                 var valStr="";
                 for (var i = 0; i < text.length; i++) {  
                	 textStr += text[i];  
                	 valStr+=value[i];
                     if (i < text.length - 1) {  
                    	 textStr += ' ';  
                    	 valStr+='-';
                     }  
                 }  

                 $("#address").textbox("setValue",textStr)  
                 $('#area_code').val(valStr);
	        },
	        onDblClickRow: function(row){
	         
	         }
		});
	})
</script>
