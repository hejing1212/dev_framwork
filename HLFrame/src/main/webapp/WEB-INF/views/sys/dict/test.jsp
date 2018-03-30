<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/dict.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<f:dicK2V nodeKey="sex" key="1"></f:dicK2V>

<br/><br/><br/>
<f:dict name="sex" nodeKey="sex" required="true" value="1" clazz="avccc" style="width:150px" ></f:dict>
<br/><br/><br/>
<f:dicK2Json   itemCode="sex"  ></f:dicK2Json>
</body>
</html>

<script type="text/javascript">
var map={};
var mapjon={};
mapjon["1"]="张三";
mapjon["2"]="张AAA";
mapjon["3"]="张BBB";
mapjon["4"]="张CCC";

var mapjson={};
mapjson["1"]="李三";
mapjson["2"]="李DDD";
mapjson["3"]="李EEE";
mapjson["4"]="李FFF";

map["sex"]=mapjon;
map["type"]=mapjson;

alert(Object.keys(map).length);
alert(map["type"]["2"]);


</script>