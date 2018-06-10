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
<style type="text/css">
 
    
.row-table {
  display: table;
  table-layout: fixed;
  height: 100%;
  width: 100%;
  margin: 0;
}
.row-table > [class*="col-"] {
  display: table-cell;
  float: none;
  table-layout: fixed;
  vertical-align: middle;
}
.row-flush > [class*="col-"] {
  padding-left: 0;
  padding-right: 0;
}
 
 
 
.bg-primary {
  background-color: #5d9cec;
  color: #ffffff !important;
}
.bg-primary-light {
  background-color: #8bb8f1;
  color: #ffffff !important;
}
.bg-primary-dark {
  background-color: #2f80e7;
  color: #ffffff !important;
}
.bg-primary small {
  color: inherit;
}
.bg-success {
  background-color: #27c24c;
  color: #ffffff !important;
}
.bg-success-light {
  background-color: #43d967;
  color: #ffffff !important;
}
.bg-success-dark {
  background-color: #1e983b;
  color: #ffffff !important;
}
.bg-success small {
  color: inherit;
}
.bg-info {
  background-color: #23b7e5;
  color: #ffffff !important;
}
.bg-info-light {
  background-color: #51c6ea;
  color: #ffffff !important;
}
.bg-info-dark {
  background-color: #1797be;
  color: #ffffff !important;
}
.bg-info small {
  color: inherit;
}
.bg-warning {
  background-color: #ff902b;
  color: #ffffff !important;
}
.bg-warning-light {
  background-color: #ffab5e;
  color: #ffffff !important;
}
.bg-warning-dark {
  background-color: #f77600;
  color: #ffffff !important;
}
.bg-warning small {
  color: inherit;
}
.bg-danger {
  background-color: #f05050;
  color: #ffffff !important;
}
.bg-danger-light {
  background-color: #f47f7f;
  color: #ffffff !important;
}
.bg-danger-dark {
  background-color: #ec2121;
  color: #ffffff !important;
}
.bg-danger small {
  color: inherit;
}
.bg-green {
  background-color: #37bc9b;
  color: #ffffff !important;
}
.bg-green-light {
  background-color: #58ceb1;
  color: #ffffff !important;
}
.bg-green-dark {
  background-color: #2b957a;
  color: #ffffff !important;
}
.bg-green small {
  color: inherit;
}
.bg-pink {
  background-color: #f532e5;
  color: #ffffff !important;
}
.bg-pink-light {
  background-color: #f763eb;
  color: #ffffff !important;
}
.bg-pink-dark {
  background-color: #e90bd6;
  color: #ffffff !important;
}
.bg-pink small {
  color: inherit;
}
.bg-purple {
  background-color: #7266ba;
  color: #ffffff !important;
}
.bg-purple-light {
  background-color: #9289ca;
  color: #ffffff !important;
}
.bg-purple-dark {
  background-color: #564aa3;
  color: #ffffff !important;
}
.bg-purple small {
  color: inherit;
}
.bg-inverse {
  background-color: #131e26;
  color: #ffffff !important;
}
.bg-inverse-light {
  background-color: #243948;
  color: #ffffff !important;
}
.bg-inverse-dark {
  background-color: #020304;
  color: #ffffff !important;
}
.bg-inverse small {
  color: inherit;
}
.bg-yellow {
  background-color: #fad732;
  color: #ffffff !important;
}
.bg-yellow-light {
  background-color: #fbe164;
  color: #ffffff !important;
}
.bg-yellow-dark {
  background-color: #f3ca06;
  color: #ffffff !important;
}
.bg-yellow small {
  color: inherit;
}
.bg-white {
  background-color: #ffffff;
  color: inherit !important;
}
.bg-gray-darker {
  background-color: #232735;
  color: #ffffff !important;
}
.bg-gray-dark {
  background-color: #3a3f51;
  color: #ffffff !important;
}
.bg-gray {
  background-color: #dde6e9;
  color: #515253 !important;
}
.bg-gray-light {
  background-color: #e4eaec;
  color: #515253 !important;
}
.bg-gray-lighter {
  background-color: #edf1f2;
  color: #515253 !important;
}
 
.panel-info{
	background-color: #ffffff;
    border:solid 1px #cfdbe2;
} 

</style>
</head>
<body>
<!-- <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页  
<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
 -->
