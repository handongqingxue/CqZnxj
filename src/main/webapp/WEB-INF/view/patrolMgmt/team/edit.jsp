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
.name_inp,.leader_inp{
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
		title:"巡检班组信息",
		width:setFitWidthInParent("body","edit_div"),
		height:350,
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

	initStartTimeCBB();
	initEndTimeCBB();
	initWorkDayCBB();
	initFirstDeptCBB();
	initSecondDeptCBB();
	initStaffIdsCBB();
	setTimeout(function(){
		loadSecondDeptCBBData();
		loadStaffIdsCBBData();
	},1000);
}

function initStartTimeCBB(){
	var startTime='${requestScope.pt.startTime}';
	var stArr=startTime.split(":");
	
	var sthData=[];
	sthData.push({"value":"","text":"时"});
	for(var i=0;i<24;i++){
		sthData.push({"value":i+"","text":i+""});
	}
	sthCBB=$("#sth_cbb").combobox({
		width:50,
		valueField:"value",
		textField:"text",
		data:sthData
	});
	var sth=stArr[0];
	sthCBB.combobox("setValue",sth);

	var stmData=[];
	stmData.push({"value":"","text":"分"});
	for(var i=0;i<60;i++){
		stmData.push({"value":(i<10?"0"+i:i)+"","text":(i<10?"0"+i:i)+""});
	}
	stmCBB=$("#stm_cbb").combobox({
		width:50,
		valueField:"value",
		textField:"text",
		data:stmData
	});
	var stm=stArr[1];
	stmCBB.combobox("setValue",stm);

	var stsData=[];
	stsData.push({"value":"","text":"秒"});
	for(var i=0;i<60;i++){
		stsData.push({"value":(i<10?"0"+i:i)+"","text":(i<10?"0"+i:i)+""});
	}
	stsCBB=$("#sts_cbb").combobox({
		width:50,
		valueField:"value",
		textField:"text",
		data:stsData
	});
	var sts=stArr[2];
	stsCBB.combobox("setValue",sts);
}

function initEndTimeCBB(){
	var endTime='${requestScope.pt.endTime}';
	var etArr=endTime.split(":");
	
	var ethData=[];
	ethData.push({"value":"","text":"时"});
	for(var i=0;i<24;i++){
		ethData.push({"value":i+"","text":i+""});
	}
	ethCBB=$("#eth_cbb").combobox({
		width:50,
		valueField:"value",
		textField:"text",
		data:ethData
	});
	var eth=etArr[0];
	ethCBB.combobox("setValue",eth);

	var etmData=[];
	etmData.push({"value":"","text":"分"});
	for(var i=0;i<60;i++){
		etmData.push({"value":(i<10?"0"+i:i)+"","text":(i<10?"0"+i:i)+""});
	}
	etmCBB=$("#etm_cbb").combobox({
		width:50,
		valueField:"value",
		textField:"text",
		data:etmData
	});
	var etm=etArr[1];
	etmCBB.combobox("setValue",etm);

	var etsData=[];
	etsData.push({"value":"","text":"秒"});
	for(var i=0;i<60;i++){
		etsData.push({"value":(i<10?"0"+i:i)+"","text":(i<10?"0"+i:i)+""});
	}
	etsCBB=$("#ets_cbb").combobox({
		width:50,
		valueField:"value",
		textField:"text",
		data:etsData
	});
	var ets=etArr[2];
	etsCBB.combobox("setValue",ets);
}

function initWorkDayCBB(){
	var data=[];
	data.push({"value":"","text":"请选择上班日"});
	data.push({"value":"1","text":"星期一"});
	data.push({"value":"2","text":"星期二"});
	data.push({"value":"3","text":"星期三"});
	data.push({"value":"4","text":"星期四"});
	data.push({"value":"5","text":"星期五"});
	data.push({"value":"6","text":"星期六"});
	data.push({"value":"7","text":"星期日"});
	wdCBB=$("#edit_div #workDay_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		multiple:true,
		onLoadSuccess:function(){
			$(this).combobox("setValues",'${requestScope.pt.workDay }'.split(","));
		}
	});
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
					$(this).combobox("setValue",'${requestScope.pt.firstDeptId }');
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
		onLoadSuccess:function(){
			$(this).combobox("setValue",'${requestScope.pt.secondDeptId }');
		},
		onSelect:function(){
			loadStaffIdsCBBData();
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

function initStaffIdsCBB(){
	var data=[];
	data.push({"value":"","text":"请选择班组人员"});
	staffIdsCBB=$("#edit_div #staffIds_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		multiple:true,
		onLoadSuccess:function(){
			$(this).combobox("setValues",'${requestScope.pt.staffIds }'.split(","));
		}
	});
}

function loadStaffIdsCBBData(){
	var deptId=secondDeptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择班组人员"});
	$.post(mainPath+"queryStaffCBBList",
		{deptId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			staffIdsCBB.combobox("loadData",data);
		}
	,"json");
}

