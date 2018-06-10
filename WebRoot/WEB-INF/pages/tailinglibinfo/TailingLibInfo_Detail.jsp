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
	<script type="text/javascript">
	$(function(){
		$('#tt').tabs({    
		    border:false,    
		    onSelect:function(title){    
		       // alert(title+' is selected');    
		    }    
		});  
	
	});
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

	<form name="myform" method="post">
	
		<div style="text-align: center;">
			<div id="tt" class="easyui-tabs" style="width:1300px;overflow: auto;">   
			    <div title="基本信息" style="padding:20px;display:block;">   
			    	<div class="submitdata" style="overflow: auto;">
				        <table width="90%">
							<tr>
								<th width="15%">具体位置</th>
								<td width="35%">${tailingLibInfo.baseLocal}</td>
								<th width="15%">所属行业</th>
								<td width="35%">${tailingLibInfo.baseTrade}</td>
							</tr>
							<tr>
								<th width="15%">库类型</th>
								<td width="35%">${tailingLibInfo.baseTailingType}</td>
								<th width="15%">库级别</th>
								<td width="35%">${tailingLibInfo.baseTailingLevel}</td>
							</tr>
							<tr>
								<th width="15%">安全度</th>
								<td width="35%">${tailingLibInfo.baseSafetyDegree}</td>
								<th width="15%">筑坝方式</th>
								<td width="35%">${tailingLibInfo.baseBuildDamType}</td>
							</tr>
							<tr>
								<th width="15%">投产日期</th>
								<td width="35%"><fmt:formatDate type="both" value="${tailingLibInfo.baseProduceStartdate}" /></td>
								<th width="15%">所属企业</th>
								<td width="35%">${tailingLibInfo.baseCompany}</td>
							</tr>
							<tr>
								<th width="15%">企业法人</th>
								<td width="35%">${tailingLibInfo.baseLegalPerson}</td>
								<th width="15%">是否有主体矿山</th>
								<td width="35%">${tailingLibInfo.baseMainbody}</td>
							</tr>
							<tr>
								<th width="15%">尾矿库负责人</th>
								<td width="35%">${tailingLibInfo.baseTailingHead}</td>
								<th width="15%">尾矿库负责人联系方式</th>
								<td width="35%">${tailingLibInfo.baseTailingHeadTel}</td>
							</tr>
							<tr>
								<th width="15%">尾矿库县负责人</th>
								<td width="35%">${tailingLibInfo.baseCountyHead}</td>
								<th width="15%">尾矿库县负责人联系电话</th>
								<td width="35%">${tailingLibInfo.baseCountyHeadTel}</td>
							</tr>
							<tr>
								<th width="15%">尾矿库乡负责人</th>
								<td width="35%">${tailingLibInfo.baseTownHead}</td>
								<th width="15%">尾矿库乡负责人联系电话</th>
								<td width="35%">${tailingLibInfo.baseTownHeadTel}</td>
							</tr>
							<tr>
								<th width="15%">安全生产许可证发证时间</th>
								<td width="35%"><fmt:formatDate type="both" value="${tailingLibInfo.baseSafetyLicStartdate}" /></td>
								<th width="15%">安全生产许可证有效期</th>
								<td width="35%">${tailingLibInfo.baseSafetyLicValidity}</td>
							</tr>
							<tr>
								<th width="15%">标准化等级</th>
								<td width="35%">${tailingLibInfo.baseStandardLevel}</td>
								<th width="15%">尾矿库所属</th>
								<td width="35%">${tailingLibInfo.baseTailingBelong}</td>
							</tr>
							<tr>
								<th width="15%">尾矿库中心点坐标</th>
								<td width="35%"  colspan="3" style="text-align: center;"><div id="allmap" style="height:300px;width:70%"></div></td>
							</tr>
					   </table>
				   </div>
			    </div>   
			    <div title="设计参数" data-options="closable:true" style="overflow:auto;padding:20px;display:block;">  
			    	<div class="submitdata" style="overflow: auto;"> 
			        <table width="100%">
						<tr>
							<th width="15%">设计服务年限</th>
							<td width="35%">${tailingLibInfo.paraDesignTime}</td>
							<th width="15%">实际服务年限</th>
							<td width="35%">${tailingLibInfo.paraFactTime}</td>
						</tr>
						<tr>
							<th width="15%">设计总库容</th>
							<td width="35%">${tailingLibInfo.paraDesignTotal}</td>
							<th width="15%">已堆积尾矿量</th>
							<td width="35%">${tailingLibInfo.paraAlready}</td>
						</tr>
						<tr>
							<th width="15%">最终堆积高度</th>
							<td width="35%">${tailingLibInfo.paraFinalHigh}</td>
							<th width="15%">最小安全超高</th>
							<td width="35%">${tailingLibInfo.paraSafetyHigh}</td>
						</tr>
						<tr>
							<th width="15%">最终侵润高度</th>
							<td width="35%">${tailingLibInfo.paraMoistenHigh}</td>
							<th width="15%">最小干滩长度</th>
							<td width="35%">${tailingLibInfo.paraLength}</td>
						</tr>
						<tr>
							<th width="15%">放矿方式</th>
							<td width="35%">${tailingLibInfo.paraStackType}</td>
							<th width="15%">排洪方式</th>
							<td width="35%">${tailingLibInfo.paraDrainfloods}</td>
						</tr>
			         </table>
			         </div>
			    </div>   
			    <div title="初期坝" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:block;">   
			        <div class="submitdata" style="overflow: auto;"> 
				        <table width="100%"> 
				        	<tr>
								<th width="15%">坝高</th>
								<td width="35%">${tailingLibInfo.primeDamHigh}</td>
								<th width="15%">坝长</th>
								<td width="35%">${tailingLibInfo.primeDamLength}</td>
							</tr>
							<tr>
								<th width="15%">坝宽</th>
								<td width="35%">${tailingLibInfo.primeDamWidth}</td>
								<th width="15%">内坡比</th>
								<td width="35%">${tailingLibInfo.primeInnerSlope}</td>
							</tr>
							<tr>
								<th width="15%">外坡比</th>
								<td width="35%">${tailingLibInfo.primeOutSlope}</td>
							</tr>
				        </table>
			        </div> 
			    </div> 
			    <div title="堆积坝" data-options="closable:true" style="overflow:auto;padding:20px;display:block;">   
			        <div class="submitdata" style="overflow: auto;"> 
				        <table width="100%"> 
				        	<tr>
								<th width="15%">设计坝高</th>
								<td width="35%">${tailingLibInfo.accumulateDesignHigh}</td>
								<th width="15%">实际坝高</th>
								<td width="35%">${tailingLibInfo.accumulateFactHigh}</td>
							</tr>
							<tr>
								<th width="15%">坝长</th>
								<td width="35%">${tailingLibInfo.accumulateDamLength}</td>
								<th width="15%">内坡比</th>
								<td width="35%">${tailingLibInfo.accumulateInnerSlope}</td>
							</tr>
							<tr>
								<th width="15%">外坡比</th>
								<td width="35%">${tailingLibInfo.accumulateOutSlope}</td>
								<th width="15%">坝宽</th>
								<td width="35%">${tailingLibInfo.accumulateDamWidth}</td>
							</tr>
				        </table>
			        </div>     
			    </div>   
			    <div title="评价审查信息" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:block;">   
			        <div class="submitdata" style="overflow: auto;"> 
				        <table width="100%"> 
				        	<tr>
								<th width="15%">是否重大危险源</th>
								<td width="35%">${tailingLibInfo.checkDanger}</td>
								<th width="15%">是否渗漏</th>
								<td width="35%">${tailingLibInfo.checkPercolation}</td>
							</tr>
							<tr>
								<th width="15%">是否自然风景区</th>
								<td width="35%">${tailingLibInfo.checkBeautyspot}</td>
								<th width="15%">下游居民户数</th>
								<td width="35%">${tailingLibInfo.checkFamilyNo}</td>
							</tr>
							<tr>
								<th width="15%">下游居民人数</th>
								<td width="35%">${tailingLibInfo.checkPersonNo}</td>
								<th width="15%">下游公路数</th>
								<td width="35%">${tailingLibInfo.checkRoadNo}</td>
							</tr>
							<tr>
								<th width="15%">下游铁路数</th>
								<td width="35%">${tailingLibInfo.checkRailwayNo}</td>
								<th width="15%">下游学校数</th>
								<td width="35%">${tailingLibInfo.checkSchoolNo}</td>
							</tr>
							<tr>
								<th width="15%">下游工厂数</th>
								<td width="35%">${tailingLibInfo.checkFactoryNo}</td>
								<th width="15%">排洪设施运行情况</th>
								<td width="35%">${tailingLibInfo.checkDrainfloodsState}</td>
							</tr>
							<tr>
								<th width="15%">有无安全监测设施</th>
								<td width="35%">${tailingLibInfo.checkMonitorEquipment}</td>
								<th width="15%">安全现状评价报告编制单位</th>
								<td width="35%">${tailingLibInfo.checkAqxzUnit}</td>
							</tr>
							<tr>
								<th width="15%">预评价报告编制单位</th>
								<td width="35%">${tailingLibInfo.checkYpjbgbzUnit}</td>
								<th width="15%">预评价备案单位</th>
								<td width="35%">${tailingLibInfo.checkYpjbaUnit}</td>
							</tr>
							<tr>
								<th width="15%">初步设计《安全专篇》编制单位</th>
								<td width="35%">${tailingLibInfo.checkCbsjbzUnit}</td>
								<th width="15%">初步设计《安全专篇》审批单位</th>
								<td width="35%">${tailingLibInfo.checkCbsjspUnit}</td>
							</tr>
							<tr>
								<th width="15%">竣工验收评价报告编制单位</th>
								<td width="35%">${tailingLibInfo.checkJgyspjbgbzUnit}</td>
								<th width="15%">竣工验收评价审批单位</th>
								<td width="35%">${tailingLibInfo.checkJgyspjspUnit}</td>
							</tr>
							<tr>
								<th width="15%">有无土地使用手续审批单位</th>
								<td width="35%">${tailingLibInfo.checkTdsyspUnit}</td>
								<th width="15%">有无环保验收审批单位</th>
								<td width="35%">${tailingLibInfo.checkHbysspUnit}</td>
							</tr>
				        </table>
			        </div>     
			    </div>    
			</div> 
			
			<div data-options="iconCls:'icon-reload',closable:true" style="padding:20px;width:1257px;">   
				        <div class="submitdata" style="overflow: auto;"> 
				        	<table width="100%">
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
							<font style="color:red">市级审核:</font>
							<c:if test="${sjshState==1}">
								&nbsp;&nbsp;&nbsp;通过
							</c:if>
							<c:if test="${sjshState==2}">
								&nbsp;&nbsp;&nbsp;未通过
							</c:if>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font>&nbsp;&nbsp;&nbsp;${sjBack}
						</td>
					</tr>
				</tr>
								<tr>
									<td colspan="4" style="text-align: center;">
										<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
									</td>
								</tr>
					        </table>
				        </div>     
				    </div>    
				</div>
			
		</div>
	</form>
	<!--  -->
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
