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
.tab1_div .toolbar{
	height:32px;
}
.tab1_div .toolbar .name_span{
	margin-left: 13px;
}
.tab1_div .toolbar .name_inp{
	width: 120px;height: 25px;
}
.tab1_div .toolbar .search_but{
	margin-left: 13px;
}

.show_tracking_bg_div{
	width: 100%;
	height: 100%;
	background-color: rgba(0,0,0,.45);
	position: fixed;
	z-index: 9016;
	display:none;
}
.show_tracking_div{
	width: 1500px;
	height: 610px;
	margin: 100px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}
.show_tracking_div .toolbar{
	height:32px;
	margin-top: 10px;
}
.show_tracking_div .toolbar .areaName_span,
.show_tracking_div .toolbar .staffName_span,
.show_tracking_div .toolbar .patrolTime_span,
.show_tracking_div .toolbar .tracking_search_but{
	margin-left: 13px;
}
.gjckCanvas_div{
	width: 100%;
}

.load_gj_div{
	width: 100%;
	height:100%;
	background-color: rgba(0,0,0,0.5);
	position: fixed;
	display:none;
	z-index: 9025;
}
.load_gj_div .text_div{
	width: 100%;
	color:#fff;
	text-align:center;
	font-size:25px;
	top:45%;
	position: absolute;
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
var dialogTop=10;
var dialogLeft=20;
var nav='${param.nav}';
var stdNum=0;

var gjckCanvas;
var gjckCanvasImgSrc;
var gjckCanvasMinWidth=20404/5;
var gjckCanvasMinHeight=9692/5;
var gjckCanvasMaxWidth=20404/5;
var gjckCanvasMaxHeight=9692/5;
var gjckCanvasStyleWidth;//gjckCanvasMinWidth
var gjckCanvasStyleHeight;//gjckCanvasMinHeight
var gjckCanvasWidth;//=gjckCanvasMaxWidth;
var gjckCanvasHeight;//=gjckCanvasMaxHeight;
var widthScale;
var heightScale;
var lineWidth=5;
var trackingList;
var loadingGJ=false;

$(function(){
	initSearchLB();
	initAddLB();
	initRemoveLB();
	initTab1();
	initShowTrackingDialog();//0
	jiSuanScale();
	initAreaCBB();
	initStaffCBB();
	initTimeStDTB();
	initTimeEtDTB();
	initTrackingSearchLB();
	
	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	var stdpw=$("body").find(".panel.window").eq(stdNum);
	var stdws=$("body").find(".window-shadow").eq(stdNum);
	
	var stdDiv=$("#show_tracking_div");
	stdDiv.append(stdpw);
	stdDiv.append(stdws);
}

function initAreaCBB(){
	var data=[];
	data.push({"value":"","text":"请选择区域"});
	data.push({"value":1,"text":"区域1"});
	/*
	$.post(mainPath+"queryDeptCBBList",
		{parentId:0},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			*/
			areaCBB=$("#area_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data
			});
			/*
		}
	,"json");
	*/
}

function initStaffCBB(){
	var data=[];
	data.push({"value":"","text":"请选择人员"});
	data.push({"value":2,"text":"铁驴"});
	/*
	$.post(mainPath+"queryDeptCBBList",
		{parentId:0},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			*/
			staffCBB=$("#staff_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data
			});
			/*
		}
	,"json");
	*/
}

function initTimeStDTB(){
	timeStDTB=$("#timeSt_dtb").datetimebox({
        required:false
    });
}

function initTimeEtDTB(){
	timeEtDTB=$("#timeEt_dtb").datetimebox({
        required:false
    });
}

function initTrackingSearchLB(){
	$("#tracking_search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			getGJCKCanvasData();
		}
	});
}

function checkArea(){
	var areaId=areaCBB.combobox("getValue");
	if(areaId==""||areaId==null){
		alert("请选择区域");
		return false;
	}
	else
		return true;
}

