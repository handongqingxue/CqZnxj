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
.name_inp,.phone_inp,.jobNumber_inp,.tagId_inp{
	width: 150px;
	height:30px;
}
.upBut_div{
	height: 30px;
	line-height:30px;
	text-align:center;
	color:#fff;
	background-color: #1777FF;
	border-radius:5px;
}
.upPhotoBut_div{
	width: 90px;
}
.photo_img{
	width: 180px;
	height:180px;
	margin-top: 10px;
}
</style>
<script type="text/javascript">
var path='<%=basePath %>';
var mainPath=path+'main/';
var deviceMgmtPath=path+'deviceMgmt/';
var patrolMgmtPath=path+'patrolMgmt/';
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
		title:"巡检人员信息",
		width:setFitWidthInParent("body","new_div"),
		height:490,
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
	$("#new_div table tr").each(function(i){
		if(i==3)
			$(this).css("height","250px");
		else
			$(this).css("height","45px");
	});

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

	initFirstDeptCBB();
	initSecondDeptCBB();
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
			firstDeptCBB=$("#new_div #firstDept_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
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
	secondDeptCBB=$("#new_div #secondDept_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
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

function checkNew(){
	if(checkName()){
		if(checkPhone()){
			if(checkJobNumber()){
				if(checkFirstDeptId()){
					if(checkSecondDeptId()){
						newArea();
					}
				}
			}
		}
	}
}

function newArea(){
	var deptId=secondDeptCBB.combobox("getValue");
	$("#new_div #deptId").val(deptId);
	var pdaIdsArr=pdaCBB.combobox("getValues");
	var pdaIds=pdaIdsArr.sort().toString();
	if(pdaIds.substring(0,1)==",")
		pdaIds=pdaIds.substring(1);
	$("#new_div #pdaIds").val(pdaIds);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:patrolMgmtPath+"newArea",
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
	if(name=="姓名不能为空"){
		$("#name").val("");
		$("#name").css("color", "#555555");
	}
}

//验证姓名
function checkName(){
	var name = $("#name").val();
	if(name==null||name==""||name=="姓名不能为空"){
		$("#name").css("color","#E15748");
    	$("#name").val("姓名不能为空");
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

function focusJobNumber(){
	var jobNumber = $("#jobNumber").val();
	if(jobNumber=="工号不能为空"){
		$("#jobNumber").val("");
		$("#jobNumber").css("color", "#555555");
	}
}

//验证工号
function checkJobNumber(){
	var jobNumber = $("#jobNumber").val();
	if(jobNumber==null||jobNumber==""||jobNumber=="工号不能为空"){
		$("#jobNumber").css("color","#E15748");
    	$("#jobNumber").val("工号不能为空");
    	return false;
	}
	else
		return true;
}

function focusTagId(){
	var tagId = $("#tagId").val();
	if(tagId=="标签号不能为空"){
		$("#jobNumber").val("");
		$("#jobNumber").css("color", "#555555");
	}
}

//验证标签号
function checkTagId(){
	var tagId = $("#tagId").val();
	if(tagId==null||tagId==""||tagId=="标签号不能为空"){
		$("#tagId").css("color","#E15748");
    	$("#tagId").val("标签号不能为空");
    	return false;
	}
	else
		return true;
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

function uploadPhoto(){
	document.getElementById("photo_file").click();
}

function showPhoto(obj){
	var file = $(obj);
    var fileObj = file[0];
    var windowURL = window.URL || window.webkitURL;
    var dataURL;
    var $img = $("#photo_img");

    if (fileObj && fileObj.files && fileObj.files[0]) {
        dataURL = windowURL.createObjectURL(fileObj.files[0]);
        $img.attr("src", dataURL);
    } else {
        dataURL = $file.val();
        var imgObj = document.getElementById("preview");
        // 两个坑:
        // 1、在设置filter属性时，元素必须已经存在在DOM树中，动态创建的Node，也需要在设置属性前加入到DOM中，先设置属性在加入，无效；
        // 2、src属性需要像下面的方式添加，上面的两种方式添加，无效；
        imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
        imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;

    }
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
	<div class="page_location_div">人员-添加</div>
	
	<div id="new_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<table>
		  <tr>
			<td class="td1" align="right">
				姓名
			</td>
			<td class="td2">
				<input type="text" class="name_inp" id="name" name="name" placeholder="请输入姓名" onfocus="focusName()" onblur="checkName()"/>
			</td>
			<td class="td1" align="right">
				电话
			</td>
			<td class="td2">
				<input type="text" class="phone_inp" id="phone" name="phone" placeholder="请输入电话" onfocus="focusPhone()" onblur="checkPhone()"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				工号
			</td>
			<td class="td2">
				<input type="text" class="jobNumber_inp" id="jobNumber" name="jobNumber" placeholder="请输入工号" onfocus="focusJobNumber()" onblur="checkJobNumber()"/>
			</td>
			<td class="td1" align="right">
				标签号
			</td>
			<td class="td2">
				<input type="text" class="tagId_inp" id="tagId" name="tagId" placeholder="请输入标签号" onfocus="focusTagId()" onblur="checkTagId()"/>
			</td>
		  </tr>
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
				<input type="hidden" id="deptId" name="deptId"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				照片
			</td>
			<td class="td2">
				<div class="upBut_div upPhotoBut_div" onclick="uploadPhoto()">选择照片</div>
				<input type="file" id="photo_file" name="photo_file" style="display: none;" onchange="showPhoto(this)"/>
				<img class="photo_img" id="photo_img" alt="" src=""/>
			</td>
			<td class="td1" align="right">
				备注
			</td>
			<td class="td2">
				<textarea rows="4" cols="15" id="remark" name="remark" placeholder="请输入备注"></textarea>
			</td>
		  </tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>