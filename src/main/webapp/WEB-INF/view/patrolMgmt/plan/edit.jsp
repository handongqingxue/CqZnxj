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
		title:"巡检计划信息",
		width:setFitWidthInParent("body","edit_div"),
		height:300,
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

	initTypeCBB();
	initPtCBB();
	initPssCBB();
	initPlsCBB();
	initStartDateDB();
	initEndDateDB();
	setTimeout(function(){
		loadPssCBBData();
	},1000);
}

function initTypeCBB(){
	var data=[];
	data.push({"value":"","text":"请选择计划类型"});
	data.push({"value":"1","text":"日常"});
	data.push({"value":"2","text":"临时"});
	typeCBB=$("#edit_div #type_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onLoadSuccess:function(){
			$(this).combobox("setValue",'${requestScope.pp.type }');
		}
	});
}

function initPtCBB(){
	var data=[];
	data.push({"value":"","text":"请选择巡检班组"});
	$.post(patrolMgmtPath+"queryTeamCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			ptCBB=$("#edit_div #pt_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onLoadSuccess:function(){
					$(this).combobox("setValue",'${requestScope.pp.ptId }');
				}
			});
		}
	,"json");
}

function initPssCBB(){
	var data=[];
	data.push({"value":"","text":"请选择巡检人员"});
	pssCBB=$("#edit_div #pss_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		multiple:true,
		onLoadSuccess:function(){
			$(this).combobox("setValues",'${requestScope.pp.psIds }'.split(","));
		}
	});
}

function loadPssCBBData(){
	var ptId=ptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择巡检人员"});
	$.post(patrolMgmtPath+"queryTeamStaffCBBList",
		{ptId:ptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			pssCBB.combobox("loadData",data);
		}
	,"json");
}

function initPlsCBB(){
	var data=[];
	data.push({"value":"","text":"请选择巡检路线"});
	$.post(patrolMgmtPath+"queryLineCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			plsCBB=$("#edit_div #pls_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				multiple:true,
				onLoadSuccess:function(){
					var plIdArr='${requestScope.pp.plIds }'.split(",");
					$(this).combobox("setValues",plIdArr);
				}
			});
		}
	,"json");
}

function initStartDateDB(){
	sdDB=$("#sd_db").datebox({
		required:false
	});
	sdDB.datebox("setValue",'${requestScope.pp.startDate }');
}

function initEndDateDB(){
	edDB=$("#ed_db").datebox({
		required:false
	});
	edDB.datebox("setValue",'${requestScope.pp.endDate }');
}

function checkEdit(){
	if(checkName()){
		if(checkType()){
			if(checkPtId()){
				if(checkPsIds()){
					if(checkPlIds()){
						if(checkStartDate()){
							if(checkEndDate()){
								editPlan();
							}
						}
					}
				}
			}
		}
	}
}

function editPlan(){
	var name = $("#name").val();
	var type=typeCBB.combobox("getValue");
	$("#edit_div #type").val(type);
	var ptId=ptCBB.combobox("getValue");
	$("#edit_div #ptId").val(ptId);
	var psIdsArr=pssCBB.combobox("getValues");
	var psIds=psIdsArr.sort().toString();
	if(psIds.substring(0,1)==",")
		psIds=psIds.substring(1);
	$("#edit_div #psIds").val(psIds);
	var plIdsArr=plsCBB.combobox("getValues");
	var plIds=plIdsArr.sort().toString();
	if(plIds.substring(0,1)==",")
		plIds=plIds.substring(1);
	$("#edit_div #plIds").val(plIds);
	var startDate=sdDB.datebox("getValue");
	$("#edit_div #startDate").val(startDate);
	var endDate=edDB.datebox("getValue");
	$("#edit_div #endDate").val(endDate);

	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:patrolMgmtPath+"editPlan",
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
	if(name=="班组名称不能为空"){
		$("#name").val("");
		$("#name").css("color", "#555555");
	}
}

//验证班组名称
function checkName(){
	var name = $("#name").val();
	if(name==null||name==""||name=="班组名称不能为空"){
		$("#name").css("color","#E15748");
    	$("#name").val("班组名称不能为空");
    	return false;
	}
	else
		return true;
}

//验证计划类型
function checkType(){
	var type=typeCBB.combobox("getValues");
	if(type==null||type==""){
	  	alert("请选择计划类型");
	  	return false;
	}
	else
		return true;
}

//验证巡检班组
function checkPtId(){
	var ptId=ptCBB.combobox("getValues");
	if(ptId==null||ptId==""){
	  	alert("请选择巡检班组");
	  	return false;
	}
	else
		return true;
}

//验证巡检人员
function checkPsIds(){
	var pssId=pssCBB.combobox("getValue");
	if(pssId==null||pssId==""){
	  	alert("请选择巡检人员");
	  	return false;
	}
	else
		return true;
}

//验证巡检路线
function checkPlIds(){
	var plIds=plsCBB.combobox("getValues");
	if(plIds==null||plIds==""){
	  	alert("请选择巡检路线");
	  	return false;
	}
	else
		return true;
}

function checkStartDate(){
	var startDate=sdDB.datebox("getValue");
	if(startDate==null||startDate==""){
		alert("请选择开始日期");
    	return false;
	}
	else
		return true;
}

function checkEndDate(){
	var endDate=edDB.datebox("getValue");
	if(endDate==null||endDate==""){
		alert("请选择结束日期");
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
	<div class="page_location_div">巡检计划-编辑</div>
	
	<div id="edit_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${requestScope.pp.id }"/>
		<table>
		  <tr>
			<td class="td1" align="right">
				计划名称
			</td>
			<td class="td2">
				<input type="text" class="name_inp" id="name" name="name" value="${requestScope.pp.name }" placeholder="请输入计划名称" onfocus="focusName()" onblur="checkName()"/>
			</td>
			<td class="td1" align="right">
				计划类型
			</td>
			<td class="td2">
				<input id="type_cbb"/>
				<input type="hidden" id="type" name="type" value="${requestScope.pp.type }"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				巡检班组
			</td>
			<td class="td2">
				<input id="pt_cbb"/>
				<input type="hidden" id="ptId" name="ptId" value="${requestScope.pp.ptId }"/>
			</td>
			<td class="td1" align="right">
				巡检人员
			</td>
			<td class="td2">
				<input id="pss_cbb"/>
				<input type="hidden" id="psIds" name="psIds" value="${requestScope.pp.psIds }"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				巡检路线
			</td>
			<td class="td2">
				<input id="pls_cbb"/>
				<input type="hidden" id="plIds" name="plIds" value="${requestScope.pp.plIds }"/>
			</td>
			<td class="td1" align="right">
				开始日期
			</td>
			<td class="td2">
				<input id="sd_db"/>
				<input type="hidden" id="startDate" name="startDate" value="${requestScope.pp.startDate }"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				结束日期
			</td>
			<td class="td2">
				<input id="ed_db"/>
				<input type="hidden" id="endDate" name="endDate" value="${requestScope.pp.endDate }"/>
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