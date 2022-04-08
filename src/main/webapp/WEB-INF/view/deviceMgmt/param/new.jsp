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
.name_inp,.unit_inp,.warnUp_inp,.warnDown_inp{
	width: 150px;
	height:30px;
}
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var mainPath=path+'main/';
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
		title:"设备参数信息",
		width:setFitWidthInParent("body","new_div"),
		height:300,
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

	initDeptCBB();
	initPDCBB();
	initPDACBB();
	initTypeCBB();
}

function initDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择部门"});
	$.post(mainPath+"queryDeptCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			deptCBB=$("#new_div #dept_cbb").combobox({
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
		data:data,
		onSelect:function(){
			loadPDACBBData();
		}
	});
}

function loadPDCBBData(){
	var deptId=deptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择设备名称"});
	$.post(deviceMgmtPath+"queryDeviceCBBList",
		{deptId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			pdCBB.combobox("loadData",data);
		}
	,"json");
}

function initPDACBB(){
	var data=[];
	data.push({"value":"","text":"请选择设备编号"});
	pdaCBB=$("#new_div #pda_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
	});
}

function loadPDACBBData(){
	var pdId=pdCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择设备编号"});
	$.post(deviceMgmtPath+"queryAccountCBBList",
		{pdId:pdId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].no});
			}
			pdaCBB.combobox("loadData",data);
		}
	,"json");
}

function initTypeCBB(){
	var data=[];
	data.push({"value":"","text":"请选择参数类型"});
	data.push({"value":"1","text":"数值类"});
	data.push({"value":"2","text":"观察类"});
	typeCBB=$("#new_div #type_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
	});
}

function checkNew(){
	if(checkDeptId()){
		if(checkPDName()){
			if(checkPDAName()){
				if(checkName()){
					if(checkTypeName()){
						if(checkUnit()){
							if(checkWarnUp()){
								if(checkWarnDown()){
									newParam();
								}
							}
						}
					}
				}
			}
		}
	}
}

function newParam(){
	var type=typeCBB.combobox("getValue");
	$("#new_div #type").val(type);
	var pdaId=pdaCBB.combobox("getValue");
	$("#new_div #pdaId").val(pdaId);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:deviceMgmtPath+"newParam",
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

//验证部门
function checkDeptId(){
	var deptId=deptCBB.combobox("getValue");
	if(deptId==null||deptId==""){
	  	alert("请选择部门");
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

//验证设备编号
function checkPDAName(){
	var pdaName=pdaCBB.combobox("getValue");
	if(pdaName==null||pdaName==""){
	  	alert("请选择设备编号");
	  	return false;
	}
	else
		return true;
}

function focusName(){
	var name = $("#name").val();
	if(name=="参数名称不能为空"){
		$("#name").val("");
		$("#name").css("color", "#555555");
	}
}

//验证参数名称
function checkName(){
	var name = $("#name").val();
	if(name==null||name==""||name=="参数名称不能为空"){
		$("#name").css("color","#E15748");
    	$("#name").val("参数名称不能为空");
    	return false;
	}
	else
		return true;
}

//验证参数类型
function checkTypeName(){
	var typeName=typeCBB.combobox("getValue");
	if(typeName==null||typeName==""){
	  	alert("请选择参数类型");
	  	return false;
	}
	else
		return true;
}

function focusUnit(){
	var unit = $("#unit").val();
	if(unit=="参数单位不能为空"){
		$("#unit").val("");
		$("#unit").css("color", "#555555");
	}
}

//验证参数单位
function checkUnit(){
	var unit = $("#unit").val();
	if(unit==null||unit==""||unit=="参数单位不能为空"){
		$("#unit").css("color","#E15748");
    	$("#unit").val("参数单位不能为空");
    	return false;
	}
	else
		return true;
}

//验证报警上限
function checkWarnUp(){
	var warnUp = $("#warnUp").val();
	if(warnUp==null||warnUp==""){
	  	alert("请选择报警上限");
	  	return false;
	}
	else
		return true;
}

//验证报警下限
function checkWarnDown(){
	var warnDown = $("#warnDown").val();
	if(warnDown==null||warnDown==""){
	  	alert("请选择报警下限");
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
	<div class="page_location_div">设备参数-添加</div>
	
	<div id="new_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<table>
		  <tr>
			<td class="td1" align="right">
				部门
			</td>
			<td class="td2">
				<input id="dept_cbb"/>
			</td>
			<td class="td1" align="right">
				设备名称
			</td>
			<td class="td2">
				<input id="pd_cbb"/>
				<input type="hidden" id="pdId" name="pdId"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				设备编号
			</td>
			<td class="td2">
				<input id="pda_cbb"/>
				<input type="hidden" id="pdaId" name="pdaId"/>
			</td>
			<td class="td1" align="right">
				参数名称
			</td>
			<td class="td2">
				<input type="text" class="name_inp" id="name" name="name" placeholder="请输入参数名称" onfocus="focusName()" onblur="checkName()"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				参数类型
			</td>
			<td class="td2">
				<input id="type_cbb"/>
				<input type="hidden" id="type" name="type"/>
			</td>
			<td class="td1" align="right">
				参数单位
			</td>
			<td class="td2">
				<input type="text" class="unit_inp" id="unit" name="unit" placeholder="请输入参数单位" onfocus="focusUnit()" onblur="checkUnit()"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				报警上限
			</td>
			<td class="td2">
				<input type="number" class="warnUp_inp" id="warnUp" name="warnUp" placeholder="请输入报警上限"/>
			</td>
			<td class="td1" align="right">
				报警下限
			</td>
			<td class="td2">
				<input type="number" class="warnDown_inp" id="warnDown" name="warnDown" placeholder="请输入报警下限"/>
			</td>
		  </tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>