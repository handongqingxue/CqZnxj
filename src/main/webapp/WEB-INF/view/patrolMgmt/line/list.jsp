<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.tab1_div{
	margin-top:65px;
	margin-left: 220px;
	position: fixed;
}
.tab1_toolbar{
	height:32px;
}
.tab1_toolbar .row_div{
	height:32px;
}
.tab1_toolbar .row_div .name_span,
.tab1_toolbar .row_div .createTime_span,
.tab1_toolbar .row_div .tab1_search_but{
	margin-left: 13px;
}
.tab1_toolbar .row_div .name_inp{
	width: 120px;
	height: 25px;
}
a {
  color: #333;
  text-decoration: none;
}

.add_line_bg_div,.edit_line_bg_div,.detail_line_bg_div,
.add_plaas_bg_div,.edit_plaas_bg_div{
	width: 100%;
	height: 100%;
	background-color: rgba(0,0,0,.45);
	position: fixed;
	z-index: 9016;
	display:none;
}
.add_line_div,.edit_line_div,.detail_line_div{
	width: 500px;
	height: 210px;
	margin: 200px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}
.add_line_div .name_inp,.edit_line_div .name_inp{
	width: 150px;
	height: 25px;
}

.tab2_div{
	margin-top:500px;
	margin-left: 220px;
	position: fixed;
}
.tab2_toolbar{
	height:32px;
}
.tab2_toolbar .row_div{
	height:32px;
}
.tab2_toolbar .row_div .plName_span,
.tab2_toolbar .row_div .paName_span,
.tab2_toolbar .row_div .tab2_search_but{
	margin-left: 13px;
}
.tab2_toolbar .row_div .plName_inp,
.tab2_toolbar .row_div .paName_inp{
	width: 120px;
	height: 25px;
}

.add_plaas_div,.edit_plaas_div{
	width: 500px;
	height: 410px;
	margin: 200px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var mainPath=path+'main/';
var deviceMgmtPath=path+'deviceMgmt/';
var patrolMgmtPath=path+'patrolMgmt/';
var dialogTop=10;
var dialogLeft=20;
var aldNum=0;
var eldNum=1;
var dldNum=2;
var aplaasdNum=3;
var eplaasdNum=4;
$(function(){
	initTab1CreateTimeStDTB();
	initTab1CreateTimeEtDTB();
	initTab1SearchLB();
	initTab1AddLB();
	initTab1RemoveLB();
	initTab1();
	initAddLineDialog();//0
	initEditLineDialog();//1
	initDetailLineDialog();//2

	initTab2SearchLB();
	initTab2AddLB();
	initTab2RemoveLB();
	initTab2();
	initAddPlaasDialog();//3
	initEditPlaasDialog();//4
	
	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	var aldpw=$("body").find(".panel.window").eq(aldNum);
	var aldws=$("body").find(".window-shadow").eq(aldNum);

	var eldpw=$("body").find(".panel.window").eq(eldNum);
	var eldws=$("body").find(".window-shadow").eq(eldNum);

	var dldpw=$("body").find(".panel.window").eq(dldNum);
	var dldws=$("body").find(".window-shadow").eq(dldNum);

	var aplaasdpw=$("body").find(".panel.window").eq(aplaasdNum);
	var aplaasdws=$("body").find(".window-shadow").eq(aplaasdNum);

	var eplaasdpw=$("body").find(".panel.window").eq(eplaasdNum);
	var eplaasdws=$("body").find(".window-shadow").eq(eplaasdNum);

	var aldDiv=$("#add_line_div");
	aldDiv.append(aldpw);
	aldDiv.append(aldws);

	var eldDiv=$("#edit_line_div");
	eldDiv.append(eldpw);
	eldDiv.append(eldws);

	var dldDiv=$("#detail_line_div");
	dldDiv.append(dldpw);
	dldDiv.append(dldws);

	var aplaasdDiv=$("#add_plaas_div");
	aplaasdDiv.append(aplaasdpw);
	aplaasdDiv.append(aplaasdws);

	var eplaasdDiv=$("#edit_plaas_div");
	eplaasdDiv.append(eplaasdpw);
	eplaasdDiv.append(eplaasdws);
}

function initTab1CreateTimeStDTB(){
	tab1CreateTimeStDTB=$("#tab1_createTimeSt_dtb").datetimebox({
        required:false
    });
}

function initTab1CreateTimeEtDTB(){
	tab1CreateTimeEtDTB=$("#tab1_createTimeEt_dtb").datetimebox({
        required:false
    });
}

function initTab1SearchLB(){
	$("#tab1_search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var name=$("#tab1_toolbar #name").val();
			var createTimeStart=tab1CreateTimeStDTB.datetimebox("getValue");
			var createTimeEnd=tab1CreateTimeEtDTB.datetimebox("getValue");
			tab1.datagrid("load",{name:name,createTimeStart:createTimeStart,createTimeEnd:createTimeEnd});
		}
	});
}

