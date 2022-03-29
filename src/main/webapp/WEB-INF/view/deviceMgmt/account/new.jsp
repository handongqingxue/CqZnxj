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
.deviceNo_inp{
	width: 150px;
	height:30px;
}
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var deviceMgmtPath=path+'deviceMgmt/';
var dialogTop=30;
var dialogLeft=20;
var ndNum=0;
$(function(){
	initNewDialog();//0

	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	//基本属性组
	var ndpw=$("body").find(".panel.window").eq(ndNum);
	var ndws=$("body").find(".window-shadow").eq(ndNum);

	var ccDiv=$("#center_con_div");
	ccDiv.append(ndpw);
	ccDiv.append(ndws);
	ccDiv.css("width",setFitWidthInParent("body","center_con_div")+"px");
}

function initNewDialog(){
	dialogTop+=20;
	$("#new_div").dialog({
		title:"设备台账信息",
		width:setFitWidthInParent("body","new_div"),
		height:200,
		top:dialogTop,
		left:dialogLeft,
		buttons:[
           {text:"保存",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkNew();
           }}
        ]
	});

	$("#new_div table").css("width",(setFitWidthInParent("body","new_div_table"))+"px");
	$("#new_div table").css("magin","-100px");
	$("#new_div table td").css("padding-left","50px");
	$("#new_div table td").css("padding-right","20px");
	$("#new_div table td").css("font-size","15px");
	$("#new_div table .td1").css("width","15%");
	$("#new_div table .td2").css("width","30%");
	$("#new_div table tr").css("border-bottom","#CAD9EA solid 1px");
	$("#new_div table tr").css("height","45px");

	$(".panel.window").eq(ndNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(ndNum).css("color","#000");
	$(".panel.window .panel-title").eq(ndNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(ndNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(ndNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(ndNum).css("border-color","#ddd");

	$("#new_div #ok_but").css("left","45%");
	$("#new_div #ok_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");

	initPDTCBB();
	initPDCBB();
}

function initPDTCBB(){
	var data=[];
	data.push({"value":"","text":"请选择设备类型"});
	$.post(deviceMgmtPath+"queryTypeCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			pdtCBB=$("#new_div #pdt_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onSelect:function(){
					loadPDCBBData();
				}
			});
		}
	,"json");
}

function initPDCBB(){
	var data=[];
	data.push({"value":"","text":"请选择设备名称"});
	pdCBB=$("#new_div #pd_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
	});
}

function loadPDCBBData(){
	var typeId=pdtCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择设备名称"});
	$.post(deviceMgmtPath+"queryDeviceCBBList",
		{typeId:typeId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			pdCBB.combobox("loadData",data);
		}
	,"json");
}

function checkNew(){
	if(checkPDTId()){
		if(checkPDName()){
			if(checkDeviceNo()){
				newAccount();
			}
		}
	}
}

function newAccount(){
	var deviceId=pdCBB.combobox("getValue");
	$("#new_div #deviceId").val(deviceId);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:deviceMgmtPath+"newAccount",
		dataType: "json",
		data:formData,
		cache: false,
		processData: false,
		contentType: false,
		success: function (data){
			if(data.message=="ok"){
				alert(data.info);
				history.go(-1);
			}
			else{
				alert(data.info);
			}
		}
	});
}

function focusDeviceNo(){
	var deviceNo = $("#deviceNo").val();
	if(deviceNo=="设备编号不能为空"||deviceNo=="设备编号已存在"){
		$("#deviceNo").val("");
		$("#deviceNo").css("color", "#555555");
	}
}

//验证设备编号
function checkDeviceNo(){
	var flag=false;
	var deviceNo = $("#deviceNo").val();
	if(deviceNo==null||deviceNo==""||deviceNo=="设备编号不能为空"){
		$("#deviceNo").css("color","#E15748");
    	$("#deviceNo").val("设备编号不能为空");
    	flag=false;
	}
	else if(deviceNo=="设备编号已存在"){
		$("#deviceNo").css("color","#E15748");
    	$("#deviceNo").val("设备编号已存在");
    	flag=false;
	}
	else{
		$.ajaxSetup({async:false});
		$.post(deviceMgmtPath+"checkDeviceNoIfExist",
			{deviceNo:deviceNo},
			function(data){
				if(data.status==1)
			    	flag=true;
				else{
					$("#deviceNo").css("color","#E15748");
			    	$("#deviceNo").val(data.msg);
			    	flag=false;
				}
			}
		,"json");
	}
	return flag;
}

//验证设备类型
function checkPDTId(){
	var pdtId=pdtCBB.combobox("getValue");
	if(pdtId==null||pdtId==""){
	  	alert("请选择设备类型");
	  	return false;
	}
	else
		return true;
}

//验证设备名称
function checkPDName(){
	var pdName=pdCBB.combobox("getValue");
	if(pdName==null||pdName==""){
	  	alert("请选择设备名称");
	  	return false;
	}
	else
		return true;
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "center_con_div":
		space=205;
		break;
	case "new_div":
		space=340;
		break;
	case "new_div_table":
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
	<div class="page_location_div">设备台账-添加</div>
	
	<div id="new_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<table>
		  <tr>
			<td class="td1" align="right">
				设备类型
			</td>
			<td class="td2">
				<input id="pdt_cbb"/>
			</td>
			<td class="td1" align="right">
				设备名称
			</td>
			<td class="td2">
				<input id="pd_cbb"/>
				<input type="hidden" id="deviceId" name="deviceId"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				设备编号
			</td>
			<td class="td2">
				<input type="text" class="deviceNo_inp" id="deviceNo" name="deviceNo" placeholder="请输入设备编号" onfocus="focusDeviceNo()" onblur="checkDeviceNo()"/>
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