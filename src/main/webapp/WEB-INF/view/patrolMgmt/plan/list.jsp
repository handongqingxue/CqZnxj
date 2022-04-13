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
.tab1_div .toolbar .name_span{
	margin-left: 13px;
}
.tab1_div .toolbar .name_inp{
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
var patrolMgmtPath=path+'patrolMgmt/';
var nav='${param.nav}';
$(function(){
	initSearchLB();
	initAddLB();
	initRemoveLB();
	initTab1();
});

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
		width:setFitWidthInParent("body"),
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
			{field:"ptNames",title:"班组",width:200},
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
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"&nav="+nav+"\">编辑</a>&nbsp;&nbsp;"
        			+"<a href=\"detail?id="+value+"&nav="+nav+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{name:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"name",colspan:9});
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
			
			$.post(patrolMgmtPath + "deleteTeam",
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
		<span class="name_span">计划名称：</span>
		<input type="text" class="name_inp" id="name" placeholder="请输入计划名称"/>
		<a class="search_but" id="search_but">查询</a>
		<a id="add_but">添加</a>
		<a id="remove_but">删除</a>
	</div>
	<table id="tab1">
	</table>
</div>
</body>
</html>