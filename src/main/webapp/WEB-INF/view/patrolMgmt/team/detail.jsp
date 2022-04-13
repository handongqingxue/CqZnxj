<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var mainPath=path+'main/';
var deviceMgmtPath=path+'deviceMgmt/';
var patrolMgmtPath=path+'patrolMgmt/';
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
		title:"巡检班组信息",
		width:setFitWidthInParent("body","edit_div"),
		height:310,
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

function initWorkDayCBB(){
	var data=[];
	data.push({"value":"","text":"请选择上班日"});
	data.push({"value":"1","text":"星期一"});
	data.push({"value":"2","text":"星期二"});
	data.push({"value":"3","text":"星期三"});
	data.push({"value":"4","text":"星期四"});
	data.push({"value":"5","text":"星期五"});
	data.push({"value":"6","text":"星期六"});
	data.push({"value":"7","text":"星期日"});
	wdCBB=$("#edit_div #workDay_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		multiple:true,
		onLoadSuccess:function(){
			$(this).combobox("setValues",'${requestScope.pt.workDay }'.split(","));
		}
	});
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
	<div class="page_location_div">巡检班组-详情</div>
	
	<div id="edit_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${requestScope.pt.id }"/>
		<table>
		  <tr>
			<td class="td1" align="right">
				班组名称
			</td>
			<td class="td2">
				${requestScope.pt.name }
			</td>
			<td class="td1" align="right">
				负责人
			</td>
			<td class="td2">
				${requestScope.pt.leader }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				巡检开始时间
			</td>
			<td class="td2">
				${requestScope.pt.startTime }
			</td>
			<td class="td1" align="right">
				巡检结束时间
			</td>
			<td class="td2">
				${requestScope.pt.endTime }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				上班日
			</td>
			<td class="td2">
				${fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(fn:replace(requestScope.pt.workDay,"1","星期一"),"2","星期二"),"3","星期三"),"4","星期四"),"5","星期五"),"6","星期六"),"7","星期日") }
			</td>
			<td class="td1" align="right">
				一级部门
			</td>
			<td class="td2">
				${requestScope.pt.firstDeptName }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				二级部门
			</td>
			<td class="td2">
				${requestScope.pt.secondDeptName }
			</td>
			<td class="td1" align="right">
				班组人员
			</td>
			<td class="td2">
				${requestScope.pt.staffNames }
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				创建时间
			</td>
			<td class="td2">
				${requestScope.pt.createTime }
			</td>
			<td class="td1" align="right">
				最近修改时间
			</td>
			<td class="td2">
				${requestScope.pt.updateTime }
			</td>
		  </tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>