<div class="container ">

 

<div class="pd-10 text-c" >
	<div class="mt-20" style="height: 100px;" >
		 <div class="col-3 mr-20">
		  <!-- START -->
                  <div class="panel bg-primary radius">
                     <div class="row row-table">
                        <div class="col-sm-4 text-c bg-primary-dark mt-20 mr-20">
                          <h2><i class="Hui-iconfont">&#xe6c6;</i></h2>
                        </div>
                        <div class="col-sm-8 mt-20 mr-20" onclick="addTabs('公告','${ctx}/jsp/information/contentInformationsList.action')"style="cursor: pointer;">
                           <h2>${infoTotals}</h2>
                           <p>公告 </p>
                        </div>
                     </div>
                  </div> 
          </div>
		 <div class="col-3 mr-20">
		  <!-- START -->
                  <div class="panel bg-warning radius">
                     <div class="row row-table">
                        <div class="col-sm-4 text-c bg-warning-dark mt-20 mr-20">
                          <h2><i class="Hui-iconfont">&#xe642;</i></h2>
                        </div>
                        <c:if test="${not empty safeInspectDistributeDzg}">
                        <div class="col-sm-8 mt-20 mr-20" onclick="addTabs('安全检查','${ctx}/jsp/distributeItem/distributeItemList.action')"style="cursor: pointer;">
                           <h2>${safeInspectDistributeDzg }</h2>
                           <p>安全检查</p>
                        </div>
                        </c:if>
                       <c:if test="${not empty companyTotal}">
                        <div class="col-sm-8 mt-20 mr-20" onclick="addTabs('企业管理','${ctx}/jsp/company/companyList.action')"style="cursor: pointer;">
                           <h2>${companyTotal }</h2>
                           <p>企业</p>
                        </div>
                       </c:if>
                     </div>
                  </div> 
          </div>
          		 <div class="col-3 mr-20">
		  <!-- START -->
                  <div class="panel bg-info radius">
                     <div class="row row-table">
                        <div class="col-sm-4 text-c bg-info-dark mt-20 mr-20">
                          <h2><i class="Hui-iconfont">&#xe642;</i></h2>
                        </div>
                        <div class="col-sm-8 mt-20 mr-20" onclick="addTabs('企业自查隐患上报','${ctx}/jsp/zcyhsb/jshxZcyhsbList.action')"style="cursor: pointer;">
                           <h2>${zcyhsbTotals}</h2>
                           <p>企业自查隐患</p>
                        </div>
                     </div>
                  </div> 
          </div>
		 
		 		 <div class="col-2 " >
		  <!-- START -->
                  <div class="panel bg-green-light radius">
                     <div class="row row-table">
                        <div class="col-sm-4 text-c bg-green mt-20 mr-20">
                           <h3 id="today">  </h3> 
                        </div>
                        <div class="col-sm-8 mt-20 mr-20">
                           <h3 id="year"> </h3>
                           <p id="date">  </p>
                        </div>
                     </div>
                  </div> 
          </div>
