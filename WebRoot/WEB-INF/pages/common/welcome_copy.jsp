<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>开发平台首页</title>
		 <%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript" src="${ctx}/webResources/json.js"></script>
		
		<script type="text/javascript">
			function getMark(){
				return '${roleName}';
			}
			function getCompanyCode(){
				return '${codeId}';
			}
			function initMap(){
			 	if('${roleName}' == '0'){
			 		window.frames["map"].LocateComPany(JSON.stringify("${codeId}"),"企业");	//调用展示企业打点方法  ${codeId}
			 	}else{
			 		window.frames["map"].InitGrid();//调用显示所有的网格
			 	}
			 }
		
			function GetCompanyGrids(){
				var name = $("#name").val();
					//异步获取网格的数据
		    	 $.ajax({
		                	url : "${ctx}/jsp/map/tbMapGetGridJSON.action?flag="+name,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ""
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','查询时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	window.frames["map"].LocateComPany(data.ids);
		                        }else{
		                        	$.messager.alert('错误','查询时出错！');
		                        }
		                    }
		                });
			}
		    function PopupComPany(){//模糊查询企业列表
		    	var name = $("#name").val();
		    	//异步获取企业的数据
		    	 $.ajax({
		                	url : "${ctx}/jsp/map/tbMapGetCompanyJSON.action?flag="+name,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ""
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','查询时出错！');
		                    },
		                    success: function(data){
		                    	
		                        if(data.result){
		                        	window.frames["map"].LocateComPany(JSON.stringify("591123490,566882209,134842764,731167816,713240231,769869856"));
		                        }else{
		                        	$.messager.alert('错误','查询时出错2！');
		                        }
		                    }
		                });
		    }
		    function GetCompanyDetail(obj){
		    	var data = "";
			        //异步获取企业的数据
		    	 $.ajax({
		                	url : "${ctx}/jsp/map/tbMapGetCompanyDetailJSON.action?flag="+obj,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : obj 
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','查询时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	data =  JSON.stringify(data.detail);
		                        }else{
		                        	$.messager.alert('错误','查询时出错！');
		                        }
		                    }
		                });
			        
			    return data;
		    	
		    }
		    //此方法接收silverlight中穿过来的id 获取企业详情
        function DetailedInfo(id) {
            //向sl传入企业详细信息进行气泡展示
            var infoStr = GetCompanyDetail(id);//json字符串
            window.frames["map"].PopupComPany(infoStr);
        }
		</script>
	</head>
	<body>
		<!--  <div>
			<s:if test="roleName == 0">
				<input type="button" value="显示位置" onclick="initMap();">
			</s:if>
			<s:else>
				<input type="button" value="显示网格" onclick="initMap();">
			</s:else>
			
		</div>-->
		<div style="width:100%;">
        	<iframe id="map" name="map" src="${ctx}/webResources/Map/index2.html"  style="height:1000px;width:100%; border:hidden;z-Index:-1000;position:absolute; "scrolling="no" ></iframe>
    	</div>
	</body>
</html>