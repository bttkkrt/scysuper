<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XA9GjGiTQiDS54abo0ga2iEd"></script>
	<!--加载鼠标绘制工具-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	
	
	<script type="text/javascript">
			var map;
			var overlays = [];
    		$(function(){
    			// 百度地图API功能
				map = new BMap.Map("allmap");
				map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
				map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    			var longitude ="";
    			var latitude = ""; 
    			if(longitude != null && longitude != "")//设置中心点
    			{
    				var point = new BMap.Point(longitude, latitude);
					map.centerAndZoom(point, 16);
					var marker = new BMap.Marker(point);  // 创建标注
					map.addOverlay(marker);              // 将标注添加到地图中
					overlays.push(marker);
    			}
    			var isshow="0";
    			if('${flag}'!= '3'){
    				if('${flag}'!= '1'){
    					var beans = eval("(" + '${mapBeans}' + ")");
    					for(var i=0;i<beans.length;i++){
    						if(beans[i].longitude!=null&&""!=beans[i].longitude&&"null"!=beans[i].longitude){
    							isshow = "1";
    							showInfoWin(beans[i].id,beans[i].longitude,beans[i].latitude,beans[i].name,beans[i].address,beans[i].linkman,beans[i].telephone,0)
    						}
    					}
    				}
    				if('${flag}'!= '2'){
    					var beans = eval("(" + '${mapNoBeans}' + ")");
    					for(var i=0;i<beans.length;i++){
    						if(beans[i].longitude!=null&&""!=beans[i].longitude&&"null"!=beans[i].longitude){
    							isshow = "1";
    							showInfoWin(beans[i].id,beans[i].longitude,beans[i].latitude,beans[i].name,beans[i].address,beans[i].linkMan,beans[i].telephone,1)
    						}
    					}
    				}
    				
    			}
    			if('${flag}'== '3'||isshow == "0"){
    				map.centerAndZoom("苏州",15);                     // 初始化地图,设置中心点坐标和地图级别。
    			}
    			
    			//实例化鼠标绘制工具
    		var drawingManager = new BMapLib.DrawingManager(map, {
        		isOpen: false, //是否开启绘制模式
        		enableDrawingTool: false, //是否显示工具栏
        		drawingToolOptions: {
            		anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            		offset: new BMap.Size(5, 5), //偏离值
            		scale: 0.8 ,//工具栏缩放比例
            		drawingModes:[
            			BMAP_DRAWING_MARKER
         			]
        		}
        		
    		});
    		});
    	function clearAll()
    	{
    		for(var i = 0; i < overlays.length; i++){
           	 	map.removeOverlay(overlays[i]);
        	}
    	}
    	//显示点
function showInfoWin(id,lon,lat,name,address,linkman,telephone,type){
if(lon!=null&&""!=lon&&"null"!=lon){
	var sContent = "<table>"+
					"<tr><th>企业名称：</th><td>"+name+"</td></tr>"+
					"<tr><th>企业地址：</th><td>"+address+"</td></tr>"+
					"<tr><th>联系人：    </th><td>"+linkman+"</td></tr>"+
					"<tr><th>联系号码：</th><td>"+telephone+"</td></tr>";
		sContent = sContent +"</table>";
	var point = new BMap.Point(lon, lat);
	
	var myIcon = "";
	if(type == "0"){
		myIcon =  new BMap.Icon("${ctx}/webResources/img/zha.png", new BMap.Size(26,33));
	}else if(type == "1"){
		myIcon =  new BMap.Icon("${ctx}/webResources/img/zhazhan.png", new BMap.Size(26,33));
	}
	
	var marker = new BMap.Marker(point,{icon:myIcon});
	
	var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
	map.centerAndZoom(point, 15);
	var label = new BMap.Label(name,{offset:new BMap.Size(20,-10)});
	marker.setLabel(label);
	map.addOverlay(marker);
	marker.addEventListener("click", function(){
	   		this.openInfoWindow(infoWindow);
		});
	}	
	}
	</script>
</head>
<body>
	<div id="allmap" style="width:100%;height:100%;"></div>
</body>
</html>