</div>

	 
			<div class="col-8 pr-20 mt-20">
				<ul   class="Huifold" id="Huifold1">
  					<li class="item">
   						 <h4 class='text-l'>企业自查隐患统计<b>-</b></h4>
   						 <div class="panel-info" id="tongjineir1" style="height: 300px;"> </div>
 					 </li>
				</ul>
			</div>
			<div class="col-4 pr-20 mt-20">
				<ul   class="Huifold" id="Huifold2">
  					<li class="item text-l">
   						 <h4>待处理事项<b>-</b></h4> 
   						 	 <%int  shijianindex =1; %>
   						 <div class="panel-info pl-20 pt-20 pr-20"> 
   						 <c:if test="${not empty companyDSH && companyDSH>0}">
   						 	<p><%=shijianindex%>. <a href="#" onclick="addTabs('待审核企业管理','${ctx}/jsp/company/companyList.action?isShenhe=0')">还有${companyDSH}个待审核企业</a></p>
   						<% ++shijianindex;%> </c:if>
   						 <c:if test="${not empty zcyhsbDzg && zcyhsbDzg>0}">
   						 	<p><%=shijianindex%>. <a href="#" onclick="addTabs('待整改企业自查隐患','${ctx}/jsp/zcyhsb/jshxZcyhsbList.action?mqzt=1&zgwcsj=shijian&&type=2')">还有${zcyhsbDzg}个待整改企业自查隐患</a></p>
   						<% ++shijianindex;%> </c:if>
   						  <c:if test="${not empty yibanDzg && yibanDzg>0}"> 
   						 	<p><%=shijianindex%>. <a href="#" onclick="addTabs('待整改一般隐患','${ctx}/jsp/commonTrouble/commoTroubleList.action?shzt=0&&type=3')">还有${yibanDzg}个待整改一般隐患</a></p>
   						 <% ++shijianindex;%></c:if>
   						  <c:if test="${not empty yibanDsh && yibanDsh>0}"> 
   						 	<p><%=shijianindex%>. <a href="#" onclick="addTabs('待审核一般隐患','${ctx}/jsp/commonTrouble/commoTroubleList.action?shzt=3&&type=2')">还有${yibanDsh}个待审核一般隐患</a></p>
   						 <% ++shijianindex;%></c:if>
   						  <c:if test="${not empty zhongdaDzg && zhongdaDzg>0}"> 
   						 	<p><%=shijianindex%>. <a href="#" onclick="addTabs('待整改重大隐患','${ctx}/jsp/majorTrouble/majorTroubleList.action?shzt=0&&type=3')">还有${zhongdaDzg}个待整改重大隐患</a></p>
   						 <% ++shijianindex;%></c:if>
   						  <c:if test="${not empty zhongdaDsh && zhongdaDsh>0}"> 
   						 	<p><%=shijianindex%>. <a href="#" onclick="addTabs('待审核重大隐患','${ctx}/jsp/majorTrouble/majorTroubleList.action?shzt=3&&type=2')">还有${zhongdaDsh}个待审核重大隐患</a></p>
   						 <% ++shijianindex;%></c:if>
   						 <%if(shijianindex==1){
   						%>
   						<p class="text-c">暂时没有事项</p>
   						<%}else{
   						%>
   						 	<p class="text-r"><a href="">刷新</a></p>
   						 	<%} 
   						%>
   						 </div>
 					 </li>
				</ul>
			</div>	
			 
			<div class="col-12 pr-20 mt-20">
				<ul   class="Huifold" id="Huifold3">
  					<li class="item">
   						 <h4 class='text-l'>企业信息<b>-</b></h4>
   						 <div class="panel-info" style="height: 380px;">
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
   						 			企业类型 : <cus:SelectOneTag property="companyBackUp.qylx" class="myInput"defaultText='请选择' codeName="企业类型"  />
   						 			行业分类 : <cus:SelectOneTag property="companyBackUp.hyfl"class="myInput" defaultText='请选择' codeName="企业行业分类"   />
   						 			<button type="button" class="btn btn-success radius" onclick="clearAllCompany();searchCompany()">查询</button>&nbsp;&nbsp; 
   						 			<button type="button" class="btn btn-primary radius" onclick="clearAllCompany()">隐藏</button>
   						 	</form>
   						 	</div>
   						 	 </s:else>
   						 <div class="col-12 pr-10" id="allmap" style="height: 380px;"></div>
   						 </div>
 					 </li>
				</ul>
			</div> 
			 <s:if test="#request.myCompany ==null">
			<div class="col-6 pr-20 mt-20">
				<ul   class="Huifold"id="Huifold4">
  					<li class="item">
   						 <h4 class='text-l'>一般安全隐患统计<b>-</b></h4>
   						 <div class="panel-info" style="height: 260px">  </div>
 					 </li>
				</ul>
			</div>
			<div class="col-6 pr-20 mt-20">
				<ul   class="Huifold"id="Huifold5">
  					<li class="item">
   						 <h4 class='text-l'>重大安全隐患统计<b>-</b></h4>
   						 <div class="panel-info" style="height: 260px">   </div>
 					 </li>
				</ul>
			</div>
			</s:if>
			<!-- <div class="col-6 pr-20 mt-20">
				<ul   class="Huifold" id="Huifold6">
  					<li class="item">
   						 <h4 class='text-l'>统计一<b>-</b></h4>
   						 <div class="panel-info"> 内容<br>很多内容 </div>
 					 </li>
				</ul>
			</div>		 -->						
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
	
		var date = new Date();
		var days = date.getDate();
		var dayList = new Array([days+1]);
		for(var i=1;i<=days;i++){
			dayList [i-1]=i;
		}
		
	function addTabs(name,url){
		parent.parent.frames["frm"].addTab('',name,url); 

	}
		
	//企业自查隐患统计
	function initlineChart(){
		var shangbaoData ="${shangbaoData}";
		 	shangbaoData= shangbaoData.replace("]","");
		 shangbaoData=shangbaoData.replace("[","");
		 shangbaoData = shangbaoData.split(",");
		for(var i=0;i<shangbaoData.length;i++){
			shangbaoData[i]=parseInt(shangbaoData[i]);
		}
		var zhenggaiData ="${zhenggaiData}";
		zhenggaiData= zhenggaiData.replace("]","");
		zhenggaiData=zhenggaiData.replace("[","");
		zhenggaiData = zhenggaiData.split(",");
		for(var i=0;i<zhenggaiData.length;i++){
			zhenggaiData[i]=parseInt(zhenggaiData[i]);
		}
		var lineChart = echarts.init(document.getElementById("tongjineir1"));
		lineOption = {
			    title: {
			        text: '自查隐患',
			        subtext: '（本月）'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['当日上报','当日整改']
			    },
			    toolbox: {
			        show: true,
			        feature: {
			        	dataView: {readOnly: false},
			        	magicType: {type: ['line', 'bar']},
			            saveAsImage: {}
			        }
			    },
			    xAxis:  {
			    	name:"日期",
			        type: 'category',
			        boundaryGap: false,
			        data: dayList
			    },
			    yAxis: {
			        type: 'value',
			        axisLabel: {
			            formatter: '{value} 件'
			        }
			    },
			    series: [
			        {
			            name:'当日上报',
			            type:'line',
			            data: shangbaoData,
			            markPoint: {
			                data: [
			                    {type: 'max', name: '最大值'},
			                    {type: 'min', name: '最小值'}
			                ]
			            },
			            markLine: {
			                data: [
			                    {type: 'average', name: '平均值'}
			                ]
			            }
			        },
			        {
			            name:'当日整改',
			            type:'line',
			            data:zhenggaiData,
			            markPoint: {
			            	data: [
				                    {type: 'max', name: '最高'},
				                    {type: 'min', name: '最低'}
				                ]
			            },
			            markLine: {
			                data: [
			                    {type: 'average', name: '平均值'},
			                ]
			            }
			        }
			    ]
			};
		lineChart.setOption(lineOption);
	}
	//一般安全隐患
	function inityibanChart(){
		var yibanData ="${yibanData}";
		yibanData= yibanData.replace("]","");
		yibanData=yibanData.replace("[","");
		yibanData = yibanData.split(",");
	for(var i=0;i<yibanData.length;i++){
		yibanData[i]=parseInt(yibanData[i]);
	}
	var yibanzhenggaiData ="${yibanzhenggaiData}";
	yibanzhenggaiData= yibanzhenggaiData.replace("]","");
	yibanzhenggaiData=yibanzhenggaiData.replace("[","");
	yibanzhenggaiData = yibanzhenggaiData.split(",");
	for(var i=0;i<yibanzhenggaiData.length;i++){
		yibanzhenggaiData[i]=parseInt(yibanzhenggaiData[i]);
	}
		var barChart = echarts.init($("#Huifold4 div")[0]);
		barOption = {
			    title: {
			        text: '一般安全隐患',
			        subtext: '（本月）'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['当日上报','当日整改']
			    },
			     grid: {
			        bottom: '3%',
			        containLabel: true
			    },  
			    toolbox: {
			        show: true,
			        feature: {
			        	magicType: {type: ['line', 'bar']},
			            saveAsImage: {}
			        }
			    },
			    xAxis:  {
			    	name:"日",
			        type: 'category',
			        boundaryGap: false,
			        data: dayList
			    },
			    yAxis: {
			        type: 'value',
				    axisLabel: {
				          formatter: '{value} 件'
				    }
			    },
			    series: [
			        {
			            name:'当日上报',
			            type:'line',
			            data:yibanData
			        },
			        {
			            name:'当日整改',
			            type:'line',
			            data:yibanzhenggaiData
			        } 
			    ]
			};

		barChart.setOption(barOption);
	}
	//重大安全隐患
	function inityanzhongChart(){
		var zhongdaData ="${zhongdaData}";
		zhongdaData= zhongdaData.replace("]","");
		zhongdaData=zhongdaData.replace("[","");
		zhongdaData = zhongdaData.split(",");
	for(var i=0;i<zhongdaData.length;i++){
		zhongdaData[i]=parseInt(zhongdaData[i]);
	}
	var zhongdazhenggaiData ="${zhongdazhenggaiData}";
	zhongdazhenggaiData= zhongdazhenggaiData.replace("]","");
	zhongdazhenggaiData=zhongdazhenggaiData.replace("[","");
	zhongdazhenggaiData = zhongdazhenggaiData.split(",");
	for(var i=0;i<zhongdazhenggaiData.length;i++){
		zhongdazhenggaiData[i]=parseInt(zhongdazhenggaiData[i]);
	}
		var barChart = echarts.init($("#Huifold5 div")[0]);
		barOption = {
			    title: {
			        text: '重大安全隐患',
			        subtext: '（本月）'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['当日上报','当日整改']
			    },
			    grid: {
			        bottom: '3%',
			        containLabel: true
			    },
			    toolbox: {
			        show: true,
			        feature: {
			        	magicType: {type: ['line', 'bar']},
			            saveAsImage: {}
			        }
			    },
			    xAxis:  {
			    	name:"日",
			        type: 'category',
			        boundaryGap: false,
			        data: dayList
			    },
			    yAxis: {
			        type: 'value',
				    axisLabel: {
				          formatter: '{value} 件'
				    }
			    },
			    series: [
			        {
			            name:'当日上报',
			            type:'line',
			            data:zhongdaData
			        },
			        {
			            name:'当日整改',
			            type:'line',
			            data:zhongdazhenggaiData
			        } 
			    ]
			};

		barChart.setOption(barOption);
	}
 $(function () {
    //当前日期
          today=new Date();
            function initArray(){
                this.length=initArray.arguments.length
                for(var i=0;i<this.length;i++)
                    this[i+1]=initArray.arguments[i]
            }
            var d=new initArray(
                    "周日",
                    "周一",
                    "周二",
                    "周三",
                    "周四",
                    "周五",
                    "周六");
            //当前时间显示初始化
            $("#year").html(today.getFullYear()+" 年</h2>");
            $("#date").html((today.getMonth()+1)+" 月 "+today.getDate()+" 日 ");
  			$("#today").html(d[today.getDay()+1]);
			
  			//初始化地图资源
  			var deptCode = "${curr_user.dept.deptCode}";
  			if(deptCode.length<12 && deptCode.length>3){
  			querySzz(deptCode.substring(0,6));
  			}
  			 initlineChart();
  			 inityibanChart();
  			 inityanzhongChart();
  			initMap();
});
 
 //地图
 

	function initMap(){
		
    			// 百度地图API功能
				map = new BMap.Map("allmap");
				map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
				map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
				var myCompany = "${myCompany}";
				if(myCompany != null && myCompany != ""){
					 showInfoWin('${myCompany.companyId}','${myCompany.longitude}','${myCompany.latitude}','${myCompany.companyname}','${myCompany.dwdz}','${myCompany.fddbr}','${myCompany.fddbrlxhm}',0)
				}
    		 map.centerAndZoom("抚顺",defaultShowLampZoom); 
    			var roleName = "${roleName}";
    			if(roleName.indexOf("A45") == -1){
    				searchCompany();
    				map.addEventListener("moveend", queryInRect);  
    				map.addEventListener("zoomend", queryInRect);  
    			}  
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
	 load = layer.load(3);
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
	    	layer.close(load);
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
    	var infoWindow = new BMap.InfoWindow("企业名称：<strong>"+company.companyname+"</strong><br />企业地址：<strong>"+company.dwdz2+"</strong><br />法人代表：<strong>"+company.fddbr+"</strong><br />法人代表联系号码：<strong>"+company.fddbrlxhm+"</strong><br /><span style='color:red;cursor:hand' onclick=\"view('"+company.companyId+"')\">查看详情</span>",opts);  // 创建信息窗口对象 
    	map.openInfoWindow(infoWindow,point); //开启信息窗口
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
</script>
</body>
</html>