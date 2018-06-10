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
        		window.location.href="${ctx}/jsp/safetysheet/safesheetDownFile.action?picName="+i+"&fileName="+j;
			}
	</script>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%">
				<tr>
					<th width="20%">所在镇名称</th>
					<td width="30%">${zdwxyjbtz.szzname}</td>
					<th width="20%">填报单位名称</th>
					<td width="30%">${zdwxyjbtz.qymc}</td>
				</tr>
				<tr>
					<th width="20%">上传时间</th>
					<td width="30%"><fmt:formatDate type="both" value="${zdwxyjbtz.createTime}" /></td>
				</tr>
				<tr>
					<th width="20%">基本特征</th>
					<td width="80%" colspan="3">
					<div style="color:green;overflow:auto; height:200px;">
										  <table width="80%">
													<c:forEach var="item" items="${list1}" varStatus="status">  
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
																<a style="color:red" href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															</td>
														</tr>
													</c:forEach>
											</table></div>
						</td>
				</tr>
				<tr>
					<th width="20%">备案申请</th>
					<td width="80%" colspan="3">
					<div style="color:green;overflow:auto; height:200px;">
										  <table id="zdwxybasqb" width="80%">
													<c:forEach var="item" items="${list2}" varStatus="status">  
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
																<a style="color:red" href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															</td>
														</tr>
													</c:forEach>
											</table></div>
					</td>
				</tr>
				<tr>
					<th width="20%">备案登记</th>
					<td width="80%" colspan="3">
					<div style="color:green;overflow:auto; height:200px;">
						<table width="80%">
													<c:forEach var="item" items="${list3}" varStatus="status">  
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
																<a style="color:red" href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
															</td>
														</tr>
													</c:forEach>
											</table></div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