function initTab1AddLB(){
	$("#tab1_add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			openAddLineDialog(true);
		}
	});
}

function initTab1RemoveLB(){
	$("#tab1_remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			deleteLineByIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"路线查询",
		url:patrolMgmtPath+"queryLineList",
		toolbar:"#tab1_toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"name",title:"名称",width:150},
			{field:"createTime",title:"创建时间",width:180},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
       			var rowJson = JSON.stringify(row).replace(/"/g, '&quot;');
            	var str="<a onclick=\"openEditLineDialog(true,"+rowJson+")\">编辑</a>&nbsp;&nbsp;"
        		+"<a onclick=\"openDetailLineDialog(true,"+rowJson+")\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{name:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"name",colspan:3});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function deleteLineByIds(ids){
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的信息！","warning");
		return false;
	}
	
	$.messager.confirm("提示","确定要删除吗？",function(r){
		if(r){
			var ids = "";
			for (var i = 0; i < rows.length; i++) {
				ids += "," + rows[i].id;
			}
			ids=ids.substring(1);
			
			$.post(patrolMgmtPath + "deleteLine",
				{ids:ids},
				function(result){
					if(result.status==1){
						alert(result.msg);
						tab1.datagrid("load");
					}
					else{
						alert(result.msg);
					}
				}
			,"json");
			
		}
	});
}

function initAddLineDialog(){
	$("#add_line_dialog_div").dialog({
		title:"路线添加",
		width:setFitWidthInParent("#add_line_div","add_line_dialog_div"),
		height:150,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkAddLine();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openAddLineDialog(false);
           }}
        ]
	});

	$("#add_line_dialog_div table").css("width",(setFitWidthInParent("#add_line_div","add_line_dialog_table"))+"px");
	$("#add_line_dialog_div table").css("magin","-100px");
	$("#add_line_dialog_div table td").css("padding-left","40px");
	$("#add_line_dialog_div table td").css("padding-right","20px");
	$("#add_line_dialog_div table td").css("font-size","15px");
	$("#add_line_dialog_div table .td1").css("width","30%");
	$("#add_line_dialog_div table .td2").css("width","60%");
	$("#add_line_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(aldNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(aldNum).css("color","#000");
	$(".panel.window .panel-title").eq(aldNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(aldNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(aldNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(aldNum).css("border-color","#ddd");

	$("#add_line_dialog_div #ok_but").css("left","30%");
	$("#add_line_dialog_div #ok_but").css("position","absolute");

	$("#add_line_dialog_div #cancel_but").css("left","50%");
	$("#add_line_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function initEditLineDialog(){
	$("#edit_line_dialog_div").dialog({
		title:"路线编辑",
		width:setFitWidthInParent("#edit_line_div","edit_line_dialog_div"),
		height:150,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkEditLine();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openEditLineDialog(false);
           }}
        ]
	});

	$("#edit_line_dialog_div table").css("width",(setFitWidthInParent("#edit_line_div","edit_line_dialog_table"))+"px");
	$("#edit_line_dialog_div table").css("magin","-100px");
	$("#edit_line_dialog_div table td").css("padding-left","40px");
	$("#edit_line_dialog_div table td").css("padding-right","20px");
	$("#edit_line_dialog_div table td").css("font-size","15px");
	$("#edit_line_dialog_div table .td1").css("width","30%");
	$("#edit_line_dialog_div table .td2").css("width","60%");
	$("#edit_line_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(eldNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(eldNum).css("color","#000");
	$(".panel.window .panel-title").eq(eldNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(eldNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(eldNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(eldNum).css("border-color","#ddd");

	$("#edit_line_dialog_div #ok_but").css("left","30%");
	$("#edit_line_dialog_div #ok_but").css("position","absolute");

	$("#edit_line_dialog_div #cancel_but").css("left","50%");
	$("#edit_line_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function initDetailLineDialog(){
	$("#detail_line_dialog_div").dialog({
		title:"路线详情",
		width:setFitWidthInParent("#edit_line_div","detail_line_dialog_div"),
		height:150,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   openDetailLineDialog(false);
           }}
        ]
	});

	$("#detail_line_dialog_div table").css("width",(setFitWidthInParent("#detail_line_div","detail_line_dialog_table"))+"px");
	$("#detail_line_dialog_div table").css("magin","-100px");
	$("#detail_line_dialog_div table td").css("padding-left","40px");
	$("#detail_line_dialog_div table td").css("padding-right","20px");
	$("#detail_line_dialog_div table td").css("font-size","15px");
	$("#detail_line_dialog_div table .td1").css("width","30%");
	$("#detail_line_dialog_div table .td2").css("width","60%");
	$("#detail_line_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(dldNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(dldNum).css("color","#000");
	$(".panel.window .panel-title").eq(dldNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(dldNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(dldNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(dldNum).css("border-color","#ddd");

	$("#detail_line_dialog_div #ok_but").css("left","40%");
	$("#detail_line_dialog_div #ok_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function openAddLineDialog(flag){
	if(flag){
		$("#add_line_bg_div").css("display","block");
	}
	else{
		$("#add_line_bg_div").css("display","none");
	}
}

function openEditLineDialog(flag,row){
	if(flag){
		$("#edit_line_bg_div").css("display","block");
		$("#edit_line_div #id").val(row.id);
		$("#edit_line_div #name").val(row.name);
	}
	else{
		$("#edit_line_bg_div").css("display","none");
		$("#edit_line_div #id").val("");
		$("#edit_line_div #name").val("");
	}
}

function openDetailLineDialog(flag,row){
	if(flag){
		$("#detail_line_bg_div").css("display","block");
		$("#detail_line_div #id").val(row.id);
		$("#detail_line_div #name_span").text(row.name);
	}
	else{
		$("#detail_line_bg_div").css("display","none");
		$("#detail_line_div #id").val("");
		$("#detail_line_div #name_span").text("");
	}
}

function focusAddLineName(){
	var name = $("#add_line_div #name").val();
	if(name=="路线名称不能为空"){
		$("#add_line_div #name").val("");
		$("#add_line_div #name").css("color", "#555555");
	}
}

//验证路线名称
function checkAddLineName(){
	var name = $("#add_line_div #name").val();
	if(name==null||name==""||name=="路线名称不能为空"){
		$("#add_line_div #name").css("color","#E15748");
    	$("#add_line_div #name").val("路线名称不能为空");
    	return false;
	}
	else
		return true;
}

function focusEditLineName(){
	var name = $("#edit_line_div #name").val();
	if(name=="路线名称不能为空"){
		$("#edit_line_div #name").val("");
		$("#edit_line_div #name").css("color", "#555555");
	}
}

//验证路线名称
function checkEditLineName(){
	var name = $("#edit_line_div #name").val();
	if(name==null||name==""||name=="路线名称不能为空"){
		$("#edit_line_div #name").css("color","#E15748");
    	$("#edit_line_div #name").val("路线名称不能为空");
    	return false;
	}
	else
		return true;
}

function checkAddPlaasPl(){
	var plId=apPlCBB.combobox("getValue");
	if(plId==null||plId==""){
	  	alert("请选择路线");
	  	return false;
	}
	else
		return true;
}

function checkEditPlaasPl(){
	var plId=epPlCBB.combobox("getValue");
	if(plId==null||plId==""){
	  	alert("请选择路线");
	  	return false;
	}
	else
		return true;
}

function checkAddPlaasFd(){
	var fdId=apFdCBB.combobox("getValue");
	if(fdId==null||fdId==""){
	  	alert("请选择一级部门");
	  	return false;
	}
	else
		return true;
}

function checkEditPlaasFd(){
	var fdId=epFdCBB.combobox("getValue");
	if(fdId==null||fdId==""){
	  	alert("请选择一级部门");
	  	return false;
	}
	else
		return true;
}

function checkAddPlaasSd(){
	var sdId=apSdCBB.combobox("getValue");
	if(sdId==null||sdId==""){
	  	alert("请选择二级部门");
	  	return false;
	}
	else
		return true;
}

function checkEditPlaasSd(){
	var sdId=epSdCBB.combobox("getValue");
	if(sdId==null||sdId==""){
	  	alert("请选择二级部门");
	  	return false;
	}
	else
		return true;
}

function checkAddPlaasPa(){
	var paId=apPaCBB.combobox("getValue");
	if(paId==null||paId==""){
	  	alert("请选择区域");
	  	return false;
	}
	else
		return true;
}

function checkEditPlaasPa(){
	var paId=epPaCBB.combobox("getValue");
	if(paId==null||paId==""){
	  	alert("请选择区域");
	  	return false;
	}
	else
		return true;
}

function checkAddPlaasPda(){
	var pdaIds=apPdaCBB.combobox("getValues");
	if(pdaIds==null||pdaIds==""){
	  	alert("请选择设备台账");
	  	return false;
	}
	else
		return true;
}

function checkEditPlaasPda(){
	var pdaIds=epPdaCBB.combobox("getValues");
	if(pdaIds==null||pdaIds==""){
	  	alert("请选择设备台账");
	  	return false;
	}
	else
		return true;
}

function checkAddLine(){
	if(checkAddLineName()){
		newLine();
	}
}

function checkEditLine(){
	if(checkEditLineName()){
		editLine();
	}
}

function checkAddPatLineAreaAccSet(){
	if(checkAddPlaasPl()){
		if(checkAddPlaasFd()){
			if(checkAddPlaasSd()){
				if(checkAddPlaasPa()){
					if(checkAddPlaasPda()){
						newPatLineAreaAccSet();
					}
				}
			}
		}
	}
}

function checkEditPatLineAreaAccSet(){
	if(checkEditPlaasPl()){
		if(checkEditPlaasFd()){
			if(checkEditPlaasSd()){
				if(checkEditPlaasPa()){
					if(checkEditPlaasPda()){
						editPatLineAreaAccSet();
					}
				}
			}
		}
	}
}

function newLine(){
	var name = $("#add_line_div #name").val();
	$.post(patrolMgmtPath+"newLine",
		{name:name},
		function(data){
			if(data.message=="ok"){
				alert(data.info);
				openAddLineDialog(false);
				tab1.datagrid("load");
			}
		}
	,"json");
}

function editLine(){
	var id = $("#edit_line_div #id").val();
	var name = $("#edit_line_div #name").val();
	$.post(patrolMgmtPath+"editLine",
		{id:id,name:name},
		function(data){
			if(data.message=="ok"){
				alert(data.info);
				openEditLineDialog(false);
				tab1.datagrid("load");
			}
		}
	,"json");
}

function newPatLineAreaAccSet(){
	var plId=apPlCBB.combobox("getValue");
	var paId=apPaCBB.combobox("getValue");
	var pdaIdsArr=apPdaCBB.combobox("getValues");
	var pdaIds=pdaIdsArr.sort().toString();
	if(pdaIds.substring(0,1)==",")
		pdaIds=pdaIds.substring(1);
	$.post(patrolMgmtPath+"newPatLineAreaAccSet",
		{plId:plId,paId:paId,pdaIds:pdaIds},
		function(data){
			if(data.message=="ok"){
				alert(data.info);
				openAddPlaasDialog(false);
				tab2.datagrid("load");
			}
		}
	,"json");
}

function editPatLineAreaAccSet(){
	var id = $("#edit_plaas_div #id").val();
	var plId=epPlCBB.combobox("getValue");
	var paId=epPaCBB.combobox("getValue");
	var pdaIdsArr=epPdaCBB.combobox("getValues");
	var pdaIds=pdaIdsArr.sort().toString();
	if(pdaIds.substring(0,1)==",")
		pdaIds=pdaIds.substring(1);
	$.post(patrolMgmtPath+"editPatLineAreaAccSet",
		{id:id,plId:plId,paId:paId,pdaIds:pdaIds},
		function(data){
			if(data.message=="ok"){
				alert(data.info);
				openEditPlaasDialog(false);
				tab2.datagrid("load");
			}
		}
	,"json");
}

function initTab2SearchLB(){
	$("#tab2_search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var name=$("#tab2_toolbar #name").val();
			tab2.datagrid("load",{name:name});
		}
	});
}

function initTab2AddLB(){
	$("#tab2_add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			openAddPlaasDialog(true);
		}
	});
}

function initTab2RemoveLB(){
	$("#tab2_remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			//deleteLineByIds();
		}
	});
}

function initTab2(){
	tab2=$("#tab2").datagrid({
		title:"路线、区域、设备台账查询",
		url:patrolMgmtPath+"queryPatLineAreaAccSetList",
		toolbar:"#tab2_toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"plName",title:"路线名称",width:150},
			{field:"paName",title:"区域名称",width:150},
			{field:"pdaNos",title:"设备台账",width:300},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
       			var rowJson = JSON.stringify(row).replace(/"/g, '&quot;');
            	var str="<a onclick=\"openEditPlaasDialog(true,"+rowJson+")\">编辑</a>&nbsp;&nbsp;"
        		+"<a onclick=\"openDetailLineDialog(true,"+rowJson+")\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{plName:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"plName",colspan:4});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function initAddPlaasDialog(){
	$("#add_plaas_dialog_div").dialog({
		title:"路线、区域、设备台账添加",
		width:setFitWidthInParent("#add_plaas_div","add_plaas_dialog_div"),
		height:350,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkAddPatLineAreaAccSet();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openAddPlaasDialog(false);
           }}
        ]
	});

	$("#add_plaas_dialog_div table").css("width",(setFitWidthInParent("#add_plaas_div","add_plaas_dialog_table"))+"px");
	$("#add_plaas_dialog_div table").css("magin","-100px");
	$("#add_plaas_dialog_div table td").css("padding-left","40px");
	$("#add_plaas_dialog_div table td").css("padding-right","20px");
	$("#add_plaas_dialog_div table td").css("font-size","15px");
	$("#add_plaas_dialog_div table .td1").css("width","30%");
	$("#add_plaas_dialog_div table .td2").css("width","60%");
	$("#add_plaas_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(aplaasdNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(aplaasdNum).css("color","#000");
	$(".panel.window .panel-title").eq(aplaasdNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(aplaasdNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(aplaasdNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(aplaasdNum).css("border-color","#ddd");

	$("#add_plaas_dialog_div #ok_but").css("left","30%");
	$("#add_plaas_dialog_div #ok_but").css("position","absolute");

	$("#add_plaas_dialog_div #cancel_but").css("left","50%");
	$("#add_plaas_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
	
	initAddPlaasPlCBB();
	initAddPlaasFirstDeptCBB();
	initAddPlaasSecondDeptCBB();
	initAddPlaasPaCBB();
	initAddPlaasPdaCBB();
}

function initEditPlaasDialog(){
	$("#edit_plaas_dialog_div").dialog({
		title:"路线、区域、设备台账编辑",
		width:setFitWidthInParent("#edit_plaas_div","edit_plaas_dialog_div"),
		height:350,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkEditPatLineAreaAccSet();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openEditPlaasDialog(false);
           }}
        ]
	});

	$("#edit_plaas_dialog_div table").css("width",(setFitWidthInParent("#edit_line_div","edit_plaas_dialog_table"))+"px");
	$("#edit_plaas_dialog_div table").css("magin","-100px");
	$("#edit_plaas_dialog_div table td").css("padding-left","40px");
	$("#edit_plaas_dialog_div table td").css("padding-right","20px");
	$("#edit_plaas_dialog_div table td").css("font-size","15px");
	$("#edit_plaas_dialog_div table .td1").css("width","30%");
	$("#edit_plaas_dialog_div table .td2").css("width","60%");
	$("#edit_plaas_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(eplaasdNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(eplaasdNum).css("color","#000");
	$(".panel.window .panel-title").eq(eplaasdNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(eplaasdNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(eplaasdNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(eplaasdNum).css("border-color","#ddd");

	$("#edit_plaas_dialog_div #ok_but").css("left","30%");
	$("#edit_plaas_dialog_div #ok_but").css("position","absolute");

	$("#edit_plaas_dialog_div #cancel_but").css("left","50%");
	$("#edit_plaas_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
	
	initEditPlaasPlCBB();
	initEditPlaasFirstDeptCBB();
	initEditPlaasSecondDeptCBB();
	initEditPlaasPaCBB();
	initEditPlaasPdaCBB();
}

function initAddPlaasPlCBB(){
	var data=[];
	data.push({"value":"","text":"请选择路线"});
	$.post(patrolMgmtPath+"queryLineCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			apPlCBB=$("#add_plaas_div #pl_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data
			});
		}
	,"json");
}

function initEditPlaasPlCBB(){
	var data=[];
	data.push({"value":"","text":"请选择路线"});
	$.post(patrolMgmtPath+"queryLineCBBList",
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			epPlCBB=$("#edit_plaas_div #pl_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data
			});
		}
	,"json");
}

function initAddPlaasFirstDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择一级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:0},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			apFdCBB=$("#apfd_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onSelect:function(){
					loadAddPlaasSecondDeptCBBData();
				}
			});
		}
	,"json");
}

function initEditPlaasFirstDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择一级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:0},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			epFdCBB=$("#epfd_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onSelect:function(){
					loadEditPlaasSecondDeptCBBData();
				}
			});
		}
	,"json");
}

function initAddPlaasSecondDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	apSdCBB=$("#apsd_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadAddPlaasPaCBBData();
		}
	});
}

function loadAddPlaasSecondDeptCBBData(){
	var parentId=apFdCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:parentId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			apSdCBB.combobox("loadData",data);
		}
	,"json");
}

function initEditPlaasSecondDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	epSdCBB=$("#epsd_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadEditPlaasPaCBBData();
		}
	});
}

function loadEditPlaasSecondDeptCBBData(){
	var parentId=epFdCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:parentId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			epSdCBB.combobox("loadData",data);
		}
	,"json");
}

function initAddPlaasPaCBB(){
	var data=[];
	data.push({"value":"","text":"请选择区域"});
	apPaCBB=$("#appa_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadAddPlaasPdaCBBData();
		}
	});
}

function loadAddPlaasPaCBBData(){
	var deptId=apSdCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择区域"});
	$.post(patrolMgmtPath+"queryAreaCBBList",
		{deptId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			apPaCBB.combobox("loadData",data);
		}
	,"json");
}

function initEditPlaasPaCBB(){
	var data=[];
	data.push({"value":"","text":"请选择区域"});
	epPaCBB=$("#eppa_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadEditPlaasPdaCBBData();
		}
	});
}

