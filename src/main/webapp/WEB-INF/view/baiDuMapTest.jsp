<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%String basePath=request.getScheme()+"://"+request.getServerName()+":"
	+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=basePath %>resource/js/jquery-3.3.1.js"></script>
<title>Insert title here</title>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<!-- 引入百度地图的API -->
<!-- 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
 -->
<script type="text/javascript" src="<%=basePath %>resource/js/baiDuApi"></script>
<title>百度地图</title>
<!-- 地图显示的样式 -->
<style type="text/css">
html{
	height:100%;
}
body{
	height:100%;
	margin:0px;
	padding:0px;
}
.container{
	width:1024px;
	height:600px;
}
</style>
<!-- 地图显示的初始化 -->
<script type="text/javascript">
var path='<%=basePath %>';
var map;
var search;
var list=[
	{longitude:116.380482,latitude:39.87649},
	{longitude:116.427337,latitude:39.87649},
	{longitude:116.447459,latitude:39.877265},
	{longitude:116.452059,latitude:39.883466},
	{longitude:116.451771,latitude:39.890221}
];
var listLast=list.length-1;
var carMk;
//var myBeginIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/car.png", new BMap.Size(52, 32),{imageOffset: new BMap.Size(0, 0) })
//var myEndIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/car.png", new BMap.Size(52, 32), {imageOffset: new BMap.Size(0, 0) })
var myBeginIcon = new BMap.Icon(path+"resource/image/car.png", new BMap.Size(52, 32),{imageOffset: new BMap.Size(0, 0) })
var myEndIcon = new BMap.Icon(path+"resource/image/car.png", new BMap.Size(52, 32), {imageOffset: new BMap.Size(0, 0) })

$(function(){
	initBaiDuMap();
	setTimeout("drawIcon()",1500);
});
        
function initBaiDuMap(){
	map = new BMap.Map("container");        //在container容器中创建一个地图,参数container为div的id属性;
    //var point = new BMap.Point(120.2,30.25);    //创建点坐标
    var point = new BMap.Point(116.380482,39.87649);    //创建点坐标
    map.centerAndZoom(point, 14);                //初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom();                //激活滚轮调整大小功能
    map.addControl(new BMap.NavigationControl());    //添加控件：缩放地图的控件，默认在左上角；
    map.addControl(new BMap.MapTypeControl());        //添加控件：地图类型控件，默认在右上方；
    map.addControl(new BMap.ScaleControl());        //添加控件：地图显示比例的控件，默认在左下方；
    map.addControl(new BMap.OverviewMapControl());  //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl
    search = new BMap.LocalSearch("中国", {
        onSearchComplete: function(result){
        if (search.getStatus() == BMAP_STATUS_SUCCESS){
            var res = result.getPoi(0);
            var point = res.point;
            map.centerAndZoom(point, 11);
        }
      },renderOptions: {  //结果呈现设置，
        map: map,  
        autoViewport: true,  
        selectFirstResult: true 
      } ,onInfoHtmlSet:function(poi,html){
        // alert(html.innerHTML)
      }
    });
    //初始化显示的城市
    //search.search(document.getElementById("cityName").value);
}

//设置城市的函数
function setCity(){
    search.search(document.getElementById("cityName").value);
}

function drawGreenLine(i){
	var polyline = new BMap.Polyline(
		[
			new BMap.Point(list[i].longitude,list[i].latitude),//起始点的经纬度
			new BMap.Point(list[i+1].longitude,list[i+1].latitude)//终点的经纬度
		], 
		{
			strokeColor:"red",//设置颜色
			strokeWeight:4, //宽度
			strokeOpacity:1
		}
	);//透明度
	map.addOverlay(polyline);
}

function drawIcon(){
	if(carMk){
		map.removeOverlay(carMk);
	}
	carMk2 = new BMap.Marker(
		new BMap.Point(list[0].longitude,
		list[0].latitude),//起始点的经纬度
		{icon:myBeginIcon}
	);
	map.addOverlay(carMk2);
	carMk = new BMap.Marker(
		new BMap.Point(list[listLast].longitude,
		list[listLast].latitude),//终点的经纬度
		{icon:myEndIcon}
	);
	map.addOverlay(carMk);
	for(var i=0;i<listLast;i++){
		drawGreenLine(i);
	}
}
</script>
</head>
<body>
<input type="text" id="cityName" value="枣庄"/>
    <input type="button" onclick="setCity()" value="查找" />
    <div class="container" id="container"></div>
</body>
</html>