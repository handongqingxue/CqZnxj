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
.name_inp{
	width: 150px;
	height:30px;
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
		title:"巡检区域信息",
		width:setFitWidthInParent("body","edit_div"),
		height:210,
		top:dialogTop,
		left:dialogLeft,
		buttons:[
           {text:"保存",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkNew();
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
	setTimeout(function(){
		loadSecondDeptCBBData();
	},1000);
	initPDACBB();
	setTimeout(function(){
		loadPDACBBData();
	},1000);
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
					$(this).combobox("setValue",'${requestScope.pa.firstDeptId }');
				},
				onSelect:function(){
					loadPDACBBData();
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
		onLoadSuccess:function(){
			$(this).combobox("setValue",'${requestScope.pa.secondDeptId }');
		},
		onSelect:function(){
			loadPDACBBData();
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

function initPDACBB(){
	var data=[];
	data.push({"value":"","text":"请选择设备编号"});
	pdaCBB=$("#edit_div #pda_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		multiple:true,
		onLoadSuccess:function(){
			$(this).combobox("setValues",'${requestScope.pa.pdaIds }'.split(","));
		}
	});
}

function loadPDACBBData(){
	var deptId=secondDeptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择设备编号"});
	$.post(deviceMgmtPath+"queryAccountCBBList",
		{deptId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].no});
			}
			pdaCBB.combobox("loadData",data);
		}
	,"json");
}

function checkNew(){
	if(checkFirstDeptId()){
		if(checkSecondDeptId()){
			if(checkPDAName()){
				if(checkName()){
					editArea();
				}
			}
		}
	}
}

function editArea(){
	var deptId=secondDeptCBB.combobox("getValue");
	$("#edit_div #deptId").val(deptId);
	var pdaIdsArr=pdaCBB.combobox("getValues");
	var pdaIds=pdaIdsArr.sort().toString();
	if(pdaIds.substring(0,1)==",")
		pdaIds=pdaIds.substring(1);
	$("#edit_div #pdaIds").val(pdaIds);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:patrolMgmtPath+"editArea",
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

//验证设备编号
function checkPDAName(){
	var pdaName=pdaCBB.combobox("getValues");
	if(pdaName==null||pdaName==""){
	  	alert("请选择设备编号");
	  	return false;
	}
	else
		return true;
}

function focusName(){
	var name = $("#name").val();
	if(name=="区域名称不能为空"){
		$("#name").val("");
		$("#name").css("color", "#555555");
	}
}

//验证区域名称
function checkName(){
	var name = $("#name").val();
	if(name==null||name==""||name=="区域名称不能为空"){
		$("#name").css("color","#E15748");
    	$("#name").val("区域名称不能为空");
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
	<div class="page_location_div">巡检区域-编辑</div>
	
	<div id="edit_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${requestScope.pa.id }"/>
		<table>
		  <tr>
			<td class="td1" align="right">
				部门
			</td>
			<td class="td2">
				<input id="firstDept_cbb"/>
			</td>
			<td class="td1" align="right">
				部门
			</td>
			<td class="td2">
				<input id="secondDept_cbb"/>
				<input type="hidden" id="deptId" name="deptId" value="${requestScope.pa.deptId }"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				设备编号
			</td>
			<td class="td2">
				<input id="pda_cbb"/>
				<input type="hidden" id="pdaIds" name="pdaIds" value="${requestScope.pa.pdaIds }"/>
			</td>
			<td class="td1" align="right">
				区域名称
			</td>
			<td class="td2">
				<input type="text" class="name_inp" id="name" name="name" value="${requestScope.pa.name }" placeholder="请输入区域名称" onfocus="focusName()" onblur="checkName()"/>
			</td>
		  </tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>