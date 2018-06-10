<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>企业自查统计</title>
    <link class="include" rel="stylesheet" type="text/css"
		href="${ctx}/css/jquery.jqplot.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/examples.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/shCoreDefault.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/shThemejqPlot.min.css" />

	<script language="javascript" type="text/javascript"
		src="${ctx}/js/excanvas.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jquery-1.4.4.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jquery.jqplot.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shCore.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shBrushJScript.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/shBrushXml.min.js"></script>
	<!-- Additional plugins go here -->

	<script class="include" type="text/javascript"
		src="${ctx}/js/jqplot.barRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jqplot.pieRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jqplot.categoryAxisRenderer.min.js"></script>
	<script class="include" type="text/javascript"
		src="${ctx}/js/jqplot.pointLabels.min.js"></script>
		
	<script type="text/javascript">
	$(document).ready(function(){
        	 var s1 = '${data_xc}'.split(',');
       		 var ticks = '${name_xc}'.split(',');
       		 
        	plot1 = $.jqplot('chart3', [s1], {
        	title: '现场检查中类',      //设置当前图的标题 
            seriesDefaults:{
                renderer:$.jqplot.BarRenderer,
                pointLabels: { show: true }
                
            },
            
            axes: {
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: ticks
                }
            },  
            
            highlighter: { show: false }
        }); 
        });
	</script>

	</head>
	<body onload="divChart2();">
		<div id="chart3" style="margin-top:20px; width:500px; height:350px;float:left;"></div>
	</body>
</html>
