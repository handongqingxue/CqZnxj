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
.deptName_inp,.leader_inp,.phone_inp{
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
		title:"部门信息",
		width:setFitWidthInParent("body","new_div"),
		height:250,
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
	initFirstDeptCBB();
}

function initLevelCBB(){
	var data=[];
	data.push({"value":"","text":"请选择部门等级"});
	data.push({"value":"1","text":"一级"});
	data.push({"value":"2","text":"二级"});
	levelCBB=$("#new_div #level_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
	});
}

function initFirstDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择上级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:0},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			firstDeptCBB=$("#new_div #firstDept_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data
			});
		}
	,"json");
}

function checkNew(){
	if(checkDeptName()){
		if(checkLeader()){
			if(checkPhone()){
				if(checkLevelId()){
					if(checkFirstDeptId()){
						newDevice();
					}
				}
			}
		}
	}
}

function newDevice(){
	var level=levelCBB.combobox("getValue");
	$("#new_div #level").val(level);
	var parentId=firstDeptCBB.combobox("getValue");
	$("#new_div #parentId").val(parentId);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:deviceMgmtPath+"newDept",
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

function focusDeptName(){
	var deptName = $("#deptName").val();
	if(deptName=="名称不能为空"){
		$("#deptName").val("");
		$("#deptName").css("color", "#555555");
	}
}

//验证名称
function checkDeptName(){
	var deptName = $("#deptName").val();
	if(deptName==null||deptName==""||deptName=="名称不能为空"){
		$("#deptName").css("color","#E15748");
    	$("#deptName").val("名称不能为空");
    	return false;
	}
	else
		return true;
}

function focusLeader(){
	var leader = $("#leader").val();
	if(leader=="负责人不能为空"){
		$("#leader").val("");
		$("#leader").css("color", "#555555");
	}
}

//验证负责人
function checkLeader(){
	var leader = $("#leader").val();
	if(leader==null||leader==""||leader=="负责人不能为空"){
		$("#leader").css("color","#E15748");
    	$("#leader").val("负责人不能为空");
    	return false;
	}
	else
		return true;
}

function focusPhone(){
	var phone = $("#phone").val();
	if(phone=="电话不能为空"){
		$("#phone").val("");
		$("#phone").css("color", "#555555");
	}
}

//验证电话
function checkPhone(){
	var phone = $("#phone").val();
	if(phone==null||phone==""||phone=="电话不能为空"){
		$("#phone").css("color","#E15748");
    	$("#phone").val("电话不能为空");
    	return false;
	}
	else
		return true;
}

//验证部门等级
function checkLevelId(){
	var levelId=levelCBB.combobox("getValue");
	if(levelId==null||levelId==""){
	  	alert("请选择部门等级");
	  	return false;
	}
	else
		return true;
}

//验证上级部门
function checkFirstDeptId(){
	var levelId=levelCBB.combobox("getValue");
	if(levelId==1)
		return true;
	var deptId=firstDeptCBB.combobox("getValue");
	if(deptId==null||deptId==""){
	  	alert("请选择上级部门");
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
	<div class="page_location_div">部门查询-添加</div>
	
	<div id="new_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<table>
		  <tr>
			<td class="td1" align="right">
				名称
			</td>
			<td class="td2">
				<input type="text" class="deptName_inp" id="deptName" name="deptName" placeholder="请输入名称" onfocus="focusDeptName()" onblur="checkDeptName()"/>
			</td>
			<td class="td1" align="right">
				负责人
			</td>
			<td class="td2">
				<input type="text" class="leader_inp" id="leader" name="leader" placeholder="请输入负责人" onfocus="focusLeader()" onblur="checkLeader()"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				电话
			</td>
			<td class="td2">
				<input type="text" class="phone_inp" id="phone" name="phone" placeholder="请输入电话" onfocus="focusPhone()" onblur="checkPhone()"/>
			</td>
			<td class="td1" align="right">
				等级
			</td>
			<td class="td2">
				<input id="level_cbb"/>
				<input type="hidden" id="level" name="level"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				上级部门
			</td>
			<td class="td2">
				<input id="firstDept_cbb"/>
				<input type="hidden" id="parentId" name="parentId"/>
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