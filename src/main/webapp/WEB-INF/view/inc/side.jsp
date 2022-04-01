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
.left_nav_div .nav_item .nav_img{
	width: 21px;
	height: 24px;
	margin-top: 12px;
}
.left_nav_div .nav_item .text_span{
	margin-left: 10px;
	position: absolute;
}
.left_nav_div .nav_item_div{
	padding: 0 10px;
}
.left_nav_div .nav_item_a{
	padding: 0 35px;
	text-decoration:none;
	display: block;
}

.top_div{
	height: 45px;
	line-height: 45px;
	margin-left: 200px;
	background-color: #18459A;
	border-bottom: #eee solid 1px;
}
.top_div .client_id_span{
	margin-right: 30px;
	color:#fff;
	float: right;
}
</style>
<script type="text/javascript">
$(function(){
	resizeSideDiv();
});

function resizeSideDiv(){
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
	<div class="nav_item nav_item_div">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">设备管理</span>
	</div>
	<a class="nav_item nav_item_a" href="<%=basePath%>deviceMgmt/type/list">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">设备类型</span>
	</a>
	<a class="nav_item nav_item_a" href="<%=basePath%>deviceMgmt/device/list">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">设备查询</span>
	</a>
	<a class="nav_item nav_item_a" href="<%=basePath%>deviceMgmt/account/list">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">设备台账</span>
	</a>
	<a class="nav_item nav_item_a" href="<%=basePath%>deviceMgmt/param/list">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">设备参数</span>
	</a>
	<div class="nav_item nav_item_div">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检管理</span>
	</div>
	<a class="nav_item nav_item_a" href="">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检路线</span>
	</a>
	<a class="nav_item nav_item_a" href="">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检区域</span>
	</a>
	<a class="nav_item nav_item_a" href="">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检记录</span>
	</a>
	<a class="nav_item nav_item_a" href="">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检计划</span>
	</a>
	<a class="nav_item nav_item_a" href="">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡更班组</span>
	</a>
	<a class="nav_item nav_item_a" href="">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡更统计分析</span>
	</a>
	<div class="nav_item nav_item_div">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">报警管理</span>
	</div>
	<a class="nav_item nav_item_a" href="">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">报警统计</span>
	</a>
</div>
<div class="top_div" id="top_div">
	<span class="client_id_span">admin</span>
</div>
</body>
</html>