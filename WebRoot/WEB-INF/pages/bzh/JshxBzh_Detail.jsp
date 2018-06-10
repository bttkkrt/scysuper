<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.css" media="screen" />
	<script type="text/javascript">
		$(document).ready(function() {
			$("a[rel=example_group]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over'
			});

		});
	function savepic(i,j){
        		window.location.href="jshxBzhSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxBzhImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">${jshxBzh.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${jshxBzh.qymc}</td>
				</tr>
				<tr>
					<th width="15%">考评分数</th>
					<td width="35%">${jshxBzh.score}</td>
					<th width="15%">发证机关</th>
					<td width="35%">${jshxBzh.fzjg}</td>
				</tr>
				<tr>
					<th width="15%">达标级别</th>
					<td width="35%"><cus:hxlabel codeName="证书级别" itemValue="${jshxBzh.dbjb}" /></td>
					<th width="15%">证书号</th>
					<td width="35%">${jshxBzh.zsh}</td>
				</tr>
				<tr>
					<th width="15%">有效期</th>
					<td width="35%">${jshxBzh.yxq}</td>
					<th width="15%">发证日期</th>
					<td width="35%">${jshxBzh.fzrq}</td>
				</tr>
				<tr>
					<th width="15%">自评报告</th>
					<td width="85%" colspan="3">
									<div style="color:green;overflow:auto;height:160px;">
										  <table id="bzhzpbg">
													<c:forEach var="item" items="${picList01}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																<c:choose>
																    <c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.png')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
																    &nbsp;&nbsp;&nbsp;
																    <a href="/upload/file/${item.picName}" rel="example_group">	
																    <img src="/upload/file/${item.picName}"
																			border='0' width='220' height='150'/>
																	</a>
																    </c:when> 
																    <c:otherwise> 
																     &nbsp;&nbsp;&nbsp;${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															</td>
														</tr>
													</c:forEach>
											</table>
									</div>
					</td>
				</tr>
				<tr>
					<th width="15%">评审报告</th>
					<td width="85%" colspan="3">
									<div style="color:green;overflow:auto;height:160px;">
										  <table id="bzhhchp">
													<c:forEach var="item" items="${picList02}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																<c:choose>
																    <c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.png')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
																    &nbsp;&nbsp;&nbsp;
																     <a href="/upload/file/${item.picName}" rel="example_group">	
																    <img src="/upload/file/${item.picName}"
																			border='0' width='220' height='150'/>
																	</a>
																    </c:when> 
																    <c:otherwise> 
																     &nbsp;&nbsp;&nbsp;${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															</td>
														</tr>
													</c:forEach>
											</table>
									</div>
					</td>
				</tr>
				<tr>
					<th width="15%">审核报告</th>
					<td width="85%" colspan="3">
									<div style="color:green;overflow:auto;height:160px;">
										  <table id="bzhshbg">
													<c:forEach var="item" items="${picList03}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																<c:choose>
																    <c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.png')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
																    	||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
																    &nbsp;&nbsp;&nbsp;
																    	 <a href="/upload/file/${item.picName}" rel="example_group">	
																    	<img src="/upload/file/${item.picName}"
																			border='0' width='220' height='150'/>
																		</a>
																    </c:when> 
																    <c:otherwise> 
																     &nbsp;&nbsp;&nbsp;${item.fileName}
																    </c:otherwise>
															   </c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															</td>
														</tr>
													</c:forEach>
											</table>
									</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" align="center" style="text-align:center;">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeLayer();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