function loadEditPlaasPaCBBData(){
	var deptId=epSdCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择区域"});
	$.post(patrolMgmtPath+"queryAreaCBBList",
		{deptId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			epPaCBB.combobox("loadData",data);
		}
	,"json");
}

function initAddPlaasPdaCBB(){
	var data=[];
	data.push({"value":"","text":"请选择设备台账"});
	apPdaCBB=$("#appda_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		multiple:true
	});
}

function loadAddPlaasPdaCBBData(){
	var deptId=apSdCBB.combobox("getValue");
	var paId=apPaCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择设备台账"});
	$.post(deviceMgmtPath+"queryAreaAccCBBList",
		{deptId:deptId,paId:paId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].no});
			}
			apPdaCBB.combobox("loadData",data);
		}
	,"json");
}

function initEditPlaasPdaCBB(){
	var data=[];
	data.push({"value":"","text":"请选择设备台账"});
	epPdaCBB=$("#eppda_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		multiple:true
	});
}

function loadEditPlaasPdaCBBData(){
	var deptId=epSdCBB.combobox("getValue");
	var paId=epPaCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择设备台账"});
	$.post(deviceMgmtPath+"queryAreaAccCBBList",
		{deptId:deptId,paId:paId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].no});
			}
			epPdaCBB.combobox("loadData",data);
		}
	,"json");
}

