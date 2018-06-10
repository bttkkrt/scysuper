<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@ taglib uri="/WEB-INF/tld/birt.tld" prefix="birt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>BIRT标签</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script src='<c:url value="/webResources/js/common.js"/>'></script>
		<script type='text/javascript'
			src='<c:url value="/webResources/js/displayTag.js"/>'> </script>
		<link rel="stylesheet" type="text/css"
			href='<c:url value="/webResources/themes/${curr_user.cssId}/css/style.css" />' />
	<script type="text/javascript">
	
	</script>
	</head>

	<body>
	<!-- 基本表格报表显示 -->
		<birt:showReport id="birtTagReportTest" rptdesignFileName="/testFolder/birtTagReportTest.rptdesign" 
			pattern="framset" title="birtTagReportTest" format="html">
		</birt:showReport>
	
	<!-- 基于分组的表格报表显示 -->
		<birt:showReport id="groupTest" rptdesignFileName="groupTest.rptdesign" 
			pattern="framset" title="groupTest" format="html">
		</birt:showReport>
	
	<!-- 图形报表（柱状图） -->
		<birt:showReport id="chartTest" rptdesignFileName="chartTest.rptdesign" 
			pattern="framset" title="chartTest" format="html" >
		</birt:showReport>
		
	<!-- 图形报表（折线图） -->
		<birt:showReport id="chartTest_Line" rptdesignFileName="chartTest_Line.rptdesign" 
			pattern="framset" title="chartTest_Line" format="html" >
		</birt:showReport>
		
	<!-- 图形报表（面积图） -->
		<birt:showReport id="chartTest_Area" rptdesignFileName="chartTest_Area.rptdesign" 
			pattern="framset" title="chartTest_Area" format="html" >
		</birt:showReport>		
		
	<!-- 图形报表（饼图） -->
		<birt:showReport id="chartTest_Pie" rptdesignFileName="chartTest_Pie.rptdesign" 
			pattern="framset" title="chartTest_Pie" format="html" >
		</birt:showReport><!--	

	
	<!-- 静态标量参数 --><!--
	<birt:showReport id="parameterTest" rptdesignFileName="parameterTest.rptdesign"
		pattern="framset" format="html" title="parameterTest">
	</birt:showReport>
	
	--><!-- 动态标量参数 --><!--
	<birt:showReport id="dynamicParameterTest" rptdesignFileName="dynamicParameterTest.rptdesign"
		pattern="framset" format="html" title="dynamicParameterTest">
	</birt:showReport>
	
	--><!-- 级联参数 --><!--
	<birt:showReport id="cascadingParameterTest" rptdesignFileName="cascadingParameterTest.rptdesign"
		pattern="framset" format="html" title="cascadingParameterTest">
	</birt:showReport>
	
	--></body>
</html>
