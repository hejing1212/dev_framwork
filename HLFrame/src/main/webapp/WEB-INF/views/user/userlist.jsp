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
<link href="${basePath}/sys/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/sys/easyui/darkblue/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath}/sys/easyui/darkblue/icon.css">
<link rel="stylesheet" href="${basePath}/sys/css/providers.css">

</head>
<body>
	<div class="container">
		<table id="dg" style="width: 100%; height: 554px" title="全体供应商列表" data-options="  rownumbers:true,
                singleSelect:false, autoRowHeight:false,  pagination:true, fitColumns:true,  striped:true,  checkOnSelect:false,
                selectOnCheck:false, collapsible:true,  toolbar:'#tb',  pageSize:10">
			<thead>
				<tr>
					<th field="user_id" width="110">用户编号</th>
					<th field="org_id" width="226">所属机构</th>
					<th field="no" width="112">工号</th>
					<th field="name" width="170">真实姓名</th>
					<th field="email" width="130">邮箱</th>
					<th field="phone" width="136">联系电话</th>
					<th field="status" width="120">状态</th>
					<th field="remarks" width="105">备注</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="padding: 0 30px;">
			供应商编码: <input class="easyui-textbox" type="text" name="code"
				style="width: 166px; height: 35px; line-height: 35px;"></input>
			供应商名称: <input class="easyui-textbox" type="text" name="name"
				style="width: 166px; height: 35px; line-height: 35px;"></input> <a
				href="#" class="easyui-linkbutton" iconCls="icon-search"
				data-options="selected:true">查询</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-reload">重置</a>
		</div>
	</div>

	<script type="text/javascript" src="${basePath}/sys/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}/sys/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath}/sys/easyui/easyui-lang-zh_CN.js"></script>

	<script type="text/javascript">
		(function($) {
			function pagerFilter(data) {
				if ($.isArray(data)) { // is array
					data = {
						total : data.length,
						rows : data
					}
				}
				var target = this;
				var dg = $(target);
				var state = dg.data('datagrid');
				var opts = dg.datagrid('options');
				if (!state.allRows) {
					state.allRows = (data.rows);
				}
				if (!opts.remoteSort && opts.sortName) {
					var names = opts.sortName.split(',');
					var orders = opts.sortOrder.split(',');
					state.allRows
							.sort(function(r1, r2) {
								var r = 0;
								for (var i = 0; i < names.length; i++) {
									var sn = names[i];
									var so = orders[i];
									var col = $(target).datagrid(
											'getColumnOption', sn);
									var sortFunc = col.sorter
											|| function(a, b) {
												return a == b ? 0 : (a > b ? 1
														: -1);
											};
									r = sortFunc(r1[sn], r2[sn])
											* (so == 'asc' ? 1 : -1);
									if (r != 0) {
										return r;
									}
								}
								return r;
							});
				}
				var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
				var end = start + parseInt(opts.pageSize);
				data.rows = state.allRows.slice(start, end);
				return data;
			}

			var loadDataMethod = $.fn.datagrid.methods.loadData;
			var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
			$.extend(
							$.fn.datagrid.methods,
							{
								clientPaging : function(jq) {
									return jq.each(function() {
										var dg = $(this);
										var state = dg.data('datagrid');
										var opts = state.options;
										opts.loadFilter = pagerFilter;
										var onBeforeLoad = opts.onBeforeLoad;
										opts.onBeforeLoad = function(param) {
											state.allRows = null;
											return onBeforeLoad.call(this,
													param);
										}
										var pager = dg.datagrid('getPager');
										pager.pagination({
											onSelectPage : function(pageNum,
													pageSize) {
												opts.pageNumber = pageNum;
												opts.pageSize = pageSize;
												pager.pagination('refresh', {
													pageNumber : pageNum,
													pageSize : pageSize
												});
												dg.datagrid('loadData',state.allRows);
											}
										});
										$(this).datagrid('loadData',state.data);
										if (opts.url) {
											$(this).datagrid('reload');
										}
									});
								},
								loadData : function(jq, data) {
									jq
											.each(function() {
												$(this).data('datagrid').allRows = null;
											});
									return loadDataMethod.call(
											$.fn.datagrid.methods, jq, data);
								},
								deleteRow : function(jq, index) {
									return jq
											.each(function() {
												var row = $(this).datagrid(
														'getRows')[index];
												deleteRowMethod.call(
														$.fn.datagrid.methods,
														$(this), index);
												var state = $(this).data(
														'datagrid');
												if (state.options.loadFilter == pagerFilter) {
													for (var i = 0; i < state.allRows.length; i++) {
														if (state.allRows[i] == row) {
															state.allRows
																	.splice(i,
																			1);
															break;
														}
													}
													$(this).datagrid(
															'loadData',
															state.allRows);
												}
											});
								},
								getAllRows : function(jq) {
									return jq.data('datagrid').allRows;
								}
							})
		})(jQuery);

		function getData() {
			var rows = [];
			for (var i = 1; i <= 800; i++) {
				rows.push({
					code : '10695',
					name : '南京天泽星网股份有限公司',
					level : '正式',
					provide : '光纤通信设备配件',
					full : '√',
					issubmit : '√',
					status : '已审核',
					note : '-'
				});
			}
			alert(rows);
			return rows;
			
		}

		$(function() {
			$('#dg').datagrid({
				data : getData()
			}).datagrid('clientPaging');
		});
	</script>

</body>
</html>