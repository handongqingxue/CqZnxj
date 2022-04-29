<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.cen_div{
	margin-top:65px;
	margin-left: 220px;
	background-color: #EFF3F6;
	padding: 1px;
}
.cen_div .toolbar{
	height:40px;
	margin-top:10px;
	margin-left: 10px;
	margin-bottom:10px;
	background-color: #fff;
}
.cen_div .toolbar .row_div{
	height:40px;
}
.cen_div .toolbar .row_div .firstDept_span,
.cen_div .toolbar .row_div .secondDept_span,
.cen_div .toolbar .row_div .date_span,
.cen_div .toolbar .row_div .patrolTeam_span{
	margin-top:10px;
	margin-left: 13px;
	position: absolute;
}
.cen_div .toolbar .row_div .to_span{
	margin-top:10px;
	margin-left: 5px;
	position: absolute;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var mainPath=path+'main/';
var patrolMgmtPath=path+'patrolMgmt/';
var nav='${param.nav}';
$(function(){
	initFirstDeptCBB();
	initSecondDeptCBB();
	initPatrolTeamCBB();
	initStartDateDB();
	initEndDateDB();
	setTimeout(function(){
		var comboLength=$(".cen_div .toolbar .combo").length;
		for(var i=0;i<comboLength;i++){
			setComboLocation(i);
		}
	},"1000");
	//getCenAnaData();
});

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
			firstDeptCBB=$("#cen_div #firstDept_cbb").combobox({
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
	secondDeptCBB=$("#cen_div #secondDept_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadPatrolTeamCBBData();
		}
	});
}

function loadSecondDeptCBBData(){
	var deptId=firstDeptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			secondDeptCBB.combobox("loadData",data);
		}
	,"json");
}

function initPatrolTeamCBB(){
	var data=[];
	data.push({"value":"","text":"请选择巡检班组"});
	patrolTeamCBB=$("#cen_div #patrolTeam_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadPssCBBData();
		}
	});
}

function loadPatrolTeamCBBData(){
	var deptId=secondDeptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择巡检班组"});
	$.post(patrolMgmtPath+"queryTeamCBBListByDeptId",
		{deptId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
		}
	,"json");
}

function initStartDateDB(){
	startDateDB=$("#startDate_db").datebox({
		required:false
	});
}

function initEndDateDB(){
	endDateDB=$("#endDate_db").datebox({
		required:false
	});
}

function setComboLocation(num){
	var marginTop;
	var marginLeft=0;
	switch (num) {
	case 0:
	case 1:
		marginTop=10;
		marginLeft=100;
		break;
	case 2:
	case 3:
		marginTop=10;
		marginLeft=70;
		break;
	case 4:
		marginTop=10;
		marginLeft=35;
		break;
	}
	$(".cen_div .toolbar .combo").eq(num).css("margin-top",marginTop+"px");
	$(".cen_div .toolbar .combo").eq(num).css("margin-left",marginLeft+"px");
}

function getCenAnaData(){
	$.post(patrolMgmtPath+"getCenAnaData",
		{ptId:2},
		function(data){
			alert(data.reachDayCount+"/"+data.sumDayCount+","+data.reachPercent);
		}
	,"json");
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="cen_div" id="cen_div">
	<div class="toolbar" id="toolbar">
		<div class="row_div">
			<span class="firstDept_span">一级部门：</span>
			<input id="firstDept_cbb"/>
			<span class="secondDept_span">二级部门：</span>
			<input id="secondDept_cbb"/>
			<span class="patrolTeam_span">班组：</span>
			<input id="patrolTeam_cbb"/>
			<span class="date_span">时间：</span>
			<input id="startDate_db"/>
			<span class="to_span">至</span>
			<input id="endDate_db"/>
		</div>
	</div>
</div>
</body>
</html>