<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
body{
	margin: 0;
}
.left_nav_div{
	width:200px;
	background-color: #060E2B;
}
.left_nav_div .nav_item{
	height:45px;
	line-height: 45px;
	color:#fff;
	font-size: 15px;
}
.left_nav_div .nav_item:hover{
	background-color:#5A62FF;
}
.left_nav_div .nav_item_div{
	padding: 0 20px;
}
.left_nav_div .nav_item_a{
	padding: 0 40px;
	text-decoration:none;
	display: block;
}

.top_div{
	height: 45px;
	line-height: 45px;
	margin-left: 200px;
	border-bottom: #eee solid 1px;
}
.top_div .client_id_span{
	margin-right: 30px;
	float: right;
}
</style>
<script type="text/javascript">
$(function(){
	resizeDiv();
});

function resizeDiv(){
	var windowWidth=$(window).width();
	var windowHeight=$(window).height();
	var leftNavDiv=$("#left_nav_div");
	leftNavDiv.css("height",windowHeight+"px");
	
	var topDiv=$("#top_div");
	var leftNavWidth=leftNavDiv.css("width");
	leftNavWidth=leftNavWidth.substring(0,leftNavWidth.length-2);
	topDiv.css("width",windowWidth-leftNavWidth+"px");
	topDiv.css("margin-top",-windowHeight+"px");
}
</script>
<title>Insert title here</title>
</head>
<body>
<div class="left_nav_div" id="left_nav_div">
	<div class="nav_item nav_item_div">设备管理</div>
	<a class="nav_item nav_item_a" href="<%=basePath%>deviceMgmt/type/list">设备类型</a>
	<a class="nav_item nav_item_a" href="<%=basePath%>deviceMgmt/device/list">设备查询</a>
	<a class="nav_item nav_item_a">设备台账</a>
	<a class="nav_item nav_item_a">设备参数</a>
</div>
<div class="top_div" id="top_div">
	<span class="client_id_span">admin</span>
</div>
</body>
</html>