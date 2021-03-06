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
}
.tab1_div .toolbar{
	height:64px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .plName_span,
.tab1_div .toolbar .row_div .paName_span,
.tab1_div .toolbar .row_div .pdName_span,
.tab1_div .toolbar .row_div .pdaNo_span,
.tab1_div .toolbar .row_div .pdpName_span,
.tab1_div .toolbar .row_div .pdpUnit_span,
.tab1_div .toolbar .row_div .time_span{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .plName_inp,
.tab1_div .toolbar .row_div .paName_inp,
.tab1_div .toolbar .row_div .pdName_inp,
.tab1_div .toolbar .row_div .pdaNo_inp,
.tab1_div .toolbar .row_div .pdpName_inp,
.tab1_div .toolbar .row_div .pdpUnit_inp{
	width: 120px;
	height: 25px;
}
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
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
var patrolMgmtPath=path+'patrolMgmt/';
var nav='${param.nav}';
$(function(){
	initStartTimeDTB();
	initEndTimeDTB();
	initSearchLB();
	initRemoveLB();
	initTab1();
});

function initStartTimeDTB(){
	startTimeDTB=$("#startTime_dtb").datetimebox({
        required:false
    });
}

function initEndTimeDTB(){
	endTimeDTB=$("#endTime_dtb").datetimebox({
        required:false
    });
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var plName=$("#toolbar #plName").val();
			var paName=$("#toolbar #paName").val();
			var pdName=$("#toolbar #pdName").val();
			var pdaNo=$("#toolbar #pdaNo").val();
			var pdpName=$("#toolbar #pdpName").val();
			var pdpUnit=$("#toolbar #pdpUnit").val();
			var startTime=startTimeDTB.datetimebox("getValue");
			var endTime=endTimeDTB.datetimebox("getValue");
			tab1.datagrid("load",{plName:plName,paName:paName,pdName:pdName,pdaNo:pdaNo,
				pdpName:pdpName,pdpUnit:pdpUnit,startTime:startTime,endTime:endTime});
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			//deleteByIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"??????????????????",
		url:patrolMgmtPath+"queryRecList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"plName",title:"????????????",width:150},
			{field:"paName",title:"????????????",width:150},
			{field:"pdName",title:"????????????",width:150},
			{field:"pdaNo",title:"????????????",width:150},
			{field:"pdpName",title:"????????????",width:150},
			{field:"pdpUnit",title:"??????",width:100},
			{field:"paramIfExce",title:"????????????",width:80,formatter:function(value,row){
				return value?"???":"???";
			}},
			{field:"pdpType",title:"????????????",width:150,formatter:function(value,row){
				var str;
				switch (value) {
				case 1:
					str=row.paramValue;
					break;
				case 2:
					str=row.paramExceInfo;
					break;
				}
				return str;
			}},
			{field:"pdLevel",title:"????????????",width:80,formatter:function(value,row){
				var str;
				switch (value) {
				case 1:
					str="???";
					break;
				case 2:
					str="???";
					break;
				case 3:
					str="???";
					break;
				}
				return str+="???";
			}},
			{field:"startTime",title:"????????????",width:180},
			{field:"endTime",title:"????????????",width:180},
            {field:"id",title:"??????",width:60,formatter:function(value,row){
            	var str="<a href=\"detail?id="+value+"&nav="+nav+"\">??????</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{plName:"<div style=\"text-align:center;\">????????????<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"plName",colspan:12});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function deleteByIds(ids){
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("??????","??????????????????????????????","warning");
		return false;
	}
	
	$.messager.confirm("??????","?????????????????????",function(r){
		if(r){
			var ids = "";
			for (var i = 0; i < rows.length; i++) {
				ids += "," + rows[i].id;
			}
			ids=ids.substring(1);
			
			$.post(patrolMgmtPath + "deletePlan",
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
			<span class="plName_span">???????????????</span>
			<input type="text" class="plName_inp" id="plName" placeholder="?????????????????????"/>
			<span class="paName_span">???????????????</span>
			<input type="text" class="paName_inp" id="paName" placeholder="?????????????????????"/>
			<span class="pdName_span">???????????????</span>
			<input type="text" class="pdName_inp" id="pdName" placeholder="?????????????????????"/>
			<span class="pdaNo_span">???????????????</span>
			<input type="text" class="pdaNo_inp" id="pdaNo" placeholder="?????????????????????"/>
		</div>
		<div class="row_div">
			<span class="pdpName_span">???????????????</span>
			<input type="text" class="pdpName_inp" id="pdpName" placeholder="?????????????????????"/>
			<span class="pdpUnit_span">?????????</span>
			<input type="text" class="pdpUnit_inp" id="pdpUnit" placeholder="???????????????"/>
			<span class="time_span">???????????????</span>
			<input id="startTime_dtb"/>
			-
			<input id="endTime_dtb"/>
			<a class="search_but" id="search_but">??????</a>
			<a id="remove_but">??????</a>
		</div>
	</div>
	<table id="tab1">
	</table>
</div>
</body>
</html>