function openAddPlaasDialog(flag){
	if(flag){
		$("#add_plaas_bg_div").css("display","block");
	}
	else{
		$("#add_plaas_bg_div").css("display","none");
	}
}

function openEditPlaasDialog(flag,row){
	if(flag){
		$("#edit_plaas_bg_div").css("display","block");
		$("#edit_plaas_div #id").val(row.id);
		epPlCBB.combobox("setValue",row.plId);
		epFdCBB.combobox("setValue",row.firstDeptId);
		loadEditPlaasSecondDeptCBBData();
		epSdCBB.combobox("setValue",row.secondDeptId);
		loadEditPlaasPaCBBData();
		epPaCBB.combobox("setValue",row.paId);
		loadEditPlaasPdaCBBData();
		epPdaCBB.combobox("setValues",row.pdaIds.split(","));
	}
	else{
		$("#edit_plaas_bg_div").css("display","none");
		$("#edit_plaas_div #id").val("");
		$("#edit_plaas_div #name").val("");
		epPlCBB.combobox("setValue","");
		epFdCBB.combobox("setValue","");
		epSdCBB.combobox("loadData",[]);
		epSdCBB.combobox("setValue","");
		epPaCBB.combobox("loadData",[]);
		epPaCBB.combobox("setValue","");
		epPdaCBB.combobox("loadData",[]);
		epPdaCBB.combobox("setValues","");
	}
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "tab1_div":
	case "tab2_div":
		space=250;
		break;
	case "add_line_dialog_div":
	case "edit_line_dialog_div":
	case "detail_line_dialog_div":
	case "add_plaas_dialog_div":
	case "edit_plaas_dialog_div":
		space=50;
		break;
	case "add_line_dialog_table":
	case "edit_line_dialog_table":
	case "detail_line_dialog_table":
	case "add_plaas_dialog_table":
	case "edit_plaas_dialog_table":
		space=68;
		break;
	}
	var width=$(parent).css("width");
	return width.substring(0,width.length-2)-space;
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="tab1_div" id="tab1_div">
	<div class="tab1_toolbar" id="tab1_toolbar">
		<div class="row_div">
			<span class="name_span">名称：</span>
			<input type="text" class="name_inp" id="name" placeholder="请输入路线名称"/>
			<span class="createTime_span">创建时间：</span>
			<input id="tab1_createTimeSt_dtb"/>
			-
			<input id="tab1_createTimeEt_dtb"/>
			<a class="tab1_search_but" id="tab1_search_but">查询</a>
			<a id="tab1_add_but">添加</a>
			<a id="tab1_remove_but">删除</a>
		</div>
	</div>
	<table id="tab1">
	</table>
