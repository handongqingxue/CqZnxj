<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
body{
	margin: 0;
	padding-top: 1px;
}
.left_nav_div{
	width:200px;
	background-color: #060E2B;
	position: fixed;
}
.left_nav_div .nav_item{
	height:45px;
	line-height: 45px;
	color:#fff;
	font-size: 15px;
}
.left_nav_div .nav_item:hover,
.left_nav_div .selected{
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
	position: fixed;
}
.top_div .nav_location_span{
	color:#fff;
	margin-left: 20px;
}
.top_div .exit_span{
	margin-right: 30px;
	float: right;
}
.top_div .exit_span a{
	color:#fff;
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
	<a class="nav_item nav_item_a${param.nav eq 'bmcx'?' selected':'' }" href="<%=basePath%>deviceMgmt/dept/list?nav=bmcx">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">部门查询</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'sbcx'?' selected':'' }" href="<%=basePath%>deviceMgmt/device/list?nav=sbcx">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">设备查询</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'sbtz'?' selected':'' }" href="<%=basePath%>deviceMgmt/account/list?nav=sbtz">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">设备台账</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'sbcs'?' selected':'' }" href="<%=basePath%>deviceMgmt/param/list?nav=sbcs">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">设备参数</span>
	</a>
	<div class="nav_item nav_item_div">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检管理</span>
	</div>
	<a class="nav_item nav_item_a${param.nav eq 'xjlx'?' selected':'' }" href="<%=basePath%>patrolMgmt/line/list?nav=xjlx">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检路线</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'xjqy'?' selected':'' }" href="<%=basePath%>patrolMgmt/area/list?nav=xjqy">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检区域</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'xjbz'?' selected':'' }" href="<%=basePath%>patrolMgmt/team/list?nav=xjbz">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检班组</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'xjry'?' selected':'' }" href="<%=basePath%>patrolMgmt/staff/list?nav=xjry">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检人员</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'xjjh'?' selected':'' }" href="<%=basePath%>patrolMgmt/plan/list?nav=xjjh">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检计划</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'xjjl'?' selected':'' }" href="<%=basePath%>patrolMgmt/rec/list?nav=xjjl">
		<img class="nav_img" src="<%=basePath%>resource/image/001.png">
		<span class="text_span">巡检记录</span>
	</a>
	<a class="nav_item nav_item_a${param.nav eq 'xgtjfx'?' selected':'' }" href="<%=basePath%>patrolMgmt/cen/info?nav=xgtjfx">
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
	<span class="nav_location_span">
	<c:if test="${param.nav eq 'bmcx' }">
		设备管理>部门查询
	</c:if>
	<c:if test="${param.nav eq 'sbcx' }">
		设备管理>设备查询
	</c:if>
	<c:if test="${param.nav eq 'sbtz' }">
		设备管理>设备台账
	</c:if>
	<c:if test="${param.nav eq 'sbcs' }">
		设备管理>设备参数
	</c:if>
	<c:if test="${param.nav eq 'xjlx' }">
		巡检管理>巡检路线
	</c:if>
	<c:if test="${param.nav eq 'xjqy' }">
		巡检管理>巡检区域
	</c:if>
	<c:if test="${param.nav eq 'xjry' }">
		巡检管理>巡检人员
	</c:if>
	<c:if test="${param.nav eq 'xjjl' }">
		巡检管理>巡检记录
	</c:if>
	<c:if test="${param.nav eq 'xjjh' }">
		巡检管理>巡检计划
	</c:if>
	<c:if test="${param.nav eq 'xjbz' }">
		巡检管理>巡检班组
	</c:if>
	<c:if test="${param.nav eq 'xgtjfx' }">
		巡检管理>巡更统计分析
	</c:if>
	</span>
	<span class="exit_span">
		<a href="<%=basePath%>main/exit">退出</a>
	</span>
	<span class="client_id_span">${sessionScope.user.userName }</span>
</div>
</body>
</html>