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
	height:32px;
}
.tab1_div .toolbar .deptName_span{
	margin-left: 13px;
}
.tab1_div .toolbar .deptName_inp{
	width: 120px;height: 25px;
}
.tab1_div .toolbar .search_but{
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
var deviceMgmtPath=path+'deviceMgmt/';
var nav='${param.nav}';
$(function(){
	initSearchLB();
	initAddLB();
	initTab1();
});

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var deptName=$("#toolbar #deptName").val();
			tab1.datagrid("load",{deptName:deptName});
		}
	});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			location.href=deviceMgmtPath+"dept/new?nav="+nav;
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"部门查询",
		url:deviceMgmtPath+"queryDeptList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"deptName",title:"名称",width:150},
			{field:"firstDeptName",title:"上级部门",width:150},
			{field:"createTime",title:"创建时间",width:180},
            {field:"deptId",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"detail?deptId="+value+"&nav="+nav+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{deptName:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"deptName",colspan:4});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

//验证部门id是否存在于集合里
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
	return width.substring(0,width.length-2)-250;
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="tab1_div" id="tab1_div">
	<div class="toolbar" id="toolbar">
		<span class="deptName_span">部门：</span>
		<input type="text" class="deptName_inp" id="deptName" placeholder="请输入部门名称"/>
		<a class="search_but" id="search_but">查询</a>
		<a id="add_but">添加</a>
	</div>
	<table id="tab1">
	</table>
</div>
</body>
</html>