function checkStaff(){
	var tagId=staffCBB.combobox("getValue");
	if(tagId==""||tagId==null){
		alert("请选择人员");
		return false;
	}
	else
		return true;
}

function getGJCKCanvasData(){
	if(checkArea()){
		if(checkStaff()){
			showLoadGJDiv(true);
			var areaId=areaCBB.combobox("getValue");
			var tagId=staffCBB.combobox("getValue");
			var timeStart=timeStDTB.datetimebox("getValue");
			var timeEnd=timeEtDTB.datetimebox("getValue");
			$.post(patrolMgmtPath + "getGJCKCanvasData",
				{areaId:areaId,tagId:tagId,timeStart:timeStart,timeEnd:timeEnd},
				function(data){
					if(data.message=="ok"){
						trackingList=data.trackingList;
						initGJCKCanvas();
					}
				}
			,"json");
		}
	}
}

function jiSuanScale(){
	//var areaWidth=area.width;
	//var areaLength=area.length;
	var areaWidth=2040;
	var areaLength=969;
	
	gjckCanvasStyleWidth=gjckCanvasMinWidth;
	gjckCanvasStyleHeight=gjckCanvasMinHeight;
	
	//gjckCanvasMaxWidth=area.picWidth;
	//gjckCanvasMaxHeight=area.picHeight;
	gjckCanvasMaxWidth=2040;
	gjckCanvasMaxHeight=969;

	gjckCanvasWidth=gjckCanvasMaxWidth;
	gjckCanvasHeight=gjckCanvasMaxHeight;
	gjckCanvasImgSrc=path+"upload/588mx252m.png";

	widthScale=areaWidth/gjckCanvasWidth;//这个宽度比例永远是地图区域宽度比地图图片宽度，便于正确缩放
	heightScale=areaLength/gjckCanvasHeight;//这个高度比例永远是地图区域高度比地图图片高度，便于正确缩放
}

function initGJCKCanvas(){
	var gjckCanvasImg = new Image();
	gjckCanvasImg.src=gjckCanvasImgSrc;
	gjckCanvas = document.createElement("canvas");
	gjckCanvas.id="gjckCanvas";
	gjckCanvas.style.width=gjckCanvasStyleWidth+"px";//通过缩放来改变画布大小，画布大小改变后，上面的定位点位置也就跟着改变了
	gjckCanvas.style.height=gjckCanvasStyleHeight+"px";
	gjckCanvas.width=gjckCanvasWidth;
	gjckCanvas.height=gjckCanvasHeight;
	gjckCanvasContext = gjckCanvas.getContext("2d");
	gjckCanvasImg.onload=function(){
		if(loadingGJ)
			showLoadGJDiv(false);
		gjckCanvasContext.drawImage(gjckCanvasImg, 0, 0, gjckCanvasWidth, gjckCanvasHeight);
		if(trackingList!=undefined){//判断集合是否存在，第一次访问页面是不存在的，搜索之后才存在
			for(var i=0;i<trackingList.length;i++){
				if(i>=1){
					var tr1=trackingList[i-1];
					var tr2=trackingList[i];
					setPointLocation(gjckCanvasContext,tr1.x,tr1.y,tr2.x,tr2.y);
					//if(i==trackingList.length-1)
						//setEntityLocation(gjckCanvasContext,tr2.x,tr2.y,staffName,tr2.floor);
				}
			}
		}
		
		var preGjckCanvas=document.getElementById("gjckCanvas");
		preGjckCanvas.parentNode.removeChild(preGjckCanvas);
		var gjckCanvasDiv=document.getElementById("gjckCanvas_div");
		gjckCanvasDiv.appendChild(gjckCanvas);
	}
}

