<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<style type="text/css">
.center_con_div{
	height: 90vh;
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
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var deviceMgmtPath=path+'deviceMgmt/';
var dialogTop=30;
var dialogLeft=20;
var edNum=0;
$(function(){
	initEditDialog();//0

	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	//基本属性组
	var edpw=$("body").find(".panel.window").eq(edNum);
	var edws=$("body").find(".window-shadow").eq(edNum);

	var ccDiv=$("#center_con_div");
	ccDiv.append(edpw);
	ccDiv.append(edws);
	ccDiv.css("width",setFitWidthInParent("body","center_con_div")+"px");
}

function initEditDialog(){
	dialogTop+=20;
	$("#edit_div").dialog({
		title:"设备参数信息",
		width:setFitWidthInParent("body","edit_div"),
		height:260,
		top:dialogTop,
		left:dialogLeft
	});

	$("#edit_div table").css("width",(setFitWidthInParent("body","edit_div_table"))+"px");
	$("#edit_div table").css("magin","-100px");
	$("#edit_div table td").css("padding-left","50px");
	$("#edit_div table td").css("padding-right","20px");
	$("#edit_div table td").css("font-size","15px");
	$("#edit_div table .td1").css("width","15%");
	$("#edit_div table .td2").css("width","30%");
	$("#edit_div table tr").css("border-bottom","#CAD9EA solid 1px");
	$("#edit_div table tr").css("height","45px");

	$(".panel.window").eq(edNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(edNum).css("color","#000");
	$(".panel.window .panel-title").eq(edNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(edNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(edNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(edNum).css("border-color","#ddd");

	$("#edit_div #ok_but").css("left","45%");
	$("#edit_div #ok_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "center_con_div":
		space=205;
		break;
	case "edit_div":
		space=340;
		break;
	case "edit_div_table":
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
	<div class="page_location_div">设备参数-详情</div>
	
	<div id="edit_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<table>
		  <tr>
			<td class="td1" align="right">
				设备类型
			</td>
			<td class="td2">
				${requestScope.pdp.pdtName }
			</td>
			<td class="td1" align="right">
				设备名称
			</td>
			<td class="td2">
				${requestScope.pdp.pdName }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				设备编号
			</td>
			<td class="td2">
				${requestScope.pdp.pdaNo }
			</td>
			<td class="td1" align="right">
				参数名称
			</td>
			<td class="td2">
				${requestScope.pdp.name }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				参数类型
			</td>
			<td class="td2">
				<c:if test="${requestScope.pdp.type eq 1 }">数值</c:if>
				<c:if test="${requestScope.pdp.type eq 2 }">观察</c:if>类
			</td>
			<td class="td1" align="right">
				参数单位
			</td>
			<td class="td2">
				${requestScope.pdp.unit }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				报警上限
			</td>
			<td class="td2">
				${requestScope.pdp.warnUp }
			</td>
			<td class="td1" align="right">
				报警下限
			</td>
			<td class="td2">
				${requestScope.pdp.warnDown }
			</td>
		  </tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>