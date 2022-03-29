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
.name_inp,.specs_inp{
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
		title:"设备信息",
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
	
	initLevelCBB();
	initPDTCBB();
}

function initLevelCBB(){
	var data=[];
	data.push({"value":"","text":"请选择设备等级"});
	data.push({"value":"1","text":"一级"});
	data.push({"value":"2","text":"二级"});
	data.push({"value":"3","text":"三级"});
	levelCBB=$("#new_div #level_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
	});
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
				data:data
			});
		}
	,"json");
}

function checkNew(){
	if(checkName()){
		if(checkSpecs()){
			if(checkLevelId()){
				if(checkPdtId()){
					newDevice();
				}
			}
		}
	}
}

function newDevice(){
	var level=levelCBB.combobox("getValue");
	$("#new_div #level").val(level);
	var pdtId=pdtCBB.combobox("getValue");
	$("#new_div #pdtId").val(pdtId);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:deviceMgmtPath+"newDevice",
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

function focusName(){
	var name = $("#name").val();
	if(name=="名称不能为空"){
		$("#name").val("");
		$("#name").css("color", "#555555");
	}
}

//验证名称
function checkName(){
	var name = $("#name").val();
	if(name==null||name==""||name=="名称不能为空"){
		$("#name").css("color","#E15748");
    	$("#name").val("名称不能为空");
    	return false;
	}
	else
		return true;
}

function focusSpecs(){
	var specs = $("#specs").val();
	if(specs=="规格不能为空"){
		$("#specs").val("");
		$("#specs").css("color", "#555555");
	}
}

//验证规格
function checkSpecs(){
	var specs = $("#specs").val();
	if(specs==null||specs==""||specs=="规格不能为空"){
		$("#specs").css("color","#E15748");
    	$("#specs").val("规格不能为空");
    	return false;
	}
	else
		return true;
}

//验证设备等级
function checkLevelId(){
	var levelId=levelCBB.combobox("getValue");
	if(levelId==null||levelId==""){
	  	alert("请选择设备等级");
	  	return false;
	}
	else
		return true;
}

//验证设备类型
function checkPdtId(){
	var pdtId=pdtCBB.combobox("getValue");
	if(pdtId==null||pdtId==""){
	  	alert("请选择设备类型");
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
	<div class="page_location_div">设备查询-添加</div>
	
	<div id="new_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<table>
		  <tr>
			<td class="td1" align="right">
				名称
			</td>
			<td class="td2">
				<input type="text" class="name_inp" id="name" name="name" placeholder="请输入名称" onfocus="focusName()" onblur="checkName()"/>
			</td>
			<td class="td1" align="right">
				规格
			</td>
			<td class="td2">
				<input type="text" class="specs_inp" id="specs" name="specs" placeholder="请输入名称" onfocus="focusSpecs()" onblur="checkSpecs()"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				等级
			</td>
			<td class="td2">
				<input id="level_cbb"/>
				<input type="hidden" id="level" name="level"/>
			</td>
			<td class="td1" align="right">
				设备类型
			</td>
			<td class="td2">
				<input id="pdt_cbb"/>
				<input type="hidden" id="pdtId" name="pdtId"/>
			</td>
		  </tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>