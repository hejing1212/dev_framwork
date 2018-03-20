 
    <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath"
	value='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath()%>' />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id="form" method="post" action="${basePath}/sys/user/fileUpload.html" enctype="multipart/form-data">
<input id="picture_upload" type="file" name="uploadFile" class="easyui-filebox"
								data-options="buttonText:'选择图片',accept:'image/*'"
								style="width: 50%" />
								
								<input id="uploadfile" type="file" name="uploadfile[]" class="easyui-filebox"
								data-options="buttonText:'选择图片',accept:'image/*'"
								style="width: 50%" />
								<button type="submit">提交</button>
</form>
</body>
</html>