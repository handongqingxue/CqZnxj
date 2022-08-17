<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<style type="text/css">
.center_con_div{
	height: 90vh;
	margin-top: 46px;
	margin-left:205px;
	position: absolute;
}
.page_location_div{
	height: 50px;
	line-height: 50px;
	margin-top: 10px;
	margin-left: 20px;
	font-size: 18px;
}
.photoUrl_img,.videoUrl_embed{
	height:180px;
	margin-top: 10px;
}
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var mainPath=path+'main/';
var dialogTop=30;
var dialogLeft=20;
var ddNum=0;
$(function(){
	initDetailDialog();//0

	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	//基本属性组
	var ddpw=$("body").find(".panel.window").eq(ddNum);
	var ddws=$("body").find(".window-shadow").eq(ddNum);

	var ccDiv=$("#center_con_div");
	ccDiv.append(ddpw);
	ccDiv.append(ddws);
	ccDiv.css("width",setFitWidthInParent("body","center_con_div")+"px");
}

function initDetailDialog(){
	dialogTop+=20;
	$("#detail_div").dialog({
		title:"巡检记录信息",
		width:setFitWidthInParent("body","detail_div"),
		height:760,
		top:dialogTop,
		left:dialogLeft
	});

	$("#detail_div table").css("width",(setFitWidthInParent("body","detail_div_table"))+"px");
	$("#detail_div table").css("magin","-100px");
	$("#detail_div table td").css("padding-left","50px");
	$("#detail_div table td").css("padding-right","20px");
	$("#detail_div table td").css("font-size","15px");
	$("#detail_div table .td1").css("width","15%");
	$("#detail_div table .td2").css("width","30%");
	$("#detail_div table tr").css("border-bottom","#CAD9EA solid 1px");
	$("#detail_div table tr").each(function(i){
		if(i==4||i==5)
			$(this).css("height","250px");
		else
			$(this).css("height","45px");
	});

	$(".panel.window").eq(ddNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(ddNum).css("color","#000");
	$(".panel.window .panel-title").eq(ddNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(ddNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(ddNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(ddNum).css("border-color","#ddd");
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "center_con_div":
		space=205;
		break;
	case "detail_div":
		space=340;
		break;
	case "detail_div_table":
		space=372;
		break;
	case "panel_window":
		space=355;
		break;
	}
	var width=$(parent).css("width");
	return width.substring(0,width.length-2)-space;
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="center_con_div" id="center_con_div">
	<div class="page_location_div">巡检记录-详情</div>
	
	<div id="detail_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<table>
		  <tr>
			<td class="td1" align="right">
				路线名称
			</td>
			<td class="td2">
				${requestScope.dppr.plName }
			</td>
			<td class="td1" align="right">
				区域名称
			</td>
			<td class="td2">
				${requestScope.dppr.paName }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				设备名称
			</td>
			<td class="td2">
				${requestScope.dppr.pdName }
			</td>
			<td class="td1" align="right">
				设备编号
			</td>
			<td class="td2">
				${requestScope.dppr.pdaNo }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				巡检内容
			</td>
			<td class="td2">
				${requestScope.dppr.pdpName }
			</td>
			<td class="td1" align="right">
				单位
			</td>
			<td class="td2">
				${requestScope.dppr.pdpUnit }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				是否异常
			</td>
			<td class="td2">
				${requestScope.dppr.paramIfExce?'是':'否' }
			</td>
			<td class="td1" align="right">
				巡检结果
			</td>
			<td class="td2">
				${requestScope.dppr.pdpType eq 1?requestScope.dppr.paramValue:requestScope.dppr.paramExceInfo }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				照片1
			</td>
			<td class="td2">
				<img class="photoUrl_img" id="photoUrl1_img" alt="" src="${requestScope.dppr.photoUrl1 }"/>
			</td>
			<td class="td1" align="right">
				照片2
			</td>
			<td class="td2">
				<img class="photoUrl_img" id="photoUrl2_img" alt="" src="${requestScope.dppr.photoUrl2 }"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				照片3
			</td>
			<td class="td2">
				<img class="photoUrl_img" id="photoUrl3_img" alt="" src="${requestScope.dppr.photoUrl3 }"/>
			</td>
			<td class="td1" align="right">
				视频1
			</td>
			<td class="td2">
				<embed class="videoUrl_embed" id="videoUrl1_embed" alt="" src="${requestScope.dppr.videoUrl1 }">
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				设备等级
			</td>
			<td class="td2">
				<c:if test="${requestScope.dppr.pdLevel eq 1 }">一</c:if>
				<c:if test="${requestScope.dppr.pdLevel eq 2 }">二</c:if>
				<c:if test="${requestScope.dppr.pdLevel eq 3 }">三</c:if>级
			</td>
			<td class="td1" align="right">
				开始时间
			</td>
			<td class="td2">
				${requestScope.dppr.startTime }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				结束时间
			</td>
			<td class="td2">
				${requestScope.dppr.endTime }
			</td>
			<td class="td1" align="right">
			</td>
			<td class="td2">
			</td>
		  </tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>