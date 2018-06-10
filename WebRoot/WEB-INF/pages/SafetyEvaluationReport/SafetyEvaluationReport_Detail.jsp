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
        		window.location.href="safetyEvaluationReportSaveFile.action?picName="+i+"&fileName="+j;
			}
	 </script>
</head>
<body>
	<div style="position: absolute;width: 80%; left: 10%">
			<table width="100%">
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 42.0pt;">
						抚顺市安全生产监督管理局
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height:5px;border:none;border-top:5px double;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 22.0pt">
						危险化学品从业单位安全现状评价报告通知书
					</td>	
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family: 仿宋_GB2312;font-size: 16.0pt">
						备案编号:${safetyEvaluationReport.safetyPlanId}
					</td>	
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
						${safetyEvaluationReport.companyName}:
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
						${safetyEvaluationReport.userName}
					</td>
				</tr>
				<tr>
					<td height="20px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						评价机构：<cus:hxlabel codeName="安全评价机构" itemValue="${safetyEvaluationReport.pjjg}" />
				   </td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						报告完成日期：${safetyEvaluationReport.bgwcDate}&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						报告上报日期：${safetyEvaluationReport.bgsbDate}&nbsp;&nbsp;
					</td>
				</tr>		
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						下次备案日期：${safetyEvaluationReport.planTime}
					</td>
				</tr>	
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						剧毒化学品名称：${safetyEvaluationReport.jdhxpname}
					</td>
				</tr>		
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						年使用量（吨/年）：${safetyEvaluationReport.nsyl}
					</td>
				</tr>	
				<tr>
					<td>
					   <div style="color:green;overflow:auto; height:200px;">
						    <table width="100%">
							    
									    <s:iterator id="taskPic" value="%{picList01}" status="sta">
											<tr>
											<td width="70%">
															<c:choose>
															    <c:when test="${fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.bmp')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.png')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.jpeg')
															    	||fn:endsWith(fn:toLowerCase(taskPic.picName),'.gif')}"> 
															    &nbsp;&nbsp;&nbsp;
															    <a href="/upload/file/${taskPic.picName}" rel="example_group">
															    <img src="/upload/file/${taskPic.picName}"
																		border='0' width='220' height='150'/>
																</a>
															    </c:when>
															    <c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;<FONT style="color:green;">&nbsp;&nbsp;&nbsp;<s:property value="#taskPic.fileName" /></FONT>
													    		</c:otherwise>
														   </c:choose>
											</td>
											<td width="30%">
													<a href="javascript:savepic('<s:property value="#taskPic.id"/>','<s:property value="#taskPic.fileName" />')">下载</a>&nbsp;&nbsp;
												</td>
											 </tr>
										</s:iterator>
							   
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size:16.0pt">
						（备案机关签章）
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 16.0pt">
						${safetyEvaluationReport.sendTime}&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td height="100px" style="text-align:center">
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>
