<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>数据字典添加</title>
<link href="${basePath}/static/css/base.css" rel="stylesheet">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/easyui.css">
<link rel="stylesheet"
	href="${basePath}/static/easyui/darkblue/icon.css">
<link href="${basePath}/static/css/basic_info.css" rel="stylesheet">
<style type="text/css">
.th{border:1px #cccccc solid; text-align: center; height: 30px;background-color: #d8dbdd;}
.td{border:1px #cccccc solid;text-align: center;height: 30px;max-height: 100px;}
.table{border-collapse:collapse;}
</style>
</head>
<body>
	<div class="container">
		<div class="content">		   
			<h2 style="text-align: center;font-size: 20px;">${db.schema}-数据库字典</h2>
			<c:forEach items="${db.tables}" varStatus="status" var="table">
				<div class="column">
					<span class="current"><h3>${table.key}&nbsp;&nbsp;&nbsp;备注:${table.value[0].tableComment}</h3></span>
				</div>
			
				<table class="table" style="width:100%">
					<thead>
						<tr>
							<th  class="th" width="30" style="height: 35px;">序号</th>
							<th class="th" width="120" align="center">字段名</th>
							<th class="th" width="80" align="center">数据类型</th>
							<th class="th" width="80" align="center">默认值</th>
							<th class="th" width="50" align="center">允许非空</th>
							<th class="th" width="50" align="center">其他选项</th>
							<th class="th" width="50" align="center">主键约束</th>
							<th class="th" width="260" align="center">备注</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${table.value}" var="columns" varStatus="s2">
							<tr>
								<td  class="td">${columns.ordinalPosition}</td>
								<td class="td">${columns.columnName}</td>
								<td class="td">${columns.columnType}</td>
								<td class="td">${columns.columnDefault}</td>
								<td class="td">${columns.isNullable}</td>
								<td class="td">${columns.extra}</td>
								<td class="td">${columns.columnKey}</td>
								<td class="td" style="text-align: left">${columns.columnComment}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:forEach>
		</div>
	</div>
 
</body>
</html>
<script type="text/javascript"
	src="${basePath}/static/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${basePath}/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${basePath}/static/easyui/easyui-lang-zh_CN.js"></script>

