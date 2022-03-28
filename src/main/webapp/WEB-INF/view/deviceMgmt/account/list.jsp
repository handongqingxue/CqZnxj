<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.tab1_div{
	margin-top:30px;
	margin-left: 220px;
}
.tab1_div .toolbar{
	height:64px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .deviceNo_span,
.tab1_div .toolbar .row_div .pdName_span,
.tab1_div .toolbar .row_div .pdtName_span,
.tab1_div .toolbar .row_div .createTime_span,
.tab1_div .toolbar .row_div .startTime_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .deviceNo_inp,
.tab1_div .toolbar .row_div .pdName_inp,
.tab1_div .toolbar .row_div .pdtName_inp{
	width: 120px;height: 25px;
}
a {
  color: #333;
  text-decoration: none;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var deviceMgmtPath=path+'deviceMgmt/';
$(function(){
	initCreateTimeStDTB();
	initCreateTimeEtDTB();
	initStartTimeStDTB();
	initStartTimeEtDTB();
	initSearchLB();
	initAddLB();
	initRemoveLB();
	initTab1();
});

function initCreateTimeStDTB(){
	createTimeStDTB=$("#createTimeSt_dtb").datetimebox({
        required:false
    });
}

function initCreateTimeEtDTB(){
	createTimeEtDTB=$("#createTimeEt_dtb").datetimebox({
        required:false
    });
}

function initStartTimeStDTB(){
	startTimeStDTB=$("#startTimeSt_dtb").datetimebox({
        required:false
    });
}

function initStartTimeEtDTB(){
	startTimeEtDTB=$("#startTimeEt_dtb").datetimebox({
        required:false
    });
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var deviceNo=$("#toolbar #deviceNo_inp").val();
			var pdName=$("#toolbar #pdName_inp").val();
			var pdtName=$("#toolbar #pdtName_inp").val();
			var createTimeStart=createTimeStDTB.datetimebox("getValue");
			var createTimeEnd=createTimeEtDTB.datetimebox("getValue");
			var startTimeStart=startTimeStDTB.datetimebox("getValue");
			var startTimeEnd=startTimeEtDTB.datetimebox("getValue");
			tab1.datagrid("load",{deviceNo:deviceNo,pdName:pdName,pdtName:pdtName,createTimeStart:createTimeStart,
				createTimeEnd:createTimeEnd,startTimeStart:startTimeStart,startTimeEnd:startTimeEnd});
		}
	});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=deviceMgmtPath+"device/new";
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			deleteByIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"设备台账查询",
		url:deviceMgmtPath+"queryAccountList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"deviceNo",title:"设备编号",width:200},
			{field:"deviceName",title:"设备名称",width:200},
            {field:"pdtName",title:"设备类型",width:200},
			{field:"createTime",title:"创建时间",width:200},
			{field:"startTime",title:"启用时间",width:200},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"\">编辑</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{deviceNo:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"deviceNo",colspan:6});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function deleteByIds() {
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
			
			$.post(deviceMgmtPath + "deleteDevice",
				{ids:ids},
				function(result){
					if(result.status==1){
						alert(result.msg);
						location.href = location.href;
					}
					else{
						alert(result.msg);
					}
				}
			,"json");
			
		}
	});
}

function setFitWidthInParent(o){
	var width=$(o).css("width");
	return width.substring(0,width.length-2)-250;
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="tab1_div" id="tab1_div">
	<div class="toolbar" id="toolbar">
		<div class="row_div">
			<span class="deviceNo_span">设备编号：</span>
			<input type="text" class="deviceNo_inp" id="deviceNo_inp" placeholder="请输入设备编号"/>
			<span class="pdName_span">设备名称：</span>
			<input type="text" class="pdName_inp" id="pdName_inp" placeholder="请输入设备名称"/>
			<span class="pdtName_span">设备类型：</span>
			<input type="text" class="pdtName_inp" id="pdtName_inp" placeholder="请输入设备类型"/>
		</div>
		<div class="row_div">
			<span class="createTime_span">创建时间：</span>
			<input id="createTimeSt_dtb"/>
			-
			<input id="createTimeEt_dtb"/>
			<span class="startTime_span">启用时间：</span>
			<input id="startTimeSt_dtb"/>
			-
			<input id="startTimeEt_dtb"/>
			<a class="search_but" id="search_but">查询</a>
			<a id="add_but">添加</a>
			<a id="remove_but">删除</a>
		</div>
	</div>
	<table id="tab1">
	</table>
</div>
</body>
</html>