function setPointLocation(context,x1,y1,x2,y2){
	context.strokeStyle = 'red';//点填充
	context.fillStyle='red';
	context.lineWidth=lineWidth;
	context.beginPath();
	context.moveTo(x1/widthScale, gjckCanvasHeight-y1/heightScale);//起始位置
	context.lineTo(x2/widthScale, gjckCanvasHeight-y2/heightScale);//停止位置
	context.stroke();
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var name=$("#toolbar #name").val();
			tab1.datagrid("load",{name:name});
		}
	});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=patrolMgmtPath+"plan/new?nav="+nav;
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
		title:"巡检计划查询",
		url:patrolMgmtPath+"queryPlanList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"name",title:"名称",width:150},
			{field:"type",title:"类型",width:100,formatter:function(value,row){
				var str;
				switch (value) {
				case 1:
					str="日常";
					break;
				case 2:
					str="临时";
					break;
				}
				return str;
			}},
			{field:"ptName",title:"班组",width:200},
			{field:"psNames",title:"人员",width:200},
			{field:"plNames",title:"路线",width:200},
			{field:"createTime",title:"创建时间",width:180},
			{field:"startDate",title:"开始日期",width:120},
			{field:"endDate",title:"结束日期",width:120},
			{field:"state",title:"状态",width:150,formatter:function(value,row){
				var str;
				switch (value) {
				case 1:
					str="未开始";
					break;
				case 2:
					str="进行中";
					break;
				case 3:
					str="已结束";
					break;
				case 4:
					str="暂停中";
					break;
				}
            	return str;
			}},
            {field:"id",title:"操作",width:130,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"&nav="+nav+"\">编辑</a>&nbsp;&nbsp;"
        			+"<a href=\"detail?id="+value+"&nav="+nav+"\">详情</a>&nbsp;&nbsp;"
        			+"<a onclick=\"openShowTrackingDialog(true)\">轨迹</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{name:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"name",colspan:10});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function initShowTrackingDialog(){
	$("#show_tracking_dialog_div").dialog({
		title:"轨迹查看",
		width:setFitWidthInParent("#show_tracking_div","show_tracking_dialog_div"),
		height:550,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openShowTrackingDialog(false);
           }}
        ]
	});

	$(".panel.window").eq(stdNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(stdNum).css("color","#000");
	$(".panel.window .panel-title").eq(stdNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(stdNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(stdNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(stdNum).css("border-color","#ddd");

	$("#show_tracking_dialog_div #cancel_but").css("left","45%");
	$("#show_tracking_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function deleteByIds(ids){
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

function openShowTrackingDialog(flag){
	if(flag){
		$("#show_tracking_bg_div").css("display","block");
	}
	else{
		$("#show_tracking_bg_div").css("display","none");
	}
}

function showLoadGJDiv(flag){
	if(flag){
		loadingGJ=true;
		$("#load_gj_div").css("display","block");
	}
	else{
		loadingGJ=false;
		$("#load_gj_div").css("display","none");
	}
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "tab1_div":
		space=250;
		break;
	case "show_tracking_dialog_div":
		space=50;
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
	<div class="toolbar" id="toolbar">
		<span class="name_span">计划名称：</span>
		<input type="text" class="name_inp" id="name" placeholder="请输入计划名称"/>
		<a class="search_but" id="search_but">查询</a>
		<a id="add_but">添加</a>
		<a id="remove_but">删除</a>
	</div>
	<table id="tab1">
	</table>
</div>

<div class="show_tracking_bg_div" id="show_tracking_bg_div">
	<div class="show_tracking_div" id="show_tracking_div">
		<div class="show_tracking_dialog_div" id="show_tracking_dialog_div">
			<div class="toolbar">
				<span class="areaName_span">区域：</span>
				<input id="area_cbb"/>
				<span class="staffName_span">人员：</span>
				<input id="staff_cbb"/>
				<span class="patrolTime_span">巡检时间：</span>
				<input id="timeSt_dtb"/>
				-
				<input id="timeEt_dtb"/>
				<a class="tracking_search_but" id="tracking_search_but">查询</a>
			</div>
			<div class="gjckCanvas_div" id="gjckCanvas_div">
				<canvas id="gjckCanvas">
				</canvas>
			</div>
		</div>
	</div>
</div>

<div class="load_gj_div" id="load_gj_div">
	<div class="text_div">轨迹加载中</div>
</div>
</body>
</html>