<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var patrolMgmtPath=path+'patrolMgmt/';
var nav='${param.nav}';
$(function(){
	getCenAnaData();
});

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

</body>
</html>