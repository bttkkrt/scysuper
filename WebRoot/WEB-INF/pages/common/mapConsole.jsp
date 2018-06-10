<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@include file="/h-ui/lib-css.jsp" %>
<title>首页</title>
</head>
<body>
<!-- <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页  
<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
 -->
<div class="container" >
 	<div class="col-12 pr-20">
				<ul   class="Huifold" id="Huifold3">
  					<li class="item">
   						 <div class="panel-info" style="height: 560px;">
   						 <s:if test='#request.myCompany !=null'>
   						 </s:if>
   						 <s:else>
   						 	<div class="col-12 pr-5 pb-5" >
   						 	<form id="myform">
   						 			企业名称 : <input type="text" class='myInput' name="companyBackUp.companyname">
   						 	<c:set var="deptCodeLength"  value="${fn:length(curr_user.dept.deptCode)}"/>
   						 	<c:if test="${deptCodeLength<12}">  
   						 			所属区域 : 
   						 			<c:if test="${deptCodeLength<6}">  
   						 			<cus:SelectOneTag property="companyBackUp.county"  class="myInput" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"/> 
									 </c:if>	 	
									<select id="dwdz1" name="companyBackUp.dwdz1"  class="myInput"   ><option value="">请选择</option></select>
   						 	</c:if>
   						 			行业分类 :<cus:SelectOneTag property="companyBackUp.hyflOneLevel" defaultText='行业一级分类' codeName="经济行业分类" 
										value="${companyBackUp.hyflOneLevel}"  class="myInput" />
   						 			<%-- 企业类型 : <cus:SelectOneTag property="companyBackUp.qylx" class="myInput"defaultText='请选择' codeName="企业类型"  />
   						 			行业分类 : <cus:SelectOneTag property="companyBackUp.hyfl"class="myInput" defaultText='请选择' codeName="企业行业分类"   /> --%>
   						 			<button type="button" class="btn btn-success radius" onclick="clearAllCompany();searchCompany()">查询</button>&nbsp;&nbsp; 
   						 			<button type="button" class="btn btn-primary radius" onclick="clearAllCompany()">隐藏</button>
   						 	</form>
   						 	</div>
   						 	 </s:else>
   						 <div class="col-12 pr-10" id="allmap" style="height: 100%;"></div>
   						 </div>
 					 </li>
				</ul>
			</div> 

 
 
</div>
<%@include file="/h-ui/lib-js.jsp" %>

<script type="text/javascript" src="${ctx}/h-ui/lib/echarts/3.1.10/echarts-all.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XA9GjGiTQiDS54abo0ga2iEd"></script>
<!--加载鼠标绘制工具-->
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
 $.Huifold("#Huifold1 .item h4","#Huifold1 .item .panel-info","fast",1,"click"); 
 $.Huifold("#Huifold2 .item h4","#Huifold2 .item .panel-info","fast",1,"click"); 
 $.Huifold("#Huifold3 .item h4","#Huifold3 .item .panel-info","fast",1,"click"); 
 $.Huifold("#Huifold4 .item h4","#Huifold4 .item .panel-info","fast",1,"click"); 
 $.Huifold("#Huifold5 .item h4","#Huifold5 .item .panel-info","fast",1,"click"); 
// $.Huifold("#Huifold6 .item h4","#Huifold6 .item .panel-info","fast",1,"click"); 
 
	var map;
 	var load;
	var overlays = [];
	var allCompanyOverlays = [];
	var tempSearchResult="";
	var defaultShowLampZoom =13;
	
 $(function () {
  			initMap();
});
 
 //地图
 

	function initMap(){
		
    			// 百度地图API功能
				map = new BMap.Map("allmap");
				map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
				map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
				var myCompany = "${myCompany}";
				/* if(myCompany != null && myCompany != ""){
					 showInfoWin('${myCompany.companyId}','${myCompany.longitude}','${myCompany.latitude}','${myCompany.companyname}','${myCompany.dwdz}','${myCompany.fddbr}','${myCompany.fddbrlxhm}',0)
				} */
    		 map.centerAndZoom("抚顺",defaultShowLampZoom); 
    			/* var roleName = "${roleName}";
    			if(roleName.indexOf("A45") == -1){
    			}   */
    				searchCompany();
    				map.addEventListener("moveend", queryInRect);  
    				map.addEventListener("zoomend", queryInRect);  
    		} 
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
	var label = new BMap.Label(name,{offset:new BMap.Size(20,-10)});
	marker.setLabel(label);
	map.addOverlay(marker);
	marker.addEventListener("click", function(){
	   		this.openInfoWindow(infoWindow);
		});
	}	
	}
 function searchCompany(){
	var load = layer.load(0, {shade: true});
    $.ajax({
	    url: '${ctx}/searchCompany.action',
	    type: 'post',
	    dataType: 'json',
	    data:$("#myform").serialize() ,
	    error: function(){
	    	layer.close(load);
	         alert( '获取网格企业信息失败！');
	    },
	    success: function(data){
	    	debugger;
	    	tempSearchResult =data;
	    	if(tempSearchResult.length>0){
				if(tempSearchResult[0]!=null&&""!=tempSearchResult[0].longitude&&"null"!=tempSearchResult[0].longitude){
					var longitude = tempSearchResult[0].longitude;
					var latitude = tempSearchResult[0].latitude;
					var point = new BMap.Point(longitude,latitude);
					 		var marker = new BMap.Marker(point);  // 创建标注
					 		 //画图  
					 		 var label = new BMap.Label(tempSearchResult[0].companyname,{offset:new BMap.Size(20,-10)});
					 	      marker.setLabel(label);
					 		map.addOverlay(marker);              // 将标注添加到地图中
					 		addCompanyClickHandler(tempSearchResult[0],marker);
					 		map.panTo(point);     
					 		map.centerAndZoom(point, 14);
					 		allCompanyOverlays.push(marker);
				}
			} 
	    	layer.close(load);
			 	//queryInRect();
	 }});
	
} 

    function addCompanyClickHandler(company,marker){
    	marker.addEventListener("click",function(e){
    		showCompanyInfo(company,e)}
    	);
    }
    function showCompanyInfo(company,e){
    	var opts = {
    			width : 280,     // 信息窗口宽度
    			height: 140,     // 信息窗口高度
    			title : " " , // 信息窗口标题
    			enableMessage:false//设置允许信息窗发送短息
    		   };
    	var p = e.target;
    	var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    	var infoWindow = new BMap.InfoWindow("企业名称：<strong>"+company.companyname+"</strong><br />企业地址：<strong>"+company.dwdz2+"</strong><br />法人代表：<strong>"+company.fddbr+"</strong><br />法人代表联系号码：<strong>"+company.fddbrlxhm+"</strong><br /><span style='color:red;cursor:hand' onclick=\"viewNew('"+company.companyId+"')\">查看详情</span>",opts);  // 创建信息窗口对象 
    	map.openInfoWindow(infoWindow,point); //开启信息窗口
    }
	
 
    function viewNew(row_Id){
    	var dt=new Date();
    	var url = "${ctx}/jsp/company/companyInitEdit.action?company.id="+row_Id;
    	//window.open(url, '_blank', 'fullscreen=1,titlebar=yes, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
        //createSimpleWindow("win_agencyInfo",agencyName,"${ctx}/jsp/agencyInfo/agencyInfoView.action?agencyInfo.id="+row_Id+"&dt="+dt.getTime(),900,600);
    	//打开新窗口全屏
       var tmp=window.open("about:blank","","fullscreen=1") 
    	tmp.moveTo(0,0); 
    	tmp.resizeTo(screen.width+20,screen.height); 
    	tmp.focus(); 
    	tmp.location=url; 
    }