</div>

<div class="add_line_bg_div" id="add_line_bg_div">
	<div class="add_line_div" id="add_line_div">
		<div class="add_line_dialog_div" id="add_line_dialog_div">
			<table>
			  <tr>
				<td class="td1" align="right">
					名称
				</td>
				<td class="td2">
					<input type="text" class="name_inp" id="name" placeholder="请输入路线名称" onfocus="focusAddLineName()" onblur="checkAddLineName()"/>
				</td>
			  </tr>
			</table>
		</div>
	</div>
</div>

<div class="edit_line_bg_div" id="edit_line_bg_div">
	<div class="edit_line_div" id="edit_line_div">
		<div class="edit_line_dialog_div" id="edit_line_dialog_div">
			<input type="hidden" id="id" name="id"/>
			<table>
			  <tr>
				<td class="td1" align="right">
					名称
				</td>
				<td class="td2">
					<input type="text" class="name_inp" id="name" placeholder="请输入路线名称" onfocus="focusEditLineName()" onblur="checkEditLineName()"/>
				</td>
			  </tr>
			</table>
		</div>
	</div>
</div>

<div class="detail_line_bg_div" id="detail_line_bg_div">
	<div class="detail_line_div" id="detail_line_div">
		<div class="detail_line_dialog_div" id="detail_line_dialog_div">
			<input type="hidden" id="id" name="id"/>
			<table>
			  <tr>
				<td class="td1" align="right">
					名称
				</td>
				<td class="td2">
					<span id="name_span"></span>
				</td>
			  </tr>
			</table>
		</div>
	</div>
