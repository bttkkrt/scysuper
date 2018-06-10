<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>信息审核</title>
	<%@include file="/common/jsLib.jsp"%>
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XA9GjGiTQiDS54abo0ga2iEd"></script>
	<!--加载鼠标绘制工具-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	
	<script type="text/javascript" src="${ctx}/webResources/js/xmlhttp.js"></script>
	<script type="text/javascript">
			var map;
			var overlays = [];
    		$(function(){
    			// 百度地图API功能
				map = new BMap.Map("allmap");
				map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
				map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    			var longitude ="${tailingLibInfo.baseTailingLong}";
    			var latitude = "${tailingLibInfo.baseTailingLat}";
    			if(longitude != null && longitude != "")
    			{
    				var point = new BMap.Point(longitude, latitude);
					map.centerAndZoom(point, 16);
					var marker = new BMap.Marker(point);  // 创建标注
					map.addOverlay(marker);              // 将标注添加到地图中
					overlays.push(marker);
    			}
    			else
    			{
    				load("抚顺市");
    			}
    			
    			//实例化鼠标绘制工具
    		var drawingManager = new BMapLib.DrawingManager(map, {
        		isOpen: true, //是否开启绘制模式
        		enableDrawingTool: true, //是否显示工具栏
        		drawingToolOptions: {
            		anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            		offset: new BMap.Size(5, 5), //偏离值
            		scale: 0.8 ,//工具栏缩放比例
            		drawingModes:[
            			BMAP_DRAWING_MARKER
         			]
        		}
        		
    		});
    		//添加鼠标绘制工具监听事件，用于获取绘制结果
    		drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    		});
		
			//回调获得覆盖物信息
    		var overlaycomplete = function(e){
    	 		clearAll();
    			overlays.push(e.overlay);
      			var longitude = e.overlay.getPosition().lng;
				var latitude = e.overlay.getPosition().lat;
				document.getElementById('longitude').value = longitude;
				document.getElementById('latitude').value = latitude;
    		};
    	function clearAll()
    	{
    		for(var i = 0; i < overlays.length; i++){
           	 	map.removeOverlay(overlays[i]);
        	}
    	}
    	function myFun(result){
   	 		var cityName = result.name;
    		map.centerAndZoom(cityName,12);
		}
	    function search_place(obj)
	    {
	    	document.getElementById('longitude').value = "";
			document.getElementById('latitude').value = "";
			clearAll();
			// 创建地址解析器实例
			var myGeo = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			myGeo.getPoint(obj, function(point){
  				if (point) {
  					var marker = new BMap.Marker(point);
    				overlays.push(marker);
    				map.centerAndZoom(point, 16);
    				map.addOverlay(marker);
    				var longitude = marker.getPosition().lng;
					var latitude = marker.getPosition().lat;
					document.getElementById('longitude').value = longitude;
					document.getElementById('latitude').value = latitude;
  				}
			}, "抚顺市");
	    }
	    
	    function load(obj)
	    {
	    	// 创建地址解析器实例
			var myGeo = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			myGeo.getPoint(obj, function(point){
  				if (point) {
    				map.centerAndZoom(point, 16);
  				}	
  				else
  				{
  					var myCity = new BMap.LocalCity();
					myCity.get(myFun);
  				}
			}, "抚顺市");
	    }
	    
	</script>
	<script>
		$(function(){
			$('#tt').tabs({    
			    border:false,    
			    onSelect:function(title){    
			        //alert(title+' is selected');    
			    }    
			});  
		
		});
		$(function(){
			baseMainbody();
			checkDanger();
			checkPercolation();
			checkBeautyspot();
		});
		//是否有主体矿山
		function baseMainbody(){
			var obj = document.getElementById("baseMainbody");
			var objValue = document.getElementById("baseMainbodyValue").value;
			var ops = obj.options;
			for(var i=0;i<ops.length; i++){
				var tempValue = ops[i].value;
				if(tempValue == objValue)   //这里是你要选的值
				{
					ops[i].selected = true;
					break;
				}
			}
		}
		// 是否重大危险源
		function checkDanger(){
			var obj = document.getElementById("checkDanger");
			var objValue = document.getElementById("checkDangerValue").value;
			var ops = obj.options;
			for(var i=0;i<ops.length; i++){
				var tempValue = ops[i].value;
				if(tempValue == objValue)   //这里是你要选的值
				{
					ops[i].selected = true;
					break;
				}
			}
		}
		//是否渗漏
		function checkPercolation(){
			var obj = document.getElementById("checkPercolation");
			var objValue = document.getElementById("checkPercolationValue").value;
			var ops = obj.options;
			for(var i=0;i<ops.length; i++){
				var tempValue = ops[i].value;
				if(tempValue == objValue)   //这里是你要选的值
				{
					ops[i].selected = true;
					break;
				}
			}
		}
		// 是否自然风景区
		function checkBeautyspot(){
			var obj = document.getElementById("checkBeautyspot");
			var objValue = document.getElementById("checkBeautyspotValue").value;
			var ops = obj.options;
			for(var i=0;i<ops.length; i++){
				var tempValue = ops[i].value;
				if(tempValue == objValue)   //这里是你要选的值
				{
					ops[i].selected = true;
					break;
				}
			}
		}
		
		
		function save1(){
			var longitude = document.getElementById('longitude').value;
			var latitude =  document.getElementById('latitude').value;
			if(longitude == null || longitude == "" || latitude == null || latitude == "")
			{
				$("#tt").tabs("select","基本信息") ;
				alert("请在地图上标注企业位置");
	            return false; 
			}
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="tailingLibInfoSave.action";
				document.myform1.submit();
			}else{
				var obj = document.getElementById("jcxx"); 
				var count = obj.getElementsByTagName("span"); 
				if(count.length>9){
				    // $('#tt').select(参数是Title);
				    $("#tt").tabs("select","基本信息") ;
				    return false;
				}else{
					var obj1 = document.getElementById("sjcs"); 
					var count1 = obj1.getElementsByTagName("span"); 
					if(count1.length>0){
					    $("#tt").tabs("select","设计参数") ;
					    return false;
					}else{
						var obj2 = document.getElementById("cqb"); 
						var count2 = obj2.getElementsByTagName("span"); 
						if(count2.length>0){
						    $("#tt").tabs("select","初期坝") ;
						    return false; 
						}else{
						    var obj3 = document.getElementById("djb"); 
							var count3 = obj3.getElementsByTagName("span"); 
							if(count3.length>0){
							    $("#tt").tabs("select","堆积坝") ;
							    return false; 
							}else{
							    var obj4 = document.getElementById("pjscxx"); 
								var count4 = obj4.getElementsByTagName("span"); 
								if(count4.length>0){
								    $("#tt").tabs("select","评价审查信息") ;
								    return false; 
								}else{
								} 
							} 
						} 
					}
				}
			}
		}
	</script>
	<script>
	 function save(){
			var state = document.getElementById('state').value;
			var remark = document.getElementById('shbs').value;
			if(state == "1" && remark == "")
			{
				alert("不合格必须注明理由！");
			}
			else
			{
				document.myform1.submit();
			}
		}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data" action="${ctx}/jsp/tailinglibinfo/tailingLibInfoShenheSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="tailingLibInfo.id" value="${tailingLibInfo.id}">
		<input type="hidden" name="tailingLibInfo.createTime" value="<fmt:formatDate type="both" value="${tailingLibInfo.createTime}" />">
		<input type="hidden" name="tailingLibInfo.updateTime" value="${tailingLibInfo.updateTime}">
		<input type="hidden" name="tailingLibInfo.createUserID" value="${tailingLibInfo.createUserID}">
		<input type="hidden" name="tailingLibInfo.updateUserID" value="${tailingLibInfo.updateUserID}">
		<input type="hidden" name="tailingLibInfo.deptId" value="${tailingLibInfo.deptId}">
		<input type="hidden" name="tailingLibInfo.delFlag" value="${tailingLibInfo.delFlag}">
		
		<input id="longitude" name="tailingLibInfo.baseTailingLong" value="${tailingLibInfo.baseTailingLong}" type="hidden">
		<input id="latitude" name="tailingLibInfo.baseTailingLat" value="${tailingLibInfo.baseTailingLat}"   type="hidden">
		<input id="baseMainbodyValue" value="${tailingLibInfo.baseMainbody}" type="hidden">	
		<input id="checkDangerValue" value="${tailingLibInfo.checkDanger}" type="hidden" >
		<input id="checkPercolationValue" value="${tailingLibInfo.checkPercolation}" type="hidden" >
		<input id="checkBeautyspotValue" value="${tailingLibInfo.checkBeautyspot}" type="hidden" >
		<div class="submitdata">
			<div style="text-align: center;">
				<div id="tt" class="easyui-tabs" style="width:1300px;overflow: auto;">   
				    <div title="基本信息" style="padding:20px;display:block;">   
				    	<div id="jcxx" class="submitdata" style="overflow: auto;">
					        <table width="90%">
								<tr>
									<th width="15%">具体位置</th>
									<td width="35%"><input name="tailingLibInfo.baseLocal" value="${tailingLibInfo.baseLocal}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">所属行业</th>
									<td width="35%"><input name="tailingLibInfo.baseTrade" value="${tailingLibInfo.baseTrade}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">库类型</th>
									<td width="35%"><input name="tailingLibInfo.baseTailingType" value="${tailingLibInfo.baseTailingType}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">库级别</th>
									<td width="35%"><input name="tailingLibInfo.baseTailingLevel" value="${tailingLibInfo.baseTailingLevel}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">安全度</th>
									<td width="35%"><input name="tailingLibInfo.baseSafetyDegree" value="${tailingLibInfo.baseSafetyDegree}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">筑坝方式</th>
									<td width="35%"><input name="tailingLibInfo.baseBuildDamType" value="${tailingLibInfo.baseBuildDamType}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">投产日期</th>
									<td width="35%"><input name="tailingLibInfo.baseProduceStartdate" value="<fmt:formatDate type='both' value='${tailingLibInfo.baseProduceStartdate}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"><font color="red">*</font></td>
									<th width="15%">所属企业</th>
									<td width="35%"><input name="tailingLibInfo.baseCompany"  value="${tailingLibInfo.baseCompany}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">企业法人</th>
									<td width="35%"><input name="tailingLibInfo.baseLegalPerson" value="${tailingLibInfo.baseLegalPerson}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">是否有主体矿山</th>
									<td width="35%">
									<select id="baseMainbody" name="tailingLibInfo.baseMainbody" style="width:30%;">
										<option value="0">否</option>
										<option value="1">是</option>
									</select><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th width="15%">尾矿库负责人</th>
									<td width="35%"><input name="tailingLibInfo.baseTailingHead" value="${tailingLibInfo.baseTailingHead}" type="text" maxlength="50"></td>
									<th width="15%">尾矿库负责人联系方式</th>
									<td width="35%"><input name="tailingLibInfo.baseTailingHeadTel" value="${tailingLibInfo.baseTailingHeadTel}" type="text" maxlength="50"></td>
								</tr>
								<tr>
									<th width="15%">尾矿库县负责人</th>
									<td width="35%"><input name="tailingLibInfo.baseCountyHead" value="${tailingLibInfo.baseCountyHead}" type="text" maxlength="50"></td>
									<th width="15%">尾矿库县负责人联系电话</th>
									<td width="35%"><input name="tailingLibInfo.baseCountyHeadTel" value="${tailingLibInfo.baseCountyHeadTel}" type="text" maxlength="50"></td>
								</tr>
								<tr>
									<th width="15%">尾矿库乡负责人</th>
									<td width="35%"><input name="tailingLibInfo.baseTownHead" value="${tailingLibInfo.baseTownHead}" type="text" maxlength="50"></td>
									<th width="15%">尾矿库乡负责人联系电话</th>
									<td width="35%"><input name="tailingLibInfo.baseTownHeadTel" value="${tailingLibInfo.baseTownHeadTel}" type="text" maxlength="50"></td>
								</tr>
								<tr>
									<th width="15%">安全生产许可证发证时间</th>
									<td width="35%"><input name="tailingLibInfo.baseSafetyLicStartdate" value="<fmt:formatDate type='both' value='${tailingLibInfo.baseSafetyLicStartdate}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
									<th width="15%">安全生产许可证有效期</th>
									<td width="35%"><input name="tailingLibInfo.baseSafetyLicValidity" value="${tailingLibInfo.baseSafetyLicValidity}" type="text" maxlength="50"></td>
								</tr>
								<tr>
									<th width="15%">标准化等级</th>
									<td width="35%"><input name="tailingLibInfo.baseStandardLevel" value="${tailingLibInfo.baseStandardLevel}" type="text" maxlength="50"></td>
									<th width="15%">尾矿库所属</th>
									<td width="35%"><input name="tailingLibInfo.baseTailingBelong" value="${tailingLibInfo.baseTailingBelong}" type="text" maxlength="50"></td>
								</tr>
								<tr>
									<th width="15%">尾矿库中心点坐标</th>
									<td width="35%"  colspan="3"  style="text-align: left;">
										<div id="allmap" style="height:300px;width:70%"></div>
									</td>
								</tr>
						   </table>
					   </div>
				    </div>   
				    <div title="设计参数" data-options="closable:true" style="overflow:auto;padding:20px;display:block;">  
				    	<div id="sjcs" class="submitdata" style="overflow: auto;"> 
					        <table width="100%">
								<tr>
									<th width="15%">设计服务年限</th>
									<td width="35%"><input name="tailingLibInfo.paraDesignTime" value="${tailingLibInfo.paraDesignTime}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">实际服务年限</th>
									<td width="35%"><input name="tailingLibInfo.paraFactTime" value="${tailingLibInfo.paraFactTime}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">设计总库容</th>
									<td width="35%"><input name="tailingLibInfo.paraDesignTotal" value="${tailingLibInfo.paraDesignTotal}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">已堆积尾矿量</th>
									<td width="35%"><input name="tailingLibInfo.paraAlready" value="${tailingLibInfo.paraAlready}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">最终堆积高度</th>
									<td width="35%"><input name="tailingLibInfo.paraFinalHigh" value="${tailingLibInfo.paraFinalHigh}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">最小安全超高</th>
									<td width="35%"><input name="tailingLibInfo.paraSafetyHigh" value="${tailingLibInfo.paraSafetyHigh}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">最终侵润高度</th>
									<td width="35%"><input name="tailingLibInfo.paraMoistenHigh" value="${tailingLibInfo.paraMoistenHigh}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">最小干滩长度</th>
									<td width="35%"><input name="tailingLibInfo.paraLength" value="${tailingLibInfo.paraLength}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">放矿方式</th>
									<td width="35%"><input name="tailingLibInfo.paraStackType" value="${tailingLibInfo.paraStackType}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">排洪方式</th>
									<td width="35%"><input name="tailingLibInfo.paraDrainfloods" value="${tailingLibInfo.paraDrainfloods}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
					        </table>
				        </div>
				    </div>   
				    <div title="初期坝" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:block;">   
				        <div id="cqb" class="submitdata" style="overflow: auto;"> 
					        <table width="100%"> 
					        	<tr>
									<th width="15%">坝高</th>
									<td width="35%"><input name="tailingLibInfo.primeDamHigh" value="${tailingLibInfo.primeDamHigh}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">坝长</th>
									<td width="35%"><input name="tailingLibInfo.primeDamLength" value="${tailingLibInfo.primeDamLength}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">坝宽</th>
									<td width="35%"><input name="tailingLibInfo.primeDamWidth" value="${tailingLibInfo.primeDamWidth}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">内坡比</th>
									<td width="35%"><input name="tailingLibInfo.primeInnerSlope" value="${tailingLibInfo.primeInnerSlope}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">外坡比</th>
									<td width="35%"><input name="tailingLibInfo.primeOutSlope" value="${tailingLibInfo.primeOutSlope}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
					        </table>
				        </div> 
				    </div> 
				    <div title="堆积坝" data-options="closable:true" style="overflow:auto;padding:20px;display:block;">   
				        <div id="djb" class="submitdata" style="overflow: auto;"> 
					        <table width="100%"> 
					        	<tr>
									<th width="15%">设计坝高</th>
									<td width="35%"><input name="tailingLibInfo.accumulateDesignHigh" value="${tailingLibInfo.accumulateDesignHigh}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">实际坝高</th>
									<td width="35%"><input name="tailingLibInfo.accumulateFactHigh" value="${tailingLibInfo.accumulateFactHigh}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">坝长</th>
									<td width="35%"><input name="tailingLibInfo.accumulateDamLength" value="${tailingLibInfo.accumulateDamLength}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">内坡比</th>
									<td width="35%"><input name="tailingLibInfo.accumulateInnerSlope" value="${tailingLibInfo.accumulateInnerSlope}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">外坡比</th>
									<td width="35%"><input name="tailingLibInfo.accumulateOutSlope" value="${tailingLibInfo.accumulateOutSlope}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">坝宽</th>
									<td width="35%"><input name="tailingLibInfo.accumulateDamWidth" value="${tailingLibInfo.accumulateDamWidth}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
					        </table>
				        </div>     
				    </div>   
				    <div title="评价审查信息" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:block;">   
				        <div id="pjscxx" class="submitdata" style="overflow: auto;"> 
					        <table width="100%"> 
					        	<tr>
									<th width="15%">是否重大危险源</th>
									<td width="35%">
									<select id="checkDanger" name="tailingLibInfo.checkDanger" style="width:30%;">
										<option value="0">否</option>
										<option value="1">是</option>
									</select><font color="red">*</font>
									</td>
									<th width="15%">是否渗漏</th>
									<td width="35%">
									<select id="checkPercolation" name="tailingLibInfo.checkPercolation" style="width:30%;">
										<option value="0">否</option>
										<option value="1">是</option>
									</select><font color="red">*</font>
									</td>
								</tr>
								<tr>
									<th width="15%">是否自然风景区</th>
									<td width="35%">
									<select id="checkBeautyspot" name="tailingLibInfo.checkBeautyspot" style="width:30%;">
										<option value="0">否</option>
										<option value="1">是</option>
									</select><font color="red">*</font>
									</td>
									<th width="15%">下游居民户数</th>
									<td width="35%"><input name="tailingLibInfo.checkFamilyNo" value="${tailingLibInfo.checkFamilyNo}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">下游居民人数</th>
									<td width="35%"><input name="tailingLibInfo.checkPersonNo" value="${tailingLibInfo.checkPersonNo}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
									<th width="15%">下游公路数</th>
									<td width="35%"><input name="tailingLibInfo.checkRoadNo" value="${tailingLibInfo.checkRoadNo}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">下游铁路数</th>
									<td width="35%"><input name="tailingLibInfo.checkRailwayNo" value="${tailingLibInfo.checkRailwayNo}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
									<th width="15%">下游学校数</th>
									<td width="35%"><input name="tailingLibInfo.checkSchoolNo" value="${tailingLibInfo.checkSchoolNo}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">下游工厂数</th>
									<td width="35%"><input name="tailingLibInfo.checkFactoryNo" value="${tailingLibInfo.checkFactoryNo}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font></td>
									<th width="15%">排洪设施运行情况</th>
									<td width="35%"><input name="tailingLibInfo.checkDrainfloodsState" value="${tailingLibInfo.checkDrainfloodsState}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">有无安全监测设施</th>
									<td width="35%"><input name="tailingLibInfo.checkMonitorEquipment" value="${tailingLibInfo.checkMonitorEquipment}" type="text" dataType="Require" msg="此项为必填" maxlength="50"><font color="red">*</font></td>
									<th width="15%">安全现状评价报告编制单位</th>
									<td width="35%"><input name="tailingLibInfo.checkAqxzUnit" value="${tailingLibInfo.checkAqxzUnit}" type="text" dataType="Require" msg="此项为必填" maxlength="100"><font color="red">*</font></td>
								</tr>
								<tr>
									<th width="15%">预评价报告编制单位</th>
									<td width="35%"><input name="tailingLibInfo.checkYpjbgbzUnit" value="${tailingLibInfo.checkYpjbgbzUnit}" type="text" maxlength="100"></td>
									<th width="15%">预评价备案单位</th>
									<td width="35%"><input name="tailingLibInfo.checkYpjbaUnit" value="${tailingLibInfo.checkYpjbaUnit}" type="text" maxlength="100"></td>
								</tr>
								<tr>
									<th width="15%">初步设计《安全专篇》编制单位</th>
									<td width="35%"><input name="tailingLibInfo.checkCbsjbzUnit" value="${tailingLibInfo.checkCbsjbzUnit}" type="text" maxlength="100"></td>
									<th width="15%">初步设计《安全专篇》审批单位</th>
									<td width="35%"><input name="tailingLibInfo.checkCbsjspUnit" value="${tailingLibInfo.checkCbsjspUnit}" type="text" maxlength="100"></td>
								</tr>
								<tr>
									<th width="15%">竣工验收评价报告编制单位</th>
									<td width="35%"><input name="tailingLibInfo.checkJgyspjbgbzUnit" value="${tailingLibInfo.checkJgyspjbgbzUnit}" type="text" maxlength="100"></td>
									<th width="15%">竣工验收评价审批单位</th>
									<td width="35%"><input name="tailingLibInfo.checkJgyspjspUnit" value="${tailingLibInfo.checkJgyspjspUnit}" type="text" maxlength="100"></td>
								</tr>
								<tr>
									<th width="15%">有无土地使用手续审批单位</th>
									<td width="35%"><input name="tailingLibInfo.checkTdsyspUnit" value="${tailingLibInfo.checkTdsyspUnit}" type="text" maxlength="100"></td>
									<th width="15%">有无环保验收审批单位</th>
									<td width="35%"><input name="tailingLibInfo.checkHbysspUnit" value="${tailingLibInfo.checkHbysspUnit}" type="text" maxlength="100"></td>
								</tr>
					        </table>
				        </div>     
				    </div>    
				
				</div> 
				
				<div data-options="iconCls:'icon-reload',closable:true" style="padding:20px;width:1257px;">   
				        <div class="submitdata" style="overflow: auto;"> 
				        	<table width="100%">
								<!-- 
								<tr>
									<th width="5%">审核结果</th>
									<td width="35%" colspan="3">
										<s:select id="state" name="tailingLibInfo.state" list="#{'0':'通过','1':'不通过'}" theme="simple"/>
									</td>
								</tr>
								<tr>
									<th width="5%">审核备注</th>
									<td width="35%" colspan="3">
										<textarea id="shbs" name="tailingLibInfo.shbs" style="width:100%;height:120px">${tailingLibInfo.shbs}</textarea>
									</td>
								</tr>
								 -->
								 <s:if test="tailingLibInfo.state!=1||tailingLibInfo.dutyFlag!=1" >
									<c:if test="${deptCodeLenth==6}">
									<c:if test="${ifzsqy==0}">
										<tr>
											<th width="15%" style="color:red"></th>
											<td width="100%" colspan="4">
												<font style="color:red">县级审核:</font>
												<c:if test="${xjshState==1}">
													&nbsp;&nbsp;&nbsp;通过
												</c:if>
												<c:if test="${xjshState==2}">
													&nbsp;&nbsp;&nbsp;未通过
												</c:if>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<font style="color:red">备注:</font>&nbsp;&nbsp;&nbsp;${xjBack}
											</td>
										</tr>
									</c:if>
									
									<tr>
										<th width="15%" style="color:red"></th>
										<td width="100%" colspan="4">
											<font style="color:red">市级审核:</font><cus:SelectOneTag property="tailingLibInfo.state" defaultText='请选择' codeName="审核结果"  dataType="Require" msg="此项为必选" /><font color="color:red">*</font>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<font style="color:red">备注:</font><input id="shbs"
												name="tailingLibInfo.shbs" 
												style="width: 50%">
											</input><font style="color:red">*(若未通过，请填写原由。)</font>
										</td>
									</tr>
									</c:if>
									
									<c:if test="${ifzsqy==0}">
										<c:if test="${deptCodeLenth==9}">
									<tr>
										<th width="15%" style="color:red"></th>
										<td width="100%" colspan="4">
											<font style="color:red">县级审核:</font><cus:SelectOneTag property="tailingLibInfo.state" defaultText='请选择' codeName="审核结果"  dataType="Require" msg="此项为必选" /><font color="color:red">*</font>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<font style="color:red">备注:</font><input id="shbs"
												name="tailingLibInfo.shbs"  
												style="width: 50%">
											</input><font style="color:red">*(若未通过，请填写原由。)</font>
										</td>
									</tr>
									
									</c:if>
									</c:if>
								
								</s:if>
								<tr>
									<td colspan="4" style="text-align: center;">
										<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">确定</a>&nbsp;&nbsp;
										<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
									</td>
								</tr>
					        </table>
				        </div>     
				    </div>    
				</div> 
				
				
			</div>
			
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
