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
var deviceMgmtPath=path+'deviceMgmt/';
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
			location.href=deviceMgmtPath+"type/new";
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			checkIfExistDeviceByTypeIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"设备类型查询",
		url:deviceMgmtPath+"queryTypeList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"name",title:"类名",width:150},
			{field:"createTime",title:"创建时间",width:180},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"edit?id="+value+"\">编辑</a>&nbsp;&nbsp;"
            		+"<a href=\"detail?id="+value+"\">详情</a>";
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

//验证设备类型id下是否存在设备
function checkIfExistDeviceByTypeIds() {
	var rows=tab1.datagrid("getSelections");
	if (rows.length == 0) {
		$.messager.alert("提示","请选择要删除的信息！","warning");
		return false;
	}
	
	$.messager.confirm("提示","确定要删除吗？",function(r){
		if(r){
			var ids = "";
			var names = "";
			for (var i = 0; i < rows.length; i++) {
				ids += "," + rows[i].id;
				names += "," + rows[i].name;
			}
			ids=ids.substring(1);
			names=names.substring(1);

			$.post(deviceMgmtPath + "checkIfExistDeviceByTypeIds",
				{typeIds:ids,typeNames:names},
				function(result){
					if(result.status==1){
						alert(result.msg);
						var delIds="";
						var idArr=ids.split(",");
						var pdtList=result.data;
						for (var i = 0; i < idArr.length; i++){
							var id=idArr[i];
							if(!checkTypeIdInList(id,pdtList)){//若不存在，则说明该类型下没有设备，就得删除掉
								delIds+=","+id;
							}
						}
						delIds=delIds.substring(1);
						if(delIds!="")//若有没有设备的设备类型id，则删除
							deleteByIds(delIds);
					}
					else{
						deleteByIds(ids);
					}
				}
			,"json");
			
		}
	});
}

function deleteByIds(ids){
	$.post(deviceMgmtPath + "deleteType",
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

//验证设备类型id是否存在于集合里
function checkTypeIdInList(typeId,pdtList){
	var flag=false;
	for (var i = 0; i < pdtList.length; i++){
		if(typeId==pdtList[i].id){
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
		<span class="name_span">类名：</span>
		<input type="text" class="name_inp" id="name" placeholder="请输入类名"/>
		<a class="search_but" id="search_but">查询</a>
		<a id="add_but">添加</a>
		<a id="remove_but">删除</a>
	</div>
	<table id="tab1">
	</table>
</div>
</body>
</html>