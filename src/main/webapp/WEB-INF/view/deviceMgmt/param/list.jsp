<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.tree_div{
	width:300px;
	margin-top:46px;
	margin-left: 200px;
	padding:1px;
	background-color: #F2F1F0;
}
.tree_div .title_div{
	height: 50px;
	line-height: 50px;
	margin: 20px 20px 0 20px;
	padding:0 20px 0 20px;
	background-color: #fff;
}
.tree{
	background-color: #fff;
}
.tree_div .space_div{
	height: 20px;
	margin: 0 20px 0 20px;
	background-color: #fff;
}
.tab1_div{
	margin-left: 520px;
}
.tab1_div .toolbar{
	height:64px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .sdn_span,
.tab1_div .toolbar .row_div .pdName_span,
.tab1_div .toolbar .row_div .pdaNo_span,
.tab1_div .toolbar .row_div .name_span,
.tab1_div .toolbar .row_div .createTime_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .sdn_inp,
.tab1_div .toolbar .row_div .pdName_inp,
.tab1_div .toolbar .row_div .pdaNo_inp,
.tab1_div .toolbar .row_div .name_inp{
	width: 120px;
	height: 25px;
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
var mainPath=path+'main/';
var deviceMgmtPath=path+'deviceMgmt/';
var deptId;
var nav='${param.nav}';
$(function(){
	initTree();
	initCreateTimeStDTB();
	initCreateTimeEtDTB();
	initSearchLB();
	initAddLB();
	initRemoveLB();
	initTab1();
	resizeDiv();
});

function resizeDiv(){
	var windowHeight=$(window).height();
	var treeDiv=$("#tree_div");
	treeDiv.css("height",windowHeight-46+"px");
	
	var tree=$("#tree");
	tree.css("margin","0 20px 0 20px");
	
	var treeDivHeight=$("#tree_div").height();
	$("#tab1_div").css("margin-top",-(treeDivHeight-20)+"px");
}

function initTree(){
	//var data=[{id:1,text:"aaa","children":[{id:3,text:"ccc"}]},{id:2,text:"bbb"}];
	$("#tree").tree({
        url:mainPath+'queryDeptTreeList',
        onClick : function(node) {
            // ??????/??????
            if (node.state === 'open') {
                $('#tree').tree('collapse', node.target);
            } else {
                $('#tree').tree('expand', node.target);
            }
        	
        	deptId=node.id;
        	loadTab1Data();
        },
        onLoadSuccess:function(node, data){// ?????????????????????????????????
        	console.log(data)
            //$('#tree').tree('collapseAll');
        	$(".tree-title").css("font-size","15px");
        	$(".tree-node").css("height","25px");
        } 
    });
}

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

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			loadTab1Data();
		}
	});
}

function loadTab1Data(){
	var secondDeptName=$("#toolbar #sdn_inp").val();
	var pdName=$("#toolbar #pdName_inp").val();
	var pdaNo=$("#toolbar #pdaNo_inp").val();
	var name=$("#toolbar #name").val();
	var createTimeStart=createTimeStDTB.datetimebox("getValue");
	var createTimeEnd=createTimeEtDTB.datetimebox("getValue");
	tab1.datagrid("load",{deptId:deptId,secondDeptName:secondDeptName,pdName:pdName,pdaNo:pdaNo,name:name,
		createTimeStart:createTimeStart,createTimeEnd:createTimeEnd});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=deviceMgmtPath+"param/new?nav="+nav;
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
		title:"??????????????????",
		url:deviceMgmtPath+"queryParamList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"firstDeptName",title:"????????????",width:150},
			{field:"secondDeptName",title:"????????????",width:150},
			{field:"pdName",title:"????????????",width:150},
			{field:"pdaNo",title:"????????????",width:150},
			{field:"name",title:"????????????",width:150},
			{field:"type",title:"????????????",width:150,formatter:function(value,row){
				var str;
				switch (value) {
				case 1:
					str="??????";
					break;
				case 2:
					str="??????";
					break;
				}
				return str+"???";
			}},
			{field:"unit",title:"????????????",width:150},
			{field:"warnUp",title:"????????????",width:150},
			{field:"warnDown",title:"????????????",width:150},
			{field:"createTime",title:"????????????",width:180},
            {field:"id",title:"??????",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"&nav="+nav+"\">??????</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"&nav="+nav+"\">??????</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{firstDeptName:"<div style=\"text-align:center;\">????????????<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"firstDeptName",colspan:11});
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
			
			$.post(deviceMgmtPath + "deleteParam",
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

//????????????id????????????????????????
function checkDeptIdInList(deptId,deptList){
	var flag=false;
	for (var i = 0; i < deptList.length; i++){
		if(deptId==deptList[i].id){
			flag=true;
			break;
		}
	}
	return flag;
}

function setFitWidthInParent(o){
	var width=$(o).css("width");
	return width.substring(0,width.length-2)-550;
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="tree_div" id="tree_div">
	<div class="title_div" id="title_div">????????????</div>
	<div id="tree">
	</div>
	<div class="space_div"></div>
</div>
<div class="tab1_div" id="tab1_div">
	<div class="toolbar" id="toolbar">
		<div class="row_div">
			<span class="sdn_span">???????????????</span>
			<input type="text" class="sdn_inp" id="sdn_inp" placeholder="?????????????????????"/>
			<span class="pdName_span">???????????????</span>
			<input type="text" class="pdName_inp" id="pdName_inp" placeholder="?????????????????????"/>
			<span class="pdaNo_span">???????????????</span>
			<input type="text" class="pdaNo_inp" id="pdaNo_inp" placeholder="?????????????????????"/>
		</div>
		<div class="row_div">
			<span class="name_span">???????????????</span>
			<input type="text" class="name_inp" id="name" placeholder="?????????????????????"/>
			<span class="createTime_span">???????????????</span>
			<input id="createTimeSt_dtb"/>
			-
			<input id="createTimeEt_dtb"/>
			<a class="search_but" id="search_but">??????</a>
			<a id="add_but">??????</a>
			<a id="remove_but">??????</a>
		</div>
	</div>
	<table id="tab1">
	</table>
</div>
</body>
</html>