<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>生成记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="${ctx}/webResources/js/scroll.js"></script>
	<style>
		.list_lh ul,li{list-style:none;}
		.list_lh{ height:400px; overflow:hidden;}
		.list_lh li{ padding:10px;}
	</style>
	<script type="text/javascript">
$(function(){
	$("div.list_lh").myScroll({
		speed:0.1, //数值越大，速度越慢
		rowHeight:50 //li的高度
	});
});

function save()
{
	window.open("${ctx}/jsp/ssjjc/ssjjcCreate.action");
	reloadData('win_ssjjc');
	parent.close_win('win_ssjjc');
}
</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="ssjjcCreate.action">
		<s:token />
			<table width="100%" border="0">
				<tr>
					<th colspan="2" style="text-align:center" width="200px">检查人员</th>
					<th colspan="${ssjjc.zrs}" style="text-align:center" width="${width}">检查企业</th>
					<th style="text-align:center" width="150px">操作</th>
				</tr>
				<tr>
					<td width="100px">
						<div class="list_lh">
							<ul>
								<c:forEach var="ry1" items="${rylist1}">
									<li>
										${ry1.displayName}
									</li>
								</c:forEach>
							</ul>
						<div>
					</td>
					<td width="100px">
						<div class="list_lh">
							<ul>
								<c:forEach var="ry2" items="${rylist2}">
									<li>
										${ry2.displayName}
									</li>
								</c:forEach>
							</ul>
						<div>
					</td>
					<c:forEach var="ssjjcqy" items="${ssjjcqylist}">
						<td width="200px">
							<div class="list_lh">
								<ul>
									<c:forEach var="qy1" items="${ssjjcqy.qylist}">
										<li>
											${qy1.enterpriseName}
										</li>
									</c:forEach>
								</ul>
							<div>
						</td>
					</c:forEach>
					<td>
						<a href="#" class="btn_01" onclick="save();" >停止<b></b></a>
					</td>	
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
