<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.tree_div{
	width:300px;
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
	height:32px;
}
.tab1_div .toolbar .name_span,.tab1_div .toolbar .deptName_span,.tab1_div .toolbar .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .name_inp,.tab1_div .toolbar .deptName_inp{
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
var mainPath=path+'main/';
var deviceMgmtPath=path+'deviceMgmt/';
var deptId;
var nav='${param.nav}';
$(function(){
	initTree();
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
	$("#tree").tree({
        url:mainPath+'queryDeptTreeList',
        onClick : function(node) {
            // 展开/折叠
            if (node.state === 'open') {
                $('#tree').tree('collapse', node.target);
            } else {
                $('#tree').tree('expand', node.target);
            }
        	
        	deptId=node.id;
			loadTab1Data();
        },
        onLoadSuccess:function(node, data){// 加载成功后折叠所有节点
        	$(".tree-title").css("font-size","15px");
        	$(".tree-node").css("height","25px");
        } 
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
	var name=$("#toolbar #name_inp").val();
	var deptName=$("#toolbar #deptName_inp").val();
	tab1.datagrid("load",{name:name,deptId:deptId,deptName:deptName});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=deviceMgmtPath+"device/new?nav="+nav;
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
		title:"设备查询",
		url:deviceMgmtPath+"queryDeviceList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"name",title:"名称",width:200},
            {field:"deptName",title:"部门",width:200},
			{field:"makeDate",title:"创建时间",width:200},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"&nav="+nav+"\">编辑</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"&nav="+nav+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{name:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"name",colspan:4});
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
	return width.substring(0,width.length-2)-550;
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="tree_div" id="tree_div">
	<div class="title_div" id="title_div">部门列表</div>
	<div id="tree">
	</div>
	<div class="space_div"></div>
</div>
<div class="tab1_div" id="tab1_div">
	<div class="toolbar" id="toolbar">
		<span class="name_span">名称：</span>
		<input type="text" class="name_inp" id="name_inp" placeholder="请输入名称"/>
		<span class="deptName_span">部门：</span>
		<input type="text" class="deptName_inp" id="deptName_inp" placeholder="请输入部门"/>
		<a class="search_but" id="search_but">查询</a>
		<a id="add_but">添加</a>
		<a id="remove_but">删除</a>
	</div>
	<table id="tab1">
	</table>
</div>
</body>
</html>