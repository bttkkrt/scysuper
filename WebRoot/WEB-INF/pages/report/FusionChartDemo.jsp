<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>FusionChart</title>
		<script type='text/javascript'
			src='<c:url value="/webResources/js/FusionCharts.js"/>'> </script>
	</head>

	<body>
	<%--
		<div id="chartDiv" align="center">FusionChart Demo</div>
		<script type="text/javascript">
   			var myChart = new FusionCharts("<c:url value='/webResources/FusionCharts/FCF_Column3D.swf'/>","MyChart","600","400");
   			myChart.setDataURL("<c:url value='/webResources/FusionCharts/Data.xml'/>");      
   			myChart.render("chartDiv");
		</script>
				
		<div id="chartDiv2" align="center">FusionChart Demo2</div>
		<script type="text/javascript">
   			var myChart = new FusionCharts("<c:url value='/webResources/FusionCharts/FCF_Line.swf'/>","MyChart2","600","400");
   			myChart.setDataURL("<c:url value='/webResources/FusionCharts/Data.xml'/>");      
   			myChart.render("chartDiv2");
		</script>
	--%>
	
	<div id="xmlDataDiv" align="center">xmlDataFusionChart Demo</div>
	<script type="text/javascript">
  			var myChart = new FusionCharts("<c:url value='/webResources/FusionCharts/FCF_Column3D.swf'/>","xmlDataFusionChart","600","400");
  			myChart.setDataXML('${xmlStr}');       
  			myChart.render("xmlDataDiv");
	</script>
	
	<!--
		<div id="chartDiv" align="center">FusionChart Demo</div>

		<script type="text/javascript">
   			var myChart = new FusionCharts("<c:url value='/webResources/FusionCharts/FCF_MSColumn3D.swf'/>","MyChart","600","400");
   			myChart.setDataURL("<c:url value='/webResources/FusionCharts/MSColumn3D.xml'/>");      
   			myChart.render("chartDiv");
		</script>	
	-->
	</body>
</html>