function checkNew(){
	if(checkName()){
		if(checkLeader()){
			if(checkStartTime()){
				if(checkEndTime()){
					if(checkWorkDay()){
						if(checkFirstDeptId()){
							if(checkSecondDeptId()){
								if(checkStaffIds()){
									editTeam();
								}
							}
						}
					}
				}
			}
		}
	}
}

function editTeam(){
	var sth=sthCBB.combobox("getValue");
	var stm=stmCBB.combobox("getValue");
	var sts=stsCBB.combobox("getValue");
	$("#edit_div #startTime").val(sth+":"+stm+":"+sts);
	
	var eth=ethCBB.combobox("getValue");
	var etm=etmCBB.combobox("getValue");
	var ets=etsCBB.combobox("getValue");
	$("#edit_div #endTime").val(eth+":"+etm+":"+ets);

	var workDayArr=wdCBB.combobox("getValues");
	var workDay=workDayArr.sort().toString();
	if(workDay.substring(0,1)==",")
		workDay=workDay.substring(1);
	$("#edit_div #workDay").val(workDay);
	var deptId=secondDeptCBB.combobox("getValue");
	$("#edit_div #deptId").val(deptId);
	var staffIdsArr=staffIdsCBB.combobox("getValues");
	var staffIds=staffIdsArr.sort().toString();
	if(staffIds.substring(0,1)==",")
		staffIds=staffIds.substring(1);
	$("#edit_div #staffIds").val(staffIds);
	
	var formData = new FormData($("#form1")[0]);
	$.ajax({
		type:"post",
		url:patrolMgmtPath+"editTeam",
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

function checkStartTime(){
	var sth=sthCBB.combobox("getValue");
	var stm=stmCBB.combobox("getValue");
	var sts=stsCBB.combobox("getValue");
	if(sth==null||sth==""||stm==null||stm==""||sts==null||sts==""){
		alert("请选择巡检开始时间");
    	return false;
	}
	else
		return true;
}

function checkEndTime(){
	var eth=ethCBB.combobox("getValue");
	var etm=etmCBB.combobox("getValue");
	var ets=etsCBB.combobox("getValue");
	if(eth==null||eth==""||etm==null||etm==""||ets==null||ets==""){
		alert("请选择巡检结束时间");
    	return false;
	}
	else
		return true;
}

//验证上班日
function checkWorkDay(){
	var workDay=wdCBB.combobox("getValues");
	if(workDay==null||workDay==""){
	  	alert("请选择上班日");
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

//验证班组人员
function checkStaffIds(){
	var staffIds=staffIdsCBB.combobox("getValues");
	if(staffIds==null||staffIds==""){
	  	alert("请选择班组人员");
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
	<div class="page_location_div">巡检班组-编辑</div>
	
	<div id="edit_div">
		<form id="form1" name="form1" method="post" action="" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${requestScope.pt.id }"/>
		<table>
		  <tr>
			<td class="td1" align="right">
				班组名称
			</td>
			<td class="td2">
				<input type="text" class="name_inp" id="name" name="name" value="${requestScope.pt.name }" placeholder="请输入班组名称" onfocus="focusName()" onblur="checkName()"/>
			</td>
			<td class="td1" align="right">
				负责人
			</td>
			<td class="td2">
				<input type="text" class="leader_inp" id="leader" name="leader" value="${requestScope.pt.leader }" placeholder="请输入负责人" onfocus="focusLeader()" onblur="checkLeader()"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				巡检开始时间
			</td>
			<td class="td2">
				<input type="text" id="sth_cbb"/>
				:
				<input type="text" id="stm_cbb"/>
				:
				<input type="text" id="sts_cbb"/>
				<input type="hidden" id="startTime" name="startTime" value="${requestScope.pt.startTime }"/>
			</td>
			<td class="td1" align="right">
				巡检结束时间
			</td>
			<td class="td2">
				<input type="text" id="eth_cbb"/>
				:
				<input type="text" id="etm_cbb"/>
				:
				<input type="text" id="ets_cbb"/>
				<input type="hidden" id="endTime" name="endTime" value="${requestScope.pt.endTime }"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				上班日
			</td>
			<td class="td2">
				<input type="text" id="workDay_cbb"/>
				<input type="hidden" id="workDay" name="workDay" value="${requestScope.pt.workDay }"/>
			</td>
			<td class="td1" align="right">
				一级部门
			</td>
			<td class="td2">
				<input id="firstDept_cbb"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				二级部门
			</td>
			<td class="td2">
				<input id="secondDept_cbb"/>
				<input type="hidden" id="deptId" name="deptId" value="${requestScope.pt.deptId }"/>
			</td>
			<td class="td1" align="right">
				班组人员
			</td>
			<td class="td2">
				<input type="text" id="staffIds_cbb"/>
				<input type="hidden" id="staffIds" name="staffIds"/>
			</td>
		  </tr>
		  <tr>
			<td class="td1" align="right">
				创建时间
			</td>
			<td class="td2">
				${requestScope.pt.createTime }
			</td>
			<td class="td1" align="right">
				最近修改时间
			</td>
			<td class="td2">
				${requestScope.pt.updateTime }
			</td>
		  </tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>