</div>

<div class="tab2_div" id="tab2_div">
	<div class="tab2_toolbar" id="tab2_toolbar">
		<div class="row_div">
			<span class="plName_span">路线名称：</span>
			<input type="text" class="plName_inp" id="plName" placeholder="请输入路线名称"/>
			<span class="paName_span">区域名称：</span>
			<input type="text" class="paName_inp" id="paName" placeholder="请输入区域名称"/>
			<a class="tab2_search_but" id="tab2_search_but">查询</a>
			<a id="tab2_add_but">添加</a>
			<a id="tab2_remove_but">删除</a>
		</div>
	</div>
	<table id="tab2">
	</table>
</div>

<div class="add_plaas_bg_div" id="add_plaas_bg_div">
	<div class="add_plaas_div" id="add_plaas_div">
		<div class="add_plaas_dialog_div" id="add_plaas_dialog_div">
			<table>
			  <tr>
				<td class="td1" align="right">
					路线
				</td>
				<td class="td2">
					<input id="pl_cbb"/>
					<input type="hidden" id="plId" name="plId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					一级部门
				</td>
				<td class="td2">
					<input id="apfd_cbb"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					二级部门
				</td>
				<td class="td2">
					<input id="apsd_cbb"/>
					<input type="hidden" id="deptId" name="deptId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					区域
				</td>
				<td class="td2">
					<input id="appa_cbb"/>
					<input type="hidden" id="paId" name="paId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					设备台账
				</td>
				<td class="td2">
					<input id="appda_cbb"/>
					<input type="hidden" id="pdaId" name="pdaId"/>
				</td>
			  </tr>
			</table>
		</div>
	</div>
</div>

<div class="edit_plaas_bg_div" id="edit_plaas_bg_div">
	<div class="edit_plaas_div" id="edit_plaas_div">
		<div class="edit_plaas_dialog_div" id="edit_plaas_dialog_div">
			<input type="hidden" id="id" name="id"/>
			<table>
			  <tr>
				<td class="td1" align="right">
					路线
				</td>
				<td class="td2">
					<input id="pl_cbb"/>
					<input type="hidden" id="plId" name="plId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					一级部门
				</td>
				<td class="td2">
					<input id="epfd_cbb"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					二级部门
				</td>
				<td class="td2">
					<input id="epsd_cbb"/>
					<input type="hidden" id="deptId" name="deptId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					区域
				</td>
				<td class="td2">
					<input id="eppa_cbb"/>
					<input type="hidden" id="paId" name="paId"/>
				</td>
			  </tr>
			  <tr>
				<td class="td1" align="right">
					设备台账
				</td>
				<td class="td2">
					<input id="eppda_cbb"/>
					<input type="hidden" id="pdaId" name="pdaId"/>
				</td>
			  </tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>