/**进入企业信息详情**/
 function view(row_Id){
 		$.ajax({
       	url : "${ctx}/jsp/company/companyInfo.action?company.id="+row_Id,
       	type: 'post',
         dataType: 'json',
         async : false,
         data:{ },
         error: function(){
           	alert('查看时出错！');
         },
         success: function(data){
             if(data.result == 'true'){
             	parent.parent.parent.parent.window.location.href = "${ctx}/index.action?indexFlag=1&pageNumber=1";	
         	}
         	else
         	{
         		alert(data.message);
         	}
         }
    });
 }
    function hideAllCompany(){
    		for(var i = 0; i < allCompanyOverlays.length; i++){
    			var marker = allCompanyOverlays[i];
    			map.removeOverlay(marker);
        	}
    		allCompanyOverlays=[];
    }
    	
    function queryInRect (event) {  
  
        var cp = map.getBounds(); // 返回map可视区域，以地理坐标表示  
        var sw = cp.getSouthWest(); // 返回矩形区域的西南角  
        var ne = cp.getNorthEast(); // 返回矩形区域的东北角  
    	hideAllCompany();	
       var zoom = map.getZoom();  
        if (zoom < defaultShowLampZoom) { 
            // 放大级数小于12后，清除所有覆盖物，但百度覆盖物不能删除  
            return;  
        }  
        load = layer.load(3);
		   //放置视野范围搜索结果企业标注
        for(var i=0;i<tempSearchResult.length;i++){
			if(tempSearchResult[i]!=null&&""!=tempSearchResult[i].longitude&&"null"!=tempSearchResult[i].longitude){
				var longitude = tempSearchResult[i].longitude;
				var latitude = tempSearchResult[i].latitude;
				var point = new BMap.Point(longitude,latitude);
				 if(cp.containsPoint(point)==true){
				 		//map.centerAndZoom(point, 14);
				 		var marker = new BMap.Marker(point);  // 创建标注
				 		 //画图  
				 		var zoom = map.getZoom();  
				 		
       	 			 if (zoom > 15 || tempSearchResult.length<15) { 
				 	     var label = new BMap.Label(tempSearchResult[i].companyname,{offset:new BMap.Size(20,-10)});
				 	      marker.setLabel(label);
       		 		}  
				 		map.addOverlay(marker);              // 将标注添加到地图中
				 		addCompanyClickHandler(tempSearchResult[i],marker);
				 		//map.panTo(point);     
				 		allCompanyOverlays.push(marker);
	             }else{
	            	 point =null;
	             }
				
			}
		}  
        layer.close(load); return;  
    }  
 
     	function clearAllCompany(){
     		hideAllCompany();
     		tempSearchResult="";
     	}
 
     
     function querySzz(obj)
	 {
	 	$.ajax({
			type:"POST",
			url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
			success:function(json){
				json = eval('(' + json + ')');
				var selectContainer = $('#dwdz1'); 
				selectContainer.empty();
				var option = $('<option></option>').text("请选择").val(""); 
				selectContainer.append(option); 
						for(var i=0; i<json.length; i++){
			    	option = $('<option></option>').text(json[i].name).val(json[i].id);
					selectContainer.append(option); 
			 	}
			},
			dateType:"json"
		});
	 }
     
     function clickModule(obj,index){
 		window.parent.clickModule(obj,index);
 	 }
</script>
</body>
</html>