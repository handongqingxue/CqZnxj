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
		height:350,
		top:dialogTop,
		left:dialogLeft,
		buttons:[
           {text:"保存",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkEdit();
           }}
        ]
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

	initFirstDeptCBB();
	initSecondDeptCBB();
	initPDCBB();
	setTimeout(function(){
		loadSecondDeptCBBData();
		secondDeptCBB.combobox("setValue",'${requestScope.pdp.secondDeptId }');
		loadPDCBBData();
		pdCBB.combobox("setValue",'${requestScope.pdp.pdId }');
		loadPDACBBData();
		pdaCBB.combobox("setValue",'${requestScope.pdp.pdaId }');
	},"1000");
	initPDACBB();
	initTypeCBB();
}

function initFirstDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择一级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:0},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			firstDeptCBB=$("#edit_div #firstDept_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onLoadSuccess:function(){
					$(this).combobox("setValue",'${requestScope.pdp.firstDeptId }');
				},
				onSelect:function(){
					loadSecondDeptCBBData();
				}
			});
		}
	,"json");
}

function initSecondDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	secondDeptCBB=$("#edit_div #secondDept_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadPDCBBData();
		}
	});
}

function loadSecondDeptCBBData(){
	var parentId=firstDeptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:parentId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			secondDeptCBB.combobox("loadData",data);
		}
	,"json");
}

function initPDCBB(){
	var data=[];
	data.push({"value":"","text":"请选择设备名称"});
	pdCBB=$("#edit_div #pd_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadPDACBBData();
		}
	});
}

function loadPDCBBData(){
	var deptId=secondDeptCBB.combobox("getValue");
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
	pdaCBB=$("#edit_div #pda_cbb").combobox({
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
	typeCBB=$("#edit_div #type_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onLoadSuccess:function(){
			$(this).combobox("setValue",'${requestScope.pdp.type }');
		}
	});
}

function checkEdit(){
	if(checkFirstDeptId()){
		if(checkSecondDeptId()){
			if(checkPDName()){
				if(checkPDAName()){
					if(checkName()){
						if(checkTypeName()){
							if(checkUnit()){
								if(checkWarnUp()){
									if(checkWarnDown()){
										editParam();
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

function editParam(){
	var type=typeCBB.combobox("getValue");
	$("#edit_div #type").val(type);
	var pdaId=pdaCBB.combobox("getValue");
	$("#edit_div #pdaId").val(pdaId);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:deviceMgmtPath+"editParam",
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

//验证一级部门
function checkFirstDeptId(){
	var deptId=firstDeptCBB.combobox("getValue");
	if(deptId==null||deptId==""){
	  	alert("请选择一级部门");
	  	return false;
	}
	else
		return true;
}

//验证二级部门
function checkSecondDeptId(){
	var deptId=secondDeptCBB.combobox("getValue");
	if(deptId==null||deptId==""){
	  	alert("请选择二级部门");
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
	<div class="page_location_div">设备参数-编辑</div>
	
	<div id="edit_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${requestScope.pdp.id }"/>
		<table>
		  <tr>
			<td class="td1" align="right">
				一级部门
			</td>
			<td class="td2">
				<input id="firstDept_cbb"/>
			</td>
			<td class="td1" align="right">
				二级部门
			</td>
			<td class="td2">
				<input id="secondDept_cbb"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				设备名称
			</td>
			<td class="td2">
				<input id="pd_cbb"/>
			</td>
			<td class="td1" align="right">
				设备编号
			</td>
			<td class="td2">
				<input id="pda_cbb"/>
				<input type="hidden" id="pdaId" name="pdaId" value="${requestScope.pdp.pdaId }"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				参数名称
			</td>
			<td class="td2">
				<input type="text" class="name_inp" id="name" name="name" value="${requestScope.pdp.name }" placeholder="请输入参数名称" onfocus="focusName()" onblur="checkName()"/>
			</td>
			<td class="td1" align="right">
				参数类型
			</td>
			<td class="td2">
				<input id="type_cbb"/>
				<input type="hidden" id="type" name="type"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				参数单位
			</td>
			<td class="td2">
				<input type="text" class="unit_inp" id="unit" name="unit" value="${requestScope.pdp.unit }" placeholder="请输入参数单位" onfocus="focusUnit()" onblur="checkUnit()"/>
			</td>
			<td class="td1" align="right">
				报警上限
			</td>
			<td class="td2">
				<input type="number" class="warnUp_inp" id="warnUp" name="warnUp" value="${requestScope.pdp.warnUp }" placeholder="请输入报警上限"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				报警下限
			</td>
			<td class="td2">
				<input type="number" class="warnDown_inp" id="warnDown" name="warnDown" value="${requestScope.pdp.warnDown }" placeholder="请输入报警下限"/>
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